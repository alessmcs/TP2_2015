import java.util.ArrayList;

public class AutoCompletion {

    //ChainHashMap<Integer, ArrayList<String>> mapPaires = new ChainHashMap<>();
    public static ArrayList<ArrayList<String>> init(String[] text) {
        ArrayList<ArrayList<String>> listPaire = new ArrayList<>();
        for (int i = 0; i < text.length - 1; i++) {
            ArrayList<String> paire = new ArrayList<>();
            paire.add(text[i]);
            paire.add(text[i+1]);
            listPaire.add(paire);
        }
        return listPaire;
    }

    public static double calculProb(int occurencePaire, int occurenceMotInit) {
        return (double) occurencePaire / occurenceMotInit;
    }

    public static int occurencesPaire(ArrayList<String> paire, ArrayList<ArrayList<String>> listePaire ) {
        int compteur = 0;
        for (int i = 0; i < listePaire.size() - 1; i++) {
            if (paire.equals(listePaire.get(i))) {
                compteur += 1;
            }
        }
        return compteur;
    }

    public static int occurenceMotInit(String mot, String[] texteTraitee) {
        int compteur = 0;
        for (int i = 0; i < texteTraitee.length; i++) {
            if (mot.equals(texteTraitee[i])) {
                compteur += 1;
            }
        }
        return compteur;
    }



}