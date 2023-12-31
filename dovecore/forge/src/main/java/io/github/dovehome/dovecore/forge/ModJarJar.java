package io.github.dovehome.dovecore.forge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.loading.moddiscovery.JarInJarDependencyLocator;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.forgespi.locating.IModLocator;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;


public class ModJarJar extends JarInJarDependencyLocator {
    private static final Logger dove$LOGGER = LogUtils.getLogger();

    @Override
    public List<IModFile> scanMods(Iterable<IModFile> loadedMods) {
        String path;
        try {
            path = getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        path = SystemUtils.IS_OS_WINDOWS
                ? path.substring(1, path.lastIndexOf("/"))
                : path.substring(0, path.lastIndexOf("/"));
        if (path.lastIndexOf("#") != -1) path = path.substring(0, path.lastIndexOf("#"));
        IModLocator.ModFileOrException mod = createMod(Paths.get(path));
        return super.scanMods(List.of(mod.file()));
    }

    @Override
    public String name() {
        return "dove jar in jar to file";
    }
}
