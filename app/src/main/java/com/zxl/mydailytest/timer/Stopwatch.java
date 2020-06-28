package com.zxl.mydailytest.timer;

/**
 * @author crazyZhangxl on 2018/11/22.
 * Describe:
 */

import android.os.SystemClock;

import static com.zxl.mydailytest.timer.Stopwatch.State.PAUSED;
import static com.zxl.mydailytest.timer.Stopwatch.State.RESET;
import static com.zxl.mydailytest.timer.Stopwatch.State.RUNNING;

/**
 * A read-only domain object representing a stopwatch.
 */
public final class Stopwatch {

    public enum State { RESET, RUNNING, PAUSED }

    /** The single, immutable instance of a reset stopwatch. */
    private static final Stopwatch RESET_STOPWATCH = new Stopwatch(RESET, Long.MIN_VALUE, 0);

    /** Current state of this stopwatch. */
    private final State mState;

    /** Elapsed time in ms the stopwatch was last started; {@link Long#MIN_VALUE} if not running. */
    private final long mLastStartTime;

    /** Elapsed time in ms this stopwatch has accumulated while running. */
    private final long mAccumulatedTime;

    Stopwatch(State state, long lastStartTime, long accumulatedTime) {
        mState = state;
        mLastStartTime = lastStartTime;
        mAccumulatedTime = accumulatedTime;
    }

    public State getState() { return mState; }
    public long getLastStartTime() { return mLastStartTime; }
    public boolean isReset() { return mState == RESET; }
    public boolean isPaused() { return mState == PAUSED; }
    public boolean isRunning() { return mState == RUNNING; }

    /**
     * @return the total amount of time accumulated up to this moment
     */
    public long getTotalTime() {
        if (mState != RUNNING) {
            return mAccumulatedTime;
        }

        // In practice, "now" can be any value due to device reboots. When the real-time clock
        // is reset, there is no more guarantee that "now" falls after the last start time. To
        // ensure the stopwatch is monotonically increasing, normalize negative time segments to 0,
        final long timeSinceStart = now() - mLastStartTime;
        return mAccumulatedTime + Math.max(0, timeSinceStart);
    }

    /**
     * @return the amount of time accumulated up to the last time the stopwatch was started
     */
    long getAccumulatedTime() {
        return mAccumulatedTime;
    }

    /**
     * @return a copy of this stopwatch that is running
     */
    Stopwatch start() {
        if (mState == RUNNING) {
            return this;
        }

        return new Stopwatch(RUNNING, now(), getTotalTime());
    }

    /**
     * @return a copy of this stopwatch that is paused
     */
    Stopwatch pause() {
        if (mState != RUNNING) {
            return this;
        }

        return new Stopwatch(PAUSED, Long.MIN_VALUE, getTotalTime());
    }

    /**
     * @return a copy of this stopwatch that is reset
     */
    Stopwatch reset() {
        return RESET_STOPWATCH;
    }

    private static long now() {
        return SystemClock.elapsedRealtime();
    }
}
