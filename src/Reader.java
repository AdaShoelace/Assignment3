import javax.swing.*;

/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Reader extends Thread{

    private JTextArea output, outputEncrypt;
    private Buffer buffer;
    boolean encryptMode;

    public Reader(JTextArea output,JTextArea outputEncrypt, Buffer buffer, boolean encryptMode){
        this.output = output;
        this.outputEncrypt = outputEncrypt;
        this.buffer = buffer;
        this.encryptMode = encryptMode;
    }

    @Override
    public void run(){
        String res;

        while(true){
            res = buffer.fetchFromReaderQueue();
            if(res != null){
                if(encryptMode == false){
                    outputEncrypt.append(res + "\n");
                } else {
                    output.append(res + "\n");
                }
            }
            try{
                Thread.sleep(500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
