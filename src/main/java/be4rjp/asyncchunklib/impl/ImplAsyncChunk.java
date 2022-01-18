package be4rjp.asyncchunklib.impl;

import be4rjp.asyncchunklib.api.AsyncChunk;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

class ImplAsyncChunk implements AsyncChunk {
    
    private Chunk chunk;
    
    private ChunkSnapshot chunkSnapshot;
    
    public ImplAsyncChunk(Chunk chunk){
        this.chunk = chunk;
        this.chunkSnapshot = chunk.getChunkSnapshot();
    }
    
    public void update(Chunk chunk){
        this.chunk = chunk;
        this.chunkSnapshot = chunk.getChunkSnapshot();
    }
    
    @Override
    public Chunk getBukkitChunk() {
        return chunk;
    }
    
    @Override
    public ChunkSnapshot getChunkSnapShot() {
        return chunkSnapshot;
    }
    
    @Override
    public Material getType(int x, int y, int z){
        return chunkSnapshot.getBlockType(x & 0xF, y, z & 0xF);
    }
    
    @Override
    public BlockData getBlockData(int x, int y, int z){
        return chunkSnapshot.getBlockData(x & 0xF, y, z & 0xF);
    }
    
    @Override
    public int getBlockLight(int x, int y, int z){
        return chunkSnapshot.getBlockEmittedLight(x & 0xF, y, z & 0xF);
    }
    
    @Override
    public int getSkyLight(int x, int y, int z){
        return chunkSnapshot.getBlockSkyLight(x & 0xF, y, z & 0xF);
    }
}
