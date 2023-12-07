public class WordMap {

    public static int hashCode(String mot) {
        int resultat = 0 ;
        for (int i = 0 ; i < mot.length(); i++) {
            resultat = (resultat << 5) | (resultat >>> 27);
            resultat += (int)mot.charAt(i);
        }

        return resultat;
    }
}
