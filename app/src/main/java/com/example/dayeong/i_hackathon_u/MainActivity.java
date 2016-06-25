package com.example.dayeong.i_hackathon_u;

/**
 * Created by Dayeong on 2016-06-22.
 */

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    private static final int NUM_PAGES = 100;
    private MyViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int prevPosition = NUM_PAGES / 2;

    SQLiteDatabase db;
    ResultDBHelper mDbHelper;

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

        // create DB!
        mDbHelper = new ResultDBHelper(getApplicationContext());
        db = mDbHelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS result_list;");
        db.execSQL("CREATE TABLE result_list (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FIRST INTEGER, SECOND INTEGER, THIRD INTEGER, NAME TEXT, ADDRESS TEXT, " +
                "EXPLAIN TEXT, IMAGE INT);");

        db.execSQL("DROP TABLE IF EXISTS coupon;");
        db.execSQL("CREATE TABLE coupon " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "company_type TEXT," +
                "company TEXT," +
                "company_desc TEXT," +
                "company_addr TEXT," +
                "company_phone TEXT," +
                "company_open_hour TEXT," +
                "company_thumb TEXT," +
                "company_menu TEXT," +
                "coupon_content TEXT," +
                "coupon_price INT," +
                "created_date TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP');");

        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name', 'address', 'explain'," + R.drawable.palace1 + " );");
        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name2', 'address2', 'explain2'," + R.drawable.chinese1 + ");");
        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name3', 'address3', 'explain3'," + R.drawable.pork1 + ");");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '풍년집', '풍년집은 숭실대 앞의 삼겹무한 전문점입니다.', '서울 동작구 상도로 383 1층', '02-822-2949', '오전 11시 30분 ~ 새벽 1시', '01.jpg', '01_m.jpg', '그린티 프라푸치노 20% 할인', 6200, '2016-06-24 23:43:23');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('chinese', '황궁쟁반짜장', '동작구 상도동에 위치한 중식황궁쟁반짜장', '서울특별시 동작구 상도동 506-1', '02-822-5575', '11:00 ~ 21:00', '02.png', '02_m.png', '쟁반짜장 할인 30%', 5700, '2016-06-25 01:15:42');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('pizza', '피자헤븐', '상도점 피자헤븐 입니다❤ 중앙대&숭실대 학생분들 좋아요눌러주세요!! 다양한 이벤트도 많이 할 예정입니다!', '서울특별시 동작구 상도1동 369', '02-826-7755', '11:00 ~ 23:00', '03.jpeg', '03_m.jpg', '핫쭈꾸쭈꾸 할인 25%', 25000, '2016-06-25 14:27:30');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('snack', '현선이네', '숭실대 중문 현선이네', '서울 동작구 사당로 20', '02-823-5056', '11:30 - 21:00', '04.jpeg', '04_m.jpg', '전메뉴 할인 20%', 0, '2016-06-25 14:31:03');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '파라다이스', '숭실대학교 정보대 밑 파라다이스', '서울특별시 동작구 상도동 506-10', '02-815-6119', '정보없음', '05.jpeg', '05_m.jpg', '김치볶음밥 할인 20%', 0, '2016-06-25 14:32:56');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '코웍비스트로', '코웍비스트로닭갈비, Seoul, South Korea.', '서울특별시 동작구 상도1동 504', '070-8277-8721', '11:00 ~ 23:00', '06.jpeg', '06_m.jpg', '닭갈비 할인 10% ', 7200, '2016-06-25 14:33:56');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('chinese', '취향', '숭실대 앞 취향저격 중국집! 취향', '서울특별시 동작구 상도1동 501-7', '', '10:30 ~ 21:00', '07.jpeg', '07_m.jpg', '찹쌀탕수육 할인 10%', 8000, '2016-06-25 14:36:50');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('snack', '추억과 김밥', '숭실대 앞 김밥집', '서울특별시 동작구 상도동 506-3', '02-815-3380', '정보없음', '08.png', '08_m.jpg', '김밥 할인 10%', 1800, '2016-06-25 14:42:16');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '청운 숯불갈비', '청운의 맛있는 식사', '서울특별시 동작구 상도동 505-7', '02-816-1001', '정보없음', '09.jpeg', '09_m.jpg', '냉동삼겹살 할인', 5500, '2016-06-25 14:46:15');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('chinese', '짬뽕 잘하는집', '짬뽕 잘하는집', '서울특별시 동작구 상도1동 502-4', ' ', '10:00 ~ 21:00', '10.jpeg', '10_m.jpg', '짬뽕 할인 10%', 5500, '2016-06-25 14:47:21');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '진국설렁탕', '진국설렁탕', '서울특별시 동작구 상도동 500', '02-813-4848', '정보없음', '11.jpeg', '11_m.jpg', '설렁탕 할인 10%', 5500, '2016-06-25 14:50:42');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('snack', '죠스떡볶이', '죠스떡볶이', '서울특별시 동작구 상도동 505-1', '02-813-6234', '매일 AM 10:00 ~ PM 24:00', '12.jpeg', '12_m.jpg', '떡볶이 할인 20%', 4800, '2016-06-25 14:52:29');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('fusion', '이삭토스트', '토스트 체인점', '서울특별시 동작구 상도1동 501-2', '02-813-4999', '08:00 ~ 22:00', '13.jpeg', '13_m.jpg', '계란토스트 할인 10%', 4200, '2016-06-25 14:55:38');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '이레김밥', '숭실대 앞 김밥집', '서울특별시 동작구 상도동 503', '02-816-6137', '07:00 ~ 21:00', '14.jpeg', '14_m.jpg', '라면 할인 10%', 2500, '2016-06-25 14:57:22');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('japanese', '오니기리와이규동 ', '오니기리와이규동 숭실대점', '서울 동작구 상도동 501-2', '02-821-0290', '매일 10:00~21:30', '15.jpeg', '15_m.jpg', '오니기리 20%', 3500, '2016-06-25 15:00:22');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '옆집식당', '숭실대 앞 김치찌개 전문점', '서울특별시 동작구 상도동 504-7', ' ', '정보없음', '16_m.jpg', '16_m.jpg', '김치찌개 할인 10%', 4400, '2016-06-25 15:03:10');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('chinese', '연래춘', '숭실대 중국집', '서울특별시 동작구 상도동 505-7', '02-816-2007', '정보없음', '17.png', '17_m.jpg', '탕수육 할인 10%', 5800, '2016-06-25 17:55:04');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('chicken', '여우치킨', '여우치킨 숭실대점입니다.', '서울특별시 동작구 상도동 505-9', '02-822-1999', '정보없음', '18_m.jpg', '18_m.jpg', '여우치킨 할인 10%', 9200, '2016-06-25 17:58:14');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('japanese', '아영이네', '아영이네 음식점입니다.', '서울특별시 동작구 상도로67길 27', '010-2701-4533', '10:00 ~ 20:30', '19.jpeg', '19_m.jpg', '돈까스 할인 10%', 5400, '2016-06-25 18:02:11');");
        db.execSQL("INSERT INTO coupon (company_type, company, company_desc, company_addr, company_phone, company_open_hour, company_thumb, company_menu, coupon_content, coupon_price, created_date) VALUES ('korean', '숭실골 고기마을', '숭실골 고기마을입니다.', '서울특별시 동작구 상도동 506-6', '02-822-7797', '정보없음', '20.jpeg', '20_m.jpg', '고기류 할인 10%', 0, '2016-06-25 18:09:54');");
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
        String SENT = "SMS_SENT";
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
        sms.sendTextMessage(phoneNumber, null, couponMessage, sentPI, null);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ScreenSlidePageFragment contentFragment = new ScreenSlidePageFragment();
            int page_cnt = 16;
            int putPage = position % page_cnt;
            Bundle args = new Bundle();

            db = mDbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM coupon", null);

            c.moveToPosition(putPage);
            String name = c.getString(2);
            String explain = c.getString(3);
            String address = c.getString(4);
            String phone = c.getString(5);
            String openHour = c.getString(6);
            String content = c.getString(9);
//            int image = c.getInt(7);

            args.putString("name", name);
            args.putString("explain", explain);
            args.putString("address", address);
            args.putString("phone", phone);
            args.putString("openHour", openHour);
            args.putString("content", content);
//            args.putInt("image", image);

            contentFragment.setArguments(args);

            prevPosition = position;

            return contentFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}