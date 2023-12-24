package io.github.dovehomemod.bakalib.fabric.model.obj;

import io.github.dovehomemod.bakalib.fabric.model.BakalibMaterial;
import net.fabricmc.fabric.api.renderer.v1.material.BlendMode;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class ObjMaterialReader {
    private static final Map<String, Option> OPTIONS = new HashMap<>();
    private static final Option EMPTY = (tokenizer, line, key, material) -> {};

    static {
        register((tokenizer, line, key, material) ->
                material.setTexture(new ResourceLocation(line.substring(key.length()).trim())),
                "map_Kd", "texture");
        register((tokenizer, line, key, material) ->
                material.setColor(
                        parseFloat(tokenizer.nextToken()),
                        parseFloat(tokenizer.nextToken()),
                        parseFloat(tokenizer.nextToken())
                ),
                "Kd");
        register((tokenizer, line, key, material) ->
                        material.setBlendMode(BlendMode.valueOf(tokenizer.nextToken().toUpperCase(Locale.ROOT))),
                "blend_mode");
        register((tokenizer, line, key, material) ->
                        material.setColorIndex(parseBoolean(tokenizer.nextToken())),
                "color_index");

        register((tokenizer, line, key, material) ->
                        material.setDiffuseShading(parseBoolean(tokenizer.nextToken())),
                "diffuse_shading");

        register((tokenizer, line, key, material) ->
                        material.setAmbientOcclusion(parseBoolean(tokenizer.nextToken())),
                "ambient_occlusion", "ambientocclusion", "ao");

        register((tokenizer, line, key, material) ->
                        material.setColor(parseInt(tokenizer.nextToken(), 16)),
                "diffuse_color", "color");

        register((tokenizer, line, key, material) ->
                        material.setEmission(parseBoolean(tokenizer.nextToken())),
                "emission", "emissive");

        register((tokenizer, line, key, material) ->
                        material.lockUv(parseBoolean(tokenizer.nextToken())),
                "uvlock");

        register((tokenizer, line, key, material) ->
                        material.setTintIndex(parseInt(tokenizer.nextToken(), 10)),
                "tint_index", "tintindex");

        register((tokenizer, line, key, material) ->
                        material.cull(Direction.valueOf(tokenizer.nextToken().toUpperCase(Locale.ROOT))),
                "cull", "cullface");
    }

    public static List<BakalibMaterial> read(BufferedReader reader) throws IOException {
        List<BakalibMaterial> materials = new ArrayList<>();

        BakalibMaterial currentMaterial = null;

        for (String line = reader.readLine(); line != null; line = next(reader)) {
            int comment = line.indexOf('#');

            if (comment > 0) {
                line = line.substring(0, comment);
            }

            StringTokenizer tokenizer = new StringTokenizer(line);

            if (!tokenizer.hasMoreTokens()) continue;

            String token = tokenizer.nextToken().toLowerCase(Locale.ROOT);

            if (token.equals("newmtl")) {
                String name = line.substring("newmtl".length()).trim();
                currentMaterial = new BakalibMaterial(name);
                materials.add(currentMaterial);
            }

            if (currentMaterial != null) {
                OPTIONS.getOrDefault(token, EMPTY).parse(tokenizer, line, token, currentMaterial);
            }
        }

        return materials;
    }

    private static String next(BufferedReader reader) throws IOException {
        String line = trim(reader.readLine());

        if (line == null) return null;

        StringBuilder result = new StringBuilder(line);

        while (line != null && line.endsWith("\\")) {
            line = trim(reader.readLine());

            if (line != null) {
                result.append(" ").append(line);
            }
        }

        return result.toString();

    }

    private static String trim(String line) {
        return line == null ? null : line.trim();
    }

    private static boolean parseBoolean(String s) {
        return s.equalsIgnoreCase("true");
    }

    private static int parseInt(String s, int i) throws IOException {
        if (i == 16 && s.startsWith("0x")) {
            s = s.substring(2);
        }
        try
        {
            return Integer.parseInt(s, i);
        }
        catch (NumberFormatException e)
        {
            throw new IOException(e);
        }
    }

    public static void register(Option option, String... tokens) {
        for (String key : tokens) {
            OPTIONS.putIfAbsent(key.toLowerCase(Locale.ROOT), option);
        }
    }

    private static float parseFloat(String s) throws IOException
    {
        try
        {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException e)
        {
            throw new IOException(e);
        }
    }

}
