package project;

import org.w3c.dom.Node;

import java.io.*;

import static project.MagicNumber.checkMagicNumber;
import static project.Util.*;

/**
 * A class for file uncompression
 */
public class UncompressFile {

    private String path;

    private BitHandling bitHandling;

    private HuffmanTree hfTree;
    public UncompressFile(String path) throws FileNotFoundException {
        this.path = path;
        bitHandling = new BitHandling(path);
        hfTree = new HuffmanTree();
    }

    /**
     * Uncompress a file
     */
    public void uncompress() throws IOException {
    	System.out.println("file uncompression");
        checkMagicNumber(bitHandling);;
        hfTree.delinearization(bitHandling);
        int bitsNum = getInteger(bitHandling);
        System.out.println(bitsNum);
        String compressedData = getCompressedData(bitsNum);
//        hfTree.preOrder();
        String fileData = hfTree.decode(compressedData);
        byte[] bytes = stringToBytes(fileData);
        bitHandling.writeFile(bytes, false);
    }

    public String getCompressedData(int bitsNum) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int bits1 = bitsNum / 8;
        for(int i = 0; i < bits1; i++) {
            int num = bitHandling.readByte();
            stringBuilder.append(getBinaryString(num));
        }
        int bits2 = bitsNum % 8;
        if(bits2 != 0) {
            int num = bitHandling.readByte();
            stringBuilder.append(getBinaryString(num), 0, bits2);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        byte[] bytes = {1, 0, 97, 1, 1, 0, 99, 0, 98, 1, 1, 0, 102, 0, 101, 0, 100};
        HuffmanTree htTree = new HuffmanTree();


    }
}
