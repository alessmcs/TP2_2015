
import edu.stanford.nlp.ling.Word;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
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

        // test sur le premier fichier 900.txt
        for(String mot: texteTraitee.get(0)){
            System.out.println(mot + ": " + WordMap.hashValue(mot));
        }

        // to map the values, create key/value pairs and THEN put them in the maps
    }

}