import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Writer extends Thread{

    private File inputFile;
    private JTextArea srcText, output;
    private Buffer buffer;
    private boolean encryptMode;

    public Writer(File inputFile, JTextArea srcText, Buffer buffer, boolean encryptMode){
        this.inputFile = inputFile;
        this.srcText = srcText;
        this.buffer = buffer;
        this.encryptMode = encryptMode;
    }

    public Writer(JTextArea srcText, JTextArea output,Buffer buffer, boolean encryptMode){
        this.srcText = srcText;
        this.buffer = buffer;
        this.encryptMode = encryptMode;
        this.output = output;
    }

    /**
        Something not right with the synchronization in "addToWriterQueue"...
        Want to be able to write the whole file to the buffer before releasing
        the semaphore permit.
     */
    @Override
    public void run() {
        String line;
        Scanner scan;
        try {

            if (encryptMode == false) {
                scan = new Scanner(output.getText());
            } else {
                scan = new Scanner(inputFile);
            }

            while(scan.hasNext()){
                line = scan.nextLine();
                buffer.addToWriterQueue(line);
                srcText.append(line + "\n");
                Thread.sleep(500);
            }

    } catch (FileNotFoundException | InterruptedException e){
            e.printStackTrace();
        }
    }



}
