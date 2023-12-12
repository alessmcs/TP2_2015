import java.util.ArrayList;
public class Search {

    private static ArrayList<String> nomsFichiers = new ArrayList<>();

    public static void setNomsFichiers(ArrayList<String> liste){
        nomsFichiers = liste;
    }

    public static String search(String[] motsRequete){

        double[] tfidf = new double[nomsFichiers.size()];

        for(String nom : nomsFichiers){
            double somme = 0;
            for(String mot : motsRequete){
                double val = TF_IDF.tfidf(mot, nom);
                somme += val;
            }
            tfidf[nomsFichiers.indexOf(nom)] = somme;
        }

        // find the best value for the word planet
        double maxVal = tfidf[0];
        int index = 0;
        // find the max value
        for (int j = 1; j < tfidf.length; j++) {
            if (tfidf[j] > maxVal) {
                maxVal = tfidf[j];
                index = j;
            }
        }

        //TODO: effacer print statement & remplacer par qqch qui écrit dans le fichier des résultats a partir de Main
        // all good car search retourne juste le nom du fichier donc il suffit juste de l'écrire
        System.out.println(nomsFichiers.get(index));
        return nomsFichiers.get(index);
    }
}
