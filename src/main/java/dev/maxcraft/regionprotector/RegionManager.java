package dev.maxcraft.regionprotector;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.*;

public class RegionManager implements Listener {
    private final Map<UUID, Region> protectedRegions = new HashMap<>();

    public void protectRegion(Player player) {
        Location loc = player.getLocation();
        protectedRegions.put(player.getUniqueId(), new Region(loc, 5));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        for (Map.Entry<UUID, Region> entry : protectedRegions.entrySet()) {
            if (entry.getValue().isInside(block.getLocation()) && !entry.getKey().equals(player.getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage("🚫 Du darfst hier nicht abbauen!");
                return;
            }
        }
    }

    private static class Region {
        private final Location center;
        private final int radius;

        public Region(Location center, int radius) {
            this.center = center;
            this.radius = radius;
        }

        public boolean isInside(Location loc) {
            return loc.getWorld().equals(center.getWorld())
                && Math.abs(loc.getBlockX() - center.getBlockX()) <= radius
                && Math.abs(loc.getBlockY() - center.getBlockY()) <= radius
                && Math.abs(loc.getBlockZ() - center.getBlockZ()) <= radius;
        }
    }
}