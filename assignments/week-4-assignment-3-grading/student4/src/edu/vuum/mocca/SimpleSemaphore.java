package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 *
 * @brief This class provides a simple counting semaphore
 *        implementation using Java a ReentrantLock and a
 *        ConditionObject.  It must implement both "Fair" and
 *        "NonFair" semaphore semantics, just liked Java Semaphores. 
 */
public class SimpleSemaphore {
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	this.lock = new ReentrantLock(fair);
    	this.condition = lock.newCondition();
    	this.count = permits;
    	this.fair = fair;
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
        try {
			lock.lockInterruptibly();
			try {
				while(count == 0)
					condition.await();				
			} catch (Exception e) {
				condition.signal();
				throw e;
			}
			this.count--;
		} finally {
			lock.unlock();
		}
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	try {
			lock.lock();
			try {
				while(count == 0)
					condition.awaitUninterruptibly();				
			} catch (Exception e) {
				condition.signal();
				throw e;
			}
			this.count--;
		} finally {
			lock.unlock();
		}
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        lock.lock();
        try {
        	this.count++;
        	if(fair)
        		condition.signal();
        	else
        		condition.signalAll();
        }
        finally {
    		lock.unlock();
    	}
    }

    private boolean fair;
    /**
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here
    private final ReentrantLock lock;

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here
    private final Condition condition;
    
    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
    private volatile int count;
}
