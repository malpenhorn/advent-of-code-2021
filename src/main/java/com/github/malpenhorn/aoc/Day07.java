package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 {
    public static int part1(List<Integer> input) {
        input.sort(Comparator.naturalOrder());
        int median = input.get(input.size() / 2);
        return input.stream().collect(Collectors.summingInt(i -> Math.abs(median - i)));
    }

    public static int part2(List<Integer> input) {
        double sum = input.stream().collect(Collectors.summingInt(Integer::intValue));
        double average = sum / input.size();
        return Math.min(
                getFuelCost(input, (int) Math.floor(average)),
                getFuelCost(input, (int) Math.ceil(average)));
    }

    private static int getFuelCost(List<Integer> input, int possibleOptimalPosition) {
        int fuelCost = 0;
        for (Integer position : input) {
            int horizontalDiff = Math.abs(possibleOptimalPosition - position);
            fuelCost += (horizontalDiff * (horizontalDiff + 1)) / 2;
        }
        return fuelCost;
    }

    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfIntegersFromSingleLine("day07.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfIntegersFromSingleLine("day07.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
