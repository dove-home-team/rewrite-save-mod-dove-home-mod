package io.github.baka4n.bakalib.faces;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface RegistryBiome {
    String[] value();
}
