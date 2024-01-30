Programming Assignment 5: K-d Trees

/* *****************************************************************************
 *  First, fill out the mid-semester survey:
 *  https://forms.gle/LdhX4bGvaBYYYXs97
 *
 *  If you're working with a partner, please do this separately.
 *
 *  Type your initials below to confirm that you've completed the survey.
 **************************************************************************** */
NN
MY

/* *****************************************************************************
 *  Describe the Node data type you used to implement the
 *  2d-tree data structure.
 **************************************************************************** */
The node data type we used was similar to a symbol table where the key was the Point
maps to a value. We also had RectHV that created the bounding box. Lastly, we had a
pointer pointing to the leftbottom and righttop


/* *****************************************************************************
 *  Describe your method for range search in a k-d tree.
 **************************************************************************** */
Our range search was a recursive call to range returning the root, rectangle, and
the stack. First we check if the current node is null, if so, we return null.
Then we check to see if the bounding box does not intersect with the query rectangle.
Then we check if the point belongs in the query rectangle, if so, we push it on top
of the stack. Lastly, we recursively went left, then right.


/* *****************************************************************************
 *  Describe your method for nearest neighbor search in a k-d tree.
 **************************************************************************** */
Our nearest neighbor search was a recursive call to nearest returning the root, the
point, the nearest point and a boolean to check whether we're comparing the x
coordinate or y coordinate. In our private helper method we check if the current
node is null, if so, we return the champion. Then we prune by checking if the nodes
bounding box is not closer than the champion. Then we check the current nodes distance
updating the champion if its closer to the point. Then we recrusively go left or right.

/* *****************************************************************************
 *  How many nearest-neighbor calculations can your PointST implementation
 *  perform per second for input1M.txt (1 million points), where the query
 *  points are random points in the unit square?
 *
 *  Fill in the table below, rounding each value to use one digit after
 *  the decimal point. Use at least 1 second of CPU time. Do not use -Xint.
 *  (Do not count the time to read the points or to build the 2d-tree.)
 *
 *  Repeat the same question but with your KdTreeST implementation.
 *
 **************************************************************************** */


                 # calls to         /   CPU time     =   # calls to nearest()
                 client nearest()       (seconds)        per second
                ------------------------------------------------------
PointST:        100                     8.153           12.3

KdTreeST:       1000000                 3.492           286368.8

Note: more calls per second indicates better performance.


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
N/A


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
Little bugs that made it hard for us. :(



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on  how helpful the class meeting was and on how much you learned
 * from doing the assignment, and whether you enjoyed doing it.
 **************************************************************************** */
This assignment was tough, but we learned a lot through our struggles.
