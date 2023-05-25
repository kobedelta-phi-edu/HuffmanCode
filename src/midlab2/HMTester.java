package midlab2;

import java.util.Scanner;

/**
 * an executable class that allows the user to convert text to huffman code and vice versa
 */
public class HMTester {
    final private Scanner scanner = new Scanner(System.in);
    HMBuilder hb;

    /**
     * calls the necessary methods for the execution of the program.
     * provides the user a menu interface where he/she can choose what he wants to do with the program.
     */
    private void run() {
        boolean isChecked = false;
        while (true) {
            System.out.println("\nWhat would you like to do?\n" +
                    "[1] Convert Text to Huffman\n" +
                    "[2] Convert Huffman to Text\n" +
                    "[3] Exit");
            byte choice = inputByte();
            switch (choice) {
                case 1 : convertTextToHuffman(input()); isChecked = true; break;
                case 2 : if(isChecked) convertHuffmanToText(input());
                        else
                            throw new IllegalStateException("Please use \" Convert Text to Huffman\" tab first to generate the needed values");
                        break;
                case 3 : exit();
                default: {
                        System.err.println("Invalid. Please input from numbers 1-3 only.");
                        run();
                }
            }
        }
    }

    /**
     * checks if a user input is a valid numerical value
     * @return user input for the menu interface
     */
    private byte inputByte() {
        while (true) {
            try {
                System.out.print("Choice: ");
                return Byte.parseByte(scanner.next());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Input a number only. Please try again.");
            }
        }
    }

    /**
     * prompts the user to input an expression
     * @return string to be used for conversion
     */
    String input() {
        System.out.print("\nInput an Expression: ");
        return new Scanner(System.in).nextLine();
    }

    /**
     * exits the program
     */
    void exit() {
        System.out.println("\nGoodbye!");
        System.exit(0);
    }

    /**
     * converts text to huffman code and outputs a table of characters and their corresponding huffman codes and frequencies
     * @param textString text to be converted to huffman code
     */
    private void convertTextToHuffman(String textString) {
        int[] frequency = new int[256];
        for (char c : textString.toCharArray()) {
            frequency[c]++;
        }
        hb = new HMBuilder(frequency);
        hb.hmCode.displayHuffmanTable();
        System.out.print("Huffman Code: ");
        for (char eachCharacter : textString.toCharArray()) {
            System.out.print(hb.hmCode.getCode(eachCharacter));
        }
        System.out.println("");
    }
    /**
     * converts huffman code to text
     */
    public void convertHuffmanToText(String huffmanCode){
        System.out.println("\n\nHUFFMAN TO TEXT ");
        for(int i = 0; i < huffmanCode.length(); i++){
            if (!Character.isDigit(huffmanCode.charAt(i)))
                throw new NumberFormatException("Invalid input. Please input binary values only that is given in the table.");
            int intHuff = Integer.parseInt(String.valueOf(huffmanCode.charAt(i)));
            if(intHuff < 0 || intHuff > 1)
                throw new NumberFormatException("Invalid input. Please input binary values only that is given in the table.");
        }

        System.out.println("Decoded text: " + hb.decode(huffmanCode));
        System.out.println("");
    }

    /**
     * executes the program
     * @param args args
     */
    public static void main(String[] args) {
        checkTester();
    }

    public static void checkTester(){
        System.out.println("************" + "\n* Welcome! *" + "\n************");
        try {
            HMTester hmTester = new HMTester();
            hmTester.run();
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.out.println();
            checkTester();
        }
    }
}