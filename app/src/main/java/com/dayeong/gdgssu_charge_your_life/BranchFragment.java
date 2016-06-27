package com.dayeong.gdgssu_charge_your_life;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BranchFragment extends Fragment {
    private static final String TAG = "BRANCH_FRAGMENT :: ";

    private static final String ARG_CHOICE = "CHOICE";  //유저가 선택한 답변. YES(1), NO(0)
    private static final String ARG_COUNT = "COUNT";    //누적된 갯수

    private static final int CHOICE_YES = 1;
    private static final int CHOICE_NO = 0;
    public static int COUNT = 0;

    private int param1;
    private int last;

    private OnFragmentInteractionListener mListener;

    private View mView;
    private TextView textview_question;

    public BranchFragment() {
        // Required empty public constructor

    }


    public static BranchFragment newInstance(int choice) {
        BranchFragment fragment = new BranchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CHOICE, choice);


        UsersChoice uc = UsersChoice.getInstance();
        uc.setChoice(COUNT, choice);
        Log.d(TAG, "[" + COUNT + "] " + choice);
        COUNT ++;

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getInt(ARG_CHOICE);
            last = getArguments().getInt("lastQuestion");



        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_branch, container, false);

        textview_question = (TextView) mView.findViewById(R.id.branch_question);

        if(last == 100){    //last question 4
            textview_question.setText("조용한 곳을 원하나요?");
        }else if(last == 10){   //3
            textview_question.setText("커피 좋아해요?");
        }else if(last == 5){      //2
            textview_question.setText("지금 혼자있어요?");
        }else if(last == 0){    //1
            textview_question.setText("단거 좋아해요?");
        }

//        switch(param1){
//            case CHOICE_YES:
//                textview_question.append(" YES" + COUNT);
//                break;
//            case CHOICE_NO:
//                textview_question.append(" NO" + COUNT);
//                break;
//            default:
//                textview_question.append(" default");
//
//        }

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
        }
        else {
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
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroy called!");
        mView = null; // now cleaning up!
        System.gc();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
