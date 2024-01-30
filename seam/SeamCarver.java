import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
    // Picture instance variable
    private Picture picture;
    // width of picture
    private int width;
    // height of picture
    private int height;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException("Argument is null");

        // Creates deep copy of argument
        this.picture = new Picture(picture);
        this.width = picture.width();
        this.height = picture.height();
    }

    // current picture
    public Picture picture() {
        // Deep copy
        Picture returnPic = new Picture(picture);
        return returnPic;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x > width()) throw new IllegalArgumentException("X is outside range");
        if (y > height()) throw new IllegalArgumentException("Y is outside range");

        return Math.sqrt(calcEnergy(x + 1, y, x - 1, y) +
                                 calcEnergy(x, y + 1, x, y - 1));
    }

    // Calculates difference in RGB values for (x1, y1) and (x2, y2)
    // and adds their squares
    private double calcEnergy(int x1, int y1, int x2, int y2) {

        if (x1 > width() - 1)
            x1 = 0;
        if (y1 > height() - 1)
            y1 = 0;
        if (x2 < 0)
            x2 = width() - 1;
        if (y2 < 0)
            y2 = height() - 1;

        // RGB value of first coordinate
        int rgb1 = picture.getRGB(x1, y1);
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = (rgb1) & 0xFF;

        // RGB value of second coordinate
        int rgb2 = picture.getRGB(x2, y2);
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = (rgb2) & 0xFF;

        // Calculate gradient
        int r = r1 - r2;
        int g = g1 - g2;
        int b = b1 - b2;

        return (r * r) + (g * g) + (b * b);

    }

    // Assigns calculated energy to 2D array
    private double[][] populateEnergyMatrix() {
        double[][] matrix = new double[width()][height()];

        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                matrix[col][row] = energy(col, row);
            }
        }

        return matrix;
    }

    // transposes picture
    private void transpose(Picture source) {
        Picture returnPic = new Picture(height(), width());

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int rgb = source.getRGB(col, row);
                returnPic.setRGB(row, col, rgb);
            }
        }

        // Swap width and heights
        int temp = width;
        this.width = height();
        this.height = temp;


        picture = returnPic;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        transpose(picture);
        int[] seam = findVerticalSeam();

        // Reset picture
        transpose(picture);
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {


        int[] seam = new int[height()];
        double[][] energyMatrix = populateEnergyMatrix();
        // tracks previous energy
        int[][] edgeTo = new int[width()][height()];
        // tracks shortest distance
        double[][] distTo = new double[width()][height()];

        // initialize distTo matrix to infinity
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                // initialize top row to their respective energy
                if (row == 0) distTo[col][row] = energyMatrix[col][row];
                else distTo[col][row] = Double.POSITIVE_INFINITY;
            }
        }

        // Iterate through row major order
        // Find lowest energy paths
        for (int row = 1; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                // System.out.println("Row: " + row);
                double ogDistance = distTo[col][row - 1] + energyMatrix[col][row];
                // check bottom adjacent vertex
                if (ogDistance < distTo[col][row]) {
                    edgeTo[col][row] = col;
                    distTo[col][row] = ogDistance;
                }

                // check bottom-right adjacent vertex if necessary
                if (col < width() - 1) {
                    ogDistance = distTo[col][row - 1] + energyMatrix[col + 1][row];
                    if (ogDistance < distTo[col + 1][row]) {
                        edgeTo[col + 1][row] = col;
                        distTo[col + 1][row] = ogDistance;
                    }
                }

                // check bottom-left adjacent vertex if necessary
                if (col > 0) {
                    ogDistance = distTo[col][row - 1] + energyMatrix[col - 1][row];
                    if (ogDistance < distTo[col - 1][row]) {
                        edgeTo[col - 1][row] = col;
                        distTo[col - 1][row] = ogDistance;
                    }
                }
            }
        }

        // Find lowest energy in the bottom row
        double lowestEnergy = Double.POSITIVE_INFINITY;
        for (int col = 0; col < width(); col++) {
            if (distTo[col][height() - 1] < lowestEnergy) {
                lowestEnergy = distTo[col][height() - 1];
                seam[height() - 1] = col;
            }
        }

        // Trace back seam from bottom row to top row
        for (int row = height() - 1; row > 0; row--) {
            seam[row - 1] = edgeTo[seam[row]][row];
        }

        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("Argument is null");
        if (height() == 1) throw new IllegalArgumentException("Width is 1");
        if (seam.length != width)
            throw new IllegalArgumentException("Seam length is incorrect");

        transpose(picture);
        removeVerticalSeam(seam);

        // Reset picture
        transpose(picture);

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("Argument is null");
        if (width() == 1) throw new IllegalArgumentException("Height is 1");
        if (seam.length != height)
            throw new IllegalArgumentException("Seam length is incorrect");
        checkValidSeam(seam);

        Picture newPic = new Picture(width() - 1, height());

        // Copy pixels of original picture into new picture, except for seam
        for (int row = 0; row < height(); row++)
            for (int col = 0; col < width() - 1; col++) {
                if (col < seam[row])
                    newPic.setRGB(col, row, picture.getRGB(col, row));
                else
                    newPic.setRGB(col, row, picture.getRGB(col + 1, row));
            }

        // Point to new picture
        picture = new Picture(newPic);
        width--;

    }

    // checks if seam is within bounds and is a valid seam
    private void checkValidSeam(int[] seam) {
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0)
                throw new IllegalArgumentException("element is negative");
            if (seam[i] >= width())
                throw new IllegalArgumentException("element is greater than width");
            if (i > 0) {
                if (Math.abs(seam[i] - seam[i - 1]) > 1)
                    throw new IllegalArgumentException("Argument is not seam");
            }
            if (i < seam.length - 1) {
                if (Math.abs(seam[i] - seam[i + 1]) > 1)
                    throw new IllegalArgumentException("Argument is not seam");
            }

        }
    }

    //  unit testing (required)
    public static void main(String[] args) {
        Picture pic = new Picture(args[0]);
        SeamCarver test = new SeamCarver(pic);
        Picture pic2 = new Picture(pic);
        System.out.println("Is pic2 == pic? " + pic2.equals(test.picture()));

        pic.show();
        StdOut.println("Height: " + test.height());
        StdOut.println("Width: " + test.width());

        StdOut.println("Energy at (0, 0): " + test.energy(0, 0));


        // Find and remove horizontal seam
        int[] hseam = test.findHorizontalSeam();
        test.removeHorizontalSeam(hseam);

        StdOut.println("Height after removing horizontal seam: " + test.height());
        StdOut.println("Width after removing horizontal seam: " + test.width());
        System.out.println("Is pic2 == pic? " + pic2.equals(test.picture()));


        // Find and remove vertical seam
        int[] vseam = test.findVerticalSeam();
        test.removeVerticalSeam(vseam);

        StdOut.println("Height after removing vertical seam: " + test.height());
        StdOut.println("Width after removing vertical seam: " + test.width());

    }

}


