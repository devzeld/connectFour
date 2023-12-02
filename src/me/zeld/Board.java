package me.zeld;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Board {
    private final int ROWS = 6;
    private final int COLS = 7;
    private final char[][] BOARD = new char[ROWS][COLS];
    private final char defaultEmptySpace, blueToken, redToken;
    private String player1Name, player2Name;
    private int turn = 0;

    public Board(boolean vsHuman) {
        defaultEmptySpace = '□';
        blueToken = '●';
        redToken = '○';

        startGame();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                BOARD[i][j] = defaultEmptySpace;
            }
        }
    }

    private void printBoard() {
        System.out.println("┌------ FORZA QUATTRO ------┐");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            /* per aggiungere una specie di caricamento (animazione)
            try {
                TimeUnit.MILLISECONDS.sleep(210);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            */
            for (int j = 0; j < COLS; j++) {
                System.out.print(BOARD[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("└---------------------------┘");
    }

    private void insertDisc(int col) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (BOARD[i][col] == defaultEmptySpace) {
                BOARD[i][col] = turn % 2 == 0 ? redToken : blueToken;
                break;
            }
        }
    }

    private boolean winCondition() {
        //verticale
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j] &&
                        BOARD[i][j] == BOARD[i + 2][j] &&
                        BOARD[i][j] == BOARD[i + 3][j] &&
                        BOARD[i][j] != defaultEmptySpace) {
                    return true;
                }
            }
        }
        //orizzontale
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i][j + 1] &&
                        BOARD[i][j] == BOARD[i][j + 2] &&
                        BOARD[i][j] == BOARD[i][j + 3] &&
                        BOARD[i][j] != defaultEmptySpace) {
                    return true;
                }
            }
        }
        //diagonali
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i - 1][j + 1] &&
                        BOARD[i][j] == BOARD[i - 2][j + 2] &&
                        BOARD[i][j] == BOARD[i - 3][j + 3] &&
                        BOARD[i][j] != defaultEmptySpace) {
                    return true;
                }
            }
        }
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j + 1] &&
                        BOARD[i][j] == BOARD[i + 2][j + 2] &&
                        BOARD[i][j] == BOARD[i + 3][j + 3] &&
                        BOARD[i][j] != defaultEmptySpace) {
                    return true;
                }
            }
        }
        return false;
    }

    private void userChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci la colonna dove inserire il token(DA 1 A 7): ");
        int userChoice = sc.nextInt();
        while (userChoice < 1 || userChoice > 7) {
            userChoice = sc.nextInt();
        }
        insertDisc(userChoice - 1);
    }

    private void botChoice() {
        //insertDisc();
    }

    private void setPlayerNames() {
        Scanner scStr = new Scanner(System.in);
        System.out.print("-Giocatore Uno: ");
        player1Name = scStr.next();
        if (player1Name.charAt(0) >= 97 && player1Name.charAt(0) <= 122) {
            player1Name = player1Name.replace(player1Name.charAt(0), (char) ((char) (int) player1Name.charAt(0) - 32));
        }
        System.out.print("-Giocatore Due: ");
        player2Name = scStr.next();
        if (player2Name.charAt(0) >= 97 && player2Name.charAt(0) <= 122) {
            player2Name = player2Name.replace(player2Name.charAt(0), (char) ((char) (int) player2Name.charAt(0) - 32));
        }
        while (player1Name.equals(player2Name)) {
            System.out.print("-Giocatore Due: ");
            player2Name = scStr.next();
            if (player2Name.charAt(0) >= 97 && player2Name.charAt(0) <= 122) {
                player2Name = player2Name.replace(player2Name.charAt(0), (char) ((char) (int) player2Name.charAt(0) - 32));
            }
        }
    }

    public void startGame() {
        initializeBoard();
        setPlayerNames();
        printBoard();
        for (int i = 0; i < ROWS * COLS; i++) {
            turn = i + 1;
            System.out.printf("Turno numero %s\n", turn);
            System.out.printf("%s (%s), tocca a te!\n", turn % 2 == 0 ? player1Name : player2Name, turn % 2 == 0 ? redToken : blueToken);
            userChoice();
            try {
                TimeUnit.MILLISECONDS.sleep(60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r");
            printBoard();
            if (winCondition()) {
                System.out.printf("%s hai vinto!", turn % 2 == 0 ? player1Name : player2Name);
                break;
            }
        }
    }

}
