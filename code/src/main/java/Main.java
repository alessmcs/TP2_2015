import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static WordMap<String, FileMaps<String, ArrayList<Integer>>> myMap = new WordMap<>(331, 57);


    public static void main(String[] args) throws IOException {

        ArrayList<String[]> texteTraitee = new ArrayList<>();
        ArrayList<String> nomsFichiers = new ArrayList<>();

        texteTraitee = TraitementDeTexte.traiterText("src/main/dataset2");
        nomsFichiers = TraitementDeTexte.nomFichier("src/main/dataset2");

        int i = 0;

        // Construire le WordMap & FileMap
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
            //System.out.println(key2 + ": " + myMap.get(key).get(key2) )
        }


        Search.setNomsFichiers(nomsFichiers);
        TF_IDF.setListes(nomsFichiers, texteTraitee);

        BufferedReader br = new BufferedReader(new FileReader("src/main/query2.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(" ");
            if(splitLine[0].equals("search")){
                String[] tabMots = Arrays.copyOfRange(splitLine, 1, splitLine.length);
                String[] tabCorrige = TraitementDeTexte.correction(tabMots);
                Search.search(tabCorrige);
            }
        }

    }

}