package midlab2.datastructure;

/**
 * Exception thrown when a mishandling or bug occurred in the utilization of the Queue interface
 */
public class QueueException extends RuntimeException {
    public QueueException(){
        super();
    }
    public QueueException(String err) {
        super(err);
    }

}
