package me.i234.gangamlorder.lphix.fuckyou.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Common {
    public static void tell(Player toWho, String message){
        String newMessage = ChatColor.translateAlternateColorCodes('&', message);
        toWho.sendMessage(newMessage);
    }
    public static String colorize(String message){
        return ChatColor.translateAlternateColorCodes('&',message);
    }
    public static Inventory makeGUI(String name, int size, Map<Integer, ItemStack> click){
        Inventory inv = Bukkit.createInventory(null, size, name);
        ItemStack[] contents = inv.getContents();
        for (int i = 0; i < size; i++){

        }

    }
}
