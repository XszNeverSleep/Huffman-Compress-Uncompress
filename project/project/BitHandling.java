package project;
import java.io.*;

/**
 * This class shows how to use bitwise operators like '<<' and '&' for reading
 * (or writing) bits in (or from) a byte value.
 */
public class BitHandling {

    // constants
    private static final int HALF_BYTE   = 128;
    private static final int MAX_BYTE    = 255;
    private static final int BYTE_LENGTH =   8;
    private File file;
    private FileInputStream fis;

    public BitHandling(String path) throws FileNotFoundException {
        file = new File(path);
        fis = new FileInputStream(file);
    }

    public byte[] readFileBytes() {
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public int readByte() throws IOException {
        int byteRead;
        if((byteRead = fis.read()) == -1)
               throw new IOException("read out of file range!");
        return byteRead;
    }

    public void writeFile(byte[] bytes, boolean isCompressed) {
        String newPath;
        if(isCompressed)
            newPath= file.getPath() + ".hu";
        else
            newPath = file.getPath().substring(0, file.getPath().length() - 3);
        File newFile = new File(newPath);

        try (FileOutputStream fos = new FileOutputStream(newFile)) {
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * The main method calls the writeByte and readByte methods.
     */
    public static void main(String[] args) throws FileNotFoundException {
        byte[] bytes = new byte[5];
        MagicNumber.addMagicNumber();


        }




}
