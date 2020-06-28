package com.zxl.mydailytest.activities.fragment.fgs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zxl.mydailytest.R;


/**
 * Created by apple on 2019-07-29.
 * description:个人中心Fragment
 */
public class CenterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        TextView tvCenter = view.findViewById(R.id.tvCenter);
        tvCenter.setText("个人中心");
        return view;
    }

}
