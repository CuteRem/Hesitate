package com.hpj.chooser;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by hpj16042 on 2017/12/19.
 */

public class TrueOrFalseFragment extends Fragment {
    private ImageView resultView;
    private AnimatedVectorDrawable animTrue, animFalse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_true_or_false_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        resultView = view.findViewById(R.id.result);
        animTrue = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_done);
        animFalse = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_close);

        view.findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new Random().nextBoolean()) {
                    resultView.setImageDrawable(animTrue);
                    animTrue.start();
                } else {
                    resultView.setImageDrawable(animFalse);
                    animFalse.start();
                }
            }
        });
    }
}
