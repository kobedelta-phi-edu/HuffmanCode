package midlab2.datastructure;

/**
 * Leaf of the Tree
 * @param <E> Object
 */
public class Leaf<E> extends Tree {
    public final E value;

    /**
     * @param freq frequency
     * @param val value
     */
    public Leaf(int freq, E val) {
        super(freq);
        this.value = val;
    }
}
