import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static WordMap<String, FileMaps<String, ArrayList<Integer>>> myMap = new WordMap<>(331, 57);


    public static void main(String[] args) throws IOException {

        ArrayList<String[]> texteTraitee = new ArrayList<>();
        ArrayList<String> nomsFichiers = new ArrayList<>();

        texteTraitee = TraitementDeTexte.traiterText("src/main/dataset");
        nomsFichiers = TraitementDeTexte.nomFichier("src/main/dataset");

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

        Search.setNomsFichiers(nomsFichiers);
        TF_IDF.setListes(nomsFichiers, texteTraitee);

        PrintWriter writer = new PrintWriter("src/main/solution.txt");
        writer.print("");
        writer.close();

        BufferedReader br = new BufferedReader(new FileReader("src/main/query.txt"));
        FileWriter fw = new FileWriter("src/main/solution.txt", true);
        String line;

        // effacer le contenu existant de solution.txt
        PrintWriter writer2 = new PrintWriter("src/main/solution.txt");
        writer.print("");
        writer.close();

        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split(" ");
            if (splitLine[0].equals("search")) {
                String[] tabMots = Arrays.copyOfRange(splitLine, 1, splitLine.length);
                String[] tabCorrige = TraitementDeTexte.correction(tabMots);
                String file = Search.search(tabCorrige);
                fw.write(file + "\n");
                fw.flush();
            } else {
                String mot = splitLine[5];
                ArrayList<ArrayList<String>> listePaire = AutoCompletion.init(texteTraitee);
                String motSuivant = AutoCompletion.trouverMotSuivant(mot, listePaire, texteTraitee);
                if (motSuivant != null) {
                    String result = "the most probable bigram of " + mot + " is " + motSuivant + "\n";
                    System.out.println(result);
                    fw.write(result + "\n");
                    fw.flush();
                }
            }
        }
        fw.close();
    }

}