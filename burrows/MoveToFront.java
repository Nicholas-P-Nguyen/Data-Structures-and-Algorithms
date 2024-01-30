import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // extended ASCII
    private static final int R = 256;


    // apply move-to-front encoding, reading from stdin and writing to stdout
    public static void encode() {
        char[] chars = new char[R];
        for (char c = 0; c < R; c++) {
            chars[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            char index = '\u0000';
            for (char i = 0; i < R; i++) {
                if (chars[i] == c) {
                    index = i;
                    break;
                }
            }
            // moving to the front
            for (int i = index - 1; i >= 0; i--) {
                chars[i + 1] = chars[i];
            }
            BinaryStdOut.write(index);
            chars[0] = c;
        }
        BinaryStdOut.close();
    }


    // apply move-to-front decoding, reading from stdin and writing to stdout
    public static void decode() {
        char[] chars = new char[R];
        for (char c = 0; c < R; c++) {
            chars[c] = c;
        }
        while (!BinaryStdIn.isEmpty()) {
            char index = BinaryStdIn.readChar();
            char out = chars[index];
            // moving to the front
            for (int i = index - 1; i >= 0; i--) {
                chars[i + 1] = chars[i];
            }
            BinaryStdOut.write(out);
            chars[0] = out;
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            encode();
        }
        else {
            decode();
        }
    }

}
