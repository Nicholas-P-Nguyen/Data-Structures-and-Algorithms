import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
    private int length; // length of the string
    private Integer[] index; // array of indices

    // circular suffix array of s
    public CircularSuffixArray(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text is null");
        }
        length = text.length();
        index = new Integer[length];
        // iterating through indexing 0 -> length
        for (int i = 0; i < length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int firstIndex = o1;
                int secondIndex = o2;

                for (int i = 0; i < length; i++) {
                    char firstChar = text.charAt(firstIndex % length);
                    char secondChar = text.charAt(secondIndex % length);
                    if (firstChar < secondChar) {
                        return -1;
                    }
                    else if (firstChar > secondChar) {
                        return +1;
                    }
                    firstIndex++;
                    secondIndex++;
                }
                return 0;
            }
        });
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length - 1) {
            throw new IllegalArgumentException("index out of range");
        }
        return index[i];
    }


    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray testing = new CircularSuffixArray("weekend");
        System.out.println("length should be 7: " + testing.length());
        for (int i = 0; i < testing.length(); i++) {
            System.out.println("index: " + i + " = " + testing.index(i));
        }


    }

}
