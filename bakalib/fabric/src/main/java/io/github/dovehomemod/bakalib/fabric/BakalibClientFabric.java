package io.github.dovehomemod.bakalib.fabric;

import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import de.javagl.obj.*;
import io.github.dovehomemod.bakalib.fabric.model.BakalibMaterial;
import io.github.dovehomemod.bakalib.fabric.model.Vertex;
import io.github.dovehomemod.bakalib.fabric.model.obj.ObjMaterialReader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
@Environment(EnvType.CLIENT)
public class BakalibClientFabric implements ClientModInitializer {

    private final static Vector3f NONE = new Vector3f();
    private final static Vector3f BLOCKS = new Vector3f(0.5F, 0.5F, 0.5F);

    public static final Map<ResourceLocation, Mesh> MESHES = new HashMap<>();

    @Override
    public void onInitializeClient() {
//        ModelLoadingRegistry.INSTANCE.registerResourceProvider();
    }

    public static @Nullable Mesh load(ResourceLocation modelPath, Function<Material, TextureAtlasSprite> textureGetter, ModelState bakeSettings, boolean isBlock) {
        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

        if (!modelPath.getPath().endsWith(".obj")) {
            modelPath = new ResourceLocation(modelPath.getNamespace(), modelPath.getPath() + ".obj");
        }

        if (!modelPath.getPath().startsWith("models/")) {
            modelPath = new ResourceLocation(modelPath.getNamespace(), "models/" + modelPath.getPath());
        }

        if (resourceManager.getResource(modelPath).isPresent()) {
            try {
                InputStream inputStream = resourceManager.getResource(modelPath).get().open();
                Obj obj = ObjReader.read(inputStream);

                Map<String, BakalibMaterial> materials = getMaterials(resourceManager, modelPath, obj);
                return build(obj, materials, textureGetter, bakeSettings, isBlock);
            } catch (IOException e) {
                System.out.printf("Failed to load model %s:\n%s%n", modelPath, e.getMessage());
            }
        }

        return null;
    }

    public static Map<String, BakalibMaterial> getMaterials(ResourceManager resourceManager, ResourceLocation identifier, Obj obj) throws IOException {
        Map<String, BakalibMaterial> materials = new LinkedHashMap<>();

        for (String s : obj.getMtlFileNames()) {
            String path = identifier.getPath();
            path = path.substring(0, path.lastIndexOf('/') + 1) + s;
            ResourceLocation resource = new ResourceLocation(identifier.getNamespace(), path);

            if (resourceManager.getResource(resource).isPresent()) {
                ObjMaterialReader.read(new BufferedReader(
                                new InputStreamReader(resourceManager.getResource(resource).get().open())))
                        .forEach(material -> materials.put(material.name, material));
            } else {
                System.out.printf("Texture does not exist: %s%n", resource);
            }
        }

        return materials;
    }

    public static @Nullable Mesh build(Obj obj, Map<String, BakalibMaterial> materials, Function<Material, TextureAtlasSprite> textureGetter, ModelState bakeSettings, boolean isBlock) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();

        if (renderer == null) return null;

        MeshBuilder builder = renderer.meshBuilder();
        QuadEmitter emitter = builder.getEmitter();

        for (Map.Entry<String, Obj> entry : ObjSplitting.splitByMaterialGroups(obj).entrySet()) {
            Obj group = entry.getValue();
            BakalibMaterial material = materials.get(entry.getKey());

            if (material == null) {
                material = BakalibMaterial.DEFAULT;
            }

            int materialColor = material.getColor();
            TextureAtlasSprite sprite = textureGetter.apply(new Material(InventoryMenu.BLOCK_ATLAS, material.getTexture()));

            for (int faceIndex = 0; faceIndex < group.getNumFaces(); ++faceIndex) {
                face(renderer, emitter, group, group.getFace(faceIndex), material, materialColor, sprite, bakeSettings, isBlock);
            }
        }

