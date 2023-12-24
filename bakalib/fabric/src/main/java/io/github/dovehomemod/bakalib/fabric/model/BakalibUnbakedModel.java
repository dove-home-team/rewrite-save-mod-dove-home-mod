package io.github.dovehomemod.bakalib.fabric.model;

import com.mojang.datafixers.util.Pair;
import de.javagl.obj.Obj;
import io.github.dovehomemod.bakalib.fabric.BakalibClientFabric;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BakalibUnbakedModel implements UnbakedModel {

    private final Obj obj;
    private final Map<String, BakalibMaterial> materials;
    private final Collection<Material> textureDependencies;
    private final Material sprite;
    private final ItemTransforms transform;
    private final boolean isSideLit;
    private final boolean isBlock;

    public BakalibUnbakedModel(@Nullable Obj obj, @Nullable Map<String, BakalibMaterial> materials, Collection<Material> textureDependencies, Material sprite, ItemTransforms modelTransformation, boolean isSideLit, boolean isBlock) {
        this.obj = obj;
        this.materials = materials;
        this.textureDependencies = textureDependencies;
        this.sprite = sprite;
        this.transform = modelTransformation;
        this.isSideLit = isSideLit;
        this.isBlock = isBlock;
    }

    @Override
    public @NotNull Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    public @NotNull Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        return this.textureDependencies;
    }

    @Nullable
    @Override
    public BakedModel bake(ModelBakery modelBakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState transform, ResourceLocation location) {
        Mesh mesh;
        if (obj == null) {
            mesh = BakalibClientFabric.load(location, spriteGetter, transform, isBlock);
        } else {
            mesh = BakalibClientFabric.build(obj, materials, spriteGetter, transform, isBlock);
        }
        BakalibClientFabric.MESHES.put(location, mesh);
        return null;
    }
}
