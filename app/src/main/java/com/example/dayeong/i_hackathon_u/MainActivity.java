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
    private static final int NUM_PAGES = 10;
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

        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name', 'address', 'explain',"+ R.drawable.palace1+" );");
        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name2', 'address2', 'explain2',"+ R.drawable.chinese1+");");
        db.execSQL("INSERT INTO result_list (first, second, third, name, address, explain, image) " + "VALUES (1, 1, 1, 'name3', 'address3', 'explain3',"+ R.drawable.pork1+");");
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
            int page_cnt = 3;
            int putPage = position % page_cnt;
            Bundle args = new Bundle();

            db = mDbHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM result_list", null);

            c.moveToPosition(putPage);
            String name = c.getString(4);
            String address = c.getString(5);
            String explain = c.getString(6);
            int image = c.getInt(7);

            args.putString("name", name);
            args.putString("address", address);
            args.putString("explain", explain);
            args.putInt("image", image);

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