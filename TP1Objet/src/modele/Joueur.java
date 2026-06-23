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
    public Integer[] getMouvement() {
        Integer[] mouvement = new Integer[2];

        do {
            mouvement[0] = mouvementAleatoire();
            mouvement[1] = mouvementAleatoire();
        } while (mouvement[0] == 0 && mouvement[1] == 0);

        return mouvement;
    }

    public void deplacer(Integer[] mouvement) {
        posLigne += mouvement[0];
        posColonne += mouvement[1];
    }

    private int mouvementAleatoire() {
        return (int) (Math.random() * 3) - 1;
    }



    public void deplacer(int[][] grille) {
        AnalyseurGrille analyseur = new AnalyseurGrille(grille);
        Integer[] positionActuelle = {posLigne, posColonne};
        Integer[] nouvellePosition = analyseur.meilleurDeplacement(positionActuelle);

        if (nouvellePosition != null) {
            posLigne = nouvellePosition[0];
            posColonne = nouvellePosition[1];
        }
    }
}






