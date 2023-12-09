
import java.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import java.util.*;

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

    public void distanceLevenshtein(String mot, String cible){

    }

    public static void  correction(String mots){
        String[] tabFaux = mots.split(" "); //<word1><space><word2><space>....
        for (String mot : tabFaux) {
            // for each word, calcule distance de levenstein
            // keep the words with the smallest value & trie en ordre lexicographique (arraylist)
            Map<String, Integer> myMap = new ChainHashMap<>();


        }
    }


}