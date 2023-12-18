package project;

import java.io.*;
import java.beans.*;
import java.util.Objects;

/**
 * This is the MODEL class for your project (MVC architecture)
 * You must complete this file but you must not change the profile
 * of the public methods (except adding throws clause)
 */
public class HuffmanModel {
	
	private PropertyChangeSupport support;


	
	/**
	 * Creates a HuffmanModel instance
	 */
	public HuffmanModel() {
		support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
	
	/**
	 * Compress the file 'inputFile'
	 */
	public void compress(File inputFile) throws IOException {
		CompressFile cf = new CompressFile(inputFile.getPath());
		cf.compress();
		inputFile.delete();
		support.firePropertyChange("msg",null , inputFile.getName() + "the file has been compressed");
	}
	
	/**
	 * Uncompress the file 'inputFile'
	 */
	public void uncompress(File inputFile) throws IOException {
		UncompressFile cf = new UncompressFile(inputFile.getPath());
		try {
			cf.uncompress();
			inputFile.delete();
			support.firePropertyChange("msg",null , inputFile.getName() + " has been uncompressed");
		}catch (IOException e) {
			if(e.getMessage().equals("MAGIC-ERROR")) {
				support.firePropertyChange("msg",null , inputFile.getName() + " doesn't fit the magic number!");
			}
		}catch (NullPointerException e) {
			if(e.getMessage().equals("HUFFMANTREE-ERROR"))
				support.firePropertyChange("msg",null , inputFile.getName() + " Huffman Tree wrong!");
		}



	}
}
