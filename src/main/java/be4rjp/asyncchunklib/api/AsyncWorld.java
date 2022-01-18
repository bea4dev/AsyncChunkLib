package be4rjp.asyncchunklib.api;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

public interface AsyncWorld {
    
    /**
     * チャンクを取得します
     * @param chunkX チャンク座標(X)
     * @param chunkZ チャンク座標(Z)
     * @return AsyncChunk
     */
    @Nullable AsyncChunk getAsyncChunk(int chunkX, int chunkZ);
    
    /**
     * ブロックのマテリアルを取得します
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block material
     */
    public Material getType(int x, int y, int z);
    
    /**
     * ブロックのブロックデータを取得します
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block data
     */
    public BlockData getBlockData(int x, int y, int z);
    
    /**
     * ブロックのライトレベル(Block)を取得します
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Block light level
     */
    public int getBlockLight(int x, int y, int z);
    
    /**
     * ブロックのライトレベル(Sky)を取得します
     * @param x block x
     * @param y block y
     * @param z block z
     * @return Sky light level
     */
    public int getSkyLight(int x, int y, int z);
}
