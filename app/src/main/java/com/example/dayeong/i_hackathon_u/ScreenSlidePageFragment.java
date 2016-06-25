package com.example.dayeong.i_hackathon_u;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        Bundle bundle = getArguments();

        ImageView image = (ImageView) rootView.findViewById(R.id.img_view);
        image.setImageResource(R.drawable.palace1);//(Integer)bundle.get("image"));

        TextView nameText = (TextView) rootView.findViewById(R.id.nameText);
        nameText.setText((String) bundle.get("name"));

        TextView addressText = (TextView) rootView.findViewById(R.id.addressText);
        addressText.setText((String) bundle.get("address"));

        TextView explainText = (TextView) rootView.findViewById(R.id.explainText);
        explainText.setText((String) bundle.get("explain"));

        TextView phoneText = (TextView) rootView.findViewById(R.id.phoneText);
        phoneText.setText((String) bundle.get("phone"));

        TextView openHourText = (TextView) rootView.findViewById(R.id.openHourText);
        openHourText.setText((String) bundle.get("openHour"));

        TextView contentText = (TextView) rootView.findViewById(R.id.contentText);
        contentText.setText((String) bundle.get("content"));


        return rootView;
    }
}