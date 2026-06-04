package modele;

public class Joueur {
    private int posLigne;
    private int posColonne;


    public Joueur (int ligne, int colonne) {

        this.posLigne = ligne;
        this.posColonne = colonne;
    }



    public int getLigne() {
        return posLigne;
    }

    public int getColonne() {
        return posColonne;
    }


}