import javax.swing.*;
import java.io.*;

/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Writer extends Thread{

    private File inputFile;
    private JTextArea srcText;
    private Buffer buffer;

    public Writer(File inputFile, JTextArea srcText, Buffer buffer){
        this.inputFile = inputFile;
        this.srcText = srcText;
        this.buffer = buffer;
    }

    /*
        Something not right with the synchronization in "addToWriterQueue"...
        Want to be able to write the whole file to the buffer before releasing
        the semaphore permit.
     */
    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            for(String line; (line = br.readLine()) != null;){
                buffer.addToWriterQueue(line);
                srcText.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
