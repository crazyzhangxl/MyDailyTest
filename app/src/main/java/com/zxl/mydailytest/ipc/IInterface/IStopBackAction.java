/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/apple/Code/Android/MyDairyTestProject/app/src/main/aidl/com/example/mydairytestproject/ipc/IInterface/IStopBackAction.aidl
 */
package com.zxl.mydailytest.ipc.IInterface;
public interface IStopBackAction extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zxl.mydailytest.ipc.IInterface.IStopBackAction
{
private static final java.lang.String DESCRIPTOR = "com.zxl.mydailytest.ipc.IInterface.IStopBackAction";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zxl.mydailytest.ipc.IInterface.IStopBackAction interface,
 * generating a proxy if needed.
 */
public static com.zxl.mydailytest.ipc.IInterface.IStopBackAction asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zxl.mydailytest.ipc.IInterface.IStopBackAction))) {
return ((com.zxl.mydailytest.ipc.IInterface.IStopBackAction)iin);
}
return new com.zxl.mydailytest.ipc.IInterface.IStopBackAction.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_stopCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.zxl.mydailytest.ipc.IInterface.ProgressData _arg0;
if ((0!=data.readInt())) {
_arg0 = com.zxl.mydailytest.ipc.IInterface.ProgressData.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.stopCallBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.zxl.mydailytest.ipc.IInterface.IStopBackAction
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
@Override public void stopCallBack(com.zxl.mydailytest.ipc.IInterface.ProgressData progressData) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((progressData!=null)) {
_data.writeInt(1);
progressData.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_stopCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_stopCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
public void stopCallBack(com.zxl.mydailytest.ipc.IInterface.ProgressData progressData) throws android.os.RemoteException;
}
