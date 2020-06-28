package com.zxl.mydailytest.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 复习权限
 * @author 张先磊
 * @description 复习下权限
 * android 6.0 运行时权限学习...
 *
 * // https://www.jianshu.com/p/174c271cd93a
 */
public class ReviewPermissionActivity extends AppCompatActivity {

    public static void show(Activity activity){
        Intent intent = new Intent(activity,ReviewPermissionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_permission);

        // ok 最好还是这样....
        findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 开启运行时权限...
                if (checkAndRequestPermission()){
                    Toast.makeText(ReviewPermissionActivity.this, "开启摄像头", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *<uses-permission android:name="android.permission.CAMERA"/>
     *<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
     *
     * @return
     */
    private boolean checkAndRequestPermission(){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(ReviewPermissionActivity.this, permissions, 123);
            return false;
        }

//        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        if (permission != PackageManager.PERMISSION_GRANTED){
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
//                Toast.makeText(this, "需要你的授权", Toast.LENGTH_SHORT).show();
//                //....
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
//            }else {
//                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
//            }
//            return false;
//        }
        return true;
    }

    /**
     * 我这样做是一个集合 集合申请完毕才会回调此方法....
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            for (int i=0;i<grantResults.length;i++){
                int result = grantResults[i];
                Log.e("权限", permissions[i]+","+grantResults[i]);
                if (result != PackageManager.PERMISSION_GRANTED){
                    switch (permissions[i]){
                        case Manifest.permission.CAMERA:
                            Log.e("权限", "拒绝了相机权限");
                            break;
                        case Manifest.permission.READ_PHONE_STATE:
                            Log.e("权限", "拒绝了读取手机状态权限");
                            break;
                        case Manifest.permission.ACCESS_FINE_LOCATION:
                            Log.e("权限", "拒绝了地图权限");
                            break;
                    }
                }
            }
        }
    }
}
