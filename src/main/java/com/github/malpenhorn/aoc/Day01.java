package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.List;

public class Day01 {
    public static int part1(List<Integer> measurements) {
        int increases = 0;
        for (int i = 1; i < measurements.size(); i++) {
            if (measurements.get(i) > measurements.get(i - 1)) {
                increases++;
            }
        }
        return increases;
    }

    public static int part2(List<Integer> measurements) {
        int previousSum = Integer.MAX_VALUE;
        int increases = 0;
        for (int i = 0; i < measurements.size() - 2; i++) {
            int currentSum = measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2);
            if (currentSum > previousSum) {
                increases++;
            }
            previousSum = currentSum;
        }
        return increases;
    }

    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfIntegers("day01.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfIntegers("day01.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
