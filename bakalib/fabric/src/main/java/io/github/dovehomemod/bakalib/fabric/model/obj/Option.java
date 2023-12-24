package io.github.dovehomemod.bakalib.fabric.model.obj;

import io.github.dovehomemod.bakalib.fabric.model.BakalibMaterial;

import java.io.IOException;
import java.util.StringTokenizer;
@FunctionalInterface
public interface Option {
    void parse(StringTokenizer tokenizer, String line, String key, BakalibMaterial material) throws IOException;
}
