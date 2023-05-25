package midlab2.datastructure;

/**
 * TreeNode class
 * @param <E> Object
 */
public class TreeNode<E> extends Tree {
    E data; // data stored in a TreeNode
    TreeNode<E> prev; // previous node
    TreeNode next; // next node
    public Tree left; // left leaf of the Tree
    public Tree right; // right leaf of the Tree

    /**
     * empty constructor for TreeNode
     */
    public TreeNode() {}

    /**
     * @param prev previous TreeNode
     * @param item data to be stored in the TreeNode
     * @param next next TreeNode
     */
    public TreeNode(TreeNode<E> prev, E item, TreeNode<E> next) {
        this.data = item;
        this.next = next;
        this.prev = prev;
    }

    /**
     * @param item data to be stored in the TreeNode
     * @param next next TreeNode
     */
    TreeNode(E item, TreeNode<E> next) {
        this.data = item;
        this.next = next;
    }

    /**
     * @param left  the left leaf of a tree
     * @param right the right leaf of a tree
     */
    public TreeNode(Tree left, Tree right) {
        super(left.frequency + right.frequency);
        this.left = left;
        this.right = right;
    }

    /**
     * sets a data to a TreeNode
     * @param data data to be set
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * @return data to be get
     */
    public E getData() {
        return data;
    }

    /**
     * @return link that points a pointer to the next node
     */
    public TreeNode<E> getNext() {
        return next;
    }
}
