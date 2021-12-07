package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.List;

public class Day06 {
    public static long simulate(List<Integer> input, int iterations) {
        long[] numFishPerTimer = new long[9];
        for (Integer i : input) {
            numFishPerTimer[i]++;
        }

        for (int i = 0; i < iterations; i++) {
            numFishPerTimer = iterateOneDay(numFishPerTimer);
        }

        long population = 0;
        for (int i = 0; i < numFishPerTimer.length; i++) {
            population += numFishPerTimer[i];
        }
        return population;
    }

    private static long[] iterateOneDay(long[] numFishPerTimer) {
        long[] iteratedValues = new long[9];
        for (int i = 0; i < numFishPerTimer.length; i++) {
            long fish = numFishPerTimer[i];
            if (i == 0) {
                iteratedValues[8] += fish;
                iteratedValues[6] += fish;
            }
            else {
                iteratedValues[i - 1] += fish;
            }
        }
        return iteratedValues;
    }

    public static void main(String[] args) {
        long part1Result = simulate(FileUtil.readFileAsListOfIntegersFromSingleLine("day06.txt"), 80);
        System.out.println(String.format("Part1 answer: %d", part1Result));
        long part2Result = simulate(FileUtil.readFileAsListOfIntegersFromSingleLine("day06.txt"), 256);
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
