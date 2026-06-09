
package controleur;

import modele.Jeu;
import vue.GUI;

public class Application
{

    public static void main(String[] args) throws InterruptedException
    {
        Jeu modele = new Jeu();
        GUI vue    = new GUI();

       while (true){
    vue.mettre_a_jours(modele.getPositionMurs(), modele.getPositionEnnemi());

    modele.deplacerEnnemi();

    Thread.sleep(1000);
    }
}
}
