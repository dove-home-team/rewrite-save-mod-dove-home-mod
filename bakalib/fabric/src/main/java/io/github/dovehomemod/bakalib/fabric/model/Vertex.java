package io.github.dovehomemod.bakalib.fabric.model;

import com.mojang.math.Vector3f;

public class Vertex {
    public final Vector3f pos;
    public final Vector3f normal;
    public final float u;
    public final float v;

    public Vertex(Vector3f pos, Vector3f normal, float u, float v) {
        this.pos = pos;
        this.normal = normal;
        this.u = u;
        this.v = v;
    }
}
