import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private final static int EXTENDED_ASCII = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String text = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(text);
        int length = csa.length();
        // finding first
        for (int i = 0; i < length; i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        // iterating through to find t
        for (int i = 0; i < length; i++) {
            char t = getLastChar(csa, text, i);
            BinaryStdOut.write(t);
        }
        BinaryStdOut.close();
    }

    // getting t
    private static char getLastChar(CircularSuffixArray csa, String text, int i) {
        return text.charAt((csa.index(i) + csa.length() - 1) % csa.length());
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        char[] tArray = t.toCharArray();
        // key index counting
        int n = t.length();
        int[] count = new int[EXTENDED_ASCII + 1];
        int[] next = new int[n];
        // compute frequencies
        for (int i = 0; i < n; i++) {
            count[tArray[i] + 1]++;
        }
        // compute cumulative frequencies
        for (int i = 0; i < EXTENDED_ASCII; i++) {
            count[i + 1] += count[i];
        }
        for (int i = 0; i < n; i++) {
            next[count[tArray[i]]++] = i;
        }
        for (int i = 0; i < n; i++) {
            BinaryStdOut.write(t.charAt(next[first]));
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if ("-".equals(args[0])) {
            transform();
        }
        else {
            inverseTransform();
        }
    }

}
