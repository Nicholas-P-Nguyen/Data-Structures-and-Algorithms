/* *****************************************************************************
 *  Operating system: Mac
 *  Compiler: Java
 *  Text editor / IDE: IntelliJ
 *
 *  Have you taken (part of) this course before: No
 *  Have you taken (part of) the Coursera course Algorithms, Part I or II:
 *
 *  Hours to complete assignment (optional):
 *  12 hours
 **************************************************************************** */

Programming Assignment 1: Percolation


/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
I used a 2d array to store the n*n matrix. I created 4 int instance variable, 1
for n, 1 for the virtual top, and 1 for the virtual bottom, and lastly 1 to
account for the number of sites opened. I also initialized UF.

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): The open method I first tested the edge cases. If row = 0 then I'd connected
it to the virtual top, and then if the last row = (n-1) then I'd connect it to the
virtual bottom. I then created 4 if statements where I check up, down, left right.
isOpen(): I just returned the boolean 2d array
isFull(): I connected the virtual top to the index of the row, col being parsed through
to see if its full.
numberOfOpenSites(): I returned the count variable.
percolates(): I connected the virtual top to the virtual bottom and if its true,
then the system percolates.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
    1000 n

 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n         time (seconds)
--------------------------
600     12.367
700     19.981
800     29.505
900     42.003
1000    59.158


/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I wanted to go up in increments of 100 because I knew quick uniion would take
longer at lower values of n. For Weighted UF I went up in increments of 500.


/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest values of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *  2300-2500 n

 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
1000    4.586
1500    16.308
2000    36.203
2500    65.964
3000    109.718


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
Limitations for quick-union is the height of the tree, if its tall, then the
run time will be significantly longer.


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
I went to office hours and Sabhya Chhabria was there.

/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
None



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
This was an interesting assignment!!
