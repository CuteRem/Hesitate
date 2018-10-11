package com.hpj.roll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by hpj16042 on 2017/12/20.
 */

public class NumberFragment extends Fragment {

    private TextView resultView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        resultView = view.findViewById(R.id.result);
        final EditText countView = view.findViewById(R.id.et_count);
        final EditText minView = view.findViewById(R.id.et_min);
        final EditText maxView = view.findViewById(R.id.et_max);
        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countStr = countView.getText().toString();
                int count = TextUtils.isEmpty(countStr) ? 1 : Integer.parseInt(countStr);
                String minStr = minView.getText().toString();
                int min = TextUtils.isEmpty(minStr) ? 0 : Integer.parseInt(minStr);
                String maxStr = maxView.getText().toString();
                int max = TextUtils.isEmpty(maxStr) ? 1 : Integer.parseInt(maxStr);

                resultView.setText(randomSet(min, max, count));
            }
        });
    }

    private String randomSet(int min, int max, int n) {

        ArrayList<Integer> list = new ArrayList<>();
        if (max < min) {
            int temp = min;
            min = max;
            max = temp;
        }
        if (n > (max - min + 1)) {
            n = max - min + 1;
        }
        for (int i = 0; i < n; i++) {
            random(min, max, list);
        }
        Collections.sort(list);
        return list.toString();
    }

    private static void random(int min, int max, ArrayList<Integer> list) {

        int num = new Random().nextInt(max - min + 1) + min;
        if (list.contains(num)) {
            random(min, max, list);
        } else {
            list.add(num);
        }
    }
}
