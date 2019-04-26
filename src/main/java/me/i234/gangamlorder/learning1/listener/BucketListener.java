package me.i234.gangamlorder.learning1.listener;

import de.tr7zw.itemnbtapi.NBTItem;
import me.i234.gangamlorder.learning1.utils.Generator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class BucketListener implements Listener {
    @EventHandler
    public void onBucketPlace(PlayerBucketEvent event) {
        ItemStack item = event.getItemStack();
        if (item == null) {
            return;
        }
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.getString("customBucket") == null) {
            return;
        }


        String type = nbtItem.getString("type");
        String blockType = nbtItem.getString("blockType");
        Material material = Material.valueOf(blockType);

        Location location = event.getBlockClicked().getLocation();
        World world = location.getWorld();
        if (world == null) {
            throw new IllegalStateException("Null world!");
        }

        Set<Material> allowed = new HashSet<>();
        allowed.add(Material.AIR);
        allowed.add(Material.CAVE_AIR);
        allowed.add(Material.VOID_AIR);

        Generator task = new Generator(location, Material.COBBLESTONE, allowed);
    }
}
