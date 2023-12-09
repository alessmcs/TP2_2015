
import edu.stanford.nlp.ling.Word;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String[]> texteTraitee = new ArrayList<>();
    public static ArrayList<String> nomsFichiers = new ArrayList<>();

    public static WordMap<String, FileMaps<String, ArrayList<Integer>>> myMap = new WordMap<>(331, 57);

    public static void main(String[] args) throws IOException {
        texteTraitee = TraitementDeTexte.traiterText("src/main/dataset");
        nomsFichiers = TraitementDeTexte.nomFichier("src/main/dataset");

        BufferedReader br = new BufferedReader(new FileReader("src/main/query.txt"));
        ArrayList<String> listeCorrige = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
//            String correctLine = TraitementDeTexte.correction(line);
//            listeCorrige.add(correctLine);
        }

        int i = 0;

        for (String nom : nomsFichiers) {
            int fileIndex = 0;
            for (String mot : texteTraitee.get(nomsFichiers.indexOf(nom))) {
                if (myMap.containsKey(mot)) {
                    FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
                    if (myMap2.containsKey(nom)) {
                        myMap2.get(nom).add(fileIndex);
                    } else {
                        myMap2.put(nom, new ArrayList<>(Arrays.asList(fileIndex)));
                    }
                } else {
                    FileMaps<String, ArrayList<Integer>> fileMap = new FileMaps<>();
                    ArrayList<Integer> positions = new ArrayList<>();
                    positions.add(fileIndex);
                    fileMap.put(nom, positions);
                    myMap.put(mot, fileMap);
                }
                fileIndex++;
            }
            i++;
        }

        //todo: effacer prints
        for (String key : myMap.keySet()) {
            System.out.println(key); // mot
            for(String key2 : myMap.get(key).keySet() ){
                System.out.println(key2 + ": " + myMap.get(key).get(key2) );
            }
        }



    }

    public static String search(String requete){

        double[] tfidf = new double[nomsFichiers.size()];

        String[] motsRequete = requete.split(" ");

        for(String nom : nomsFichiers){
            double somme = 0;
            for(String mot : motsRequete){
                double val = TD_IDF.tdidf(mot, nom);
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

        //TODO: effacer print statement
        System.out.println("Le fichier est : " + nomsFichiers.get(index));
        return nomsFichiers.get(index);
    }
}