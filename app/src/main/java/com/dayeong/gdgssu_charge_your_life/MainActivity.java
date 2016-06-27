package com.dayeong.gdgssu_charge_your_life;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements BranchFragment.OnFragmentInteractionListener, LeafFragment.OnFragmentInteractionListener {

    private static final String ARG_CHOICE = "CHOICE";  //유저가 선택한 답변. YES(1), NO(0)

    private static final int CHOICE_YES = 1;
    private static final int CHOICE_NO = 0;
    private static final int IS_END = UsersChoice.QUEST_NUM;


    private TextView textView_main;
    private TextView textView_yes;
    private TextView textView_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLayout();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            Fragment rootfragment = new RootFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_place, rootfragment);
            fragmentTransaction.commit();
        }
        //nope

    }

    private void setLayout() {
        textView_main = (TextView) findViewById(R.id.text_main);
        textView_yes = (TextView) findViewById(R.id.text_yes);
        textView_no = (TextView) findViewById(R.id.text_no);

    }


    public void ChangeFragment(View v) {

        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int temp_choice = 0;
        switch (v.getId()){
            case R.id.text_yes:
                temp_choice = CHOICE_YES;
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

                break;
            case R.id.text_no:
                temp_choice = CHOICE_NO;
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down);

                break;

        }
        Bundle data = new Bundle();
        //마지막 질문이면 leaf fragment를 생성
        if (BranchFragment.COUNT == IS_END - 1) {

            fragment = LeafFragment.newInstance(BranchFragment.COUNT, temp_choice);
        }else if(BranchFragment.COUNT == IS_END - 2){
            //4
            fragment = BranchFragment.newInstance(temp_choice);
            data.putInt("lastQuestion", 100);
            fragment.setArguments(data);

        }else if(BranchFragment.COUNT == IS_END - 3){
            //3
            data.putInt("lastQuestion",10);
            fragment = BranchFragment.newInstance(temp_choice);
            fragment.setArguments(data);

        }else if(BranchFragment.COUNT == IS_END - 4){
            //2
            data.putInt("lastQuestion",5);
            fragment = BranchFragment.newInstance(temp_choice);
            fragment.setArguments(data);
        }else{
            data.putInt("lastQuestion",0);
            fragment = BranchFragment.newInstance(temp_choice);
            fragment.setArguments(data);
        }

        fragment.setArguments(data);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_place, fragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {


    }
}
