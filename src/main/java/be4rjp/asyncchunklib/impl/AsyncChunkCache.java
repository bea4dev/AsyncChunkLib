package be4rjp.asyncchunklib.impl;

import be4rjp.asyncchunklib.api.AsyncChunk;
import be4rjp.asyncchunklib.api.AsyncWorld;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncChunkCache {
    
    private static final Map<String, ImplAsyncWorld> worldMap = new ConcurrentHashMap<>();
    
    /**
     * Register chunk cache to be able to get from async thread.
     */
    public static void register(Chunk chunk){
        worldMap.computeIfAbsent(chunk.getWorld().getName(), ImplAsyncWorld::new).setAsyncChunk(chunk);
    }
    
    public static void update(Chunk chunk){
        worldMap.computeIfAbsent(chunk.getWorld().getName(), ImplAsyncWorld::new).update(chunk);
    }
    
    /**
     * Get thread safe chunk cache.
     */
    public static AsyncWorld getAsyncWorld(String world){
        return worldMap.computeIfAbsent(world, ImplAsyncWorld::new);
    }
    
    public static ChunkSnapshot getChunkCache(String worldName, int chunkX, int chunkZ){
        AsyncWorld asyncWorld = getAsyncWorld(worldName);
        if(asyncWorld == null) return null;
        
        AsyncChunk asyncChunk = asyncWorld.getAsyncChunk(chunkX, chunkZ);
        if(asyncChunk == null) return null;
        
        return asyncChunk.getChunkSnapShot();
    }
    
}
