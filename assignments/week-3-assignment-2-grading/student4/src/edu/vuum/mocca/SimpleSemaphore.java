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
	
	  final Lock lock;
	  final Condition noMorePermits;
	  volatile int permitCounter;
    /**
     * Constructor initialize the data members.  
     */
    public SimpleSemaphore (int permits,
                            boolean fair)
    { 
        // TODO - you fill in here
    	 lock = new ReentrantLock( fair );
         noMorePermits = lock.newCondition();
         permitCounter = permits;
    	
    }

    /**
     * Acquire one permit from the semaphore in a manner that can
     * be interrupted.
     */
    public void acquire() throws InterruptedException {
    	
        // TODO - you fill in here
    	
    	  lock.lockInterruptibly();
          try
          {
                  while(permitCounter == 0)
                          noMorePermits.await();
                  permitCounter--;
          }
          finally
          {
                  lock.unlock();
          }
    }

    /**
     * Acquire one permit from the semaphore in a manner that
     * cannot be interrupted.
     */
    public void acquireUninterruptibly() {
        // TODO - you fill in here
    	
    	  lock.lock();
          try
          {
                  while(permitCounter == 0)
                          noMorePermits.awaitUninterruptibly();
                  permitCounter--;
          }
          finally
          {
                  lock.unlock();
          }
    }

    /**
     * Return one permit to the semaphore.
     */
    void release() {
        // TODO - you fill in here
    	
    	 lock.lock();
         try
         {
                 permitCounter++;
                 noMorePermits.signal();
         }
         finally
         {
                 lock.unlock();
         }
    }

    
    public int availablePermits(){
        // TODO - you fill in here
        return permitCounter; 
    }
    /**
     * 
     * 
     * Define a ReentrantLock to protect the critical section.
     */
    // TODO - you fill in here

    /**
     * Define a ConditionObject to wait while the number of
     * permits is 0.
     */
    // TODO - you fill in here

    /**
     * Define a count of the number of available permits.
     */
    // TODO - you fill in here
}

