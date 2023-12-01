//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Twitter feed
// Course: CS 300 Spring 2023
//
// Author: Sebastian Lau
// Email: sglau@wisc.edu
// Lecturer: (Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE (identify each by name and describe how they helped)
// Online Sources: NONE (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and 
 * removed with respect to the First In First Out (FIFO) scheduling policy.
 * @author sebas
 *
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

    private int capacity;
    private int size;
    private LinkedNode <Bottle> front;
    private LinkedNode <Bottle> back;
/**
 * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
 *  deep copy of the queue passed as input.
 * @param capacity
 * @throws IllegalArgumentException
 */
    public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.size = 0;
        this.front = null;
        this.back = null;
    }
/**
 * returns true if there is the iteration is not yet exhausted, meaning at least one bottle is not
 *  iterated over
 *  @return Returns true if there is the iteration is not yet exhausted
 */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //create modifiable string
        LinkedNode  <Bottle> curr = front;
        while (curr != null) {
            sb.append(curr.getData().toString()).append("\n");
            curr = curr.getNext(); //update value
        }
        return sb.toString().trim(); //trims final \n
        
        

   }
/**
 * Returns the first bottle in the queue without removing it
 * @return Top/First bottle in the queue
 * @throws NoSuchElementException - When queue is empty
 */
    @Override
    public Bottle peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (Bottle) front.getData();
    }
/**
 * Checks and returns true if the queue is empty
 * @return  true if the queue is empty
 */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
/**
 * Checks and returns true if the queue is full
 * @return  true if the queue is full
 */
    @Override
    public boolean isFull() {
        return size == capacity;
    }
/**
 * Removes and returns the first bottle in the queue
 * @return first bottle in queue
 * @throws NoSuchElementException - when queue is empty
 */
    @Override
    public Bottle dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Bottle bottle = (Bottle) front.getData();
        front = front.getNext();
        size--;
        if (isEmpty()) {
          
            back = null;
        }
        return bottle;
    }
/**
 * Add a bottle to the end of the queue
 * @param bottle to add
 * @throws IllegalStateException - when queue is full
 * @throws nullPointerException - when bottle to add is null
 */
    @Override
    public void enqueue(Bottle bottle) {
        if (bottle == null) {
            throw new NullPointerException("Bottle is null");
        }
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
      LinkedNode <Bottle> add = new LinkedNode<Bottle>(bottle);
        if (isEmpty()) {
            front = add;
            back = add;
        } else {
            back.setNext(add);
            back = add;
        }
       
        size++;
    }
/**
 * Returns the number of bottles in the queue
 * @return size
 */
    @Override
    public int size() {
        return size;
    }
/**
 * Returns an iterator for traversing the queue's items
 * @return iterator for bottlequeue
 */
    @Override
    public Iterator<Bottle> iterator() {
    
        return new BottleQueueIterator(this);
    }
/**
 * Returns a deep copy of this queue.
 * @return deep copy of this queue.
 */
    @Override
    public QueueADT<Bottle> copy() {
      LinkedBottleQueue copy = new LinkedBottleQueue(capacity);
      LinkedNode<Bottle> curr = front;
      Bottle bottle;
      for(int i=0; i<=size-1; i++) { //iterate through queue size
          bottle= curr.getData();
          copy.enqueue(bottle); 
          curr = curr.getNext();
          
      }
      return copy;
    }

   

  

}
