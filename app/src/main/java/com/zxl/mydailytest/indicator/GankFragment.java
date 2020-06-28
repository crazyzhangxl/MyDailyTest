package com.zxl.mydailytest.indicator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zxl.mydailytest.R;

/**
 * @author crazyZhangxl on 2018/10/13.
 * Describe:
 */
public class GankFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_sample, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        TextView tvDes = inflate.findViewById(R.id.tvDes);
        tvDes.setText("干货来咯");
    }
}
