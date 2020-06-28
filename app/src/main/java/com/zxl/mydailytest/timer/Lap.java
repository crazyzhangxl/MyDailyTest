package com.zxl.mydailytest.timer;

/**
 * @author crazyZhangxl on 2018/11/22.
 * Describe:
 */
/**
 * A read-only domain object representing a stopwatch lap.
 */
public final class Lap {

    /** The 1-based position of the lap. */
    private final int mLapNumber;

    /** Elapsed time in ms since the lap was last started. */
    private final long mLapTime;

    /** Elapsed time in ms accumulated for all laps up to and including this one. */
    private final long mAccumulatedTime;

    Lap(int lapNumber, long lapTime, long accumulatedTime) {
        mLapNumber = lapNumber;
        mLapTime = lapTime;
        mAccumulatedTime = accumulatedTime;
    }

    public int getLapNumber() { return mLapNumber; }
    public long getLapTime() { return mLapTime; }
    public long getAccumulatedTime() { return mAccumulatedTime; }
}