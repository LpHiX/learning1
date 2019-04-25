package me.i234.gangamlorder.learning1;

import me.i234.gangamlorder.learning1.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Learning1 extends JavaPlugin {

    private static Learning1 instance;

    public static Learning1 getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().log(Level.WARNING, "I HATE YOU SO MUCH ANDY");
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}

