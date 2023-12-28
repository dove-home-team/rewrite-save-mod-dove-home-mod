package dev.monarkhes.myron.impl.client.obj;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import dev.monarkhes.myron.impl.client.MyronClient;
import dev.monarkhes.myron.impl.client.model.MyronMaterial;
import dev.monarkhes.myron.impl.client.model.MyronUnbakedModel;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

public class AbstractObjLoader {
    public static final Material DEFAULT_SPRITE = new Material(TextureAtlas.LOCATION_BLOCKS, MissingTextureAtlasSprite.getLocation());

    protected @Nullable UnbakedModel loadModel(
            ResourceManager resourceManager, ResourceLocation identifier, ItemTransforms transformation, boolean isSideLit) {
        boolean isBlock = identifier.getPath().startsWith("block");

        if (!identifier.getPath().endsWith(".obj")) {
            identifier = new ResourceLocation(identifier.getNamespace(), identifier.getPath() + ".obj");
        }

        if (!identifier.getPath().startsWith("models/")) {
            identifier = new ResourceLocation(identifier.getNamespace(), "models/" + identifier.getPath());
        }

        var resource = resourceManager.getResource(identifier);
        if (resource.isPresent()) {
            try {

                InputStream inputStream = resource.get().open();
                Obj obj = ObjReader.read(inputStream);
                Map<String, MyronMaterial> materials = MyronClient.getMaterials(resourceManager, identifier, obj);

                Collection<Material> textureDependencies = new HashSet<>();

                for (MyronMaterial material : materials.values()) {
                    textureDependencies.add(new Material(TextureAtlas.LOCATION_BLOCKS, material.getTexture()));
                }

                MyronMaterial material = materials.get("sprite");
                return new MyronUnbakedModel(
                        obj, materials,
                        textureDependencies, materials.size() > 0
                        ? new Material(TextureAtlas.LOCATION_BLOCKS, (material == null
                        ? materials.values().iterator().next()
                        : material).getTexture())
                        : DEFAULT_SPRITE, transformation, isSideLit, isBlock);
            } catch (IOException e) {
                MyronClient.LOGGER.warn("Failed to load model {}:\n{}", identifier, e.getMessage());
            }
        }

        return null;
    }
}
