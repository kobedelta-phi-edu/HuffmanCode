package midlab2.datastructure;

/**
 * class that compares the frequency of the values in the tree
 */
public class Tree implements Comparable<Tree> {
    public int frequency; // frequency of a character

    /**
     * @param freq frequency of a character in the Tree
     */
    Tree(int freq) {
        frequency = freq;
    }

    /**
     * empty constructor for Tree
     */
    public Tree() { }

    /**
     * @param tree tree
     * @return frequency of a value
     */
    public int compareTo(Tree tree) {
        return frequency - tree.frequency;
    }
}
