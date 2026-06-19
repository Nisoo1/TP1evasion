
package controleur;

import modele.Jeu;
import vue.GUI;

public class Application
{

    public static void main(String[] args) throws InterruptedException
    {
        Jeu modele = new Jeu();
        GUI vue    = new GUI();

        vue.mettre_a_jours(modele.getPositionMurs(),modele.getPositionEnnemi());
        Thread.sleep(600);

        modele.deplacerEnnemi();

        while (true){
    vue.mettre_a_jours(modele.getPositionMurs(), modele.getPositionEnnemi());





    if (vue.clicDoitEtreGerer()) {

        Integer[] caseChoisie = vue.getIndicieCaseChoisie();
        int ligne = caseChoisie[0];
        int colonne = caseChoisie[1];

        modele.ajouterMur(ligne, colonne);

        vue.mettre_a_jours(modele.getPositionMurs(),modele.getPositionEnnemi());
        Thread.sleep(200);

        modele.deplacerEnnemi();
        vue.mettre_a_jours(modele.getPositionMurs(),modele.getPositionEnnemi());
        Thread.sleep(200);

    }

    }
}
}
