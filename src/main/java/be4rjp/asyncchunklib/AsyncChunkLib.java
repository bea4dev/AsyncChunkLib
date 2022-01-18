package be4rjp.asyncchunklib;

import be4rjp.asyncchunklib.api.AsyncWorld;
import be4rjp.asyncchunklib.impl.AsyncChunkCache;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AsyncChunkLib extends JavaPlugin {
    
    private static AsyncChunkLib plugin;
    
    public static AsyncChunkLib getPlugin() {return plugin;}
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public static AsyncWorld getAsyncWorld(String world){return AsyncChunkCache.getAsyncWorld(world);}
}
