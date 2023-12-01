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
 * This class makes an iterator for bottle objects in a queue
 * @author sebas
 *
 */
public class BottleQueueIterator implements Iterator<Bottle> {

    private QueueADT<Bottle> queue;
    private QueueADT<Bottle> queueCopy;
/**
 * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
 *  deep copy of the queue passed as input.
 * @param queue
 * @throws IllegalArgumentException
 */
    public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
        if (queue == null) {
            throw new IllegalArgumentException("Queue is null");
        }
        this.queue = queue;
        this.queueCopy = queue.copy();
    }
/**
 * Returns true if there is the iteration is not yet exhausted, meaning at 
 * least one bottle is not iterated over
 * @return true if there is the iteration is not yet exhausted
 */
    public boolean hasNext() {
        return !queueCopy.isEmpty();
    }
/**
 * Returns the next bottle in the iteration
 * @return Bottle The next bottle in the iteration
 * @throws NoSuchElementException - if there are no more elements in the iteration
 */
    public Bottle next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("queue is empty");
        }
        return queueCopy.dequeue();
    }

   

}
