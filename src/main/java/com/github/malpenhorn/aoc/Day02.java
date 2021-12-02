package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 {
    public static int part1(List<String> commands) {
        Map<String, Integer> tracked = new HashMap<>();
        tracked.put("horizontal", 0);
        tracked.put("depth", 0);
        for (String command : commands) {
            String[] instructions = command.split(" ");
            String direction = instructions[0];
            int value = Integer.parseInt(instructions[1]);
            switch (direction) {
                case "forward":
                    tracked.merge("horizontal", value, Integer::sum);
                    break;
                case "up":
                    tracked.merge("depth", -value, Integer::sum);
                    break;
                case "down":
                    tracked.merge("depth", value, Integer::sum);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown direction: %s", direction));
            }
        }
        return tracked.get("horizontal") * tracked.get("depth");
    }

    public static int part2(List<String> commands) {
        Map<String, Integer> tracked = new HashMap<>();
        tracked.put("horizontal", 0);
        tracked.put("depth", 0);
        tracked.put("aim", 0);
        for (String command : commands) {
            String[] instructions = command.split(" ");
            String direction = instructions[0];
            int value = Integer.parseInt(instructions[1]);
            switch (direction) {
                case "forward":
                    tracked.merge("horizontal", value, Integer::sum);
                    tracked.merge("depth", tracked.get("aim") * value, Integer::sum);
                    break;
                case "up":
                    tracked.merge("aim", -value, Integer::sum);
                    break;
                case "down":
                    tracked.merge("aim", value, Integer::sum);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Unknown direction: %s", direction));
            }
        }
        return tracked.get("horizontal") * tracked.get("depth");
    }

    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfStrings("day02.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfStrings("day02.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
