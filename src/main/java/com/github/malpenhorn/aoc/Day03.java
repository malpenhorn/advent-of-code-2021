package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day03 {
    private final static char CHAR_VALUE_FOR_0 = 48;
    private final static char CHAR_VALUE_FOR_1 = 49;

    public static int part1(List<String> binaryNumbers) {
        Map<Integer, Integer> calculatedRates = new HashMap<>();
        for (String binary : binaryNumbers) {
            char[] numbers = binary.toCharArray();
            for (int i = 0; i < numbers.length; i++) {
                int bit = Integer.parseInt("" + numbers[i]);
                if (bit == 0) {
                    bit = -1;
                }
                calculatedRates.merge(i, bit, Integer::sum);
            }
        }

        char[] gammaRateBinary = new char[calculatedRates.size()];
        char[] epsilonRateBinary = new char[calculatedRates.size()];
        for (int i = 0; i < calculatedRates.size(); i++) {
            int value = calculatedRates.get(i);
            gammaRateBinary[i] = value > 0 ? CHAR_VALUE_FOR_1 : CHAR_VALUE_FOR_0;
            epsilonRateBinary[i] = value > 0 ? CHAR_VALUE_FOR_0 : CHAR_VALUE_FOR_1;
        }

        int gammaValue = Integer.parseInt(String.copyValueOf(gammaRateBinary),2);
        int epsilonValue = Integer.parseInt(String.copyValueOf(epsilonRateBinary),2);
        return gammaValue * epsilonValue;
    }

    public static int part2(List<String> binaryNumbers) {
        String oxygenGeneratorRating = calculateOxygenGeneratorRating(new ArrayList<>(binaryNumbers), 0);
        String co2ScrubberRating = calculateCo2ScrubberRating(binaryNumbers, 0);
        int oxygenValue = Integer.parseInt(oxygenGeneratorRating,2);
        int co2Value = Integer.parseInt(co2ScrubberRating,2);
        return oxygenValue * co2Value;
    }

    private static String calculateOxygenGeneratorRating(List<String> binaryNumbers, int index) {
        if (binaryNumbers.size() < 2) {
            return binaryNumbers.get(0);
        }

        char mostCommonBit = getMostCommon(binaryNumbers, index) >= 0 ? CHAR_VALUE_FOR_1 : CHAR_VALUE_FOR_0;

        binaryNumbers = binaryNumbers.stream()
                .filter(number -> number.charAt(index) == mostCommonBit)
                .collect(Collectors.toList());
        return calculateOxygenGeneratorRating(binaryNumbers, index + 1);
    }

    private static int getMostCommon(List<String> binaryNumbers, int index) {
        int mostCommon = 0;
        for (String binary : binaryNumbers) {
            int bit = Integer.parseInt("" + binary.charAt(index));
            if (bit == 0) {
                bit = -1;
            }
            mostCommon += bit;
        }
        return mostCommon;
    }

    private static String calculateCo2ScrubberRating(List<String> binaryNumbers, int index) {
        if (binaryNumbers.size() < 2) {
            return binaryNumbers.get(0);
        }

        int mostCommon = getMostCommon(binaryNumbers, index);
        char leastCommonBit = -mostCommon <= 0 ? CHAR_VALUE_FOR_0 : CHAR_VALUE_FOR_1;

        binaryNumbers = binaryNumbers.stream()
                .filter(number -> number.charAt(index) == leastCommonBit)
                .collect(Collectors.toList());
        return calculateCo2ScrubberRating(binaryNumbers, index + 1);
    }


    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfStrings("day03.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfStrings("day03.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
