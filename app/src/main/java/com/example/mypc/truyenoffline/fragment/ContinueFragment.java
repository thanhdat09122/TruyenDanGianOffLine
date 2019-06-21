package com.example.mypc.truyenoffline.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mypc.truyenoffline.R;
import com.example.mypc.truyenoffline.database.preferences.MyPreferences;
import com.example.mypc.truyenoffline.entity.Story;

import java.util.ArrayList;
import java.util.List;

public class ContinueFragment extends Fragment {
    MyPreferences myPreferences;
    TextView txtContinue, txtTitle;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myPreferences = MyPreferences.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_continue, container, false);
        txtContinue = view.findViewById(R.id.txtCon);
        txtTitle    = view.findViewById(R.id.txtTitle);
        Story story = myPreferences.getReadContinues();
        txtContinue.setText(story.getContent());
        txtTitle.setText(story.getTitle());
        Log.d("TAGGG", story.getTitle());
        return view;
    }
}
