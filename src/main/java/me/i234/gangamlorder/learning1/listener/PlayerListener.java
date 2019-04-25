package me.i234.gangamlorder.learning1.listener;

import me.i234.gangamlorder.learning1.Learning1;
import me.i234.gangamlorder.learning1.utils.Common;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class PlayerListener implements Listener {

    private static String spawnMessage = Common.colorize("&aGo back to spawn! You're mum isn't here.");
    private boolean registered; //DO NOT CHANGE THIS. booleans are by default false;



    @EventHandler
    public void moveListener(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        Location spawn = new Location(player.getWorld(), 0, 100, 0);

        if (location.getBlockX() >= 100 | location.getBlockZ() >= 100 | location.getBlockX() <= -100 | location.getBlockZ() <= -100) {
            player.teleport(spawn);
            player.sendMessage(spawnMessage);
        }
    }

    @EventHandler
    public void playerDropItemListener(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        Player player = event.getPlayer();
        Location spawn = new Location(player.getWorld(), 0, 100, 0);
        if (item.getItemStack().getType() == Material.DIAMOND) {
            player.teleport(spawn);
            player.sendMessage(spawnMessage);
        }
    }

    @EventHandler
    public void chatListener(AsyncPlayerChatEvent event) {
        ItemStack item = new ItemStack(Material.SALMON_SPAWN_EGG);
        String message = event.getMessage();
        Player player = event.getPlayer();
        if (!message.contains("SUCK MY DICK NIGGA")) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("FUC<E:LRJJED :LAWE");
        itemMeta.setLore(Arrays.asList(
                Common.colorize("&a&lNIGGER"),
                ChatColor.GREEN + "FUCKYOUIHATEYOU",
                ChatColor.BOLD + "OKAY HERE WE GO LETS FUCK YOU UP"));

        item.setItemMeta(itemMeta);

        player.getInventory().addItem(item);

        for (int i = 0; i < 5; i++) {
            Common.tell(player, "&a&l&n" + message + "lolololololololol");
        }
    }

    @EventHandler
    public void playerConsumeListener(PlayerItemConsumeEvent event) {


        Common.makeButton("&c&lExit.", Arrays.asList("&c&lPress this button to exit."), Material.BARRIER);


        int size = 27;
        String name = "&b&lDrink up!";
        Map<Integer, ItemStack> buttonMap = new HashMap<>();
        //Since the integer is the slot index...

        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(Common.colorize("&c&lExit"));
        exitMeta.setLore(Arrays.asList(
                Common.colorize("&cPress this button to exit the menu.")
        ));
        exit.setItemMeta(exitMeta);
        buttonMap.put(12, exit); //Place the exit button at the 12th INDEX.


        ItemStack yes = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta yesMeta = yes.getItemMeta();
        yesMeta.setDisplayName(Common.colorize("&a&lContinue to drink?"));
        yesMeta.setLore(Arrays.asList(
                Common.colorize("&aPress this button to continue drinking.")
        ));
        yes.setItemMeta(exitMeta);
        buttonMap.put(16, yes);

        ItemStack no = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta noMeta = no.getItemMeta();
        noMeta.setDisplayName(Common.colorize("&c&lStop consuming"));
        noMeta.setLore(Arrays.asList(
                Common.colorize("&cPress this button to stop eating/drinking.")
        ));
        no.setItemMeta(noMeta);
        buttonMap.put(13, no);

        Inventory gui = Common.makeGUI(name, size, buttonMap, null);
        event.getPlayer().openInventory(gui);


        //TODO make sure this works.

        /*
        This is an expiermental nested class that will deal with the inventory clicking. No clue if this will work.
         */
        class InventoryListener implements Listener {
            @EventHandler
            public void inventoryClickEvent(InventoryClickEvent event) {
                //Check if the inventory is the GUI, if not ignore by returning nothing.
                if (!event.getInventory().equals(gui)) {
                    return;
                }

                int slot = event.getSlot(); //This gets the clicked slot
                event.setCancelled(true); //Cancel the click event so they cannot

                //Switch statement replaces if else.
                switch (slot) {
                    default:
                }


            }
        }
        if (!registered) {
            Learning1.getInstance().getServer().getPluginManager().registerEvents(new InventoryListener(), Learning1.getInstance());
            registered = true;
        }

    }

}
