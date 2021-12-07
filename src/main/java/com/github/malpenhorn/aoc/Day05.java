package com.github.malpenhorn.aoc;

import com.github.malpenhorn.aoc.util.FileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 {
    private static class Line {
        private Point rightMost;
        private Point leftMost;

        private void addPoint(Point point) {
            if (rightMost == null) {
                rightMost = point;
            }
            else if (rightMost.x > point.x) {
                leftMost = rightMost;
                rightMost = point;
            }
            else {
                leftMost = point;
            }
        }
    }

    private static class Point {
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static int part1(List<String> input) {
        List<Line> lines = getLines(input);
        lines = getOnlyHorizontalOrVerticalLines(lines);

        Map<Point, Integer> grid = populateGrid(lines);
        int moreThan2Overlaps = 0;
        for (int overlaps : grid.values()) {
            if (overlaps > 1) {
                moreThan2Overlaps++;
            }
        }
        return moreThan2Overlaps;
    }

    private static Map<Point, Integer> populateGrid(List<Line> lines) {
        Map<Point, Integer> grid = new HashMap<>();
        for (Line line : lines) {
            if (line.rightMost.y == line.leftMost.y) {
                for (int x = line.rightMost.x; x <= line.leftMost.x; x++) {
                    grid.merge(new Point(x, line.rightMost.y), 1, Integer::sum);
                }
            }
            else if (line.rightMost.x == line.leftMost.x) {
                int start = Math.min(line.rightMost.y, line.leftMost.y);
                int end = Math.max(line.rightMost.y, line.leftMost.y);
                for (int y = start; y <= end; y++) {
                    grid.merge(new Point(line.rightMost.x, y), 1, Integer::sum);
                }
            }
            else {
                int y = line.rightMost.y;
                boolean negativeY = false;
                if (y > line.leftMost.y) {
                    negativeY = true;
                }
                for (int x = line.rightMost.x; x <= line.leftMost.x; x++) {
                    grid.merge(new Point(x, y), 1, Integer::sum);
                    if (negativeY) {
                        y--;
                    }
                    else {
                        y++;
                    }
                }
            }
        }
        return grid;
    }

    private static List<Line> getOnlyHorizontalOrVerticalLines(List<Line> lines) {
        lines = lines.stream()
                .filter(line ->
                        line.rightMost.x == line.leftMost.x ||
                        line.rightMost.y == line.leftMost.y)
                .collect(Collectors.toList());
        return lines;
    }

    private static List<Line> getLines(List<String> input) {
        List<Line> lines = new LinkedList<>();
        for (String s : input) {
            String[] points = s.split(" -> ");
            Line line = new Line();
            for (String p : points) {
                String[] coordinates = p.split(",");
                line.addPoint(new Point(
                        Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
            }
            lines.add(line);
        }
        return lines;
    }

    public static int part2(List<String> input) {
        List<Line> lines = getLines(input);

        Map<Point, Integer> grid = populateGrid(lines);
        int moreThan2Overlaps = 0;
        for (int overlaps : grid.values()) {
            if (overlaps > 1) {
                moreThan2Overlaps++;
            }
        }
        return moreThan2Overlaps;
    }

    public static void main(String[] args) {
        int part1Result = part1(FileUtil.readFileAsListOfStrings("day05.txt"));
        System.out.println(String.format("Part1 answer: %d", part1Result));
        int part2Result = part2(FileUtil.readFileAsListOfStrings("day05.txt"));
        System.out.println(String.format("Part2 answer: %d", part2Result));
    }
}
