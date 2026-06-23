package modele;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AnalyseurGrille {

    private int[][] grilleJeu;
    private int[][] grilleDistances;

    public AnalyseurGrille  (int[][] grille) {
        this.grilleJeu = grille;
        this.grilleDistances = new int[grille.length][grille[0].length];

        for (int i = 0; i < grilleDistances.length; i++) {
            for (int j = 0; j < grilleDistances[i].length; j++) {
                grilleDistances[i][j] = Integer.MAX_VALUE;
            }
        }
    }



public Integer[] meilleurDeplacement(Integer[] positionEnnemi) {
    calculerDistanceVoisin(positionEnnemi);
    System.out.println(this.toString());
    return null;
}



@Override
public String toString() {
    String resultat = "";
    for (int i = 0; i < grilleDistances.length; i++) {
        for (int j = 0; j < grilleDistances[i].length; j++) {
            if (grilleDistances[i][j] == Integer.MAX_VALUE) {
                resultat += " Max";
            } else {
                resultat += String.format(" %3d", grilleDistances[i][j]);
            }
        }
        resultat += "\n";
    }
    return resultat;
}



private class ComparateurPosition implements Comparator<Integer[]> {
    @Override
    public int compare(Integer[] position1, Integer[] position2) {
        int d1 = grilleDistances[position1[0]][position1[1]];
        int d2 = grilleDistances[position2[0]][position2[1]];

        if (d1 < d2) return -1;
        if (d1 == d2) return 0;
        return 1;
    }
}



private void calculerDistanceVoisin(Integer[] positionEnnemi) {

    for (int i = 0; i < grilleDistances.length; i++)
        for (int j = 0; j < grilleDistances[i].length; j++)
            grilleDistances[i][j] = Integer.MAX_VALUE;

    int ligneEnnemi = positionEnnemi[0];
    int colonneEnnemi = positionEnnemi[1];

    grilleDistances[ligneEnnemi][colonneEnnemi] = 0;


    PriorityQueue<Integer[]> file = new PriorityQueue<>(new ComparateurPosition());


    file.add(new Integer[]{ligneEnnemi, colonneEnnemi, 0});

    while (!file.isEmpty()) {
        Integer[] courant = file.poll();
        int ligne = courant[0];
        int colonne = courant[1];


        for (int dl = -1; dl <= 1; dl++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dl == 0 && dc == 0) continue;
                int vl = ligne + dl;
                int vc = colonne + dc;

                if (vl < 0 || vl >= grilleJeu.length) continue;
                if (vc < 0 || vc >= grilleJeu[0].length) continue;

                if (grilleJeu[vl][vc] == 0) {
                    int distance = grilleDistances[ligne][colonne] + 1;

                    if (distance < grilleDistances[vl][vc]) {
                        grilleDistances[vl][vc] = distance;
                        file.add(new Integer[]{vl, vc, distance});
                    }
                }
            }
        }
    }
}
}