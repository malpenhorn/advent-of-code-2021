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
}
