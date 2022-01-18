package be4rjp.asyncchunklib;

import be4rjp.asyncchunklib.impl.AsyncChunkCache;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import world.chiyogami.chiyogamilib.scheduler.WorldThreadRunnable;

public class EventListener implements Listener {
    
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event){
        Chunk chunk = event.getChunk();
        new WorldThreadRunnable(event.getWorld()){
            @Override
            public void run() {
                AsyncChunkCache.register(chunk);
            }
        }.runTask(AsyncChunkLib.getPlugin());
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        AsyncChunkCache.update(event.getBlock().getChunk());
    }
    
}
