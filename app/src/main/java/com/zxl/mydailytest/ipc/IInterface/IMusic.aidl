// IMusic.aidl
package com.zxl.mydailytest.ipc.IInterface;

// Declare any non-default types here with import statements
// aidl文件里的变量类型得用parcelable
import com.zxl.mydailytest.ipc.IInterface.IStopBackAction;

// 方法得实现parcelable
interface IMusic {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void play();

    void pause();

    void stop();

    void registerStop(in IStopBackAction iStopBackAction);
}
