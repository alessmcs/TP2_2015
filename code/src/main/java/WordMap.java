import java.util.ArrayList;
import java.util.Random;

public class WordMap<K,V> extends ChainHashMap<String, FileMaps<String, ArrayList<Integer>>> {

    // K -> mot & V -> ref à FileMap

    protected int n = 0; // number of entries in the map
    protected static int prime;
    protected static int capacity; // size of the table
    private static long scale;
    private static long shift; // shift and scale factors


    private UnsortedTableMap<String,FileMaps<String, ArrayList<Integer>>>[] table; // initialized in createTable

    public WordMap( int cap, int p ) {
        this.prime = p;
        this.capacity = cap;
        Random rand = new Random();
        this.scale = rand.nextInt( prime - 1 ) + 1;
        this.shift = rand.nextInt( prime );
        this.createTable();
    }

    // TODO: citer ce code!
    public static int hashCode(String mot) {
        int resultat = 0 ;
        for (int i = 0 ; i < mot.length(); i++) {
            resultat = (resultat << 1) | (resultat >>> 31); // shift by 1 instead of 5 bits bc less collisions for this method
            resultat += (int)mot.toLowerCase().charAt(i);
        }

        return resultat;
    }

    // fonction de compression
    public static int hashValue(String key){
        return (int)( ( Math.abs( key.hashCode() * scale + shift ) % prime ) % capacity );
    }

    @Override
    public FileMaps<String, ArrayList<Integer>> put( String key, FileMaps<String, ArrayList<Integer>> value ) {
        FileMaps<String, ArrayList<Integer>> retV = bucketPut( hashValue( key ), key, value );
        if( n > capacity * 0.75 ) // keep load factor <= 0.75
            resize( 2 * capacity + 1 ); // (or find a nearby prime)
        return retV;
    }

    @Override
    protected void createTable() {
        table = (UnsortedTableMap<String,FileMaps<String, ArrayList<Integer>>>[]) new UnsortedTableMap[this.capacity];
    }

    public boolean containsKey( String key ) { return bucketGet( hashValue( key ), key ) != null; }

    @Override
    protected FileMaps<String, ArrayList<Integer>> bucketPut( int h, String k, FileMaps<String, ArrayList<Integer>> v ) {
        UnsortedTableMap<String,FileMaps<String, ArrayList<Integer>>> bucket = table[h];

        if( bucket == null )
            bucket = table[h] = new UnsortedTableMap<>();

        int oldSize = bucket.size();
        FileMaps<String, ArrayList<Integer>> old = bucket.put( k, v );
        this.n += ( bucket.size() - oldSize ); // size may have increased
        return old;
    }

    protected FileMaps<String, ArrayList<Integer>> bucketGet( int h, String k ) {
        UnsortedTableMap<String,FileMaps<String, ArrayList<Integer>>> bucket = table[h];
        if( bucket == null ) return null;
        return bucket.get( k );
    }

    public FileMaps<String, ArrayList<Integer>> get( String key ) { return bucketGet( hashValue( key ), key ); }

    private void resize( int newCap ) {
        ArrayList<Entry<String, FileMaps<String, ArrayList<Integer> > >> buffer = new ArrayList<>();
        for( Entry<String,  FileMaps<String, ArrayList<Integer> > > e : this.entrySet() )
            buffer.add( e );
        this.capacity = newCap;
        this.createTable(); // based on updated capacity
        this.n = 0; // wil be recomputed while reinserting entries
        for( Entry<String, FileMaps<String, ArrayList<Integer>>> e : buffer )
            put((String) e.getKey(), e.getValue());
    }

    public Iterable<Entry<String, FileMaps<String, ArrayList<Integer>>>> entrySet() {
        ArrayList<Entry<String, FileMaps<String, ArrayList<Integer>>>> buffer = new ArrayList<>();
        for( int h = 0; h < this.capacity; h++ )
            if( table[h] != null )
                for( Entry<String, FileMaps<String, ArrayList<Integer>>> entry : table[h].entrySet() )
                    buffer.add( entry );
        return buffer;
    }

}
