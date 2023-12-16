package io.github.baka4n.bakalib.faces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FluidBlock {
    String fieldName();

    Class<?> value();
}
