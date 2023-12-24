import java.util.ArrayList;
import java.util.Arrays;

public class AutoCompletion {

    public static ArrayList<ArrayList<String>> init(ArrayList<String[]> text) {
        ArrayList<ArrayList<String>> listPaire = new ArrayList<>();
        for (int i = 0; i < text.size() - 1; i++) {
            for (int j = 0; j < text.get(i).length - 1; j++) {
                ArrayList<String> paire = new ArrayList<>();
                paire.add(text.get(i)[j]);
                paire.add(text.get(i)[j + 1]);
                listPaire.add(paire);
            }
        }
        return listPaire;
    }

    public static double calculProb(int occurencePaire, int occurenceMotInit) {
        return (double) occurencePaire / occurenceMotInit;
    }

    public static int occurencesPaire(ArrayList<String> paire, ArrayList<ArrayList<String>> listePaire) {
        int compteur = 0;
        for (int i = 0; i < listePaire.size(); i++) {
            if (paire.equals(listePaire.get(i))) {
                compteur++;
            }
        }
        return compteur;
    }

    public static int occurenceMotInit(String mot, ArrayList<String[]> texteTraitee) {
        int compteur = 0;
        for (int i = 0; i < texteTraitee.size(); i++) {
            for (int j = 0; j < texteTraitee.get(i).length ; j++) {
                if (mot.equals(texteTraitee.get(i)[j])) {
                    compteur++;
                }
            }
        }
        return compteur;
    }

    public static String trouverMotSuivant(String mot, ArrayList<ArrayList<String>> listePaire, ArrayList<String[]> texteTraitee) {
        ArrayList<String> paire = new ArrayList<>();
        paire.add(mot);

        // Recherche des occurrences de la paire
        int occurencesPaire = occurencesPaire(paire, listePaire);

        // Recherche des occurrences du mot initial
        int occurenceMotInit = occurenceMotInit(mot, texteTraitee);

        // Recherche du mot suivant le plus probable
        double maxProb = 0;
        String motSuivant = null;

        for (ArrayList<String> candidatPaire : listePaire) {
            if (candidatPaire.get(0).equals(mot)) {
                double prob = calculProb(occurencesPaire(candidatPaire, listePaire), occurenceMotInit);
                if (prob > maxProb || (prob == maxProb && candidatPaire.get(1).compareTo(motSuivant) < 0)) {
                    maxProb = prob;
                    motSuivant = candidatPaire.get(1);
                }
            }
        }

        return motSuivant;
    }

}
