package be4rjp.asyncchunklib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AsyncChunkLib extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
