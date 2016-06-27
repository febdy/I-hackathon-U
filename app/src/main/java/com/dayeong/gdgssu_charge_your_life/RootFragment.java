package com.dayeong.gdgssu_charge_your_life;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

;


public class RootFragment extends Fragment {

    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_root, container, false);
        return mView;
    }

    @Override
    public void onDestroyView(){
        super.onDestroy();
        mView = null;
    }


}
