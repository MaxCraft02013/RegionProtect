package dev.maxcraft.regionprotector;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor {
    private final RegionManager regionManager;

    public RegionCommand(RegionManager manager) {
        this.regionManager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            regionManager.protectRegion(player);
            player.sendMessage("✅ Dein Gebiet wurde geschützt!");
            return true;
        }
        return false;
    }
}