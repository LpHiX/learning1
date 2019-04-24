package me.i234.gangamlorder.lphix.fuckyou;

import me.i234.gangamlorder.lphix.fuckyou.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Learning1 extends JavaPlugin {

    private static Learning1 instance;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        instance = this;
        getLogger().log(Level.WARNING, "I HATE YOU SO MUCH ANDY");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public Learning1 getInstance() {
        return instance;
    }
}

