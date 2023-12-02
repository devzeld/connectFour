package me.zeld;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Board {
    private final int ROWS = 6;
    private final int COLS = 7;
    private int turn = 0;
    private final Discs discs = new Discs();
    private final Discs[][] BOARD = new Discs[ROWS][COLS];

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                BOARD[i][j] = new Discs();
            }
        }
    }
    private void printBoard(){
        System.out.println("_____________________________");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            try {
                TimeUnit.MILLISECONDS.sleep(210);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int j = 0; j < COLS; j++) {
                System.out.print(BOARD[i][j].getCell() + " | ");
            }
            System.out.println();
        }
    }

    private void insertDisc(int col){
        for (int i = ROWS - 1; i >= 0; i--) {
            if(BOARD[i][col].getCell() == discs.getDefaultEmptySpace()){
                BOARD[i][col] = new Discs(turn % 2 == 0);
                break;
            }
        }
    }

    private boolean winCondition(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (BOARD[i][j].getCell() != discs.getDefaultEmptySpace()) {
                    if (BOARD[i][j].getCell() == BOARD[i][j + 1].getCell() &&
                            BOARD[i][j].getCell() == BOARD[i][j + 2].getCell() &&
                            BOARD[i][j].getCell() == BOARD[i][j + 3].getCell()) {
                        return true;
                    }
                    if (BOARD[i][j].getCell() == BOARD[i + 1][j].getCell() &&
                            BOARD[i][j].getCell() == BOARD[i + 2][j].getCell() &&
                            BOARD[i][j].getCell() == BOARD[i + 3][j].getCell()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void getUserChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci la colonna dove inserire il token(DA 1 A 7): ");
        int userChoice = sc.nextInt();
        insertDisc(userChoice - 1);
    }

    public void startGame(){
        initializeBoard();
        System.out.println("------- FORZA QUATTRO -------");
        printBoard();

        for (int i = 0; i < ROWS * COLS; i++) {
            turn = i + 1;
            System.out.printf("turn n.%s\n", turn);

            getUserChoice();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r");
            printBoard();
            if (winCondition()) {
                System.out.println("Qualcuno ha vinato");
                break;
            }
        }
    }

    public Board(boolean vsHuman) {
        startGame();
    }

}
