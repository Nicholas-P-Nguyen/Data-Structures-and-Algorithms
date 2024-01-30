Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
For deque, I used a doubly linked list because we're just removing/adding from the
front and back. For randomized queue, I used a resizing array because it was easier
to get a random index/element in an array rather than using a linked list and
potentionally taking more time having to go through each node to find a random item.


/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  8n  bytes

overhead = 16 bytes
private int n = 4 bytes
private int capacityInitial = 4 bytes
items = (Item[]) new Object[capacityInitial] = 8n + 32

Deque:              ~ 48n   bytes

private Node first = 8 bytes
private Node last = 8 bytes
int n = 4 bytes
padding = 4 bytes

private Item item = 8n
private Node next = 8n
private Node previous = 8n
overhead = 16n
inner class overhead = 8n






/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
Not that I know of.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
I went to the lab TA's and their names are as followed
02/09/23 Amir Touil (lab TA)
02/09/23 Fernando (lab TA)
02/11/23 Windsor Nguyen (lab TA)


/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
N/A


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
This was fun implementing!
