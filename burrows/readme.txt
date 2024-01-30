Programming Assignment 8: Burrows-Wheeler

/* *****************************************************************************
 *  Which sorting algorithm did you use in CircularSuffixArray.java?
 *  Why did you choose it?
 **************************************************************************** */
We used Arrays.sort (timssort) Because it was the fastest, and to us, it was the
most intutive to use especially if we were going to use a comparator.

/* *****************************************************************************
 *  How long does your implementation of the Burrows-Wheeler data compression
 *  algorithm (BurrowsWheeler + MoveToFront + Huffman) take to compress and
 *  expand mobydick.txt? What is the compression ratio (number of bytes in
 *  compressed message divided by the number of bytes in the message)?
 *  Compare the results to that for running Huffman alone and GNU
 *  command-line utility Gzip.
 **************************************************************************** */

Algorithm       Compression time    Expansion time           Compression ratio
------------------------------------------------------------------------------
My program      3.312s              0.621s                   0.345
Huffman alone   0.388s              0.285s                   0.560 (667651 / 1191463)
gzip            0.127s              0.032s                   0.412

See the Checklist for Bash commands to compress/expand.



/* *****************************************************************************
 *  What is the running time of each of the following methods as a function
 *  of the input size n and the alphabet size R, both in practice
 *  (on typical English text inputs) and in theory (in the worst case)?
 *  Use big Theta notation to simplify your answer (e.g., n, n + R, n log n,
 *  n log^2n n, n^2, or R n).
 *
 *  Include the time for sorting circular suffixes in Burrows-Wheeler
 *  transform().
 **************************************************************************** */

                                      typical            worst
---------------------------------------------------------------------
CircularSuffixArray constructor       n log n             n log n
BurrowsWheeler transform()            n log n             n^2
BurrowsWheeler inverseTransform()     n + R               n + R
MoveToFront encode()                  n + R               R * n
MoveToFront decode()                  n + R               R * n
Huffman compress()                    n + R log R         n + R log R
Huffman expand()                      n                   n





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
 *  you enjoyed doing it. Additionally, you may include any suggestions
 *  for what to change or what to keep (assignments or otherwise) in future
 *  semesters.
 **************************************************************************** */
N/A
