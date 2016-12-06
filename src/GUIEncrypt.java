


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.CookieStore;

import javax.swing.*;

/**
 * The GUI for assignment 2
 */
public class GUIEncrypt 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;			// The Main window
	private JLabel lblSrc;			// The source text
	private JLabel lblDst;			// The encrypted text

	private JTextArea txtSrc;		// The input field for source text
	private JTextArea txtDst;		// The input field for encrypted text
	private JButton btnEnc;         // The Encrypt button
	private JButton btnDec;			// The Decrypt button
	private JButton btnLoad;		// Load file button
	private File file;
    private Buffer buffer;

	/**
	 * Constructor
	 */
	public GUIEncrypt() {
        buffer = new Buffer();
	}
	
	/**
	 * Starts the application
	 */
	public void start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 893, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Simple Encryption");
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI() {
		// First, create the static components
		JLabel lab1 = new JLabel("Plain Text");
		lab1.setBounds(13, 13, 74, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Encrypted Text");
		lab2.setBounds(483, 13, 99, 13);
		frame.add(lab2);
		
		// Then add the two lists (of string) 
		txtSrc = new JTextArea();
		txtSrc.setEditable(false);
		JScrollPane s1 = new JScrollPane(txtSrc);
		s1.setBounds(12, 35, 356, 264);
		s1.setBorder(BorderFactory.createLineBorder(Color.black));		
		frame.add(s1);
		txtDst = new JTextArea();
		txtDst.setEditable(false);
		JScrollPane s2 = new JScrollPane(txtDst);
		s2.setBounds(486, 35, 393, 264);
		s2.setBorder(BorderFactory.createLineBorder(Color.black));		
		frame.add(s2);
		
		// The buttons
		btnEnc = new JButton("Encrypt ->");
		btnEnc.setBounds(374, 102, 106, 23);
        btnEnc.addActionListener(new ChooseFileListener());
		frame.add(btnEnc);
		btnDec = new JButton("<- Decrypt");
		btnDec.setBounds(374, 141, 106, 23);
		frame.add(btnDec);
		btnLoad = new JButton("Load Working Text");
		btnLoad.setBounds(343, 319, 159, 23);
        btnLoad.addActionListener(new ChooseFileListener());
		frame.add(btnLoad);
	}

	private class ChooseFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == btnLoad){
			   fileChooser();
		   }else if(e.getSource() == btnDec){

		   }else if(e.getSource() == btnEnc){
			   Writer w = new Writer(file, txtSrc, buffer);
			   Encrypter enc = new Encrypter(buffer);
			   w.start();
               enc.start();
		   }
        }

		/**
		 * Method to open JFileChooser and retrieve the file we wish to encrypt.
		 */
		private void fileChooser(){
			int returnVal;
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				file = fc.getSelectedFile();
                System.out.println("File chosen: " + file.getName());
            }
		}

    }

    public static void main(String[] args) {
        GUIEncrypt gui = new GUIEncrypt();
        gui.start();
    }

}
