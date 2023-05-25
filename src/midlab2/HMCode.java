package midlab2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * constructs huffman code
 * @param <E> Object
 */
public class HMCode<E> {
    private final ArrayList<Integer> frequency;
    private final ArrayList<E> object;
    private final ArrayList<String> code;

    /**
     * instantiates frequency, object, and code as new ArrayLists
     */
    HMCode(){
        frequency = new ArrayList<>();
        object = new ArrayList<E>();
        code = new ArrayList<>();
    }

    /**
     *
     * @param object object (character) to be added
     * @param frequency frequency of the object (character) to be added
     * @param code huffman code of the object (character) to be added
     */
    void includeToList (E object, int frequency, String code) {
        this.object.add(object);
        this.frequency.add(frequency);
        this.code.add(code);
    }

    /**
     * gets the huffman code af a character
     * @param bits bits
     * @return huffman code of a character
     */
    String getCode(int bits){
        try {
            return code.get(object.indexOf(bits));
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Data does not exist.");
        }
     return "";
    }

    /**
     * displays a table of characters and their respective huffman code and number of bits
     */
    void displayHuffmanTable() {
        double totalNumBits = 0, totalFreq = 0, huffmanBits = 0;
        System.out.println("\nTEXT TO HUFFMAN");
        System.out.println("===========================================================");
        System.out.printf("%7s%12s%21s%18s", "Char", "Code", "Number of Bits", "Frequency\n");
        System.out.println("===========================================================");

        List<List<String>> input = new ArrayList<>();
        List<String> subInput;
        subInput = new ArrayList<>();
        for (int i = 0; i < object.size(); i++){
            subInput.add(String.valueOf((char)(int)object.get(i)));
            subInput.add(code.get(i));
            subInput.add(Integer.toString(code.get(i).length()));
            subInput.add(Integer.toString(frequency.get(i)));
            input.add(subInput);
            subInput = new ArrayList<>();

            totalNumBits += code.get(i).length();
            totalFreq += frequency.get(i);
            huffmanBits += (frequency.get(i) * code.get(i).length());
        }
        input.sort(Comparator.comparing(a -> a.get(0)));
        int j = 0;
        for(int i = 0; i < input.size(); i++){
            System.out.printf("%5s%12s%15s%20s\n", input.get(i).get(j), input.get(i).get(j + 1), input.get(i).get(j + 2), input.get(i).get(j+3));
        }

        double asciiBits = totalFreq * 7;
        double savings = ((asciiBits-huffmanBits)/asciiBits) * 100;
        System.out.println("===========================================================");
        System.out.println("Total Number of Bits: " + totalNumBits);
        System.out.print("Percentage of Storage Savings: " + (double) Math.round(savings * 100)/100 + " %");
        System.out.println("\n===========================================================");
    }
}
