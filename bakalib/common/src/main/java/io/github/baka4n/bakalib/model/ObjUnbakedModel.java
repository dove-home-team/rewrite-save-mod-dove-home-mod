package io.github.baka4n.bakalib.model;

import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;
import de.javagl.obj.Mtl;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import de.javagl.obj.ObjSplitting;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class ObjUnbakedModel implements UnbakedModel {

    public static final Material DEFAULT_SPRITE = new Material(InventoryMenu.BLOCK_ATLAS, null);

    private final Obj obj;
    private final Map<String, BakalibMaterial> mtls;
    private final ItemTransforms transform;
    private final Material sprite;

    public ObjUnbakedModel(Obj obj, Map<String, BakalibMaterial> mtls, ItemTransforms transform) {
        this.obj = obj;
        this.mtls = mtls;
        this.transform = transform == null ? ItemTransforms.NO_TRANSFORMS : transform;
        Mtl mtl = this.findMtlForName("sprite");
        this.sprite = !mtls.isEmpty()
                ? new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation((mtl == null ? mtls.values().iterator().next() : mtl).getMapKd()))
                : DEFAULT_SPRITE;
    }

    private BakalibMaterial findMtlForName(@SuppressWarnings("SameParameterValue") String name) {
        return mtls.get(name);
    }

    @Override
    public @NotNull Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    public @NotNull Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        List<Material> sprites = new ArrayList<>();
        mtls.values().forEach(mtl -> sprites.add(new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation(mtl.getMapKd()))));
        return sprites;
    }

    @Nullable
    @Override
    public BakedModel bake(ModelBakery modelBakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState transform, ResourceLocation location) {
        Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(obj);
        Object2IntMap<BlockState> modelGroups = modelBakery.getModelGroups();
        modelGroups.forEach((blockState, integer) -> {
        });
        return new ObjBakedModelLoader();
    }
}
