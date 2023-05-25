package midlab2;

import midlab2.datastructure.*;

/**
 * this class constructs the huffman tree, produce huffman codes for each character, and decode a huffman code string
 */
public class HMBuilder {
    HMCode<Integer> hmCode;
    Tree huffTree;

    /**
     * constructs HMBuilder
     * @param frequencyList frequency list of an item
     */
    HMBuilder(int[] frequencyList){
        StringBuffer prefix = new StringBuffer();
        huffTree = constructTree(frequencyList);
        hmCode = new HMCode<>();
        produceCodes(huffTree,prefix);
        decode("");
    }

    /**
     * method that builds the tree for the huffman code
     * @param frequencyList the list of character frequencies
     * @return tree for huffman code
     */

    private Tree constructTree(int[] frequencyList) {
        PriorityQueue<Tree> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < frequencyList.length; i++) {
            if (frequencyList[i] > 0)
                priorityQueue.enqueue(new Leaf<>(frequencyList[i], i), frequencyList[i]);
        }
        while (priorityQueue.size() > 1) {
            Tree a = priorityQueue.dequeue();
            Tree b = priorityQueue.dequeue();
            priorityQueue.enqueue(new TreeNode(a, b), a.frequency + b.frequency);
        }
        return priorityQueue.dequeue();
    }

    /**
     * method that takes the produced codes from the tree
     * @param constructTree tree for huffman code
     * @param prefix character (0 || 1)
     */

    private void produceCodes(Tree constructTree, StringBuffer prefix) {
        if (constructTree instanceof Leaf) {
            @SuppressWarnings("unchecked")
            Leaf<Integer> leaf = (Leaf<Integer>) constructTree;
            hmCode.includeToList(leaf.value, leaf.frequency, prefix.toString());
        }
        else if (constructTree instanceof TreeNode) {
            TreeNode node = (TreeNode) constructTree;
            //traverse left
            prefix.append('0');
            produceCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
            // traverse right
            prefix.append('1');
            produceCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * decodes the input of the user and concatenates them on the tree
     * @param binary string of huffman code
     * @return decoded text of huffman code
     */

    String decode(String binary){
        StringBuilder onesZeroes = new StringBuilder(binary);
        Tree hmTree = huffTree;
        String text = "";
        int countBinary = 0;
        int countLeafBin = 0;

        for (int i = 0; i < onesZeroes.length(); i++){
            if (hmTree instanceof Leaf){
                text = text.concat(String.valueOf((char)(int) ((Leaf) hmTree).value));
                hmTree = huffTree;
                countLeafBin = countLeafBin + countBinary;
                countBinary = 0;
            }
            if (hmTree instanceof TreeNode) {
                if (onesZeroes.charAt(i) == '0') {
                    hmTree = ((TreeNode) hmTree).left;
                } else
                    hmTree = ((TreeNode) hmTree).right;
                countBinary++;
            }
        }
        if (hmTree instanceof Leaf) {
            text = text.concat(String.valueOf((char) (int) ((Leaf) hmTree).value));
            countLeafBin = countLeafBin + countBinary;
        }
        if (onesZeroes.length() != countLeafBin){
            throw new IllegalStateException("The huffman code is not convertible. Please use \"Convert to huffman\" tab again and base your input from the table generated.");
        }
        return text;
    }
}
