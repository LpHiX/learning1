package me.i234.gangamlorder.learning1.utils;

import me.i234.gangamlorder.learning1.object.GenerateType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Generator extends BukkitRunnable {

    private Location original;
    private Material toSet;
    private Set<Material> allowed = new HashSet<>();
    private int currentY;
    private GenerateType generateType;

    private Generator() {
    }

    public Generator(Location original, Material toSet, Set<Material> allowed, GenerateType generateType) {
        this();
        this.allowed = allowed;
        this.original = original;
        this.toSet = toSet;
        this.generateType = generateType;

        if (original.getWorld() == null) {
            throw new IllegalStateException("Null world!");
        }

    }

    @Override
    public void run() {

        Location left = original.add(-1, -1, 0);
        Location right = original.add(1, -1, 0);
        Location front = original.add(0, -1, 1);
        Location back = original.add(0, -1, -1);
        World world = left.getWorld();

        int y = 0;

        switch (generateType) {
            case UP:
                y = currentY + 1;
                break;
            case DOWN:
                y = -currentY - 1;
                break;
        }

        if (allowed.contains(left.getBlock().getType())) {
            Block block = world.getBlockAt(left.add(0, y, 0));
            if (allowed.contains(block.getType())) {
                block.setType(toSet);
            }
        } else if (allowed.contains(right.getBlock().getType())) {
            Block block = world.getBlockAt(right.add(0, y, 0));
            if (allowed.contains(block.getType())) {
                block.setType(toSet);
            }
        } else if (allowed.contains(front.getBlock().getType())) {
            Block block = world.getBlockAt(front.add(0, y, 0));
            if (allowed.contains(block.getType())) {
                block.setType(toSet);
            }
        } else if (allowed.contains(back.getBlock().getType())) {
            Block block = world.getBlockAt(back.add(0, y, 0));
            if (allowed.contains(block.getType())) {
                block.setType(toSet);
            }
        }

        if (!allowed.contains(world.getBlockAt(original.add(0, currentY, 0)).getType())) {
            this.cancel();
        }

        currentY = (generateType == GenerateType.UP) ? currentY + 1 : currentY - 1;
    }
}
