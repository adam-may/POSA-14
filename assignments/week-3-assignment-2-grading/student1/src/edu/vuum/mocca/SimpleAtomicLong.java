package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;

/**
* @class SimpleAtomicLong
*
* @brief This class implements a subset of the
*        java.util.concurrent.atomic.SimpleAtomicLong class using a
*        ReentrantReadWriteLock to illustrate how they work.
*/
class SimpleAtomicLong
{
    /**
     * The value that's manipulated atomically via the methods.
     */
    private long mValue;
    
    /**
     * The ReentrantReadWriteLock used to serialize access to mValue.
     */
    // TODO - replace the null with the appropriate initialization:
    private ReentrantReadWriteLock mRWLock = new ReentrantReadWriteLock();

    /**
     * Creates a new SimpleAtomicLong with the given initial value.
     */
    public SimpleAtomicLong(long initialValue)
    {
        // TODO - you fill in here
       mValue = initialValue;
    }

    /**
     * @brief Gets the current value.
     * 
     * @returns The current value
     */
    public long get()
    {
        long value;
        // TODO - you fill in here, using a readLock()
        mRWLock.readLock().lock();
        try{
             value = mValue;
        }
             
        finally{
              mRWLock.readLock().unlock(); 
        }
        
        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the updated value
     */
    public long decrementAndGet()
    {
        long value  = 0;
        // TODO - you fill in here, using a writeLock()
        
        mRWLock.writeLock().lock();
        try{
             
             value = -1 + get();
              mValue = value;
        }
             
        finally{
              mRWLock.writeLock().unlock(); 
        }
        
        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the previous value
     */
    public long getAndIncrement()
    {
       mRWLock.writeLock().lock();
       long value = get();
        // TODO - you fill in here, using a writeLock()
       
        try{
                    mValue = value + 1;
        }
        finally{
              mRWLock.writeLock().unlock(); 
        }

       
        return value;
    }

    /**
     * @brief Atomically decrements by one the current value
     *
     * @returns the previous value
     */
    public long getAndDecrement()
    {
       mRWLock.writeLock().lock();
        long value = get();
        // TODO - you fill in here, using a writeLock()
         
       
        try{
             //value = -1;
              mValue = value - 1;
        }
             
        finally{
              mRWLock.writeLock().unlock(); 
        }
        

        
        return value;
    }

    /**
     * @brief Atomically increments by one the current value
     *
     * @returns the updated value
     */
    public long incrementAndGet()
    {
        long value;
        // TODO - you fill in here, using a writeLock()
        
        mRWLock.writeLock().lock();
        
        try{
             value =  +1 + get();
                    mValue = value;
        }
             
        finally{
              mRWLock.writeLock().unlock(); 
        }
        return value;
    }
}
