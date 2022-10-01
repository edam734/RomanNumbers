package edu.eduardo.roman;

public class RomanNumbers {

	public static final String[] ROMANOS = new String[] { "I", "V", "X", "L", "C", "D", "M" };
	public static final Integer[] ALGARISMOS = new Integer[] { 1, 5, 10, 50, 100, 500, 1000 };

	public static void main(String[] args) {
		RomanNumbers rn = new RomanNumbers();
		System.out.println(rn.isRomanNum("MMDCLXVI"));

		System.out.println(rn.numToRoman(3999));
	}

	public boolean isRomanNum(String s) {
		String[] letras = s.split("");
		// 1. letters appear in descending order of value;
		boolean isDescendingOrder = isDescendingOrder(letras);
		// 2. there cannot be more than one occurrence of any of the letters V, L or D,
		// nor more than four occurrences of any of the letters I, X or C.
		boolean isBelowLimitOccurrences = isBelowLimitOccurrences(letras);

		return isDescendingOrder && isBelowLimitOccurrences;
	}

	private boolean isDescendingOrder(String[] letras) {
		boolean isDec = true;
		int i = 0;
		int prevPos = 6;
		while (isDec && i < letras.length) {
			String letra = letras[i];
			int pos = getPos(ROMANOS, letra);
			if (pos > prevPos) {
				isDec = false;
			} else {
				prevPos = pos;
				i++;
			}
		}
		return isDec;
	}

	private boolean isBelowLimitOccurrences(String[] letras) {
		int[] occurrences = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < letras.length; i++) {
			String letra = letras[i];
			switch (letra) {
			case "I":
				occurrences[0]++;
				break;
			case "V":
				occurrences[1]++;
				break;
			case "X":
				occurrences[2]++;
				break;
			case "L":
				occurrences[3]++;
				break;
			case "C":
				occurrences[4]++;
				break;
			case "D":
				occurrences[5]++;
				break;
			case "M":
				occurrences[6]++;
				break;
			default:
				// M doesn't count
			}
		}
		// V, L or D < 2
		// I, X or C < 5
		boolean isBelowLimitOccurrences = false;
		if (occurrences[0] < 5 && occurrences[1] < 2 && occurrences[2] < 5 && occurrences[3] < 2 && occurrences[4] < 5
				&& occurrences[5] < 2 && occurrences[6] < 4) {
			isBelowLimitOccurrences = true;
		}
		return isBelowLimitOccurrences;
	}

	private int getPos(String[] romanos, String letra) {
		int i = 0;
		boolean found = false;
		String l = "";
		while (!found) {
			l = romanos[i];
			if (l.equalsIgnoreCase(letra)) {
				found = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public String numToRoman(int n) {
		if (n <= 0 || n >= 4000) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		int r = -1;
		int i = 1;
		while (r != 0) {
			r = n % 10;
			n = n / 10;
			if (r > 0) {
				String letters = getLetters(r, i);
				sb.insert(0, treatment(letters));
			}
			i++;
		}
		return sb.toString();
	}

	private String getLetters(int r, int pos) {
		String letters = null;
		if (pos == 1) {
			letters = getGroup(r, "I");
		} else if (pos == 2) {
			letters = getGroup(r, "X");
		} else if (pos == 3) {
			letters = getGroup(r, "C");
		} else if (pos == 4) {
			letters = getGroup(r, "M");
		}
		return letters;
	}

	private String treatment(String letters) {
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

	private String treat(int length, String small, String middle, String big) {
		StringBuilder sb = new StringBuilder();
		if (length < 4) {
			for (int i = 0; i < length; i++) {
				sb.append(small);
			}
		} else if (length == 4) {
			sb.append(small).append(middle);
		} else if (length == 5) {
			sb.append(middle);
		} else if (length > 5 && length < 9) {
			sb.append(middle);
			for (int i = 0; i < length - 5; i++) {
				sb.append(small);
			}
		} else if (length == 9) {
			sb.append(small).append(big);
		}
		return sb.toString();
	}

	private String milleniumTreat(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append("M");
		}
		return sb.toString();
	}

	private String getGroup(int length, String letter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(letter);
		}
		return sb.toString();
	}

	public String romanToNum(String num) {
		return null;
	}

	public String add(String num1, String num2) {
		return null;
	}

	public String diff(String num1, String num2) {
		return null;
	}

}
