package midlab2.datastructure;

/**
 * Queue interface to be implemented in the program
 * @param <E>
 */
public interface Queue<E> {
    /** @return the size of the queue */
    int size();

    /** @return true if the queue is empty */
    boolean isEmpty();

    /** @return the first object in the queue */
    E first() throws QueueException;

    /** @return the size of the queue */
    E dequeue() throws QueueException;

    /**
     * queues an item in the list while maintaining order
     * @param item item to be queued
     * @param freq frequency of the item
     * @throws QueueException thrown when an error in the queue has occurred
     */
    void enqueue(E item, int freq) throws QueueException;
    }
