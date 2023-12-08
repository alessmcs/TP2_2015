import java.util.ArrayList;

public class FileMaps<K, V> extends ChainHashMap<String, ArrayList<Integer>> {

//    @Override
//    protected void createTable() {
//
//    }


    @Override
    protected ArrayList<Integer> bucketRemove(int h, String s) {
        return null;
    }

//    @Override
//    public Iterable<Entry<String, ArrayList<Integer> >> entrySet() {
//        return null;
//    }
}
