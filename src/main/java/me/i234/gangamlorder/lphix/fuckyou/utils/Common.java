package me.i234.gangamlorder.lphix.fuckyou.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class Common {

    /**
     * Tell a player a message. Automatically colourised.
     *
     * @param toWho   you want to tell the message to.
     * @param message the message you want to send.
     */

    public static void tell(Player toWho, String message){
        String newMessage = ChatColor.translateAlternateColorCodes('&', message);
        toWho.sendMessage(newMessage);
    }


    /**
     * Coloursise a string
     *
     * @param message any string
     * @return the colourised version.
     */

    public static String colorize(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }


    /**
     * Creates an inventory with no owner.
     *
     * @param name        Name of the inventory.
     * @param size        Size of the inventory.
     * @param buttonMap   map of the button to it's {@link Integer} slot index;
     * @param defaultItem The item that fills in the rest of GUI menu when there is nothing. Default set it <code>Material.BLACK_STAINED_GLASS_PANE</code>.
     * @return the finished inventory.
     */

    public static Inventory makeGUI(String name, int size, Map<Integer, ItemStack> buttonMap, ItemStack defaultItem) {

        Inventory inv = Bukkit.createInventory(null, size, colorize(name));
        ItemStack[] contents = inv.getContents();

        for (int i = 0; i < contents.length; i++) {
            if (buttonMap.get(i) != null) {
                contents[i] = buttonMap.get(i);
                continue;
            }
            if (defaultItem == null) {
                defaultItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta defaultItemMeta = defaultItem.getItemMeta();
                defaultItemMeta.setDisplayName(" ");
                defaultItem.setItemMeta(defaultItemMeta);
            }
            contents[i] = defaultItem;
        }
        inv.setContents(contents);
        return inv;
    }
}
