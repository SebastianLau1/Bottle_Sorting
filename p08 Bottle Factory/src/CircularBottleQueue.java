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
 * Constructs a CircularBottleQueue object, initializing its data fields as follows:
 *the bottles oversize array to an empty array of Bottle objects whose length is the input capacity,
 *its size to zero, and both its front and back to -1.
 * @author sebas
 *
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    private Bottle[] bottles;
    private int front;
    private int back;
    private int size;
    /**
     * Constructs a CircularBottleQueue object, initializing its data fields 
     * @param capacity
     * @throws IllegalArgumentException
     */
    
    public CircularBottleQueue(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        bottles = new Bottle[capacity];
        front = -1;
        back = -1;
        size = 0;
    }

    /**
     * Returns an iterator to traverse the queue.
     * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this);
    }


    /**
     * Add a bottle to the end of the queue
     * @param bottle to add
     * @throws IllegalStateException
     * @throws NullPointerException
     */
    @Override
    public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        if (bottle == null) {
            throw new NullPointerException("Bottle cannot be null");
        }
        if (isEmpty()) {
            front = 0;
        }
        back = (back + 1) % bottles.length;
        bottles[back] = bottle;
        size++;
    }

    
    /**
     * Removes and returns the first bottle in the queue.
     * @return the first bottle in queue
     * @throws NoSuchElementException
     */
    @Override
    public Bottle dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Bottle bottle = bottles[front];
        bottles[front] = null;
        if (front == back) {
            front = -1;
            back = -1;
        } else {
            front = (front + 1) % bottles.length;
        }
        size--;
        return bottle;
    }

    /**
     * Returns the first bottle in the queue without removing it
     * @return Top/First bottle in the queue
     * @throws NoSuchElementException
     */
    @Override
    public Bottle peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return bottles[front];
    }

    /**
     * Checks and returns true if the queue is empty
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

   /**
    * Checks and returns true if the queue is full
    * @return true if full
    */
    @Override
    public boolean isFull() {
        return size == bottles.length;
    }

    /**
     * Returns the number of bottles in the queue
     * @return number of bottles
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the queue
     * @return string 
     * @Override 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); //make string variable
        for (Bottle bottle : this) { //iterate through queue
            sb.append(bottle.toString()).append("\n");
        }
        
        return sb.toString().trim(); //remove final \n
    }

    /**
     * Returns a deep copy of this Queue
     * @return a deep copy of the queue
     */
    @Override
    public QueueADT<Bottle> copy() {
      CircularBottleQueue copy = new CircularBottleQueue(bottles.length);
      for (int i = 0; i < bottles.length; i++) {
          if (bottles[i] != null) {
              copy.enqueue(bottles[i]);
          }
      }
      return copy;
  }
    
}