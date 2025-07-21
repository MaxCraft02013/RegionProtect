package dev.maxcraft.regionprotector;

import org.bukkit.plugin.java.JavaPlugin;

public class RegionProtector extends JavaPlugin {
    private RegionManager regionManager;

    @Override
    public void onEnable() {
        this.regionManager = new RegionManager();
        getCommand("protect").setExecutor(new RegionCommand(regionManager));
        getServer().getPluginManager().registerEvents(regionManager, this);
        getLogger().info("RegionProtector aktiviert!");
    }
}