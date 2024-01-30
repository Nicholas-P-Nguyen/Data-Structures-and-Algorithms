Programming Assignment 6: WordNet


/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in synsets.txt. Why did you make this choice?
 **************************************************************************** */

When I saw the synsets.txt file my mind immediately went to a Hash Map or a
symbol table because there was a key/value pair with the integers and the noun.
There are also useful dot operators like contains key/value and get.

/* *****************************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in hypernyms.txt. Why did you make this choice?
 **************************************************************************** */

Similarly to synsets.txt, my mind immediately went to a Hash Map or a
symbol table because there was a key/value pair with the integers and the noun.
There are also useful dot operators like contains key/value and get.

/* *****************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithm? Express your answer as a function of the
 *  number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify
 *  your answer.
 **************************************************************************** */

Description:
I created a private helper method called "isRooted" to check if its a rooted DAG.
I created a new int variable to keep track of the number of out degrees. Inside
my first if statement i called .hasOrder to check if its in topological order,
if so, the digraph is a DAG. Then I iterate through each vertex to check for
outdegrees, if its greater than 1 then it's not rooted.

Order of growth of running time:
E + V

/* *****************************************************************************
 *  Describe concisely your algorithm to compute the shortest common ancestor
 *  in ShortestCommonAncestor. For each method, give the order of growth of
 *  the best- and worst-case running times. Express your answers as functions
 *  of the number of vertices V and the number of edges E in the digraph.
 *  (Do not use other parameters.) Use Big Theta notation to simplify your
 *  answers.
 *
 *  If you use hashing, assume the uniform hashing assumption so that put()
 *  and get() take constant time per operation.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't forget
 *  to count the time needed to initialize the marked[], edgeTo[], and
 *  distTo[] arrays.
 **************************************************************************** */

Description: To find the shortest common ancestor, I perform BFS on the ints being
passed through. Then, I created 3 int variables to keep track of the shortest
distance, shortest ancestor, and the current distance. Then I used a for loop
to iterate through all of the vertex and check if there is a path to the root, if so,
I calculated the current distance and compare it to the shortest distance. Update
the shortest distance if shorter than assign the shortest ancestor to the current
index and return shortest ancestor.


                                 running time
method                  best case            worst case
--------------------------------------------------------
length()                E + V                   E + V

ancestor()              E + V                   E + V

lengthSubset()          E + V                   E + V

ancestorSubset()        E + V                   E + V



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
This was a fun assignment.
