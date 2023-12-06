package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class First {
    //
//    467..114..
//    ...*......
//    ..35..633.
//    ......#...
//    617*......
//    .....+.58.
//    ..592.....
//    ......755.
//    ...$.*....
//    .664.598..

    //TODO Refactor and rewrite to Kotlin
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

        List<NumberWithData> numbers = new ArrayList<>();
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
                    numbers.add(new NumberWithData(num, i, firstJ, len));
                }
            }
        }
        for (NumberWithData number : numbers) {
            int len = number.numOfDigits + 2;
            int i = number.iPosition;
            int j = number.jPosition;
            if (i > 0) {
                if (j - 1 >= 0) {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i - 1).charAt(j - 1);
                        if (!Character.isDigit(c) && c != '.') {
                            number.isTouchingChar = true;
                        }
                        j++;
                        len--;
                    }
                } else {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i - 1).charAt(j);
                        if (!Character.isDigit(c) && c != '.') {
                            number.isTouchingChar = true;
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
                if (!Character.isDigit(c) && c != '.') {
                    number.isTouchingChar = true;
                }
            }
            if (j + number.numOfDigits < matrixLength) {
                char c = list.get(i).charAt(j + number.numOfDigits);
                if (!Character.isDigit(c) && c != '.') {
                    number.isTouchingChar = true;
                }
            }

            if (i + 1 < list.size()) {
                if (j - 1 >= 0) {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i + 1).charAt(j - 1);
                        if (!Character.isDigit(c) && c != '.') {
                            number.isTouchingChar = true;
                        }
                        j++;
                        len--;
                    }
                } else {
                    while (len > 0 && j < matrixLength) {
                        char c = list.get(i + 1).charAt(j);
                        if (!Character.isDigit(c) && c != '.') {
                            number.isTouchingChar = true;
                        }
                        j++;
                        len--;
                    }

                }
            }
        }
        AtomicLong result = new AtomicLong();
        numbers.forEach(n -> {
            if (n.isTouchingChar) {
                result.addAndGet(n.number);
            }
        });
        System.out.println(result);
    }
}

class NumberWithData {
    int number;
    int iPosition;
    int jPosition;
    int numOfDigits;
    boolean isTouchingChar;

    public NumberWithData(int number, int iPosition, int jPosition, int numOfDigits) {
        this.number = number;
        this.iPosition = iPosition;
        this.jPosition = jPosition;
        this.numOfDigits = numOfDigits;
        this.isTouchingChar = false;
    }
}
