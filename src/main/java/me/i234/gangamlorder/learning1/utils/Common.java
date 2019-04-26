package me.i234.gangamlorder.learning1.utils;

import me.i234.gangamlorder.learning1.Learning1;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Common {

    /**
     * Tell a player a message. Automatically colourised.
     *
     * @param toWho   you want to tell the message to.
     * @param message the message you want to send.
     */

    public static void tell(CommandSender toWho, String message) {
        String newMessage = ChatColor.translateAlternateColorCodes('&', message);
        toWho.sendMessage(newMessage);

    }


    /**
     * Coloursise a string
     *
     * @param message any string
     * @return the colourised version.
     */

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
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
                defaultItem = new ItemStack(Material.AIR);
            }
            contents[i] = defaultItem;
        }

        inv.setContents(contents);
        return inv;
    }

    /**
     * Create a button for GUI creation.
     *
     * @param displayName of the Item.
     * @param lore        of the Item
     * @param itemType    of the Item
     * @return an {@link ItemStack} with the lore, name and itemType completed.
     * @throws UnsupportedOperationException if the itemType is <code>null</code>;
     */

    public static ItemStack makeButton(String displayName, List<String> lore, Material itemType) {

        displayName = colorize(displayName);

        if (itemType == null) {
            throw new UnsupportedOperationException("itemType is null!");
            //THis means the method will be UNABLE to run because we can't create an item with no item type.
        }

        if (lore == null) {
            ItemStack item = new ItemStack(itemType);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(displayName);
            item.setItemMeta(itemMeta);
            return item;
        }
        for (String string : lore) {
            lore.set(lore.indexOf(string), colorize(string));
        }
        ItemStack item = new ItemStack(itemType);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static void log(Level level, String message) {

        if (level == null) {
            throw new IllegalArgumentException("Null level.");
        }

        Learning1.getInstance().getServer().getLogger().log(level, message);
    }
}
