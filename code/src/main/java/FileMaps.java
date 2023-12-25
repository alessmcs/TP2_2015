import java.util.ArrayList;

public class FileMaps<K, V> extends ChainHashMap<String, ArrayList<Integer>> {


    @Override
    protected ArrayList<Integer> bucketRemove(int h, String s) {
        return null;
    }


}
