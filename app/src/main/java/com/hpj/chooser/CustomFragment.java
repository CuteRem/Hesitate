package com.hpj.chooser;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

/**
 * Created by hpj16042 on 2017/12/19.
 */

public class CustomFragment extends Fragment {
    private TextView resultView;
    private String[] options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        resultView = view.findViewById(R.id.result);
        final EditText countView = view.findViewById(R.id.et_count);
        final EditText optionsView = view.findViewById(R.id.et_options);
        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countStr = countView.getText().toString();
                int count = TextUtils.isEmpty(countStr) ? 1 : Integer.parseInt(countStr);
                String optionsStr = optionsView.getText().toString();

                if (TextUtils.isEmpty(optionsStr)) {
                    Toast.makeText(getContext(), "请输入备选项", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (optionsStr.contains(",")) {
                    options = optionsStr.split(",");
                } else {
                    options = optionsStr.split("，");
                }
                resultView.setText(resultStr(0, options.length - 1, count));
            }
        });
    }

    private void randomSet(int min, int max, int n, HashSet<String> set) {

        if (max < min) {
            int temp = min;
            min = max;
            max = temp;
        }
        if (n > (max - min + 1)) {
            n = max - min + 1;
        }
        for (int i = 0; i < n; i++) {
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(options[num]);// 将不同的数存入HashSet中
        }
        int setSize = set.size();
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (setSize < n) {
            randomSet(min, max, n - setSize, set);// 递归
        }
    }

    private String resultStr(int min, int max, int n) {
        HashSet<String> set = new HashSet<>();
        randomSet(min, max, n, set);
        String s = set.toString();
        return s.substring(1, s.length() - 1);
    }

}
