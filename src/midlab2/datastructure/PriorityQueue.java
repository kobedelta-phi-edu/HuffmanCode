package midlab2.datastructure;

/**
 * implements the Queue interface while keeping the queue in order
 * @param <E> Object
 */
public class PriorityQueue<E> implements Queue<E> {
    private TreeNode<E> front; //the first object in the queue
    private int count = 0; // the size of the queue

    /**
     * empty constructor PriorityQueue
     */
    public PriorityQueue() { }

    /**
     * @return size of the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * @return the first object in the queue
     * @throws QueueException thrown when the queue is empty.
     */
    @Override
    public E first() throws QueueException {
        if (isEmpty())
            throw new QueueException("Sorry, The queue is empty.");
        return front.getData();
    }

    /**
     * @return data of the first item in the queue
     * @throws QueueException thrown when the queue is empty
     */
    @Override
    public E dequeue() throws QueueException {
        TreeNode<E> temp;
        if (isEmpty())
            throw new QueueException("Dequeue operation failed. The queue is empty.");
        temp = front;
        front = front.getNext();
        count--;
        return temp.getData();
    }

    /**
     * gets the count of items in the queue
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * queues a TreeNode to the list
     * @param item data to be queued
     * @param freq frequency of the data
     * @throws QueueException thrown when an error in the queue has occurred
     */
    public void enqueue(E item, int freq) throws QueueException {
        boolean isInserted = false;
        TreeNode<E> newNode = new TreeNode<>();
        newNode.frequency = freq;
        newNode.setData(item);
        if (front == null) {
            front = newNode;
            isInserted = true;
        }
        if (front.frequency >= newNode.frequency && !isInserted) {
            newNode.next = front;
            front = newNode;
            isInserted = true;
        }
        if (front.frequency < newNode.frequency && front.next == null && !isInserted){
            front.next = newNode;
            isInserted = true;
        }
        if (count == 2 && front.next.frequency < newNode.frequency && !isInserted){
            front.next.next = newNode;
            isInserted = true;
        }
        if (count == 2 && front.next.frequency > newNode.frequency && !isInserted){
            newNode.next = front.next;
            front.next = newNode;
            isInserted = true;
        }

        TreeNode curr = front;
        if (!isInserted) {
            while (curr.next != null && curr.next.frequency <= newNode.frequency) {
                curr = curr.next;
            }
            if (curr.next == null) {
                curr.next = newNode;
                isInserted = true;
            }
            if (!isInserted) {
                newNode.next = curr.next;
                curr.next = newNode;
            }
        }
        count++;
        }
    }