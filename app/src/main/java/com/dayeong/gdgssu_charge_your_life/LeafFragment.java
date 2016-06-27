package com.dayeong.gdgssu_charge_your_life;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LeafFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_CHOICE = "CHOICE";  //유저가 선택한 답변. YES(1), NO(0)
    private static final String ARG_COUNT = "COUNT";    //누적된 갯수

    private static final int CHOICE_YES = 1;
    private static final int CHOICE_NO = 0;
    private static final String TAG = "LEAF_FRAGMENT";

    private int mParam1;

    private View mView;
    private TextView textView_result;
    private UsersChoice usersChoice_result;

    private OnFragmentInteractionListener mListener;

    public LeafFragment() {
        // Required empty public constructor
    }

    public static LeafFragment newInstance(int count, int choice) {


        LeafFragment fragment = new LeafFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, count);
        args.putInt(ARG_CHOICE, choice);

        UsersChoice uc = UsersChoice.getInstance();
        uc.setChoice(count, choice);
        Log.d(TAG, "[" + count + "] " + choice);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_CHOICE);
            usersChoice_result = UsersChoice.getInstance();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //마지막 leaf node
        //choice가 yes면 from left
        //choice가 no 면 form up

        int leafLayout = R.layout.fragment_leaf_from_left;
        int[] temp = usersChoice_result.getArrayChoices();
        int direction = temp[3];
        Log.d(TAG, direction + "");
        switch(direction){
            case CHOICE_YES:
                leafLayout = R.layout.fragment_leaf_from_left;
                break;
            case CHOICE_NO:
                leafLayout = R.layout.fragment_leaf_from_up;
                break;
        }

        mView = inflater.inflate(leafLayout, container, false);

        textView_result = (TextView) mView.findViewById(R.id.leaf_textview);
        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("resultArray", usersChoice_result.getArrayChoices());
                startActivity(intent);

            }
        });


        Log.d(TAG, usersChoice_result.getResultRawValue() + " " + usersChoice_result.getResultValue());

        return mView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mView = null; // now cleaning up!
        System.gc();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
