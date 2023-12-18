package project;

import java.io.FileNotFoundException;

import static project.Util.addInteger;
import static project.Util.stringToBytes;

/**
 * A class for file compression
 */
public class CompressFile {

    /**
     * Makes a file compressor
     */

    private String path;

    private BitHandling bitHandling;
    private HuffmanTree hfTree;
    public CompressFile(String path) throws FileNotFoundException {
        this.path = path;
        hfTree = new HuffmanTree();
        bitHandling = new BitHandling(path);
    }

    /**
     * Compress a file
     */
    public void compress() {
    	System.out.println("file compression");
        byte[] bytes = bitHandling.readFileBytes();
        byte[] compressedBytes = getBytes(bytes);
        bitHandling.writeFile(compressedBytes, true);
    }
    public byte[] getBytes(byte[] bytes) {
        hfTree.create(bytes);
        hfTree.getHuffmanCodes();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MagicNumber.addMagicNumber());
        System.out.println(stringBuilder);
        stringBuilder.append(hfTree.linearization());
        String dataBits = hfTree.enCode(bytes);
        int n = dataBits.length();
        stringBuilder.append(addInteger(n));
        stringBuilder.append(dataBits);
        stringBuilder.append("0".repeat(8 - (stringBuilder.length() % 8)));
        return stringToBytes(stringBuilder.toString());
    }


}
