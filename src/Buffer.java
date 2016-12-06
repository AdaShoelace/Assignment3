import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Buffer {

    private Queue<String> writerQueue, readerQueue;
    private Semaphore writerSem, readerSem;

    public Buffer(){
        writerQueue = new LinkedList<String>();
        readerQueue = new LinkedList<String>();
        writerSem = new Semaphore(1);
        readerSem = new Semaphore(1);
    }

    /**
     * Method to add strings to the buffer of strings _TO_BE_ encrypted.
     * @param writerQueueInput
     */
    public void addToWriterQueue(String writerQueueInput){
        try {
            writerSem.acquire();
            writerQueue.add(writerQueueInput);
            for(String s : writerQueue){
                System.out.println(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writerSem.release();
        }
    }

    /**
     * Returns first element of writerQueue
     * @return
     */
    public String fetchFromWriterQueue(){

        String res = null;

        try {
            writerSem.acquire();
            res = writerQueue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writerSem.release();
        }

        return res;

    }

    /**
     * Adds readerQueueInput to the readerQueue
     * @param readerQueueInput
     */
    public void addToReaderQueue(String readerQueueInput){
        try {
            readerSem.acquire();
            readerQueue.add(readerQueueInput);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readerSem.release();
        }
    }

    public int getWriterSize(){
        return writerQueue.size();
    }

    /**
     * Just for testing
     */
    public void printReaderQueue(){
        for(String s : readerQueue){
            System.out.println(s);
        }
    }
}