        return builder.build();
    }

    private static void emit(Renderer renderer, QuadEmitter emitter, BakalibMaterial material, int materialColor, TextureAtlasSprite sprite, ModelState settings) {
        emitter.material(material.getMaterial(renderer));
        emitter.spriteColor(0, materialColor, materialColor, materialColor, materialColor);
        emitter.colorIndex(material.getTintIndex());
        emitter.nominalFace(emitter.lightFace());

        if (material.getCullDirection() != null) {
            emitter.cullFace(material.getCullDirection());
        }

        boolean bl = settings.isUvLocked() || material.isUvLocked();
        emitter.spriteBake(0, sprite, MutableQuadView.BAKE_NORMALIZED | (bl ? MutableQuadView.BAKE_LOCK_UV : 0));
        emitter.emit();
    }

    private static void rotate(ModelState settings, Vector3f pos, Vector3f normal) {
        if (settings.getRotation() != Transformation.identity()) {
            pos.add(-0.5F, -0.5F, -0.5F);
            pos.transform(settings.getRotation().getRightRotation());
            pos.add(0.5f, 0.5f, 0.5f);

            normal.transform(settings.getRotation().getRightRotation());
        }
    }

    private static Vector3f of(FloatTuple tuple) {
        return new Vector3f(tuple.getX(), tuple.getY(), tuple.getZ());
    }

    private static void face(Renderer renderer, QuadEmitter emitter, Obj group, ObjFace face, BakalibMaterial material, int materialColor, TextureAtlasSprite sprite, ModelState settings, boolean isBlock) {
        if (face.getNumVertices() <= 4) {
            for (int vertex = 0; vertex < face.getNumVertices(); ++vertex) {
                vertex(emitter, group, face, vertex, settings, isBlock);
            }

            emit(renderer, emitter, material, materialColor, sprite, settings);
        } else {
            final int vertices = face.getNumVertices();

            FloatTuple textureCoords = face.containsTexCoordIndices()
                    ? group.getTexCoord(face.getTexCoordIndex(0))
                    : null;

            Vector3f pos = of(group.getVertex(face.getVertexIndex(0)));
            pos.add(isBlock ? BLOCKS : NONE);
            Vector3f normal = of(group.getNormal(face.getNormalIndex(0)));

            rotate(settings, pos, normal);

            final Vertex start = new Vertex(
                    pos,
                    normal,
                    textureCoords == null ? 0 : textureCoords.getX(),
                    textureCoords == null ? 0 : textureCoords.getY()
            );

            for (int vertex = 1; vertex < vertices - 1; ++vertex) {
                vertex(emitter, 0, start.pos, start.normal, start.u, start.v);

                textureCoords = face.containsTexCoordIndices()
                        ? group.getTexCoord(face.getTexCoordIndex(vertex))
                        : null;

                pos = of(group.getVertex(face.getVertexIndex(vertex)));
                pos.add(isBlock ? BLOCKS : NONE);
                normal = of(group.getNormal(face.getNormalIndex(vertex)));

                rotate(settings, pos, normal);

                vertex(emitter, 1,
                        pos,
                        normal,
                        textureCoords == null ? 0 : textureCoords.getX(),
                        textureCoords == null ? 0 : textureCoords.getY()
                );

                textureCoords = face.containsTexCoordIndices()
                        ? group.getTexCoord(face.getTexCoordIndex(vertex + 1))
                        : null;

                pos = of(group.getVertex(face.getVertexIndex(vertex + 1)));
                pos.add(isBlock ? BLOCKS : NONE);
                normal = of(group.getNormal(face.getNormalIndex(vertex + 1)));

                rotate(settings, pos, normal);


                vertex(emitter, 2,
                        pos,
                        normal,
                        textureCoords == null ? 0 : textureCoords.getX(),
                        textureCoords == null ? 0 : textureCoords.getY()
                );

                vertex(emitter, 3,
                        pos,
                        normal,
                        textureCoords == null ? 0 : textureCoords.getX(),
                        textureCoords == null ? 0 : textureCoords.getY()
                );

                emit(renderer, emitter, material, materialColor, sprite, settings);
            }
        }
    }

    private static void vertex(QuadEmitter emitter, int vertex, Vector3f pos, Vector3f normal, float u, float v) {
        emitter.pos(vertex, pos);
        emitter.normal(vertex, normal);
        emitter.sprite(vertex, 0, u, v);
    }

    private static Vector3f calculateNormal(Obj group, ObjFace face) {
        Vector3f p1 = of(group.getVertex(face.getVertexIndex(0)));
        Vector3f v1 = of(group.getVertex(face.getVertexIndex(1)));
        Vector3f v2 = of(group.getVertex(face.getVertexIndex(2)));

        v1.sub(p1);
        v2.sub(p1);

        return new Vector3f(
                v1.y() * v2.z() - v1.z() * v2.y(),
                v1.z() * v2.x() - v1.x() * v2.z(),
                v1.x() * v2.y() - v1.y() * v2.x()
        );
    }

    private static void vertex(QuadEmitter emitter, Obj group, ObjFace face, int vertex, ModelState settings, boolean isBlock) {
        Vector3f pos = of(group.getVertex(face.getVertexIndex(vertex)));

        // Used to offset blocks
        pos.add(isBlock ? BLOCKS : NONE);

        Vector3f normal = face.containsNormalIndices()
                ? of(group.getNormal(face.getNormalIndex(vertex)))
                : calculateNormal(group, face);

        float u = 0, v = 0;
        if (face.containsTexCoordIndices()) {
            FloatTuple textureCoords = group.getTexCoord(face.getTexCoordIndex(vertex));
            u = textureCoords.getX();
            v = 1F - textureCoords.getY();

            u = u > 1 || u < 0 ? (((u % 1F) + 1F) % 1F) : u;
            v = v > 1 || v < 0 ? (((v % 1F) + 1F) % 1F) : v;
        }

        rotate(settings, pos, normal);

        vertex(emitter, vertex, pos, normal, u, v);

        if (face.getNumVertices() == 3) {
            vertex(emitter, vertex + 1, pos, normal, u, v);
        }
    }


}
