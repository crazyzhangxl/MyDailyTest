// IStopBackAction.aidl
package com.zxl.mydailytest.ipc.IInterface;

// Declare any non-default types here with import statements
import com.zxl.mydailytest.ipc.IInterface.ProgressData;
interface IStopBackAction {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void stopCallBack(in ProgressData progressData);
}
