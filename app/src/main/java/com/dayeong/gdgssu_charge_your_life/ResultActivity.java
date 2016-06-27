package com.dayeong.gdgssu_charge_your_life;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends FragmentActivity {
    private static final int NUM_PAGES = 1000;
    private static final String TAG = "RESULT_ACTIVITY";
    private MyViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int prevPosition = NUM_PAGES / 2;

    SQLiteDatabase db;
    ResultDBHelper mDbHelper;

    int first=0, second=0, third=0, fourth=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        mPager = (MyViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPagingEnabled(true);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(this.NUM_PAGES / 2, false);

        EditText putPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        putPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        Intent i = getIntent();
        if(i != null){
            int[] res = i.getIntArrayExtra("resultArray");

            first = res[0];
            second = res[1];
            third = res[2];
            fourth = res[3];

            Log.d(TAG, Integer.toString(first) + Integer.toString(second) + Integer.toString(third) + Integer.toString(fourth) + "");
        }



        // create DB!
        mDbHelper = new ResultDBHelper(getApplicationContext());
        db = mDbHelper.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS coupon;");
        db.execSQL("CREATE TABLE coupon (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "first INTEGER , second INTEGER , third INTEGER , fourth INTEGER ," +
                "company_type TEXT, company TEXT, company_desc TEXT, company_addr TEXT," +
                "company_phone TEXT, company_open_hour TEXT, company_thumb TEXT," +
                "company_menu TEXT, coupon_content TEXT, coupon_price INT," +
                "created_date TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP');");

        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '풍년집', '풍년집은 숭실대 앞의 삼겹무한 전문점입니다.', '서울 동작구 상도로 383 1층', '02-822-2949', '오전 11시 30분 ~ 새벽 1시',"+R.drawable.pic01+", '01_m.jpg', '그린티 프라푸치노 20% 할인', 6200, '2016-06-24 23:43:23');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'chinese', '황궁쟁반짜장', '동작구 상도동에 위치한 중식황궁쟁반짜장', '서울특별시 동작구 상도동 506-1', '02-822-5575', '11:00 ~ 21:00', "+R.drawable.pic02+", '02_m.png', '쟁반짜장 할인 30%', 5700, '2016-06-25 01:15:42');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'pizza', '피자헤븐', '상도점 피자헤븐 입니다❤ 중앙대&숭실대 학생분들 좋아요눌러주세요!! 다양한 이벤트도 많이 할 예정입니다!', '서울특별시 동작구 상도1동 369', '02-826-7755', '11:00 ~ 23:00', "+R.drawable.pic03+", '03_m.jpg', '핫쭈꾸쭈꾸 할인 25%', 25000, '2016-06-25 14:27:30');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'snack', '현선이네', '숭실대 중문 현선이네', '서울 동작구 사당로 20', '02-823-5056', '11:30 - 21:00', "+R.drawable.pic04+", '04_m.jpg', '전메뉴 할인 20%', 0, '2016-06-25 14:31:03');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '파라다이스', '숭실대학교 정보대 밑 파라다이스', '서울특별시 동작구 상도동 506-10', '02-815-6119', null, "+R.drawable.pic05+", '05_m.jpg', '김치볶음밥 할인 20%', 0, '2016-06-25 14:32:56');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '코웍비스트로', '코웍비스트로닭갈비, Seoul, South Korea.', '서울특별시 동작구 상도1동 504', '070-8277-8721', '11:00 ~ 23:00', "+R.drawable.pic06+", '06_m.jpg', '닭갈비 할인 10% ', 7200, '2016-06-25 14:33:56');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'chinese', '취향', '숭실대 앞 취향저격 중국집! 취향', '서울특별시 동작구 상도1동 501-7', '', '10:30 ~ 21:00', "+R.drawable.pic07+", '07_m.jpg', '찹쌀탕수육 할인 10%', 8000, '2016-06-25 14:36:50');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'snack', '추억과 김밥', '숭실대 앞 김밥집', '서울특별시 동작구 상도동 506-3', '02-815-3380', null, "+R.drawable.pic08+", '08_m.jpg', '김밥 할인 10%', 1800, '2016-06-25 14:42:16');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '청운 숯불갈비', '청운의 맛있는 식사', '서울특별시 동작구 상도동 505-7', '02-816-1001', null, "+R.drawable.pic09+", '09_m.jpg', '냉동삼겹살 할인', 5500, '2016-06-25 14:46:15');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'chinese', '짬뽕 잘하는집', '짬뽕 잘하는집', '서울특별시 동작구 상도1동 502-4', ' ', '10:00 ~ 21:00', "+R.drawable.pic10+", '10_m.jpg', '짬뽕 할인 10%', 5500, '2016-06-25 14:47:21');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '진국설렁탕', '진국설렁탕', '서울특별시 동작구 상도동 500', '02-813-4848', null, '11.jpeg', "+R.drawable.pic11+", '설렁탕 할인 10%', 5500, '2016-06-25 14:50:42');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'snack', '죠스떡볶이', '죠스떡볶이', '서울특별시 동작구 상도동 505-1', '02-813-6234', '매일 AM 10:00 ~ PM 24:00', "+R.drawable.pic12+", '12_m.jpg', '떡볶이 할인 20%', 4800, '2016-06-25 14:52:29');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'fusion', '이삭토스트', '토스트 체인점', '서울특별시 동작구 상도1동 501-2', '02-813-4999', '08:00 ~ 22:00', "+R.drawable.pic13+", '13_m.jpg', '계란토스트 할인 10%', 4200, '2016-06-25 14:55:38');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'korean', '이레김밥', '숭실대 앞 김밥집', '서울특별시 동작구 상도동 503', '02-816-6137', '07:00 ~ 21:00', "+R.drawable.pic14+", '14_m.jpg', '라면 할인 10%', 2500, '2016-06-25 14:57:22');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'japanese', '오니기리와이규동 ', '오니기리와이규동 숭실대점', '서울 동작구 상도동 501-2', '02-821-0290', '매일 10:00~21:30', "+R.drawable.pic15+", '15_m.jpg', '오니기리 20%', 3500, '2016-06-25 15:00:22');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '옆집식당', '숭실대 앞 김치찌개 전문점', '서울특별시 동작구 상도동 504-7', ' ', null, "+R.drawable.pic16_m+", '16_m.jpg', '김치찌개 할인 10%', 4400, '2016-06-25 15:03:10');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'chinese', '연래춘', '숭실대 중국집', '서울특별시 동작구 상도동 505-7', '02-816-2007', null, "+R.drawable.pic17+", '17_m.jpg', '탕수육 할인 10%', 5800, '2016-06-25 17:55:04');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'chicken', '여우치킨', '여우치킨 숭실대점입니다.', '서울특별시 동작구 상도동 505-9', '02-822-1999', null, "+R.drawable.pic18_m+", '18_m.jpg', '여우치킨 할인 10%', 9200, '2016-06-25 17:58:14');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 1, 'japanese', '아영이네', '아영이네 음식점입니다.', '서울특별시 동작구 상도로67길 27', '010-2701-4533', '10:00 ~ 20:30', "+R.drawable.pic19+", '19_m.jpg', '돈까스 할인 10%', 5400, '2016-06-25 18:02:11');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (0, 0, 0, 0, 'korean', '숭실골 고기마을', '숭실골 고기마을입니다.', '서울특별시 동작구 상도동 506-6', '02-822-7797', null, "+R.drawable.pic20+", '20_m.jpg', '고기류 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 1, 1, 1, 'cafe', 'cafe 뿌리', '아메리카노가 맛있는 집!', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic21+", '20_m.jpg', '커피류 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 1, 0, 1, 'cafe', '커피 투모로우', '신선한 커피만 제공합니다.', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic22+", '20_m.jpg', '모든 메뉴 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 1, 0, 1, 'cafe', '다락방', '진짜 다락방에 놀러온 느낌!', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic23+", '20_m.jpg', '음료 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 1, 1, 1, 'cafe', '파심 A+', '조용한 분위기의 스터디 카페', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic24+", '20_m.jpg', '모든 메뉴 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 1, 0, 1, 'cafe', '냥', '고양이와 함께하는 즐거운 하루', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic25+", '20_m.jpg', '음료 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 0, 0, 0, 'clothes', '언니네 옷가게', '어디서 샀는지 궁금한 언니의 그 옷', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic26+", '20_m.jpg', '상의 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 0, 0, 0, 'clothes', '월요일', '내가 입고싶은 옷만 팔아요', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic27+", '20_m.jpg', '모든 옷 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 0, 1, 0, 'clothes', '클로즈포맨', '남성을 위한 옷가게', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic28+", '20_m.jpg', '니트류 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 0, 0, 0, 'clothes', '엄마! 나 이거', '아이들이 직접 고르는 예쁜 옷들', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic29+", '20_m.jpg', '모든 옷 할인 10%', 0, '2016-06-25 18:09:54');");
        db.execSQL("INSERT INTO coupon (first, second, third, fourth, company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) " +
                "VALUES (1, 0, 1, 0, 'clothes', '멋질권리', '중장년층을 위한 옷', '서울특별시 동작구 상도동', null, null, "+R.drawable.pic30+", '20_m.jpg', '모든 옷 할인 10%', 0, '2016-06-25 18:09:54');");
    }

    public void onClickLeftButton(View view) {
        if (mPager.getCurrentItem() == 0) {
            mPager.setCurrentItem(NUM_PAGES - 1);
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void onClickRightButton(View view) {
        if (mPager.getCurrentItem() == NUM_PAGES - 1) {
            mPager.setCurrentItem(0);
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        }
    }

    public void onClickSend(View view) {
        Toast.makeText(getBaseContext(), "쿠폰 전송 완료", Toast.LENGTH_SHORT).show();
   /*     String SENT = "SMS_SENT";
        String couponMessage = "이것은 쿠폰입니당~";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "쿠폰 전송 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        SmsManager sms = SmsManager.getDefault();

        EditText getPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        String phoneNumber = getPhoneNumber.getText().toString();
        sms.sendTextMessage(phoneNumber, null, couponMessage, sentPI, null);*/
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment contentFragment = new ScreenSlidePageFragment();

            db = mDbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM coupon WHERE first="+ first +" AND fourth=" + fourth + ";", null);

            int page_cnt = c.getCount();

            String state = "조용한 곳을 원하는";

            if(first==0 && fourth == 0)
                state = "식사하실 활발한 곳을 원하는";
            else if(first==0 && fourth == 1)
                state = "식사하실 조용한 곳을 원하는";
            else if(first==1 && fourth == 0)
                state = "식사하신 활발한 곳을 원하는";
            else if(first==1 && fourth == 1)
                state = "식사하신 조용한 곳을 원하는";

            if(page_cnt == 0){
                Bundle args = new Bundle();
                args.putString("to", null);
                args.putString("name", null);
                args.putString("explain", null);
                args.putString("address", null);
                args.putString("phone", null);
                args.putString("openHour", null);
                args.putString("content", null);
                args.putInt("image", 0);
                args.putString("state", null);

                contentFragment.setArguments(args);
                return contentFragment;
            }
            else {
                int putPage = position % page_cnt;
                Bundle args = new Bundle();

                c.moveToPosition(putPage);
                String to = "동작구";
                String name = c.getString(6);
                String explain = c.getString(7);
                String address = c.getString(8);
                String phone = c.getString(9);
                String openHour = c.getString(10);
                String content = c.getString(13);
                int image = c.getInt(11);

                args.putString("to", to);
                args.putString("name", name);
                args.putString("explain", explain);
                args.putString("address", address);
                args.putString("phone", phone);
                args.putString("openHour", openHour);
                args.putString("content", content);
                args.putInt("image", image);
                args.putString("state", state);

                contentFragment.setArguments(args);

                TextView toAndStateText = (TextView) findViewById(R.id.toAndStateText);
                String you = to + "로 가시는 " + state + " 당신!";
                toAndStateText.setText(you);

                prevPosition = position;

                return contentFragment;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
