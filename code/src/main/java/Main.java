
import edu.stanford.nlp.ling.Word;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<String[]> texteTraitee = TraitementDeTexte.traiterText("src/main/dataset");
        ArrayList<String> nomsFichiers = TraitementDeTexte.nomFichier("src/main/dataset");

        BufferedReader br = new BufferedReader(new FileReader("src/main/query.txt"));
        ArrayList<String> listeCorrige = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
//            String correctLine = TraitementDeTexte.correction(line);
//            listeCorrige.add(correctLine);
        }
//        int j = 0;
//        WordMap<String, Integer> myMap = new WordMap<>(331,57);
//        int i = 0;
//
//        for (String nom : nomsFichiers) {
//            for(String mot: texteTraitee.get(i)){
//                if (myMap.containsKey(mot)){
//                    FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
//
//                    if(myMap2.containsKey(nom)){ // si le nom du fichier existe
//                        myMap2.get(nom).add(i);
//                    } else {
//                        myMap2.put(nom,new ArrayList<>(Arrays.asList(i)) );
//                    }
//                } else {
//                    myMap.put(mot, new FileMaps<String, ArrayList<Integer>>());
//                    FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
//                    myMap2.put(nom,new ArrayList<>(Arrays.asList(i)) );
//                }
//                i++;
//            }
//        }

        WordMap<String, FileMaps<String, ArrayList<Integer>>> myMap = new WordMap<>(331, 57);
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

        for (String key : myMap.keySet()) {
            System.out.println(key); // mot
            for(String key2 : myMap.get(key).keySet() ){
                System.out.println(key2 + ": " + myMap.get(key).get(key2) );
            }
        }

//        WordMap<String, Integer> myMap = new WordMap<>(331,57);
//        int i = 0; //index dans le string
//        for(String mot: texteTraitee.get(0)){
//            if (myMap.containsKey(mot)){
//                FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
//                myMap2.get("900.txt").add(i);
//            } else {
//                myMap.put(mot, new FileMaps<String, ArrayList<Integer>>());
//                FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
//                myMap2.put("900.txt",new ArrayList<>(Arrays.asList(i)) );
//            }
//            i++;
//        }
//
//        for (String key : myMap.keySet()) {
//                System.out.println(key + ", " + myMap.get(key).get("900.txt"));
//            }
//    }

}
}