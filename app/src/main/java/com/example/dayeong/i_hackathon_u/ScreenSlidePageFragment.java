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

/*        TextView page_num=(TextView)rootView.findViewById(R.id.content);
        int num = (int)bundle.get("num");
        page_num.setText(String.valueOf(num)); */

        ImageView image = (ImageView) rootView.findViewById(R.id.img_view);
        image.setImageResource((Integer)bundle.get("image"));

        TextView nameText = (TextView) rootView.findViewById(R.id.nameText);
        nameText.setText((String) bundle.get("name"));

        TextView addressText = (TextView) rootView.findViewById(R.id.addressText);
        addressText.setText((String) bundle.get("address"));

        TextView explainText = (TextView) rootView.findViewById(R.id.explainText);
        explainText.setText((String) bundle.get("explain"));
        /*"사적 제117호. 도성의 북쪽에 있다고 하여 북궐(北闕)이라고도 불리었다." +
                "조선왕조의 건립에 따라 창건되어 초기에 정궁으로 사용되었으나 임진왜란 " +
                "때 전소된 후 오랫동안 폐허로 남아 있다가 " +
                "조선 말기 고종 때 중건되어 잠시 궁궐로 이용되었다.");
*/
        return rootView;
    }
}