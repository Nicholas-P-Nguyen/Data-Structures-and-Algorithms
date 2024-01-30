Programming Assignment 3: Autocomplete


/* *****************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that is equal to the search key.
 **************************************************************************** */
The firstIndexOf() method in our BinarySearchDeluxe works by setting up
variables to track the "low", middle, and "high" indexes of the array,
comparing the middle to the key, and changing the value of either the
low or high index based on the comparison result. In the case of when the
middle is equal to the key, we have a searchKey variable to track the
middle and we perform an additional binary search just in case there are earlier
keys that match and not just the middle one.


/* *****************************************************************************
 *  Identify which sorting algorithm (if any) that your program uses in the
 *  Autocomplete constructor and instance methods. Choose from the following
 *  options:
 *
 *    none, selection sort, insertion sort, mergesort, quicksort, heapsort
 *
 *  If you are using an optimized implementation, such as Arrays.sort(),
 *  select the principal algorithm.
 **************************************************************************** */

Autocomplete() : We use an optimized implementation, Arrays.sort(),  which uses MergeSort


allMatches() : We similarly use an optimized implementation with Arrays.sort(), which uses MergeSort


numberOfMatches() : None, as we did not use a sorting algorithm

/* *****************************************************************************
 *  How many compares (in the worst case) does each of the operations in the
 *  Autocomplete data type make, as a function of both the number of terms n
 *  and the number of matching terms m? Use Big Theta notation to simplify
 *  your answers.
 *
 *  Recall that with Big Theta notation, you should discard both the
 *  leading coefficients and lower-order terms, e.g., Theta(m^2 + m log n).
 **************************************************************************** */

Autocomplete():     Theta(nlogn)

allMatches():       Theta(nlogn)

numberOfMatches():  Theta(logn)




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
N/A

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 **************************************************************************** */
 2/18/23 Kaan Odabas (lab TA)
 2/18/23 Grant Chen (lab TA)


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
N/A

/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */

We both contributed to Term, BinarySearch, and Autocomplete with
varying levels of implementation and feedback on implementation depending
on where we were in the assignments.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

