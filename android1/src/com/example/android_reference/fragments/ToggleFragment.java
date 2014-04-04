package com.example.android_reference.fragments;

import com.example.android_reference.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class ToggleFragment extends Fragment implements OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_toggle, container,
                false);

        // register radio group event listener
        ((RadioGroup) rootView.findViewById(R.id.toggleGroup)).setOnCheckedChangeListener(_toggleListener);
        ((ToggleButton) rootView.findViewById(R.id.btn_toggle_a)).setOnClickListener(this);
        ((ToggleButton) rootView.findViewById(R.id.btn_toggle_b)).setOnClickListener(this);

        return rootView;
    }

    static final RadioGroup.OnCheckedChangeListener _toggleListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_toggle_a:
            case R.id.btn_toggle_b:
                ((RadioGroup)view.getParent()).check(view.getId());
                break;
        }
    }
}
