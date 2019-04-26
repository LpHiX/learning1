package me.i234.gangamlorder.learning1;

import me.i234.gangamlorder.learning1.command.BucketCommand;
import me.i234.gangamlorder.learning1.listener.BucketListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.logging.Level;

public final class Learning1 extends JavaPlugin {

    private static Learning1 instance;

    public static Learning1 getInstance() {
        return instance;
    }

    public static HashSet<ItemStack> test = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;
        // getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new BucketListener(), this);
        getLogger().log(Level.WARNING, "I HATE YOU SO MUCH ANDY");
        getCommand("BucketCommand").setExecutor(new BucketCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

