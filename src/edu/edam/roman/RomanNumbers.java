package edu.edam.roman;

public class RomanNumbers {

    public static final String[] ROMANS = new String[]{"I", "V", "X", "L", "C", "D", "M"};
    public static final int[] NUMBERS = new int[]{1, 5, 10, 50, 100, 500, 1000};

    public static boolean isRomanNum(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        return s.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
    }

    private static int getPos(String letter) {
        for (int i = 0; i < ROMANS.length; i++) {
            if (ROMANS[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }

    public static String numToRoman(int n) {
        if (n <= 0 || n >= 4000) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;

        while (n != 0) {
            int r = n % 10;
            n = n / 10;

            if (r > 0) {
                String letters = getLetters(r, i);
                sb.insert(0, treatment(letters));
            }
            i++;
        }
        return sb.toString();
    }

    private static String getLetters(int r, int pos) {
        String letters = null;
        if (pos == 1) {
            letters = getGroupLetters(r, "I");
        } else if (pos == 2) {
            letters = getGroupLetters(r, "X");
        } else if (pos == 3) {
            letters = getGroupLetters(r, "C");
        } else if (pos == 4) {
            letters = getGroupLetters(r, "M");
        }
        return letters;
    }

    private static String getGroupLetters(int length, String letter) {
        return letter.repeat(length);
    }

    private static String treatment(String letters) {
        String result = "";
        int length = letters.length();
        if (letters.contains("I")) {
            result = treat(length, "I", "V", "X");
        } else if (letters.contains("X")) {
            result = treat(length, "X", "L", "C");
        } else if (letters.contains("C")) {
            result = treat(length, "C", "D", "M");
        } else if (letters.contains("M")) {
            result = milleniumTreat(length);
        }

        return result;
    }

    private static String treat(int length, String small, String middle, String big) {
        StringBuilder sb = new StringBuilder();
        if (length < 4) {
            sb.append(small.repeat(length));
        } else if (length == 4) {
            sb.append(small).append(middle);
        } else if (length == 5) {
            sb.append(middle);
        } else if (length < 9) {
            sb.append(middle);
            sb.append(small.repeat(length - 5));
        } else if (length == 9) {
            sb.append(small).append(big);
        }
        return sb.toString();
    }

    private static String milleniumTreat(int length) {
        return "M".repeat(length);
    }

    public static int romanToNum(String num) {
        if (!isRomanNum(num)) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }
        int result = 0;
        String[] romanNum = num.split("");
        for (int i = 0; i < romanNum.length; i++) {
            int indexCur = getPos(romanNum[i]);
            if (i > 0) {
                int indexPrev = getPos(romanNum[i - 1]);
                boolean isPrecededByLesserLetter = indexPrev < indexCur;
                if (isPrecededByLesserLetter) {
                    result += (NUMBERS[indexCur] - (NUMBERS[indexPrev] * 2));
                    continue;
                }
            }
            result += NUMBERS[indexCur];
        }
        return result;
    }

    public static String add(String num1, String num2) {
        int n1 = romanToNum(num1);
        int n2 = romanToNum(num2);
        int total = n1 + n2;
        if (total >= 4000) {
            return ">= 4000";
        }
        return numToRoman(total);
    }

    public static String diff(String num1, String num2) {
        int n1 = romanToNum(num1);
        int n2 = romanToNum(num2);
        int total = n1 - n2;
        if (total < 1) {
            return "< 1 or >= 4000";
        }
        return numToRoman(total);
    }

}
