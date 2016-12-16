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
        String toAdd = "";
        while(true){
            toAdd = buffer.fetchFromWriterQueue();
            if(toAdd != null){
                buffer.addToReaderQueue(encrypt(toAdd));
            }
            try {
                //buffer.printReaderQueue(); //Remove when done
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * Method to reverse given string
     * @param toEncrypt
     * @return the input string in reversed order
     */
    private String encrypt(String toEncrypt) {
        //String result = new StringBuffer(toEncrypt).reverse().toString();

        String result = "";

        for(int i = toEncrypt.length() - 1; i >= 0; i--){
            result += toEncrypt.charAt(i);
        }

        return result;
    }

}
