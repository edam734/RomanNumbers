package edu.eduardo.roman;

public class RomanNumbers {

    public static final String[] ROMANS = new String[]{"I", "V", "X", "L", "C", "D", "M"};
    public static final int[] NUMBERS = new int[]{1, 5, 10, 50, 100, 500, 1000};

    public static void main(String[] args) {

        // test isRomanNum
        System.out.println(isRomanNum("MMDCLXVI")); // true
        System.out.println(isRomanNum("MMMCMXCIX")); // false
        System.out.println(isRomanNum("IIII")); // true
        System.out.println(isRomanNum("AB")); // true
        System.out.println(isRomanNum("MMMMI")); // true
        System.out.println(isRomanNum("iiii")); // false

        System.out.println(numToRoman(3999)); // MMMCMXCIX
        System.out.println(numToRoman(2666)); // MMDCLXVI
        System.out.println(romanToNum("MMMCMXCIX")); // 3999
        System.out.println(romanToNum("MMDCLXVI")); // 2666

        System.out.println(add("CD", "L")); // 400 + 50 -> 450 (CDL)
        System.out.println(add("CCCXLIX", "CCXXV")); // 349 + 225 -> 574 (DLXXIV)
        System.out.println(add("MMMDLX", "MMDCCC")); // 3560 + 2800 > 4000
        System.out.println(diff("CCCXLIX", "CCXXV")); // 349 - 225 -> 124 (CXXIV)
        System.out.println(diff("CCXXV", "CCCXLIX")); // 225 - 349 < 1
    }

    // rudimentar validation
    public static boolean isRomanNum(String s) {
        if (s ==
                null ||
                s.isEmpty()) {
            return false;
        }
        String[] letters = s.split("");
        // there cannot be more than one occurrence of any of the letters V, L or D,
        // nor more than four occurrences of any of the letters I, X or C.

        return isBelowLimitOccurrences(letters) &&
                isDescendingOrder(letters);
    }

    private static boolean isBelowLimitOccurrences(String[] letters) {
        int[] occurrences = new int[7];
        for (String letter : letters) {
            switch (letter) {
                case "I" -> occurrences[0]++;
                case "V" -> occurrences[1]++;
                case "X" -> occurrences[2]++;
                case "L" -> occurrences[3]++;
                case "C" -> occurrences[4]++;
                case "D" -> occurrences[5]++;
                case "M" -> occurrences[6]++;
                default -> {
                    return false;
                }
            }
        }
        // V, L or D < 2
        // I, X or C < 5
        boolean isBelowLimitOccurrences = occurrences[0] < 5 &&
                occurrences[1] < 2 &&
                occurrences[2] < 5 &&
                occurrences[3] < 2 &&
                occurrences[4] < 5 &&
                occurrences[5] < 2;
        return isBelowLimitOccurrences;
    }

    private static boolean isDescendingOrder(String[] letters) {
        for (int i = 0; i <
                letters.length; i++) {
            if (getPos(letters[i]) ==
                    -1) {
                return false;
            }

            if (i >
                    0 &&
                    getPos(letters[i -
                            1]) <
                            getPos(letters[i])) {
                return false;
            }
        }

        return true;
    }

    private static int getPos(String letter) {
        for (int i = 0; i <
                ROMANS.length; i++) {
            if (ROMANS[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }

    public static String numToRoman(int n) {
        if (n <=
                0 ||
                n >=
                        4000) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int r;
        int i = 1;
        while (n !=
                0) {
            r = n %
                    10;
            n = n /
                    10;
            if (r >
                    0) {
                String letters = getLetters(r, i);
                sb.insert(0, treatment(letters));
            }
            i++;
        }
        return sb.toString();
    }

    private static String getLetters(int r, int pos) {
        String letters = null;
        if (pos ==
                1) {
            letters = getGroupLetters(r, "I");
        } else if (pos ==
                2) {
            letters = getGroupLetters(r, "X");
        } else if (pos ==
                3) {
            letters = getGroupLetters(r, "C");
        } else if (pos ==
                4) {
            letters = getGroupLetters(r, "M");
        }
        return letters;
    }

    private static String getGroupLetters(int length, String letter) {
        return String.valueOf(letter).repeat(Math.max(0, length));
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
        if (length <
                4) {
            sb.append(String.valueOf(small).repeat(Math.max(0, length)));
        } else if (length ==
                4) {
            sb.append(small).append(middle);
        } else if (length ==
                5) {
            sb.append(middle);
        } else if (length <
                9) {
            sb.append(middle);
            sb.append(String.valueOf(small).repeat(length -
                    5));
        } else if (length ==
                9) {
            sb.append(small).append(big);
        }
        return sb.toString();
    }

    private static String milleniumTreat(int length) {
        return "M".repeat(Math.max(0, length));
    }

    public static String romanToNum(String num) {
        int result = 0;
        String[] romanNum = num.split("");
        int indexCur;
        int indexPrev;
        for (int i = 0; i <
                romanNum.length; i++) {
            indexCur = getPos(romanNum[i]);
            if (i >
                    0) {
                indexPrev = getPos(romanNum[i -
                        1]);
                boolean isPreceededByLesserLetter = indexPrev <
                        indexCur;
                if (isPreceededByLesserLetter) {
                    result += (NUMBERS[indexCur] -
                            (NUMBERS[indexPrev] *
                                    2));
                    continue;
                }
            }
            result += NUMBERS[indexCur];
        }
        return String.valueOf(result);
    }

    public static String add(String num1, String num2) {
        int n1 = Integer.parseInt(romanToNum(num1));
        int n2 = Integer.parseInt(romanToNum(num2));
        int total = n1 +
                n2;
        if (total >=
                4000) {
            return ">= 4000";
        }
        return numToRoman(total);
    }

    public static String diff(String num1, String num2) {
        int n1 = Integer.parseInt(romanToNum(num1));
        int n2 = Integer.parseInt(romanToNum(num2));
        int total = n1 -
                n2;
        if (total <
                1 ||
                total >=
                        4000) {
            return "< 1 or >= 4000";
        }
        return numToRoman(total);
    }

}
