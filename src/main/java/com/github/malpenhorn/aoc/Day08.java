package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.*;
import java.util.function.BiPredicate;

public class Day08 {
    public static long part1(List<String> lines) {
        return lines.stream()
                    .map(line -> line.split(" \\| ")[1].trim())
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(segments -> segments.length() == 2 || segments.length() == 3 || segments.length() == 4 || segments.length() == 7)
                    .count();
    }

    public static int part2(List<String> lines) {
        int outputSum = 0;
        for (String line : lines) {
            String[] parts = line.split("\\|");
            String[] inputDigits = parts[0].trim().split("\\s+");
            String[] outputDigits = parts[1].trim().split("\\s+");

            Map<Integer, Set<Character>> digitToSegments = new HashMap<>();
            decodeUniqueLengthDigits(inputDigits, digitToSegments);
            decodeDigit(3, (segments, decodedDigits) ->
                    segments.size() == 5 && segments.containsAll(decodedDigits.get(1)),
                    inputDigits, digitToSegments);
            decodeDigit(9, (segments, decodedDigits) ->
                    segments.size() == 6 && (segments.containsAll(decodedDigits.get(4)) || segments.containsAll(decodedDigits.get(3))),
                    inputDigits, digitToSegments);
            decodeDigit(0, (segments, decodedDigits) ->
                            segments.size() == 6 && !segments.containsAll(decodedDigits.get(9)) && segments.containsAll(decodedDigits.get(1)),
                    inputDigits, digitToSegments);
            decodeDigit(6, (segments, decodedDigits) ->
                            segments.size() == 6 && !segments.containsAll(decodedDigits.get(9)) && !segments.containsAll(decodedDigits.get(0)),
                    inputDigits, digitToSegments);
            decodeDigit(5, (segments, decodedDigits) ->
                            segments.size() == 5 && !segments.containsAll(decodedDigits.get(3)) && decodedDigits.get(9).containsAll(segments),
                    inputDigits, digitToSegments);
            decodeDigit(2, (segments, decodedDigits) ->
                            segments.size() == 5 && !segments.containsAll(decodedDigits.get(3)) && !segments.containsAll(decodedDigits.get(5)),
                    inputDigits, digitToSegments);

            outputSum += getOutputValue(outputDigits, digitToSegments);
        }
        return outputSum;
    }

    private static void decodeUniqueLengthDigits(String[] inputDigits, Map<Integer, Set<Character>> digitToSegments) {
        for (String segments : inputDigits) {
            Set<Character> segmentsSet = new TreeSet<>();
            for (char inputSegment : segments.toCharArray()) {
                segmentsSet.add(inputSegment);
            }
            Integer uniqueLengthDigit = getMatchingDigitsByUniqueLength(segments);
            if (uniqueLengthDigit != -1) {
                digitToSegments.put(uniqueLengthDigit, segmentsSet);
            }
        }
    }

    private static Integer getMatchingDigitsByUniqueLength(String segments) {
        switch (segments.length()) {
            case 2:
                return 1;
            case 3:
                return 7;
            case 4:
                return 4;
            case 7:
                return 8;
            default:
                return -1;
        }
    }

    private static void decodeDigit(Integer digit,
                                    BiPredicate<Set<Character>, Map<Integer, Set<Character>>> condition,
                                    String[] scrambledDigits, Map<Integer, Set<Character>> decodedDigits) {
        for (String segments : scrambledDigits) {
            Optional<Set<Character>> mappedSegments = decodeSegments(segments, decodedDigits, condition);
            if (mappedSegments.isPresent()) {
                decodedDigits.put(digit, mappedSegments.get());
            }
        }
    }

    private static Optional<Set<Character>> decodeSegments(String segments, Map<Integer, Set<Character>> decodedDigits,
                                           BiPredicate<Set<Character>, Map<Integer, Set<Character>>> condition) {
        Set<Character> segmentsSet = new TreeSet<>();
        for (char inputSegment : segments.toCharArray()) {
            segmentsSet.add(inputSegment);
        }
        return condition.test(segmentsSet, decodedDigits) ? Optional.of(segmentsSet) : Optional.empty();
    }

    private static int getOutputValue(String[] outputDigits, Map<Integer, Set<Character>> digitToSegments) {
        Map<Set<Character>, Integer> segmentsToDigit = new HashMap<>();
        for (Map.Entry<Integer, Set<Character>> entry : digitToSegments.entrySet()) {
            segmentsToDigit.put(entry.getValue(), entry.getKey());
        }

        StringBuilder outputValue = new StringBuilder();
        for (String segments : outputDigits) {
            Set<Character> segmentsSet = new TreeSet<>();
            for (char inputSegment : segments.toCharArray()) {
                segmentsSet.add(inputSegment);
            }
            outputValue.append(segmentsToDigit.get(segmentsSet));
        }
        return Integer.parseInt(outputValue.toString());
    }

    public static void main(String[] args) {
        long part1Result = part1(FileUtil.readFileAsListOfStrings("day08.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfStrings("day08.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
