package edu.edam.roman;

public class RomanNumbersTest {

    public static void main(String[] args) {

        // test isRomanNum
        System.out.println(RomanNumbers.isRomanNum("MMDCLXVI")); // true
        System.out.println(RomanNumbers.isRomanNum("MMMCMXCIX")); // true
        System.out.println(RomanNumbers.isRomanNum("IIII")); // false
        System.out.println(RomanNumbers.isRomanNum("AB")); // false
        System.out.println(RomanNumbers.isRomanNum("MMMMI")); // false
        System.out.println(RomanNumbers.isRomanNum("iiii")); // false

        // Test numToRoman
        System.out.println(RomanNumbers.numToRoman(3999)); // MMMCMXCIX
        System.out.println(RomanNumbers.numToRoman(2666)); // MMDCLXVI

        // Test romanToNum
        System.out.println(RomanNumbers.romanToNum("MMMCMXCIX")); // 3999
        System.out.println(RomanNumbers.romanToNum("MMDCLXVI")); // 2666

        // Test add
        System.out.println(RomanNumbers.add("CD", "L")); // 400 + 50 -> 450 (CDL)
        System.out.println(RomanNumbers.add("CCCXLIX", "CCXXV")); // 349 + 225 -> 574 (DLXXIV)
        System.out.println(RomanNumbers.add("MMMDLX", "MMDCCC")); // 3560 + 2800 > 4000

        // Test diff
        System.out.println(RomanNumbers.diff("CCCXLIX", "CCXXV")); // 349 - 225 -> 124 (CXXIV)
        System.out.println(RomanNumbers.diff("CCXXV", "CCCXLIX")); // 225 - 349 < 1
    }
}