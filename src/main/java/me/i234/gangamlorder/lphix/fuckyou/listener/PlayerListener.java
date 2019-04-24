package me.i234.gangamlorder.lphix.fuckyou.listener;

import me.i234.gangamlorder.lphix.fuckyou.utils.Common;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerListener implements Listener {

    private static String spawnMessage = ChatColor.GREEN + "wYOUR MOM IS NOT HERE, GO BACK TO SPAWN";

    @EventHandler
    public void movement(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        Location spawn = new Location(player.getWorld(), 0, 100, 0);

        if (location.getBlockX() >= 100 | location.getBlockZ() >= 100 | location.getBlockX() <= -100 | location.getBlockZ() <= -100) {
            player.teleport(spawn);
            player.sendMessage(spawnMessage);
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        Player player = event.getPlayer();
        Location spawn = new Location(player.getWorld(), 0, 100, 0);
        if (item.getItemStack().getType() == Material.DIAMOND) {
            player.teleport(spawn);
            player.sendMessage(spawnMessage);
        }
    }

    @EventHandler
    public void listenToMyChatNigger(AsyncPlayerChatEvent event) {
        ItemStack item = new ItemStack(Material.SALMON_SPAWN_EGG);
        String nigger = event.getMessage();
        Player penis = event.getPlayer();
        if (!nigger.contains("SUCK MY DICK NIGGA")) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("FUC<E:LRJJED :LAWE");
        itemMeta.setLore(Arrays.asList(
                Common.colorize("&a&lNIGGER"),
                ChatColor.GREEN + "FUCKYOUIHATEYOU",
                ChatColor.BOLD + "OKAY HERE WE GO LETS FUCK YOU UP"));

        item.setItemMeta(itemMeta);

        penis.getInventory().addItem(item);
        for (int i = 0; i < 5; i++) {
            Common.tell(penis, "&a&l&n" + nigger + "lolololololololol");
        }
    }
    @EventHandler
    public void stronk(PlayerItemConsumeEvent event){
        Inventory inventory = new Inventory();
    }

}
