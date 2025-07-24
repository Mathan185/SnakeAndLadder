package snakeandladder;

import java.util.*;

public class Game {
    private static final List<List<Integer>> board = new ArrayList<>();
    private static final Map<Integer, Integer> ladderMp = new HashMap<>();
    private static final Map<Integer, Integer> snakeMp = new HashMap<>();
    private static final List<Pair<Integer, Integer>> ladArray = Arrays.asList(
        new Pair<>(2, 13), new Pair<>(14, 23), new Pair<>(8, 15)
    );
    private static final List<Pair<Integer, Integer>> skeArray = Arrays.asList(
        new Pair<>(27, 22), new Pair<>(16, 12), new Pair<>(11, 1)
    );

    private int p1 = 1;
    private int p2 = 1;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        createBoard();
        printBoard();

        while (true) {
            System.out.println("Player 1, press 1 to roll the dice");
            int ch = scanner.nextInt();
            if (ch == 1) {
                int roll = randNumber();
                System.out.println("Player 1 rolled: " + roll);
                p1 += roll;
                p1 = findLadderOrSnake(p1);
                printBoard();
                if (p1 >= 30) {
                    System.out.println("Player 1 wins...!!");
                    break;
                }
            }

            System.out.println("Player 2, press 1 to roll the dice");
            ch = scanner.nextInt();
            if (ch == 1) {
                int roll = randNumber();
                System.out.println("Player 2 rolled: " + roll);
                p2 += roll;
                p2 = findLadderOrSnake(p2);
                printBoard();
                if (p2 >= 30) {
                    System.out.println("Player 2 wins...!!");
                    break;
                }
            }
        }

        scanner.close();
    }

    private void createBoard() {
        int n = 30;
        for (int i = 0; i < 5; i++) {
            List<Integer> row = new ArrayList<>();
            if (i % 2 == 0) {
                int temp = n - 5;
                for (int j = 0; j < 6; j++) {
                    row.add(temp++);
                }
            } else {
                int temp = n;
                for (int j = 0; j < 6; j++) {
                    row.add(temp--);
                }
            }
            n -= 6;
            board.add(row);
        }

        for (Pair<Integer, Integer> pair : ladArray) {
            ladderMp.put(pair.getFirst(), pair.getSecond());
        }

        for (Pair<Integer, Integer> pair : skeArray) {
            snakeMp.put(pair.getFirst(), pair.getSecond());
        }
    }

    private void printBoard() {
        for (List<Integer> row : board) {
            for (Integer cell : row) {
                System.out.print("| ");
                if (ladderMp.containsKey(cell)) {
                    System.out.print("L");
                } else if (snakeMp.containsKey(cell)) {
                    System.out.print("S");
                } else if (cell == p1) {
                    System.out.print("1");
                } else if (cell == p2) {
                    System.out.print("2");
                } else {
                    System.out.print("_");
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private int randNumber() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    private int findLadderOrSnake(int position) {
        if (ladderMp.containsKey(position)) {
            return ladderMp.get(position);
        } else if (snakeMp.containsKey(position)) {
            return snakeMp.get(position);
        }
        return position;
    }
}
