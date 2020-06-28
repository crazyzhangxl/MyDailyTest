package com.zxl.mydailytest.activities.fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zxl.mydailytest.R;
import com.zxl.mydailytest.activities.fragment.fgs.CategoryFragment;
import com.zxl.mydailytest.activities.fragment.fgs.CenterFragment;
import com.zxl.mydailytest.activities.fragment.fgs.HomeFragment;

import java.util.List;

/**
 * Fragment 活动
 *
 * replace 才需要用到addToBackStack add 和 hide 的话 fragment 还是存在的 所以不需要 addToStack，不知道我的理解是不是对的
 *
 * 多Fragment优化切换
 * https://mp.weixin.qq.com/s/cvvUb4xged0NpV8hnctyLg
 */
public class FgActivity extends AppCompatActivity {
    private FrameLayout mFrameContainer;
    private RadioGroup mRGContainer;
    private RadioButton mRbHome,mRbCenter,mRbCategory;
    private FragmentManager mFragmentManager;
    private CategoryFragment mCategoryFragment;
    private CenterFragment mCenterFragment;
    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fg);
        initViews();
        mRbHome.setChecked(true);
    }

    private void initViews() {
        mFrameContainer = findViewById(R.id.container);
        mRGContainer = findViewById(R.id.radioGroup);
        mRbHome = findViewById(R.id.rbHome);
        mRbCenter = findViewById(R.id.rbCenter);
        mRbCategory = findViewById(R.id.rbCategory);

        mRGContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbHome:
                        showWhichDialog(0);
                        break;
                    case R.id.rbCategory:
                        showWhichDialog(1);
                        break;
                    case R.id.rbCenter:
                        showWhichDialog(2);
                        break;
                }
            }
        });
    }

    private void showWhichDialog(int index){
        if (mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        if (mCategoryFragment != null){
            mFragmentTransaction.hide(mCategoryFragment);
        }

        if (mCenterFragment != null){
            mFragmentTransaction.hide(mCenterFragment);
        }

        if (mHomeFragment != null){
            mFragmentTransaction.hide(mHomeFragment);
        }

        switch (index){
            case 0:
                if (mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    mFragmentTransaction.add(R.id.container,mHomeFragment);
                }else {
                    if (mHomeFragment.isAdded()){
                        if (mHomeFragment.isHidden()){
                            mFragmentTransaction.show(mHomeFragment);
                        }
                    }else {
                        mFragmentTransaction.add(R.id.container,mHomeFragment);
                    }
                }
                break;
            case 1:
                if (mCategoryFragment == null){
                    mCategoryFragment = new CategoryFragment();
                    mFragmentTransaction.add(R.id.container,mCategoryFragment);
                }else {
                    if (mCategoryFragment.isAdded()){
                        if (mCategoryFragment.isHidden()){
                            mFragmentTransaction.show(mCategoryFragment);
                        }
                    }else {
                        mFragmentTransaction.add(R.id.container,mCategoryFragment);
                    }
                }
                break;
            case 2:
                if (mCenterFragment == null){
                    mCenterFragment = new CenterFragment();
                    mFragmentTransaction.add(R.id.container,mCenterFragment);
                }else {
                    if (mCenterFragment.isAdded()){
                        if (mCenterFragment.isHidden()){
                            mFragmentTransaction.show(mCenterFragment);
                        }
                    }else {
                        mFragmentTransaction.add(R.id.container,mCenterFragment);
                    }
                }
                break;
        }
        mFragmentTransaction.commit();
    }



    /**
     *  Fragment的添加
     * @param manager Fragment管理器
     * @param aClass 相应的Fragment对象的getClass
     * @param containerId 容器的id
     * @param args 需要传值的话可将bundle填入  不需要传值就填null
     */
    protected void addFragment(FragmentManager manager, Class<? extends Fragment> aClass, int containerId, Bundle args) {
        String tag = aClass.getName();
        Fragment fragment = manager.findFragmentByTag(tag);
        FragmentTransaction transaction = manager.beginTransaction(); // 开启一个事务
        if (fragment == null) {// 没有添加
            try {
                fragment = aClass.newInstance(); // 通过反射 new 出一个 fragment 的实例
                transaction.add(containerId, fragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (fragment.isAdded()) {
                if (fragment.isHidden()) {
                    transaction.show(fragment);
                }
            } else {
                transaction.add(containerId, fragment, tag);
            }
        }

        if (fragment != null) {
            fragment.setArguments(args);
            hideBeforeFragment(manager, transaction, fragment);
            transaction.commit();
        }
    }


    /**
     * 除当前 fragment 以外的所有 fragment 进行隐藏
     *
     * @param manager
     * @param transaction
     * @param currentFragment
     */
    private void hideBeforeFragment(FragmentManager manager, FragmentTransaction transaction, Fragment currentFragment) {
        List<Fragment> fragments = manager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != currentFragment && !fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
    }


}
