package Game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
// • ◦ · ♥

public class Game {

    public static void main(String[] args) throws IOException {
        int saves = 0;
        System.out.println("Welcome to the Mine Field, mines(X) are hidden, you can move horizontally and vertically, the field(·) is 15m x 100m, mines(X)/safe titles(◦) are revealed when explored.");
        System.out.println("Reach the other side to win while saving as many Survivors(♥) as possible, a small amount of survivors may also be mines");
        Random rand = new Random();
        String[][][] board = new String[15][100][3];
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 100; x++) {
                int r2 = rand.nextInt(100);
                if (r2 == 1) {
                    board[y][x][0] = ("♥ ");
                } else {
                    board[y][x][0] = ("· ");
                }
            }
        }
        board[7][0][0] = "• ";
        printboard(board, saves);
    }

    public static void printboard(String[][][] board, int saves) throws IOException {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        for (int y = 0; y < 15; y++) {
            System.out.print("|");
            for (int x = 0; x < 100; x++) {

                if (((board[y][x][0]) == "· ") || ((board[y][x][0]) == "• ") || ((board[y][x][0]) == "♥ ")) {
                    System.out.print((board[y][x][0]));
                } else {
                    if (((board[y][x][1]) == "◦ ")) {
                        System.out.print((board[y][x][1]));
                    } else {
                        if (((board[y][x][2]) == "X ")) {
                            System.out.print((board[y][x][2]));
                        }
                    }
                }
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        System.out.println("");
        move(board, saves);
    }

    public static void move(String[][][] board, int saves) throws IOException {
        int a = 0;
        int b = 0;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 100; j++) {
                if ((board[i][j][0] == "• ")) {
                    a = i;
                    b = j;
                    break;
                }
            }
        }
        choosing(board, a, b, saves);
    }

    public static void choosing(String[][][] board, int a, int b, int saves) throws IOException {
        System.out.println("Move w(up), a(left), s(down), d(right)");
        Scanner input = new Scanner(System.in);
        String choose = input.next();
        if (("w".equals(choose)) || ("a".equals(choose)) || ("s".equals(choose)) || ("d".equals(choose))) {
            moving(choose, board, a, b, saves);
        } else {
            move(board, saves);
        }
    }

    public static void moving(String choose, String[][][] board, int a, int b, int saves) throws IOException {
        System.out.println("Current Positon: " + (b + 1) + "," + (a + 1) + "    Survivors saved:" + saves);
        switch (choose) {
            case ("w"):
                board[a][b][1] = "◦ ";
                board[a][b][0] = "";
                a = a - 1;
                if ((a > 15) || (a < 0) || (b > 99) || (b < 0)) {
                    choosing(board, (a + 1), b, saves);
                } else {
                    Check(board, a, b, saves);
                }
                break;
            case ("a"):
                board[a][b][1] = "◦ ";
                board[a][b][0] = "";
                b = b - 1;
                if ((a > 15) || (a < 0) || (b > 99) || (b < 0)) {
                    choosing(board, a, (b + 1), saves);
                } else {
                    Check(board, a, b, saves);
                }
                break;
            case ("s"):
                board[a][b][1] = "◦ ";
                board[a][b][0] = "";
                a = a + 1;
                if ((a > 15) || (a < 0) || (b > 99) || (b < 0)) {
                    choosing(board, (a - 1), b, saves);
                } else {
                    Check(board, a, b, saves);
                }
                break;
            case ("d"):
                board[a][b][1] = "◦ ";
                board[a][b][0] = "";
                b = b + 1;
                if ((a > 15) || (a < 0) || (b > 99) || (b < 0)) {
                    choosing(board, a, (b - 1), saves);
                } else {
                    Check(board, a, b, saves);
                }
                break;
        }
    }

    public static void Check(String[][][] board, int a, int b, int saves) throws IOException {
        Random rand = new Random();
        int r1 = rand.nextInt(10);
        int lives = 0;
        if (b == 99) {
            System.out.println("You WIN only losing " + lives + " lives and saving " + saves + " people.");
        } else {
            if (board[a][b][0] == "♥ ") {
                saves = saves + 1;
            }
            board[a][b][0] = "";
            if (board[a][b][2] == "X ") {
                board[7][0][0] = "• ";
                lives = lives + 1;
            } else {
                if ((r1 == 1) && (board[a][b][1] != "◦ ") && (board[a][b][0] != "♥ ")) {
                    board[a][b][1] = "";
                    board[a][b][2] = "X ";
                    board[7][0][0] = "• ";
                    lives = lives + 1;
                } else {
                    board[a][b][0] = "• ";
                }
            }
            printboard(board, saves);
        }
    }
}
