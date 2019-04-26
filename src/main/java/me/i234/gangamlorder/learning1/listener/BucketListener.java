package me.i234.gangamlorder.learning1.listener;

import de.tr7zw.itemnbtapi.NBTItem;
import me.i234.gangamlorder.learning1.Learning1;
import me.i234.gangamlorder.learning1.object.GenerateType;
import me.i234.gangamlorder.learning1.utils.Generator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class BucketListener implements Listener {

    @EventHandler
    public void onBucketPlace(PlayerBucketEmptyEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (item == null || !event.getBucket().equals(Material.WATER_BUCKET)) {
            return;
        }
        event.setCancelled(true);
        Location location = event.getBlockClicked().getLocation();
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.getString("customBucket") == null) {
            return;
        }
        String type = nbtItem.getString("generationType");
        String blockType = nbtItem.getString("blockType");
        Material material = Material.valueOf(blockType);

        World world = location.getWorld();
        if (world == null) {
            throw new IllegalStateException("Null world!");
        }

        Set<Material> allowed = new HashSet<>();
        allowed.add(Material.AIR);
        allowed.add(Material.CAVE_AIR);
        allowed.add(Material.VOID_AIR);
        System.out.println(type);
        Generator task = new Generator(location, material, allowed, GenerateType.valueOf(type));
        task.runTaskTimer(Learning1.getInstance(), 10, 10);
    }
}
