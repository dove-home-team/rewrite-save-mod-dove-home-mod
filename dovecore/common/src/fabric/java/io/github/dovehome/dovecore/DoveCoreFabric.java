package io.github.dovehome.dovecore;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class DoveCoreFabric implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        DoveCore.preLaunch();
    }
}
