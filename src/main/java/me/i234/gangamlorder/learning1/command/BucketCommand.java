package me.i234.gangamlorder.learning1.command;

import de.tr7zw.itemnbtapi.NBTItem;
import me.i234.gangamlorder.learning1.Learning1;
import me.i234.gangamlorder.learning1.utils.Common;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BucketCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            Common.tell(sender, "Debug. Wrong syntax");
        } else if (!(sender instanceof Player)) {
            return false;
        }

        ItemStack item = new ItemStack(Material.WATER_BUCKET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Common.colorize("&dMagic Bucket"));
        itemMeta.setLore(Arrays.asList(
                Common.colorize("&7This magic buckets was forged by a divine wizard"),
                Common.colorize("&7and can only be found here."),
                Common.colorize("&b&l(!) Divine Item")
        ));
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("customBucket", "yes");
        nbtItem.setString("generationType", args[1]);
        nbtItem.setString("blockType", args[0]);
        item = nbtItem.getItem();
        Player player = (Player) sender;
        player.getInventory().addItem(item);
        Learning1.test.add(item);
        return false;
    }
}
