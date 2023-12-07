
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
// set up pipeline properties
                    Properties props = new Properties();
// set the list of annotators to run
                    props.setProperty("annotators", "tokenize,pos,lemma");
// set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
                    props.setProperty("coref.algorithm", "neural");
// build pipeline
                    StanfordCoreNLP pipeline = new StanfordCoreNLP(props); // create a document object

                    CoreDocument document = new CoreDocument(finalline); // annnotate the document

                    pipeline.annotate(document);
//System.out.println(document.tokens());
                    for (CoreLabel tok : document.tokens()) {
//System.out.println(String.format("%s\t%s", tok.word(), tok.lemma()));
                        String str = String.valueOf(tok.lemma());
                        if (!(str.contains("'s") || str.contains("’s"))) {
                            word.append(str).append(" ");
                        }

                    }
                }
                String str = String.valueOf(word);
                str = str.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s+", " ").trim();
/* now str is a string which has the content of the read file
                        but it is processed and their words are space-separated. However there maybe
                        some details which has not been cleaned very well, just follow these steps to
                        clean the text.
*/
                //in the following you can continue your own implementation
                texteTraitee.add(str.split(" "));
                /*for(String [] string : texteTraitee){
                    System.out.println(Arrays.toString(string));
                    System.out.println();
                }

                 */

            }
        }
        return texteTraitee;
    }

    public void distanceLevenshtein(String fichier){

    }

    public static void  correction(String line){
        ArrayList<String> tabFaux = new ArrayList<>();
        String unMot = Arrays.toString(line.split(""));
        tabFaux.add(unMot);
        for (String mot : tabFaux) {

        }
    }


}