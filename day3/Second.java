package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Second {
    public static void main(String[] args) {
        String fileName = "day3/input.txt";
        File file = new File(fileName);
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int matrixLength = list.get(0).length();

        List<NumberWithData2> numbers = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(0).length(); j++) {
                if (Character.isDigit(list.get(i).charAt(j))) {
                    int num = Character.getNumericValue(list.get(i).charAt(j));
                    int len = 1;
                    int firstJ = j;
                    while (j + 1 < list.get(0).length() && Character.isDigit(list.get(i).charAt(j + 1))) {
                        num = num * 10 + Character.getNumericValue(list.get(i).charAt(j + 1));
                        j++;
                        len++;
                    }
                    numbers.add(new NumberWithData2(num, i, firstJ, len));
                }
            }
        }
        for (NumberWithData2 number : numbers) {
            int len = number.numOfDigits + 2;
            int i = number.iPosition;
            int j = number.jPosition;
            if (i > 0) {
                if (j - 1 >= 0) {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i - 1).charAt(j - 1);
                        if (c == '*') {
                            number.isTouchingChar = true;
                            number.starI = i - 1;
                            number.starJ = j - 1;
                        }
                        j++;
                        len--;
                    }
                } else {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i - 1).charAt(j);
                        if (c == '*') {
                            number.isTouchingChar = true;
                            number.starI = i - 1;
                            number.starJ = j;
                        }
                        j++;
                        len--;
                    }
                }
            }

            i = number.iPosition;
            j = number.jPosition;
            len = number.numOfDigits + 2;
            if (j - 1 >= 0) {
                char c = list.get(i).charAt(j - 1);
                if (c == '*') {
                    number.isTouchingChar = true;
                    number.starI = i;
                    number.starJ = j - 1;
                }
            }
            if (j + number.numOfDigits < matrixLength) {
                char c = list.get(i).charAt(j + number.numOfDigits);
                if (c == '*') {
                    number.isTouchingChar = true;
                    number.starI = i;
                    number.starJ = j + number.numOfDigits;
                }
            }

            if (i + 1 < list.size()) {
                if (j - 1 >= 0) {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i + 1).charAt(j - 1);
                        if (c == '*') {
                            number.isTouchingChar = true;
                            number.starI = i + 1;
                            number.starJ = j - 1;
                        }
                        j++;
                        len--;
                    }
                } else {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i + 1).charAt(j);
                        if (c == '*') {
                            number.isTouchingChar = true;
                            number.starI = i + 1;
                            number.starJ = j;
                        }
                        j++;
                        len--;
                    }

                }
            }
        }

        Map<String, List<NumberWithData2>> map = new HashMap<>();
        for (NumberWithData2 n : numbers) {
            String starCoordinates = String.format("%dx%d", n.starI, n.starJ);
            if (map.containsKey(starCoordinates)) {
                map.get(starCoordinates).add(n);
            } else {
                map.put(starCoordinates, new ArrayList<>(List.of(n)));
            }
        }
        long result = 0;
        for (Map.Entry<String, List<NumberWithData2>> entry : map.entrySet()) {
            long multiplied = 0;
            if (entry.getValue().size() == 2) {
                multiplied = entry.getValue().get(0).number * entry.getValue().get(1).number;
            }
            result += multiplied;

        }
        System.out.println(result);

    }
}

class NumberWithData2 {
    int number;
    int iPosition;
    int jPosition;
    int numOfDigits;
    boolean isTouchingChar;
    int starI = -1;
    int starJ = -1;

    public NumberWithData2(int number, int iPosition, int jPosition, int numOfDigits) {
        this.number = number;
        this.iPosition = iPosition;
        this.jPosition = jPosition;
        this.numOfDigits = numOfDigits;
        this.isTouchingChar = false;
    }
}
