package dev.monarkhes.myron.impl.client.model;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.MaterialFinder;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class MyronMaterial {
    public static final MyronMaterial DEFAULT = new MyronMaterial("missing_texture");

    static {
        DEFAULT.setTexture(new ResourceLocation(""));
    }

    public final String name;

    private int tintIndex = -1;
    private int color = -1;
    private ResourceLocation texture;
    private BlendMode blendMode = BlendMode.DEFAULT;

    private boolean uvLocked = false;
    private boolean diffuseShading = true;
    private boolean ambientOcclusion = true;
    private boolean emission = false;
    private boolean colorIndex = true;

    private RenderMaterial material;
    private Direction cullDirection;

    public MyronMaterial(String name) {
        this.name = name;
    }

    public void setTexture(ResourceLocation textureId) {
        this.texture = textureId;
    }

    public void setColor(float kd0, float kd1, float kd2) {
        this.color = 0xFF000000;
        this.color |= (int) (255 * kd0) << 16;
        this.color |= (int) (255 * kd1) << 8;
        this.color |= (int) (255 * kd2);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return this.color;
    }

    public int getTintIndex() {
        return this.tintIndex;
    }

    public void setTintIndex(int tintIndex) {
        this.tintIndex = tintIndex;
    }

    public ResourceLocation getTexture() {
        if(this.texture == null)
            return MissingTextureAtlasSprite.getLocation();
        return this.texture;
    }

    public void lockUv(boolean enabled) {
        this.uvLocked = enabled;
    }

    public boolean isUvLocked() {
        return this.uvLocked;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public void setAmbientOcclusion(boolean enabled) {
        this.ambientOcclusion = enabled;
    }

    public void setEmissive(boolean enabled) {
        this.emission = enabled;
    }

    public void setDiffuseShading(boolean enabled) {
        this.diffuseShading = enabled;
    }

    public void setColorIndex(boolean enabled) {
        this.colorIndex = enabled;
    }

    public void cull(Direction direction) {
        this.cullDirection = direction;
    }

    public @Nullable Direction getCullDirection() {
        return this.cullDirection;
    }

    public RenderMaterial getMaterial(Renderer renderer) {
        if (this.material == null) {
            MaterialFinder finder = renderer.materialFinder()
                    .blendMode(0, this.blendMode)
                    .disableAo(0, !this.ambientOcclusion)
                    .emissive(0, this.emission)
                    .disableDiffuse(0, !this.diffuseShading)
                    .disableColorIndex(0, !this.colorIndex);

            this.material = finder.find();
        }

        return this.material;
    }

    @Override
    public int hashCode() {
        if (this.texture == null) {
            return this.name.hashCode();
        } else {
            return this.texture.hashCode();
        }
    }
}