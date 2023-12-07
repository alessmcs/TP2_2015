import java.util.ArrayList;

public class WordMap<K,V> extends ChainHashMap<String,FileMap<String, V>> {

    // K -> mot & V -> ref à FileMap

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
        int a = 5; int b = 13; int p = 577;
        return ( (a*hashCode(key) + b)%p)%331; // MAD method, N = 331 (prime number)
    }

    private void resize( int newCap ) {
        ArrayList<Entry<String,FileMap<String, V>>> buffer = new ArrayList<>();
        for( Entry<String, FileMap<String, V>> e : this.entrySet() )
            buffer.add( e );
        this.capacity = newCap;
        this.createTable(); // based on updated capacity
        this.n = 0; // wil be recomputed while reinserting entries
        for( Entry<String,FileMap<String, V>> e : buffer )
            put((String) e.getKey(), e.getValue());
    }




}
