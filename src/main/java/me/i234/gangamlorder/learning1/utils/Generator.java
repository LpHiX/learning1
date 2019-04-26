package me.i234.gangamlorder.learning1.utils;

import me.i234.gangamlorder.learning1.object.TemporaryObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Generator extends BukkitRunnable {

    private Location original;
    private Material toSet;
    private Set<Material> allowed = new HashSet<>();
    private int currentY;
    private UUID uuid;

    private Generator() {
    }

    public Generator(UUID uuid, Location original, Material toSet, Set<Material> allowed) {
        this();

        if (TemporaryObject.getY(uuid) == -1) {
            TemporaryObject.registerTask(uuid, original.getBlockY());
        }

        this.allowed = allowed;
        this.original = original;
        this.toSet = toSet;
        this.uuid = uuid;
        this.currentY = TemporaryObject.getY(uuid);

        if (currentY < 0 || currentY > 255) {
            throw new IllegalStateException("Invalid Y limit.");
        }
    }

    @Override
    public void run() {

        Location left = original.add(-1, -1, 0);
        Location right = original.add(1, -1, 0);
        Location front = original.add(0, -1, 1);
        Location back = original.add(0, -1, -1);
        World world = left.getWorld();

        if (allowed.contains(left.getBlock().getType())) {
            Block block = world.getBlockAt(left.add(0, -currentY + 1, 0));
            if (allowed.contains(block.getType())) {
                block.setType(Material.COBBLESTONE);
            }
        } else if (allowed.contains(right.getBlock().getType())) {
            Block block = world.getBlockAt(right.add(0, -currentY + 1, 0));
            if (allowed.contains(block.getType())) {
                block.setType(Material.COBBLESTONE);
            }
        } else if (allowed.contains(front.getBlock().getType())) {
            Block block = world.getBlockAt(front.add(0, -currentY + 1, 0));
            if (allowed.contains(block.getType())) {
                block.setType(Material.COBBLESTONE);
            }
        } else if (allowed.contains(back.getBlock().getType())) {
            Block block = world.getBlockAt(back.add(0, -currentY + 1, 0));
            if (allowed.contains(block.getType())) {
                block.setType(Material.COBBLESTONE);
            }
        }
        currentY = currentY - 1;
        if (!allowed.contains(world.getBlockAt(original.add(0, currentY, 0)).getType())) {
            this.cancel();
        }
        TemporaryObject.updateY(this.uuid, currentY);
    }
}
