package be4rjp.asyncchunklib;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class EventListener implements Listener {
    
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event){
        Chunk chunk = event.getChunk();
        AsyncChunkCache.register(chunk);
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        AsyncChunkCache.update(event.getBlock().getChunk());
    }
    
}
