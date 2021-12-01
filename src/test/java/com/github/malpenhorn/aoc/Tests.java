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
}
