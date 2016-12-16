import sun.awt.Mutex;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Buffer {

    private LinkedList<String> writerQueue, readerQueue;
    private Semaphore writerSem, readerSem, encSem;
    private Mutex readMutex, writeMutex;

    public Buffer(){
        writerQueue = new LinkedList<String>();
        readerQueue = new LinkedList<String>();
        writerSem = new Semaphore(1);
        readerSem = new Semaphore(1);
        encSem = new Semaphore(1);
        readMutex = new Mutex();
        writeMutex = new Mutex();
    }

    /**
     * Method to add strings to the buffer of strings _TO_BE_ encrypted.
     * @param writerQueueInput
     */
    public void addToWriterQueue(String writerQueueInput){
        try {
            writerSem.acquire();
            writeMutex.lock();
            writerQueue.add(writerQueueInput);
            for(String s : writerQueue){
                System.out.println(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeMutex.unlock();
            encSem.release();
        }
    }

    /**
     * Returns first element of writerQueue
     * @return
     */
    public String fetchFromWriterQueue(){

        String res = null;

        try {
            encSem.acquire();
            writeMutex.lock();
            res = writerQueue.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeMutex.unlock();
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
            encSem.acquire();
            readMutex.lock();
            readerQueue.add(readerQueueInput);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readMutex.unlock();
            readerSem.release();
        }
    }

    /**
     * Returns first element of the reader queue
     * @return toFetch
     */
    public String fetchFromReaderQueue(){
        String toFetch = "";

        try{
            readerSem.acquire();
            readMutex.lock();
            toFetch = readerQueue.poll();
        } catch(InterruptedException e){

            e.printStackTrace();

        } finally{
            readMutex.unlock();
            encSem.release();
        }
        return toFetch;
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
