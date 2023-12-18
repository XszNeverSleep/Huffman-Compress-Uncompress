package project;

import java.io.IOException;


public class Util {

    private static final int BASE = 256;
    public static String getBinaryString(int num) {
        StringBuilder byteString = new StringBuilder();
        String tmp = Integer.toBinaryString(num);
        int fix = 8 - tmp.length();
        byteString.append("0".repeat(Math.max(0, fix)));
        byteString.append(tmp);
        return byteString.toString();
    }

    public static String getBinaryString(byte val) {
        StringBuilder result = new StringBuilder();
        byte a = val; ;
        for (int i = 0; i < 8; i++){
            byte c = a;
            a = (byte)(a >> 1);
            a = (byte)(a << 1);
            if(a == c){
                result.insert(0, "0");
            }else{
                result.insert(0, "1");
            }
            a = (byte)(a >> 1);
        }
        return result.toString();
    }

    public static byte[] stringToBytes(String fileData) {
        byte[] bytes = new byte[fileData.length() / 8];
        int index = 0;
        for(int i = 0; i < fileData.length(); i += 8) {
            bytes[index++] = (byte) Integer.parseInt(fileData.substring(i, i + 8), 2);
        }
        return bytes;
    }
    public static String addInteger(int value) {
        int n = 0;
        StringBuilder res = new StringBuilder();
        while (value != 0) {
            int num = value % BASE;
            res.append(getBinaryString(num));
            value /= BASE;
            n++;
        }

        return getBinaryString(n) + res;
    }

    public static int getInteger(BitHandling bitHandling) throws IOException {
        int m = bitHandling.readByte();
//        System.out.println("m = " + m);
        int num = 0;
        for(int i = 0; i < m; i++) {
            num += (int) (Math.pow(BASE, i) * bitHandling.readByte());
        }
        return num;
    }
}
