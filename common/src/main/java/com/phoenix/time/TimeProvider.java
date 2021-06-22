package com.phoenix.time;

/**
 * An interface abstracting the clock to use in unit testing classes that make use of clock time
 */
public interface TimeProvider {
    /**
     * The current time in milliseconds
     */
    long milliseconds();

    /**
     * @return The number of seconds since Jan 1st 1970, 00:00:00 UTC.
     */
    long getTime();

    /**
     * The current time in nanoseconds
     */
    long nanoseconds();

    /**
     * Sleep for the given number of milliseconds
     */
    void sleep(long ms);
}
