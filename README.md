### AsyncChunkLib

非同期スレッドから安全に、読み込み済みのブロックの状態を取得するためのプラグイン

```java
import be4rjp.asyncchunklib.api.AsyncWorld;
import be4rjp.asyncchunklib.impl.AsyncChunkCache;
import org.bukkit.Material;

public class TestCode {
    public static void test() {
        AsyncWorld asyncWorld = AsyncChunkCache.getAsyncWorld("world");
        Material material = asyncWorld.getType(0, 64, 0);
        
        if(material != null){
            System.out.println("Material is " + material);
        }
    }
}
```