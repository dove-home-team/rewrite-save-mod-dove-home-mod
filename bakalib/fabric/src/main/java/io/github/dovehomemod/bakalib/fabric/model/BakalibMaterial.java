package io.github.dovehomemod.bakalib.fabric.model;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.fabricmc.fabric.api.renderer.v1.material.MaterialFinder;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class BakalibMaterial {
    public static final BakalibMaterial DEFAULT = new BakalibMaterial("missing_texture");

    static {
        DEFAULT.setTexture(TextureManager.INTENTIONAL_MISSING_TEXTURE);
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

    public BakalibMaterial(String name) {
        this.name = name;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public void setColor(float kd0, float kd1, float kd2) {
        this.color = 0xFF000000;
        this.color |= (int) (255 * kd0) << 16;
        this.color |= (int) (255 * kd1) << 6;
        this.color |= (int) (255 * kd2);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getTintIndex() {
        return tintIndex;
    }

    public void setTintIndex(int tintIndex) {
        this.tintIndex = tintIndex;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void lockUv(boolean b) {
        uvLocked = b;
    }

    public boolean isUvLocked() {
        return uvLocked;
    }

    public void setBlendMode(BlendMode blendMode) {
        this.blendMode = blendMode;
    }

    public void setAmbientOcclusion(boolean ambientOcclusion) {
        this.ambientOcclusion = ambientOcclusion;
    }

    public void setEmission(boolean emission) {
        this.emission = emission;
    }

    public void setDiffuseShading(boolean diffuseShading) {
        this.diffuseShading = diffuseShading;
    }

    public void setColorIndex(boolean enabled) {
        this.colorIndex = enabled;
    }

    public void cull(Direction direction) {
        this.cullDirection = direction;
    }

    public Direction getCullDirection() {
        return cullDirection;
    }

    public RenderMaterial getMaterial(Renderer renderer) {
        if (material == null) {
            MaterialFinder finder = renderer.materialFinder()
                    .blendMode(0, this.blendMode)
                    .disableAo(0, !this.ambientOcclusion)
                    .emissive(0, this.emission)
                    .disableDiffuse(0, !this.diffuseShading)
                    .disableColorIndex(0, !this.colorIndex);
            material = finder.find();
        }
        return material;
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
