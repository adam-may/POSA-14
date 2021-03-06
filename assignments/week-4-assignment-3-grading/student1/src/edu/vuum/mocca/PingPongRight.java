package edu.vuum.mocca;

// Import the necessary Java synchronization and scheduling classes.
import java.util.concurrent.CountDownLatch;

/**
 * @class PingPongRight
 * 
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public final static int mMaxIterations = 10;

    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch mLatch = null;

    /**
     * @class PlayPingPongThread
     * 
     * @brief This class implements the ping/pong processing algorithm
     *        using the SimpleSemaphore to alternate printing "ping"
     *        and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread {

        /**
         * Constants to distinguish between ping and pong
         * SimpleSemaphores, if you choose to use an array of
         * SimpleSemaphores.  If you don't use this implementation
         * feel free to remove these constants.
         */
        private final static int FIRST_SEMA = 0;
        private final static int SECOND_SEMA = 1;

        /**
         * Maximum number of loop iterations.
         */
        private int mMaxLoopIterations = 0;

        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
        private String mStringToPrint = null;

        /**
         * Two SimpleSemaphores use to alternate pings and pongs.  You
         * can use an array of SimpleSemaphores or just define them as
         * two data members.
         */
        // TODO - You fill in here.
        private SimpleSemaphore mSemaphoreOne = null, mSemaphoreTwo = null;

        /**
         * Constructor initializes the data member(s).
         */
        public PlayPingPongThread(String stringToPrint,
                                  SimpleSemaphore semaphoreOne,
                                  SimpleSemaphore semaphoreTwo,
                                  int maxIterations) {
            // TODO - You fill in here.
        	mStringToPrint = stringToPrint;
        	mSemaphoreOne = semaphoreOne;
        	mSemaphoreTwo = semaphoreTwo;
        	mMaxLoopIterations = maxIterations;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run() {
            /**
             * This method runs in a separate thread of control and
             * implements the core ping/pong algorithm.
             */

            // TODO - You fill in here.
            for (int loopsDone = 1;
                    loopsDone <= mMaxIterations;
                    ++loopsDone) {
                   // Perform the template method protocol for printing a
                   // "ping" or a "pong" on the display.  Note that the
                   // acquire() and release() hook methods that control
                   // the scheduling of the threads are deferred to
                   // subclasses.

                   acquire();

                   System.out.println(mStringToPrint + "(" + loopsDone + ")" );

                   release();

                   mLatch.countDown();
               }
        	
        }

        /**
         * Method for acquiring the appropriate SimpleSemaphore.
         */
        private void acquire() {
            // TODO fill in here
       		mSemaphoreOne.acquireUninterruptibly ( );
        }

        /**
         * Method for releasing the appropriate SimpleSemaphore.
         */
        private void release() {
            // TODO fill in here
        	mSemaphoreTwo.release ( );
        }
    }

    /**
     * The method that actually runs the ping/pong program.
     */
    public static void process(String startString, 
                               String pingString,
                               String pongString, 
                               String finishString, 
                               int maxIterations) throws InterruptedException {

        // TODO initialize this by replacing null with the appropriate
        // constructor call.
        mLatch = new CountDownLatch(maxIterations * 2);

        // Create the ping and pong SimpleSemaphores that control
        // alternation between threads.

        // TODO - You fill in here, make pingSema start out unlocked.
        SimpleSemaphore pingSema = new SimpleSemaphore ( 1, true );

        // TODO - You fill in here, make pongSema start out locked.
        SimpleSemaphore pongSema = new SimpleSemaphore ( 0, true );

        System.out.println(startString);

        // Create the ping and pong threads, passing in the string to
        // print and the appropriate SimpleSemaphores.
        PlayPingPongThread ping = new PlayPingPongThread(pingString,
        												 pingSema,
        												 pongSema,
        												 maxIterations);
        PlayPingPongThread pong = new PlayPingPongThread(pongString,
        												 pongSema,
        												 pingSema,
        												 maxIterations);

        // TODO - Initiate the ping and pong threads, which will call
        // the run() hook method.
        pong.start ( );
        ping.start ( );

        // TODO - replace the following line with a barrier
        // synchronizer call to mLatch that waits for both threads to
        // finish.
        mLatch.await ( );

        System.out.println(finishString);
    }

    /**
     * The main() entry point method into PingPongRight program.
     * 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        process("Ready...Set...Go!", 
                "Ping!  ",
                " Pong! ",
                "Done!",
                mMaxIterations);
    }
}

