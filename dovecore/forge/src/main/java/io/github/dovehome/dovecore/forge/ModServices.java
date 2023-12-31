package io.github.dovehome.dovecore.forge;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import io.github.dovehome.dovecore.DoveCore;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class ModServices implements ITransformationService {
    @Override
    public @NotNull String name() {
        return "dove core mod";
    }

    @Override
    public void initialize(IEnvironment environment) {
        DoveCore.preLaunch();
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {

    }

    @Override
    public @NotNull List<ITransformer> transformers() {
        return List.of(new ModCoreMod());
    }
}
