package be4rjp.asyncchunklib;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncChunkCache {
    
    private static final Map<String, AsyncChunkCache> worldChunkMap = new ConcurrentHashMap<>();
    
    /**
     * Register chunk cache to be able to get from async thread.
     */
    public static void register(Chunk chunk){
        worldChunkMap.computeIfAbsent(chunk.getWorld().getName(), AsyncChunkCache::new).setChunkCacheMap(chunk);
    }
    
    public static void update(Chunk chunk){
        worldChunkMap.computeIfAbsent(chunk.getWorld().getName(), AsyncChunkCache::new);
    }
    
    /**
     * Get thread safe chunk cache.
     */
    public static AsyncChunkCache getAsyncChunkCache(String world){
        return worldChunkMap.get(world);
    }
    
    public static ChunkSnapshot getChunkCache(String worldName, int chunkX, int chunkZ){
        AsyncChunkCache asyncChunkCache = getAsyncChunkCache(worldName);
        if(asyncChunkCache == null) return null;
        
        return asyncChunkCache.chunkCacheMap.get(getChunkKey(chunkX, chunkZ));
    }
    
    
    private final String world;
    
    private final Map<Long, ChunkSnapshot> chunkCacheMap;
    
    private AsyncChunkCache(String world){
        this.world = world;
        chunkCacheMap = new ConcurrentHashMap<>();
    }
    
    private void setChunkCacheMap(Chunk chunk){
        chunkCacheMap.computeIfAbsent(getChunkKey(chunk.getX(), chunk.getZ()), k -> chunk.getChunkSnapshot());
    }
    
    private void updateChunkCache(Chunk chunk){
        chunkCacheMap.put(getChunkKey(chunk.getX(), chunk.getZ()), chunk.getChunkSnapshot());
    }
    
    /**
     * Get block material from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block material
     */
    public Material getType(int x, int y, int z){
        ChunkSnapshot chunkSnapshot = chunkCacheMap.get(getChunkKey(x >> 4, z >> 4));
        if(chunkSnapshot == null) return null;
        
        return chunkSnapshot.getBlockType(x & 0xF, y, z & 0xF);
    }
    
    /**
     * Get block data from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block data
     */
    public BlockData getBlockData(int x, int y, int z){
        ChunkSnapshot chunkSnapshot = chunkCacheMap.get(getChunkKey(x >> 4, z >> 4));
        if(chunkSnapshot == null) return null;
        
        return chunkSnapshot.getBlockData(x & 0xF, y, z & 0xF);
    }
    
    /**
     * Get block light level from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block light level
     */
    public int getBlockLight(int x, int y, int z){
        ChunkSnapshot chunkSnapshot = chunkCacheMap.get(getChunkKey(x >> 4, z >> 4));
        if(chunkSnapshot == null) return 0;
        
        return chunkSnapshot.getBlockEmittedLight(x & 0xF, y, z & 0xF);
    }
    
    /**
     * Get block light level from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block light level
     */
    public int getSkyLight(int x, int y, int z){
        ChunkSnapshot chunkSnapshot = chunkCacheMap.get(getChunkKey(x >> 4, z >> 4));
        if(chunkSnapshot == null) return 0;
        
        return chunkSnapshot.getBlockSkyLight(x & 0xF, y, z & 0xF);
    }
    
    private static long getChunkKey(int x, int z){return ((long)z << 32) | (x & 0xFFFFFFFFL);}
}
