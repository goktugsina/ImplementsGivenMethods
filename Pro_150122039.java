/*This program implements the given methods (numOfChars, printWords...)
 * Göktuğ Sina Bekçioğulları

 * */

import java.util.Scanner;

public class Pro_150122039 {

    // The method that finds given char's occurence in the given string
    public static int numOfChars(String str, char ch){
        // Declare variable outside the loop to make it accessible
        int k = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch)
                k++;
        }
        return k;
    }

    // The method that takes an input sentence as a string
    // and prints the words inside it. A word is a sequence of characters without any whitespaces and
    // punctuation marks.
    public static void printWords(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) == ',') || (str.charAt(i) == '.') || (str.charAt(i) == '!') ||
                    (str.charAt(i) == '?') || (str.charAt(i) == '_') || (str.charAt(i) == '-') || (str.charAt(i) == '(')
                    || (str.charAt(i) == ')') || (str.charAt(i) == ' '))
            {
                if ((i != 0) && !((str.charAt(i -1) == ',') || (str.charAt(i - 1) == '.') || (str.charAt(i -1) == '!') ||
                        (str.charAt(i-1) == '?') || (str.charAt(i-1) == '_') || (str.charAt(i-1) == '-') || (str.charAt(i-1) == '(')
                        || (str.charAt(i-1) == ')') || (str.charAt(i-1) == ' '))) {
                        System.out.println();
                }
            }
            else{
                System.out.print(str.charAt(i));
            }
        }
    }

    // The method that finds a substring in a given string
    // and delete it’s first occurrence if the type argument is 0 or delete it’s all occurrences if the type
    // argument is 1.
    public static String delete(String str, String subStr, int type) {
        if (type == 0) {
            // Deletes the first occurrence of the substring
            int index = str.indexOf(subStr);
            if (index != -1) {
                return str.substring(0, index) + str.substring(index + subStr.length());
            }
        } else if (type == 1) {
            // Deletes all occurrences of the substring
            String result = "";
            int index = 0;
            while (index < str.length()) {
                int nextIndex = str.indexOf(subStr, index);
                if (nextIndex == -1) {
                    result += str.substring(index);
                    break;
                }
                result += str.substring(index, nextIndex);
                index = nextIndex + subStr.length();
            }
            return result;
        }
        return str;
    }

    // The method that finds a substring (subStr1) in the
    // given string (str) and replaces it’s all occurrences with the given substring as the third argument
    // (subStr2).
    public static String replace(String str, String subStr1, String subStr2) {
        String result = "";
        int index = 0;
        int nextIndex = str.indexOf(subStr1);

        while (nextIndex != -1) {
            result += str.substring(index, nextIndex);
            result += subStr2;
            index = nextIndex + subStr1.length();
            nextIndex = str.indexOf(subStr1, index);
        }

        result += str.substring(index);

        return result;
    }

    // The sorting method
    public static String sortChars(String str, int type) {
        if (type == 0) {
            // Sort characters based on ASCII value from low to high
            str = sortAscii(str);
        } else if (type == 1) {
            // Sort characters based on custom order
            str = sortCustomOrder(str);
        }

        return str;
    }

    public static String sortAscii(String str) {
        char[] sortedChars = str.toCharArray();

        for (int i = 0; i < sortedChars.length - 1; i++) {
            for (int j = 0; j < sortedChars.length - 1 - i; j++) {
                if (sortedChars[j] > sortedChars[j + 1]) {
                    // Swap characters in case they are out of order
                    char temp = sortedChars[j];
                    sortedChars[j] = sortedChars[j + 1];
                    sortedChars[j + 1] = temp;
                }
            }
        }

        return new String(sortedChars);
    }

    public static String sortCustomOrder(String str) {
        int[] charTypes = new int[128];

        for (char c : str.toCharArray()) {
            charTypes[c]++;
        }

        String sortedString = "";

        for (char c = 'a'; c <= 'z'; c++) {
            sortedString = addChars(sortedString, c, charTypes[c]);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            sortedString = addChars(sortedString, c, charTypes[c]);
        }

        for (char c = '0'; c <= '9'; c++) {
            sortedString = addChars(sortedString, c, charTypes[c]);
        }

        for (char c = 0; c < 128; c++) {
            if (!Character.isLetterOrDigit(c)) {
                sortedString = addChars(sortedString, c, charTypes[c]);
            }
        }

        return sortedString;
    }

    public static String addChars(String str, char c, int count) {
        for (int i = 0; i < count; i++) {
            str += c;
        }
        return str;
    }

    // The method that finds and returns a hash code of a
    // given string based on an integer value
    public static int hashCode(String str, int b) {
        int hashCode = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            hashCode += str.charAt(i) * Math.pow(b, n - 1 - i);
        }
        return hashCode;
    }

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Welcome to our String Analyzer Program.");

        while (true) {
            System.out.println("1. Count number of chars");
            System.out.println("2. Print the words in a sentence");
            System.out.println("3. Delete substring");
            System.out.println("4. Replace substring");
            System.out.println("5. Sort characters");
            System.out.println("6. Hash code of a string");
            System.out.print("Please enter your menu choice (for quiting type exit or quit):");

            String choice = inp.nextLine();

            if (choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("quit")) {
                System.out.println("Program ends. Bye :)");
                break;
            }

            switch (choice) {
                case "1":
                    System.out.print("Enter an input string: ");
                    String str = inp.nextLine();
                    System.out.print("Enter an input char: ");
                    String s = inp.nextLine();
                    char ch = s.charAt(0);
                    System.out.println("The number of " + s + " is " + numOfChars(str, ch) + ".");
                    break;
                case "2":
                    System.out.print("Enter an input string: ");
                    String str2 = inp.nextLine();
                    System.out.println("The output is: ");
                    printWords(str2);
                    break;
                case "3":
                    System.out.print("Enter an input string: ");
                    String str3 = inp.nextLine();
                    System.out.print("Enter an input substring: ");
                    String subStr = inp.nextLine();
                    System.out.print("Enter a type (0 or 1): ");
                    int type3 = inp.nextInt();
                    inp.nextLine();
                    System.out.println(delete(str3, subStr, type3));
                    break;
                case "4":
                    System.out.print("Enter an input string: ");
                    String str4 = inp.nextLine();
                    System.out.print("Enter the first substring: ");
                    String subStr1 = inp.nextLine();
                    System.out.print("Enter the second substring: ");
                    String subStr2 = inp.nextLine();
                    System.out.println(replace(str4, subStr1, subStr2));
                    break;
                case "5":
                    System.out.print("Enter an input string: ");
                    String str5 = inp.nextLine();
                    System.out.print("Enter a type (0 or 1): ");
                    int type5 = inp.nextInt();
                    inp.nextLine();
                    System.out.println(sortChars(str5, type5));;
                    break;
                case "6":
                    System.out.print("Enter an input string: ");
                    String str6 = inp.nextLine();
                    System.out.print("Enter an input integer: ");
                    int b = inp.nextInt();
                    inp.nextLine();
                    System.out.println("The hash code for "+ str6 + " is " + hashCode(str6, b) + ".");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
