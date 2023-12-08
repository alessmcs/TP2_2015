
import edu.stanford.nlp.ling.Word;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<String[]> texteTraitee = TraitementDeTexte.traiterText("src/main/dataset");


        BufferedReader br = new BufferedReader(new FileReader("src/main/query.txt"));
        ArrayList<String> listeCorrige = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
//            String correctLine = TraitementDeTexte.correction(line);
//            listeCorrige.add(correctLine);
        }

        String nomFichier = "900.txt";
        // test sur le premier fichier 900.txt
        WordMap<String, Integer> myMap = new WordMap<>();
        int i = 0; //index dans le string
        for(String mot: texteTraitee.get(0)){
            System.out.println(mot + ": " + WordMap.hashValue(mot));
            if (myMap.containsKey(mot)){
                FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
                myMap2.get(nomFichier).add(i);
            } else {
                myMap.put(mot, new FileMaps<String, ArrayList<Integer>>());
                FileMaps<String, ArrayList<Integer>> myMap2 = myMap.get(mot);
                myMap2.put(nomFichier,new ArrayList<>(Arrays.asList(i)) );
            }
            System.out.println(mot + " , " + myMap.get(mot).get(nomFichier).toString());
            i++;
        }

//        for (String key : myMap.keySet()) {
//            System.out.println(key);
//        }

    }

}