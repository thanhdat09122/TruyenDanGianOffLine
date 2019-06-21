package com.example.mypc.truyenoffline.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.adapter.SpinnerAdapter;
import com.example.mypc.truyenoffline.common.Constant;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;
import com.example.mypc.truyenoffline.utils.AssetFontUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingFragment extends Fragment {
    ToggleButton toggleButton;
    Spinner spinner;
    NumberPicker numberPicker;
    MyPreferences myPreferences;
    List<String> fontNames = new ArrayList<>();
    SpinnerAdapter spinnerAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myPreferences = MyPreferences.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initViews(view);
        settingSpinner();
        settingNumberPicker();
        return view;
    }


    private void settingSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String font = (String) parent.getAdapter().getItem(position);
                myPreferences.setTextFonts(font);
                myPreferences.setFontsPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void settingNumberPicker() {
        numberPicker.setMinValue(Constant.MIN_TEXT);
        numberPicker.setMaxValue(Constant.MAX_TEXT);
        numberPicker.setValue(myPreferences.getTextSize());
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d("TAGGG", newVal +"");
                myPreferences.setTextSize(newVal);
            }
        });
    }

    private void initViews(View view) {
        toggleButton   = view.findViewById(R.id.toggle);
        spinner        = view.findViewById(R.id.spinner);
        fontNames.addAll(AssetFontUtil.getFontsNameAssets(getActivity()));
        spinnerAdapter = new SpinnerAdapter(getActivity().getApplicationContext() ,fontNames);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(myPreferences.getFontsPosition());

        numberPicker = view.findViewById(R.id.numerPicker);
        if(myPreferences.getAutoScroll()){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if(on) {
                    myPreferences.setAutoScroll(true);
                }else{
                    myPreferences.setAutoScroll(false);
                }
            }
        });
    }


}
