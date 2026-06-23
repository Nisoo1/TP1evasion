
package controleur;

import modele.Jeu;
import vue.GUI;

public class Application
{

    public static void main(String[] args) throws InterruptedException
    {
        Jeu modele = new Jeu();
        GUI vue    = new GUI();

      /*  vue.mettre_a_jours(modele.getPositionMurs(),modele.getPositionEnnemi());
        Thread.sleep(800);

        modele.deplacerEnnemi();
*/
        while (modele.deplacementEstPossible()){
    vue.mettre_a_jours(modele.getPositionMurs(), modele.getPositionEnnemi());

    if (vue.clicDoitEtreGerer()) {

        Integer[] caseChoisie = vue.getIndicieCaseChoisie();
        int ligne = caseChoisie[0];
        int colonne = caseChoisie[1];

        boolean mur_ajoute = modele.ajouterMur(ligne, colonne);

        if (mur_ajoute) {
            vue.mettre_a_jours(modele.getPositionMurs(), modele.getPositionEnnemi());
            Thread.sleep(200);

            if (modele.deplacementEstPossible()) {
                modele.deplacerEnnemi();
                vue.mettre_a_jours(modele.getPositionMurs(), modele.getPositionEnnemi());
                Thread.sleep(200);
            }
        }
    }
    }
        vue.afficherGagnant(modele.getGagnant());
}
}
