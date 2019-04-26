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

        /*
              TODO using the methods in Common - create a new GUI and buttons that either kill the player or tp them back to spawn.
              TODO exiting the menu will mean that they will be instantly killed.
              TODO use the nested clickListener to handle the click and use event.cancel(boolean) to cancel the event.
         */


        if (location.getBlockX() >= 100 | location.getBlockZ() >= 100 | location.getBlockX() <= -100 | location.getBlockZ() <= -100) {
            player.teleport(spawn);
            player.sendMessage(spawnMessage);
        }

        class ClickListener implements Listener {
            @EventHandler
            public void ClickListener(InventoryClickEvent e) {


            }
        }
        Learning1.getInstance().getServer().getPluginManager().registerEvents(new ClickListener(), Learning1.getInstance());
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

        int size = 27;
        String name = "&b&lDrink up!";
        Map<Integer, ItemStack> buttonMap = new HashMap<>();

        // Make use of new utility method makeButton

        //TODO Add an extra button. That shows the player's name and the current item they are holding.


        ItemStack exit = Common.makeButton("&c&lExit.", Arrays.asList("&c&lPress this button to exit."), Material.BARRIER);
        buttonMap.put(10, exit); //Place the exit button at the 10th INDEX.


        ItemStack yes = Common.makeButton("&a&lContinue to drink?", Arrays.asList("&aPress this button to continue drinking."), Material.EMERALD_BLOCK);
        buttonMap.put(16, yes);

        ItemStack no = Common.makeButton("&c&lStop consuming.", Arrays.asList("&cPress this button to stop eating/drinking."), Material.REDSTONE_BLOCK);
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

                Player player = (Player) event.getWhoClicked();
                //Since #getWhoClicked() returns a HumanEntity which is a subclass of Player, its ok to "cast" it to player

                //Switch statement replaces if else.
                switch (slot) {
                    default:
                        return;
                    case 10:
                        player.closeInventory();
                        ;
                        Common.tell(player, "&cYou have closed the menu - the action has been cancelled.");
                        break;
                    case 13:
                        player.closeInventory();
                        Common.tell(player, "&cThe action has been stipped");
                        break;
                    case 16:
                        player.closeInventory();
                        event.setCancelled(false);
                        Common.tell(player, "&aThe action has been allowed");

                }
            }
        }
            Learning1.getInstance().getServer().getPluginManager().registerEvents(new InventoryListener(), Learning1.getInstance());
    }

}
