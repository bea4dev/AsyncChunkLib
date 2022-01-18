package be4rjp.asyncchunklib.impl;

import be4rjp.asyncchunklib.api.AsyncChunk;
import be4rjp.asyncchunklib.api.AsyncWorld;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ImplAsyncWorld implements AsyncWorld {
    
    private final String worldName;
    
    private final Map<Long, ImplAsyncChunk> asyncChunkMap = new ConcurrentHashMap<>();
    
    public ImplAsyncWorld(String worldName){
        this.worldName = worldName;
    }
    
    public void setAsyncChunk(Chunk chunk){
        asyncChunkMap.computeIfAbsent(getChunkKey(chunk.getX(), chunk.getZ()), c -> new ImplAsyncChunk(chunk));
    }
    
    public void update(Chunk chunk){
        asyncChunkMap.computeIfAbsent(getChunkKey(chunk.getX(), chunk.getZ()), c -> new ImplAsyncChunk(chunk)).update(chunk);
    }
    
    @Override
    public AsyncChunk getAsyncChunk(int chunkX, int chunkZ) {
        return asyncChunkMap.get(getChunkKey(chunkX, chunkZ));
    }
    
    @Override
    public Material getType(int x, int y, int z) {
        AsyncChunk asyncChunk = getAsyncChunk(x >> 4, z >> 4);
        if(asyncChunk == null) return null;
        
        return asyncChunk.getType(x, y, z);
    }
    
    @Override
    public BlockData getBlockData(int x, int y, int z) {
        AsyncChunk asyncChunk = getAsyncChunk(x >> 4, z >> 4);
        if(asyncChunk == null) return null;
    
        return asyncChunk.getBlockData(x, y, z);
    }
    
    @Override
    public int getBlockLight(int x, int y, int z) {
        AsyncChunk asyncChunk = getAsyncChunk(x >> 4, z >> 4);
        if(asyncChunk == null) return 0;
    
        return asyncChunk.getBlockLight(x, y, z);
    }
    
    @Override
    public int getSkyLight(int x, int y, int z) {
        AsyncChunk asyncChunk = getAsyncChunk(x >> 4, z >> 4);
        if(asyncChunk == null) return 0;
    
        return asyncChunk.getSkyLight(x, y, z);
    }
    
    private static long getChunkKey(int x, int z){return ((long)z << 32) | (x & 0xFFFFFFFFL);}
}
