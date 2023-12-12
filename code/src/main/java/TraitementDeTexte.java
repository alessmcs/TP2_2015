
import java.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import java.util.*;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;

import static edu.stanford.nlp.util.StringUtils.editDistance;

public class TraitementDeTexte {

    public static ArrayList<String[]> traiterText(String dir) throws IOException {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String[]>  texteTraitee = new ArrayList<>();

        for (File file : listOfFiles)
        {
            if(file.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(new File(dir + "/" + file.getName())));
                StringBuffer word = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    String newline = line.replaceAll("[^’'a-zA-Z0-9]", " ");
                    String finalline = newline.replaceAll("\\s+", " ").trim();
                    Properties props = new Properties();
                    props.setProperty("annotators", "tokenize,pos,lemma");
                    props.setProperty("coref.algorithm", "neural");
                    //RedwoodConfiguration.current().clear().apply();
                    StanfordCoreNLP pipeline = new StanfordCoreNLP(props); // create a document object

                    CoreDocument document = new CoreDocument(finalline); // annnotate the document

                    pipeline.annotate(document);
                    for (CoreLabel tok : document.tokens()) {
                        String str = String.valueOf(tok.lemma());
                        if (!(str.contains("'s") || str.contains("’s"))) {
                            word.append(str).append(" ");
                        }

                    }
                }
                String str = String.valueOf(word);
                str = str.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s+", " ").trim();

                texteTraitee.add(str.split(" "));
            }
        }
        return texteTraitee;
    }

    public static ArrayList<String> nomFichier(String dir) throws IOException {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> noms = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                noms.add(file.getName());
            }
        }
        return noms;
    }

    public static String[] correction(String[] mots){
        String[] tabFaux = mots;
        int i = 0;
        // Create a sortedTableMap
        for (String mot : tabFaux) {
            SortedTableMap<Integer, ArrayList<String>> mapsIndices = new SortedTableMap<>();
            for (String cle : Main.myMap.keySet() ){
                // pour chaque mot, calculer la distance de levenshtein et le mettre dans la liste
                int dist = editDistance(mot, cle); // utiliser la méthode intégrée dans CoreNLP
                boolean contains = false;
                contains = mapsIndices.containsKeyDirectly(dist);
                if(contains){
                    mapsIndices.get(dist).add(cle);
                } else {
                    mapsIndices.put(dist, new ArrayList<>());
                    mapsIndices.get(dist).add(cle);

                }
            }
            // prendre le premier (smallest) elem des indices
            ArrayList<String> index = mapsIndices.firstEntry().getValue(); // arraylist des mots
            index.sort(Comparator.naturalOrder()); // sort the inner arrayList
            String remplacement = index.get(0); // closest word to the misspelled word
            tabFaux[i] = remplacement;
            i++;
        }
        return tabFaux; // retourner le tableau de mots corrigés
    }
}