package me.zeld;

import java.util.Random;
import java.util.Scanner;

public class Board {
    private final int ROWS = 6;
    private final int COLS = 7;
    private final char[][] BOARD = new char[ROWS][COLS];
    private final char defaultEmptySpace, humanToken, otherToken;
    private String player1Name, player2Name;
    private final boolean versusHuman;
    private int turn;

    public Board(boolean vsHuman) {
//        defaultEmptySpace = '■';
        //ci sono anche altre alternative per lo spazio vuoto:
        //defaultEmptySpace = '≡';
        defaultEmptySpace = ' ';
        humanToken = '●';
        otherToken = '○';
        versusHuman = vsHuman;
        turn = 0;

        startGame();
    }


    // ----------------------------- BOARD -----------------------------
    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                BOARD[i][j] = defaultEmptySpace;
            }
        }
    }

    private void printBoard() {
        System.out.println("\n┌------ FORZA QUATTRO ------┐");
        for (int i = 0; i < ROWS; i++) {
            /* per aggiungere una specie di animazione (loading...)
                try {
                    TimeUnit.MILLISECONDS.sleep(210);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            */
            System.out.print("| ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(BOARD[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("└---------------------------┘");
    }


    // ----------------------------- CHECKS -----------------------------
    private boolean insertDisc(int col) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (thereIsToken(i, col)) {
                BOARD[i][col] = turn % 2 == 0 ? otherToken : humanToken;
                return true;
            }
        }
        return false;
    }

    private boolean thereIsToken(int row, int col) {
        return BOARD[row][col] == defaultEmptySpace;
    }

    private boolean hasWon() {

        //verticale
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j] &&
                        BOARD[i][j] == BOARD[i + 2][j] &&
                        BOARD[i][j] == BOARD[i + 3][j] &&
                        !thereIsToken(i, j)) {
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
                        !thereIsToken(i, j)) {
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
                        !thereIsToken(i, j)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j + 1] &&
                        BOARD[i][j] == BOARD[i + 2][j + 2] &&
                        BOARD[i][j] == BOARD[i + 3][j + 3] &&
                        !thereIsToken(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // TODO: ----------------------------- BOT SETTING -----------------------------
    private int possibleWinPosition(char token) {
        Random r = new Random();
        //verticale
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j] &&
                        BOARD[i][j] == BOARD[i + 2][j] &&
                        !thereIsToken(i + 3, j)) {
                    return j;
                }
            }
        }
        //orizzontale
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i][j + 1] &&
                        BOARD[i][j] == BOARD[i][j + 2] &&
                        BOARD[i][j + 3] != token &&
                        !thereIsToken(i, j)) {
                    return j;
                }
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j + 1] == BOARD[i][j + 2] &&
                        BOARD[i][j + 1] == BOARD[i][j + 3] &&
                        BOARD[i][j] != token &&
                        !thereIsToken(i, j + 3)) {
                    return j + 3;
                }
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i][j + 2] &&
                        BOARD[i][j] == BOARD[i][j + 3] &&
                        BOARD[i][j + 1] != token &&
                        !thereIsToken(i, j)) {
                    return j + 1;
                }
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i][j + 1] &&
                        BOARD[i][j] == BOARD[i][j + 3] &&
                        BOARD[i][j + 2] != token &&
                        !thereIsToken(i, j)) {
                    return j + 2;
                }
            }
        }

        //diagonali
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i - 1][j + 1] &&
                        BOARD[i][j] == BOARD[i - 2][j + 2] &&
                        BOARD[i - 3][j + 3] != token &&
                        !thereIsToken(i, j)) {
                    return j + 3;
                }
            }
        }

        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (BOARD[i][j] == BOARD[i + 1][j + 1] &&
                        BOARD[i][j] == BOARD[i + 2][j + 2] &&
                        BOARD[i + 3][j + 3] != token &&
                        !thereIsToken(i, j)) {
                    return j + 3;
                }
            }
        }

        return r.nextInt(0, COLS);
    }

    public void botChoice() {
        boolean canInsert = insertDisc(possibleWinPosition(turn % 2 != 0 ? humanToken : otherToken));
        System.out.println(possibleWinPosition(turn % 2 != 0 ? humanToken : otherToken));
        while (!canInsert) {
            canInsert = insertDisc(possibleWinPosition(turn % 2 != 0 ? humanToken : otherToken));
        }
    }


// ----------------------------- USER INFO -----------------------------

    private void userChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci la colonna dove inserire il token(DA 1 A 7): ");
        int userChoice = sc.nextInt();
        while (userChoice < 1 || userChoice > 7 || !insertDisc(userChoice - 1)) {
            System.out.print("Numero inserito non valido.\nInserisci la colonna dove inserire il token(DA 1 A 7): ");
            userChoice = sc.nextInt();
        }
    }

    private void setPlayerNames() {
        Scanner sc = new Scanner(System.in);
        System.out.print(versusHuman ? "-Giocatore Uno: " : "Nome Giocatore: ");
        player1Name = sc.next();
        player1Name = player1Name.substring(0, 1).toUpperCase() + player1Name.substring(1);

        if (versusHuman) {
            System.out.print("-Giocatore Due: ");
            System.out.print("\r");
            player2Name = sc.next();
            player2Name = player2Name.substring(0, 1).toUpperCase() + player2Name.substring(1);

            while (player1Name.equals(player2Name)) {
                System.out.print("Il nome da lei inserito non è valido, lo reinserisca per piacere.\nGiocatore Due: ");
                player2Name = sc.next();
                player2Name = player2Name.substring(0, 1).toUpperCase() + player2Name.substring(1);
            }
        }
    }


    // ----------------------------- GAME -----------------------------
    public void startGame() {
        initializeBoard();
        setPlayerNames();
        printBoard();
        for (int i = 0; i < ROWS * COLS; i++) {
            turn = i + 1;
            if (versusHuman) {
                System.out.printf("Turno numero %s\n", turn);
                System.out.printf("%s (%s), tocca a te!\n", turn % 2 == 0 ? player1Name : player2Name, turn % 2 == 0 ? otherToken : humanToken);
                userChoice();
            } else {
                if (turn % 2 != 0) {
                    System.out.printf("Turno numero %s\n", turn);
                    System.out.printf("%s (%s), tocca a te!\n", player1Name, humanToken);
                    userChoice();
                    //printBoard();
                } else {
                    System.out.printf("Turno numero %s\n", turn);
                    System.out.printf("Il bot ha fatto la sua mossa(%s)!\n", otherToken);
                    botChoice();
                }
            }
            printBoard();
            if (hasWon()) {
                System.out.printf("%s hai vinto!", versusHuman ? turn % 2 == 0 ? player1Name : player2Name : turn % 2 != 0 ? player1Name : "Il bot");
                return;
            }
        }
    }
}