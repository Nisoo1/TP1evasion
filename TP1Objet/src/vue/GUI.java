/**
 * Cette classe permet d'afficher l'état actuel du jeu.
 * Elle permet également de connaître sur quel case l'utilisateur choisie de jouer son prochain coup.
 *
 * @author Francis Bourdeau
 * @version 1er juillet 2022
 *
 */
package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame
{
    //L'endroit dans la grille où l'utilisateur a décidé de jouer.
    private int ligneChoisie  = 0;
    private int colonneChoisie= 0;

    //Les références sur les boutons qui forme la grille de jeu.
    private Case[][] grille;

    //Permet de définir si une nouvelle action de souris a été faite.
    private boolean nouveauClic = false;

    //Permet d'effacer l'ancienne position de l'ennemi.
    Integer[] anciennePosition = null;



    /**
     * Constructeur qui crée l'interface graphique, aucune donnée n'est affichée, on ne fait que
     * préparer la fenêtre.
     *
     */
    public GUI()
    {
        // On gère comment la fenêtre ce comporte lorsque l'on click sur X.
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On définie l'apparence de la fenêtre
        super.setTitle("INF111 TP2 :: Evasion");
        super.setSize(800,800);

        //On crée les boutons de la planche de jeu
        this.initComponent();

        //On affihce l'interface graphique.
        super.setVisible(true);
    }


    /**
     * Crée les différents éléments visuels de l'interface graphique.
     *
     */
    private void initComponent()
    {
        //La taille de la planche de jeu
        final int nbLignes      = 13;
        final int nbColonnes    = 13;

        //On définie le layout du panneau.
        JPanel panneauPrincipal = (JPanel) super.getContentPane();
        panneauPrincipal.setLayout(new GridLayout(nbLignes, nbColonnes));

        //On crée les boutons formant la grille.
        //Et on les ajoutes au panneau du GUI.
        this.grille = new Case[nbLignes][nbColonnes];
        for(int i = 0; i < nbLignes; i++)
        {
            for (int j = 0; j < nbColonnes; j++)
            {
                this.grille[i][j] = new Case(i,j);
                panneauPrincipal.add(this.grille[i][j]);
            }
        }
    }


    /**
     * Permet de savoir si un clic de souris a eu lieu ou non.
     *
     * @return True  lorsqu'un nouveau clic de souris est effectuée.
     *         False sinon.
     */
    public boolean clicDoitEtreGerer()
    {
        return this.nouveauClic;
    }


    /**
     * Retourne les indices de la case sur laquelle l'utilisateur a appuyée.
     *
     * @return Un tableau de deux éléments dans lequel la 1ere valeur représente le no de
     *         ligne de la case sur lequel l'utilisateur a appuyé et dans lequel la 2e
     *         valeur représente le no de colonne de la case sur lequel l'utilisateur
     *         a appuyé.
     */
    public Integer[] getIndicieCaseChoisie()
    {
        nouveauClic = false;
        return new Integer[]{this.ligneChoisie, this.colonneChoisie};
    }


    /**
     * Affiche l'état du jeu en attribuant une couleur aux différentes éléments du jeu.
     *
     * @param   positionMurs   : Une liste contenant le no de ligne et de colonne de chaque murs.
     *
     * @param   positionEnnemi : Un tableau 1D contenant le no de ligne et de colonne où
     *                           est situé l'ennemi.
     */
    public void mettre_a_jours(ArrayList<Integer[]> positionMurs,
                               Integer[] positionEnnemi)
    {
        //On place chaque mur
        for(Integer[] murs : positionMurs)
        {
            int i = murs[0];
            int j = murs[1];
            this.grille[i][j].setBackground(Color.DARK_GRAY);
        }

        //On place l'ennemi
        int i = positionEnnemi[0];
        int j = positionEnnemi[1];
        this.grille[i][j].setBackground(Color.BLUE);

        //On efface l'ancienne position de l'ennemi
        if (this.anciennePosition != null &&
          !(this.anciennePosition[0] == i && this.anciennePosition[1] == j))
        {
            int a = this.anciennePosition[0];
            int b = this.anciennePosition[1];
            this.grille[a][b].setBackground(Color.LIGHT_GRAY);
        }

        //On conserve l'ancienne position pour la réeffacer lors d'un prochain
        //mouvement.
        this.anciennePosition = positionEnnemi;

    }


    /**
     * Affiche qui gagne le jeu
     *
     * @param   noJoueur   : 1 si l'utilisateur gagne, 0 si l'ordinateur gagne.
     *
     */
    public void afficherGagnant(int noJoueur)
    {
        if(noJoueur == 1)
            JOptionPane.showMessageDialog(null, "Bravo! L'ennemi est entourer. Vous gagnez.");

        else
            JOptionPane.showMessageDialog(null, "Dommage! L'ennemi s'est enfui. Vous perdez.", "Echec",
                                          JOptionPane.ERROR_MESSAGE);

    }


    /**
     * Cette classe représente l'une des cases de la grille de jeu.
     *
     * @author Francis Bourdeau
     * @version 1er juillet 2022
     *
     */
    private class Case extends JButton
    {
        //Les indices dans la grille où la case actuelle est située.
        int noLigne;
        int noColonne;


        /**
         * Constructeur qui crée le bouton sur lequel l'utilisateur peut appuyer.
         *
         * @param  noLigne   :   La ligne où est situé le bouton dans la grille.
         * @param  noColonne :   La colonne où est situé le bouton dans la grille.
         *
         */
        public Case(int noLigne, int noColonne){

            //On mémorise où cette case sera située.
            this.noLigne = noLigne;
            this.noColonne = noColonne;

            //On défini l'apparence de la case.
            super.setBackground(Color.LIGHT_GRAY);
            super.setBorder(BorderFactory.createLineBorder(Color.black));

            //On défini l'action effectué par le programme lorsque l'on appuie sur la case.
            super.addActionListener(new ActionBoutonPeser());
        }


        /**
         * Cette classe défini le comportement d'un bouton de la gille de jeu lorsque l'on appuie sur cette
         * case.
         *
         * @author Francis Bourdeau
         * @version 1er juillet 2022
         *
         */
        private class ActionBoutonPeser implements ActionListener
        {
            /**
             * On note qu'un nouveau clic de souris est survenu et l'on conserve sur quelle case il a eu lieu.
             */
            public void actionPerformed(ActionEvent event) {
                nouveauClic = true;

                ligneChoisie = noLigne;
                colonneChoisie = noColonne;
            }
        }
    }
}

