package io.github.dovehome.dovecore.forge;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModCoreMod implements ITransformer<ClassNode> {
    @Override
    public @NotNull ClassNode transform(ClassNode input, ITransformerVotingContext context) {
        return input;
    }

    @Override
    public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
        return TransformerVoteResult.YES;
    }

    @Override
    public @NotNull Set<Target> targets() {
        return new HashSet<>(List.of(Target.targetClass("dovecore")));
    }
}
