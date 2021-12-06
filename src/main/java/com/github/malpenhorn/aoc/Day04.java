package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.*;

public class Day04 {
    private static class Board {
        private Map<Integer, Square> squares = new HashMap<>(25);
        private int[] rowHits = new int[5];
        private int[] columnHits = new int[5];
        private Set<Integer> markedNumbers = new HashSet<>();

        private boolean containsNumber(Integer number) {
            return squares.containsKey(number);
        }

        private boolean markHit(Integer number) {
            Square square = squares.get(number);
            if (square != null) {
                markedNumbers.add(number);
                rowHits[square.row]++;
                columnHits[square.column]++;
            }
            return rowHits[square.row] == 5 || columnHits[square.column] == 5;
        }
    }

    private static class Square {
        private int row;
        private int column;
        private Square(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    public static int part1(List<String> input) {
        List<Integer> numbers = getNumbers(input);
        List<Board> boards = getBoards(input);

        for (Integer number : numbers) {
            for (Board board : boards) {
                if (board.containsNumber(number)) {
                    boolean bingo = board.markHit(number);
                    if (bingo) {
                        return calculateBoardScore(number, board);
                    }
                }
            }
        }
        return -1;
    }

    private static int calculateBoardScore(Integer number, Board board) {
        int unmarkedSum = 0;
        for (Integer num : board.squares.keySet()) {
            if (!board.markedNumbers.contains(num)) {
                unmarkedSum += num;
            }
        }
        return unmarkedSum * number;
    }

    private static List<Integer> getNumbers(List<String> input) {
        String[] nums = input.get(0).split(",");
        List<Integer> numbers = new ArrayList<>(nums.length);
        for (String s : nums) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers;
    }

    private static List<Board> getBoards(List<String> input) {
        List<Board> boards = new LinkedList<>();
        int currentBoard = 0;
        int currentColumn = 0;
        for (int i = 2; i < input.size(); i++) {
            String line = input.get(i);
            if (!line.isEmpty()) {
                Board board;
                if (currentBoard == boards.size()) {
                    board = new Board();
                    boards.add(board);
                }
                else {
                    board = boards.get(currentBoard);
                }

                String[] row = line.trim().split("\\s+");
                for (int n = 0; n < row.length; n++) {
                    board.squares.put(Integer.parseInt(row[n]), new Square(currentColumn, n));
                }
                currentColumn++;
            }
            else {
                currentBoard++;
                currentColumn = 0;
            }
        }
        return boards;
    }

    public static int part2(List<String> input) {
        List<Integer> numbers = getNumbers(input);
        List<Board> boards = getBoards(input);

        for (Integer number : numbers) {
            Iterator<Board> boardsIterator = boards.iterator();
            while (boardsIterator.hasNext()) {
                Board board = boardsIterator.next();
                if (board.containsNumber(number)) {
                    boolean bingo = board.markHit(number);
                    if (bingo) {
                        if (boards.size() > 1) {
                            boardsIterator.remove();
                        }
                        else {
                            return calculateBoardScore(number, board);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfStrings("day04.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfStrings("day04.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
