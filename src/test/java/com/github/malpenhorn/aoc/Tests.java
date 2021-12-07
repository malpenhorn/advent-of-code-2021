package com.github.malpenhorn.aoc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    void testDay01Part1() {
        List<Integer> measurements = Arrays.asList(
                199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        assertEquals(7, Day01.part1(measurements));
    }

    @Test
    void testDay01Part2() {
        List<Integer> measurements = Arrays.asList(
                199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
        assertEquals(5, Day01.part2(measurements));
    }

    @Test
    void testDay02Part1() {
        List<String> commands = Arrays.asList(
                "forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        assertEquals(150, Day02.part1(commands));
    }

    @Test
    void testDay02Part2() {
        List<String> commands = Arrays.asList(
                "forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        assertEquals(900, Day02.part2(commands));
    }

    @Test
    void testDay03Part1() {
        List<String> binaryNumbers = Arrays.asList(
                "00100", "11110", "10110", "10111", "10101", "01111",
                "00111", "11100", "10000", "11001", "00010", "01010");
        assertEquals(198, Day03.part1(binaryNumbers));
    }

    @Test
    void testDay03Part2() {
        List<String> binaryNumbers = Arrays.asList(
                "00100", "11110", "10110", "10111", "10101", "01111",
                "00111", "11100", "10000", "11001", "00010", "01010");
        assertEquals(230, Day03.part2(binaryNumbers));
    }

    @Test
    void testDay04Part1() {
        List<String> input = Arrays.asList(
                "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
                "",
                "22 13 17 11  0",
               " 8  2 23  4 24",
                "21  9 14 16  7",
                "6 10  3 18  5",
                "1 12 20 15 19",
                "",
                "3 15  0  2 22",
                "9 18 13 17  5",
                "19  8  7 25 23",
                "20 11 10 24  4",
                "14 21 16 12  6",
                "",
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                "2  0 12  3  7");
        assertEquals(4512, Day04.part1(input));
    }

    @Test
    void testDay04Part2() {
        List<String> input = Arrays.asList(
                "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
                "",
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                "6 10  3 18  5",
                "1 12 20 15 19",
                "",
                "3 15  0  2 22",
                "9 18 13 17  5",
                "19  8  7 25 23",
                "20 11 10 24  4",
                "14 21 16 12  6",
                "",
                "14 21 17 24  4",
                "10 16 15  9 19",
                "18  8 23 26 20",
                "22 11 13  6  5",
                "2  0 12  3  7");
        assertEquals(1924, Day04.part2(input));
    }

    @Test
    void testDay05Part1() {
        List<String> input = Arrays.asList(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2");
        assertEquals(5, Day05.part1(input));
    }

    @Test
    void testDay05Part2() {
        List<String> input = Arrays.asList(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2");
        assertEquals(12, Day05.part2(input));
    }

    @Test
    void testDay06Part1() {
        List<Integer> input = Arrays.asList(3,4,3,1,2);
        assertEquals(5934, Day06.simulate(input, 80));
    }

    @Test
    void testDay06Part2() {
        List<Integer> input = Arrays.asList(3,4,3,1,2);
        assertEquals(26984457539L, Day06.simulate(input, 256));
    }
}
