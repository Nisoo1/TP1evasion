package modele;
import java.util.ArrayList;


public class Jeu
{
    private final int posLigne = 13;
    private final int posColonne = 13;
    int[][] grille = new int[posLigne][posColonne];
    Joueur joueurEnnemi;

    public Jeu()
    {
        grille = new int[13][13];
        joueurEnnemi = new Joueur(6, 6);
        placerMurs();
    }
    public Integer[] getPositionEnnemi() {
        Integer[] posJoueur = {joueurEnnemi.getLigne(), joueurEnnemi.getColonne()};
        return posJoueur;
    }

    private void placerMurs() {
        int nbMursAPlacer = nombreAleatoire(grille.length * 2, grille.length * 3 + 1);
        int nbMursPlaces = 0;

        while (nbMursPlaces < nbMursAPlacer) {
            int ligne = nombreAleatoire(0, grille.length);
            int colonne = nombreAleatoire(0, grille[0].length);

            try {
                verifierMurs(ligne, colonne);
                grille[ligne][colonne] = 1;
                nbMursPlaces++;
            } catch (PositionInvalideException e) {
            }
        }
    }
    private void verifierMurs(int ligne, int colonne) throws PositionInvalideException {
        if (ligne == joueurEnnemi.getLigne() && colonne == joueurEnnemi.getColonne()) {
            throw new PositionInvalideException(
                    "Impossible de mettre un mur sur la case ou se situe l'ennemi."
            );
        }

        if (grille[ligne][colonne] == 1) {
            throw new PositionInvalideException(
                    "Impossible de mettre un mur sur une case etant déjà un mur."
            );
        }
    }
    public ArrayList<Integer[]> getPositionMurs() {
        ArrayList<Integer[]> positions = new ArrayList<>();

        for (int ligne = 0; ligne < grille.length; ligne++) {
            for (int colonne = 0; colonne < grille[ligne].length; colonne++) {
                if (grille[ligne][colonne] == 1) {
                    Integer[] position = new Integer[2];
                    position[0] = ligne;
                    position[1] = colonne;
                    positions.add(position);
                }
            }
        }

        return positions;
    }
    private void verifierMouvementEnnemi(Integer[] mouvement) throws PositionInvalideException {
        int nouvelleLigne = joueurEnnemi.getLigne() + mouvement[0];
        int nouvelleColonne = joueurEnnemi.getColonne() + mouvement[1];

        if (grille[nouvelleLigne][nouvelleColonne] == 1) {
            throw new PositionInvalideException(
                    "Impossible de déplacer l'ennemi sur un mur"
            );
        }
    }

    public void deplacerEnnemi() {
        boolean mouvementValide = false;

        while (!mouvementValide) {
            try {
                Integer[] mouvement = joueurEnnemi.getMouvement();
                verifierMouvementEnnemi(mouvement);
                joueurEnnemi.deplacer(mouvement);

                System.out.printf("Deplacement :: succes {%2d, %2d}\n",
                        joueurEnnemi.getLigne(), joueurEnnemi.getColonne());

                mouvementValide = true;

            } catch (PositionInvalideException e) {
            }
        }
    }

    /**
     * Retourne un entier choisit aléatoirement entre min et max.
     *
     * @param min   La plus petite valeur aléatoire pouvant être générée.
     * @param max   La plus petite valeur aléatoire pouvant être générée.
     *
     * @return Un nombre aléatoire entre min et max.
     */
    private int nombreAleatoire(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private class PositionInvalideException extends Exception {
        public PositionInvalideException(String message) {
            super(message);
        }

    }
}
