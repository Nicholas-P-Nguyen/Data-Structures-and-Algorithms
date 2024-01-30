Programming Assignment 7: Seam Carving


/* *****************************************************************************
 *  Describe concisely your algorithm to find a horizontal (or vertical)
 *  seam.
 **************************************************************************** */
To find the vertical seam, we used the Bellman-ford shortest path variation.
We first created a new array to return the seam, then we created a edgeTo and
distTo 2d array to keep track of previous energy and to track the shortest distance.
We then set everything in the matrix to infinity except for the first row.
To find the lowest energy path to checked 3 conditions when its adjacent to the
bottom, bottom right, and bottom left, if necessary. Then we used a for loop to
find the lowest energy in the bottom row and traced it back to the top and returned
the seam.

/* *****************************************************************************
 *  Describe what makes an image suitable to the seam-carving approach
 *  (in terms of preserving the content and structure of the original
 *  image, without introducing visual artifacts). Describe an image that
 *  would not work well.
 **************************************************************************** */
An image that's suitable to the seam-carving approach is a picture with vibrant
colors in which we can find the lowest energy. An image that would not work well
is white dominate photo.


/* *****************************************************************************
 *  Perform computational experiments to estimate the running time to reduce
 *  a W-by-H image by one column and one row (i.e., one call each to
 *  findVerticalSeam(), removeVerticalSeam(), findHorizontalSeam(), and
 *  removeHorizontalSeam()). Use a "doubling" hypothesis, where you
 *  successively increase either W or H by a constant multiplicative
 *  factor (not necessarily 2).
 *
 *  To do so, fill in the two tables below. Each table must have 5-10
 *  data points, ranging in time from around 0.25 seconds for the smallest
 *  data point to around 30 seconds for the largest one.
 **************************************************************************** */

(keep W constant)
 W = 2000
 multiplicative factor (for H) = 3

 H           time (seconds)      ratio       log ratio
------------------------------------------------------
500         0.352               N/A         N/A
1500        0.732               2.07        0.64
4500        2.082               2.82        0.96
13500       6.978               3.34        1.10
40500       21.427              3.09        1.05

(keep H constant)
 H = 2000
 multiplicative factor (for W) = 3

 W           time (seconds)      ratio       log ratio
------------------------------------------------------
500         0.379               N/A         N/A
1500        0.729               2.15        0.65
4500        2.120               2.80        0.95
13500       7.174               3.28        1.13
40500       21.461              3.05        1.03

/* *****************************************************************************
 *  Using the empirical data from the above two tables, give a formula
 *  (using tilde notation) for the running time (in seconds) as a function
 *  of both W and H, such as
 *
 *       ~ 5.3*10^-8 * W^5.1 * H^1.5
 *
 *  Briefly explain how you determined the formula for the running time.
 *  Recall that with tilde notation, you include both the coefficient
 *  and exponents of the leading term (but not lower-order terms).
 *  Round each coefficient and exponent to two significant digits.
 **************************************************************************** */


Running time (in seconds) to find and remove one horizontal seam and one
vertical seam, as a function of both W and H:


    ~ 5.3*10^-8 * W^ 1.0 * H^1.0
       _______________________________________

For both W and H, the log base 3 ratio converges to a constant, b = 1,
and c = 1. Running time is calculated as aW * dH + aW * b * dH.
With running time = 21.3, n = 40500, and the same values for the Height to make
it one term A (multiply by 2), we can solve for a*d and obtain A = 5.3*10-8.


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
N/A



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
N/A


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
N/A
