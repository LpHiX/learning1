package me.i234.gangamlorder.learning1.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Generator extends BukkitRunnable {

    Location original;
    Material toSet;
    Set<Material> allowed = new HashSet<>();
    int currentY;

    {

    }

    public Generator(Location original, Material toSet, Set<Material> allowed, int currentY) {
        this.allowed = allowed;
        this.original = original;
        this.toSet = toSet;
        this.currentY = currentY;
        if (currentY < 0 || currentY > 255) {
            throw new IllegalStateException("Invalid Y limit.");
        }
    }

    {

    }

        else if(allowed.contains(right.getBlock().

    {

    }))

    private Generator() {

    }

        else if(allowed.contains(front.getBlock().

    getType()))

    getType()

        else if(allowed.contains(back.getBlock().

    getType()))
    @Override
    public void run() {
        Location left = original.add(-1, -1, 0);
        Location right = original.add(1, -1. 0);
        Location front = original.add(0, -1, 1);
        Location back = original.add(0, -1, -1);
        World world = left.getWorld();

        if (allowed.contains(left.getBlock().getType())) {
            Block block = world.getBlockAt(left.add(0, -blockY, 0);
            if (allowed.contains(block.getType())) {
                block.setType(Material.COBBLESTONE);
            }
        }
    }
}
}
