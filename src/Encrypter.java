/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Encrypter extends Thread{

    private Buffer buffer;

    public Encrypter(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        String toAdd;
        for(int i = 0; i < buffer.getWriterSize(); i++){
            toAdd = buffer.fetchFromWriterQueue();
            toAdd = encrypt(toAdd);
            buffer.addToReaderQueue(toAdd);
        }

        buffer.printReaderQueue();
    }

    /**
     * Method to reverse given string
     * @param toEncrypt
     * @return the input string in reversed order
     */
    private String encrypt(String toEncrypt) {
        String result = new StringBuffer(toEncrypt).reverse().toString();
        return result;
    }

}
