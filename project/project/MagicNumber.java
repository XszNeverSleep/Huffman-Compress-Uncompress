package project;

import javax.annotation.processing.FilerException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static project.Util.addInteger;
import static project.Util.getInteger;


public class MagicNumber {

        private static final int MAGICNUMBER = 123456789;

        public static void checkMagicNumber(BitHandling bitHandling) throws IOException {
            if(getInteger(bitHandling) != MAGICNUMBER)
                    throw new IOException("MAGIC-ERROR");
        }

        public static String addMagicNumber() {
                return addInteger(MAGICNUMBER);
        }


}
