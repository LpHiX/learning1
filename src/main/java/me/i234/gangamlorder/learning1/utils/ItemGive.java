package me.i234.gangamlorder.learning1.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class ItemGive extends BukkitRunnable {

    private Player player;
    private String message;
    private ItemStack item;

    public ItemGive(Player player, String message, ItemStack item) {
        this.player = player;
        this.message = message;
        this.item = item;
    }

    @Override
    public void run() {

        if (!message.contains("SUCK MY DICK NIGGA")) {
            return;
        }
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("Item");
        itemMeta.setLore(Arrays.asList(
                Common.colorize("&a&lTestItem")
        ));
        item.setItemMeta(itemMeta);
        player.getInventory().addItem(item);
    }
}
