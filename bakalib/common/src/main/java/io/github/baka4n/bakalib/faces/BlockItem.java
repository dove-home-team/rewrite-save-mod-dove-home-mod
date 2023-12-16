package io.github.baka4n.bakalib.faces;

import net.minecraft.world.item.Item;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.function.Consumer;

@Retention(RetentionPolicy.RUNTIME)
public @interface BlockItem {
    Class<?> value();
    String fieldName();
}
