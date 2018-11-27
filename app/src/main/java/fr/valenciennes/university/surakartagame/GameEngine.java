package fr.valenciennes.university.surakartagame;

import android.view.View;

import java.util.Random;

public class GameEngine {
    private static final Random RANDOM = new Random();
    public char[] elts;
    public char currentPlayer;
    public boolean ended;
    public int[] select;

    public GameEngine() {
        elts = new char[36];
        select = new int[2];
        newGame();
    }

    //** Demande si le jeu est fini **//

    public boolean isEnded() {
        return ended;
    }

    //** Lance le jeu **//

    public void play(int x, int y){

        if(!ended && elts[6 * y + x] == ' '){
            elts[6 * y +x] = currentPlayer;
            changePlayer();
        }
    }

    //** Changement de joueur **//

    private void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }


    public char elt(int x, int y) {
        return elts[6 * y + x];
    }

    //** Création d'une nouvelle partie **//

    public void newGame() {


        for (int i = 0; i < 12; i++) {
            elts[i] = 'X';
        }
        for (int j = (elts.length - 1); j >= (elts.length - 12); j--) {
            elts[j] = 'O';

        }
        for(int k=12; k<=elts.length-13;k++){
            elts[k] = ' ';
        }

        currentPlayer = 'X';
        ended = false;
    }

    //** Vérification de la fin du jeu **//

    public boolean checkEnd() {

        int countX=0;
        int countY=0;

        for (int i = 0; i < elts.length-1; i++) {
            if ((elts[i] == 'O')) {
                countX++;
            } else {
                countY++;
            }
        }

        if(countX>=1 && countY==0){
            ended = true;
            return true;                    //Partie finie
        }
        else if(countX==0 && countY>=1) {
            ended=true;
            return true;                    //Partie finie
        }
        else{
            ended=false;
            return false;                   // La partie continue
        }

    }

    //**Vérification de l'emplacement **//

    public boolean isFree(int x, int y) {
        //Renvoie vrai si l'empacement est libre
        return (elt(x, y) == ' ' ? true : false);
    }

    //** Verifie si la case selectionner est du bon type  **//

    public boolean isSelectionOk(int x, int y) {
        // Renvoie faux si la case selectionner est à l'adverssaire
        return (elt(x, y) == currentPlayer) ? true : false;
    }

    //** Déplace un pion sans manger **//

    public void Deplacement(int px, int py, int x, int y) {
        play(x,y);                              // px py position du pion a déplacer
        elts[6 * py + px] = ' ';                // On vide la case desormais vide
    }

    public boolean DeplacementPossible(int px, int py, int x, int y){
        if((px-1 == x ) && (py-1 == y)){
            return true;
        }else if((px-1 == x ) && (py == y)) {
            return true;
        } else if((px-1 == x ) && (py+1 == y)) {
            return true;
        }else if((px == x ) && (py-1 == y)) {
            return true;
        }else if((px == x ) && (py+1 == y)) {
            return true;
        }else if((px+1 == x ) && (py-1 == y)) {
            return true;
        }else if((px+1 == x ) && (py == y)) {
            return true;
        }else if((px+1 == x ) && (py+1 == y)) {
            return true;
        }else{return false;}
    }

    //** Verifie si on peu manger le pion **//

    /*public boolean isPriseOk(int pi, int py, int x, int y) {

    }


    // verifie si une prise d'un pion est possible de (pi,pj) vers (i,j) en commencant par la droite ou la gauche
    public boolean chercherLateralement(int px, int py, int x, int y, boolean droite) {

    }

    //verifie si une prise d'un pion est possible de (pi,pj) vers (i,j) en commencant par le haut ou lebas
    public boolean chercherVerticalement(int px, int py, int x, int y, boolean haut) {

    }

    // verifie si (pi, pj) est aligne sur (i,j)
    public boolean trouverCible(int px, int py, int x int y, boolean colonne) {

    }*/

    // Effectue une prise
    public void Prise(int px, int py, int x, int y){
    elts[6*py+px]=elts[6*y+x];    // px py position du pion a déplacer
    elts[6*py+px]=' ';              // On vide la case desormais vide
    }

    //**    Computer play   **//
    //**    Launch IA       **//

    public boolean computer() {
        if (!ended) {
            int position = -1;
            do {
                position = RANDOM.nextInt(36);
            } while (elts[position] != ' ');
            elts[position] = currentPlayer;
            changePlayer();
        }
        return checkEnd();
    }


}
