import java.util.ArrayList;

public class TD_IDF {


    // Calculer TD
    // count(w) & totalW
    public static double tf(double countW, double totalW){
        return countW / totalW;
    }
//
//    // Calculer IDF
    // count(d,w)
    public static double idf(double totalD, double countDW){
        return 1 + Math.log((1 + totalD)/(1+countDW));
    }
//
    // Calculer TD-IDF
    public static double tdidf(String word, String fileName){
        int[] occurences = count(word, fileName);
        int countW = occurences[0];
        int countDW = occurences[1];

        int index = Main.nomsFichiers.indexOf(fileName);

        int totalD = Main.nomsFichiers.size(); // nombre de fichiers
        int totalW = Main.texteTraitee.get(index).length; // nombre de mots dans le fichier qu'on regarde actuellement

        double tf = tf(countW, totalW);
        double idf = idf(totalD, countDW);

        double tfidf = tf * idf;

        return tfidf;
    }

    // Compter le nombre d'occurences d'un mot dans un document
    public static int[] count(String w, String fileName){
        int[] occurences = new int[2];

        // occurence du mot dans le fichier
        FileMaps<String, ArrayList<Integer>> map1 = Main.myMap.get(w);
        if(map1.containsKey(fileName)){
            occurences[0] = map1.get(fileName).size();
        } else {
            occurences[0] = 0;
        }
        //nombre de fichiers contenant le mot
        occurences[1] = map1.size(); // nbr de keys dans le fileMap associé au mot

        return occurences;
    }




}
