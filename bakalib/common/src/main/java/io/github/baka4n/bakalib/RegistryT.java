package io.github.baka4n.bakalib;

import dev.architectury.registry.registries.DeferredRegister;

public interface RegistryT<T> {
    void register(DeferredRegister<T> register);
    T get();
}
