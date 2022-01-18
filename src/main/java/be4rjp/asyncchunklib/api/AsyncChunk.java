package be4rjp.asyncchunklib.api;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface AsyncChunk {
    
    /**
     * BukkitのChunkを取得します
     * @return Chunk
     */
    @NotNull Chunk getBukkitChunk();
    
    /**
     * 予め取得したチャンクの状態を取得します
     * @return ChunkSnapshot
     */
    @NotNull ChunkSnapshot getChunkSnapShot();
    
    
    /**
     * Get block material from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block material
     */
    public Material getType(int x, int y, int z);
    
    /**
     * Get block data from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block data
     */
    public BlockData getBlockData(int x, int y, int z);
    
    /**
     * Get block light level from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block light level
     */
    public int getBlockLight(int x, int y, int z);
    
    /**
     * Get sky light level from chunk cache.
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Sky light level
     */
    public int getSkyLight(int x, int y, int z);
}
