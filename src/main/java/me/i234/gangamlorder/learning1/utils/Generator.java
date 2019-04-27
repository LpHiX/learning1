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
    private int currentY = 0;
    private GenerateType generateType;

    private boolean front = true;
    private boolean back = true;
    private boolean left = true;
    private boolean right = true;

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
        currentY = original.getBlockY();
        int y = 0;

        Location left = original.clone().add(-1, y, 0);
        Location right = original.clone().add(1, y, 0);
        Location front = original.clone().add(0, y, 1);
        Location back = original.clone().add(0, y, -1);

        switch (generateType) {
            case UP:
                y = +1;
                break;
            case DOWN:
                y = -1;
                break;
        }


        World world = left.getWorld();
        if (allowed.contains(left.getBlock().getType()) && this.left) {
            Location location = left.clone().add(0, y, 0);
            Block block = world.getBlockAt(location.add(0, y, 0));
            if (!allowed.contains(block.getType())) {
                this.left = false;
            }
            block.setType(toSet);

        }
        if (allowed.contains(right.getBlock().getType()) && this.right) {
            Location location = right.clone().add(0, y, 0);
            Block block = world.getBlockAt(location.add(0, y, 0));
            if (!allowed.contains(block.getType())) {
                this.right = false;
            }
            block.setType(toSet);

        }
        if (allowed.contains(front.getBlock().getType()) && this.front) {
            Location location = front.clone().add(0, y, 0);
            Block block = world.getBlockAt(location.add(0, y, 0));
            if (!allowed.contains(block.getType())) {
                block.setType(toSet);
            }

        }
        if (allowed.contains(back.getBlock().getType()) && this.back) {
            Location location = back.clone().add(0, y, 0);
            Block block = world.getBlockAt(location.add(0, y, 0));
            if (!allowed.contains(block.getType())) {
                this.back = false;
            }
            block.setType(toSet);

        }

        if (!allowed.contains(world.getBlockAt(original.clone().add(0, y, 0)).getType())) {
            this.cancel();
        }

        if (currentY > 254 || currentY == 0) {
            this.cancel();
        }

        currentY = (generateType == GenerateType.UP) ? currentY + 1 : currentY - 1;
        original = new Location(original.getWorld(), original.getBlockX(), currentY, original.getBlockZ());
        System.out.println(currentY);
    }
}
