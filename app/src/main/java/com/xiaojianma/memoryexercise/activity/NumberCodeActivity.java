package com.xiaojianma.memoryexercise.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.xiaojianma.memoryexercise.model.NumberImage;
import com.xiaojianma.memoryexercise.R;
import com.xiaojianma.memoryexercise.adapter.ViewPagerAdapter;
import com.xiaojianma.memoryexercise.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class NumberCodeActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private List<NumberImage> list = new ArrayList<>();

    private ViewPager2 viewPager;

    private Handler handler = new Handler();

    private Menu menu;

    private volatile boolean pauseAutoPlay = false;

    private volatile boolean pauseRandomPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_code);
        initActionBar(getString(R.string.number_code));
        initRes();
        initSearch();
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(110);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // TODO validate success, do something
                speechText(list.get(position).getNumberText());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initSearch() {
        final EditText searchText = (EditText) findViewById(R.id.search_text);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchContent(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final Button searchBtn = findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchText = ((EditText) findViewById(R.id.search_text)).getText().toString().trim();
                searchContent(searchText);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speechText(list.get(viewPager.getCurrentItem()).getNumberText());
            }
        }, 200);
    }

    private void searchContent(CharSequence searchText) {
        int i = 0;
        for (NumberImage numberImage : list) {
            if (numberImage.getNumberText().contains(searchText)) {
                Log.d(TAG, "onClick: searchText: " + searchText + ", position: " + i);
                viewPager.setCurrentItem(i);
                break;
            }
            i++;
        }
    }

    private void initRes() {
        list = DataUtils.getNumberData(getResources());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.isCheckable()) {
            item.setChecked(true);
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                // 返回键的id
                finish();
            case R.id.auto_play:
                CharSequence title = item.getTitle();
                if (title.equals(getString(R.string.auto_play))) {
                    autoPlay();
                    item.setTitle(R.string.pause);
                } else if (title.equals(getString(R.string.pause))) {
                    pauseAutoPlay(item);
                }
                break;
            case R.id.random_play:
                CharSequence state = item.getTitle();
                if (state.equals(getString(R.string.open_random_play))) {
                    randomPlay();
                    item.setTitle(R.string.close_random_play);
                } else if (state.equals(getString(R.string.close_random_play))) {
                    pauseRandomPlay(item);
                }
                break;
            case R.id.play_speed:
                PopupWindow popupWindow = new PopupWindow(this);
                View inflate = LayoutInflater.from(this).inflate(R.layout.pop_window_layout, null);
                popupWindow.setContentView(inflate);
                setSelected(inflate);
                setClickListener(inflate, popupWindow);
//                popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//                popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                View decorView = getWindow().getDecorView();
                int width = decorView.getWidth();
                int height = decorView.getHeight();
                popupWindow.showAtLocation(decorView, Gravity.NO_GRAVITY, width / 10 * 7, height / 5);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickListener(View inflate, final PopupWindow popupWindow) {
        setSpeechRateText(inflate, popupWindow, R.id.point_five);
        setSpeechRateText(inflate, popupWindow, R.id.point_seventy_five);
        setSpeechRateText(inflate, popupWindow, R.id.one);
        setSpeechRateText(inflate, popupWindow, R.id.one_point_twenty_five);
        setSpeechRateText(inflate, popupWindow, R.id.one_point_five);
        setSpeechRateText(inflate, popupWindow, R.id.one_point_seventy_five);
        setSpeechRateText(inflate, popupWindow, R.id.two);
    }

    private void setSpeechRateText(final View inflate, final PopupWindow popupWindow, final int id) {
        final TextView text = inflate.findViewById(id);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRateText = text.getText().toString().trim();
                setGrayBackground(inflate, id);
                popupWindow.dismiss();
                Toast.makeText(NumberCodeActivity.this, "当前播放速度为：" + speechRateText, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void setSelected(View inflate) {
        if (getString(R.string.point_five_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.point_five);
        } else if (getString(R.string.point_seventy_five_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.point_seventy_five);
        } else if (getString(R.string.one_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.one);
        } else if (getString(R.string.one_point_twenty_five_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.one_point_twenty_five);
        } else if (getString(R.string.one_point_five_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.one_point_five);
        } else if (getString(R.string.one_point_seventy_five_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.one_point_seventy_five);
        } else if (getString(R.string.two_rate).equals(speechRateText)) {
            setGrayBackground(inflate, R.id.two);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void setGrayBackground(View inflate, int p) {
        inflate.findViewById(p).setBackgroundColor(R.color.gray);
    }

    private void pauseAutoPlay(MenuItem item) {
        pauseAutoPlay = true;
        item.setTitle(R.string.auto_play);
        isAutoPlay = false;
//        executeThread = 1;
//        speckCondition.signal();
    }

    private void autoPlay() {
        // 先暂停随机自动播放
        if (menu != null) {
            pauseRandomPlay(menu.getItem(1));
        }
        pauseAutoPlay = false;
        isAutoPlay = true;
        executeThread = 2;
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                int playSize = list.size();
                int index = viewPager.getCurrentItem();
                while (!pauseAutoPlay) {
//                    lock.lock();
                    try {
//                        while (executeThread != 2) {
//                            Log.w(TAG, "call: autoPlay await");
//                            autoPlayCondition.await();
//                        }
                        index %= playSize;
                        viewPager.setCurrentItem(index);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Log.e(TAG, "run: autoPlay()" + e.toString());
                        }
//                        executeThread = 1;
//                        speckCondition.signal();
                        index++;
                    } catch (Exception e) {
                        Log.e(TAG, "call: autoPlay error: " + e.toString());
                    } finally {
//                        lock.unlock();
                    }
                }
//                executeThread = 1;
//                speckCondition.signal();
                return Boolean.TRUE;
            }
        };
        service.submit(callable);
    }

    private void randomPlay() {
        // 先暂停自动播放
        if (menu != null) {
            pauseAutoPlay(menu.getItem(0));
        }
        pauseRandomPlay = false;
        isRandomAutoPlay = true;
        executeThread = 3;
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                int playSize = list.size();
                Random random = new Random();
                int index;
                while (!pauseRandomPlay) {
//                    lock.lock();
                    try {
//                        while (executeThread != 3) {
//                            Log.w(TAG, "call: randomPlay await");
//                            randomPlayCondition.await();
//                        }
                        index = random.nextInt(playSize);
//                        index %= playSize;
                        viewPager.setCurrentItem(index);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Log.e(TAG, "run: randomPlay()" + e.toString());
                        }
//                        executeThread = 1;
//                        speckCondition.signal();
                    } catch (Exception e) {
                        Log.e(TAG, "call: randomPlay error: " + e.toString());
                    } finally {
//                        lock.unlock();
                    }
                }
//                executeThread = 1;
//                speckCondition.signal();
                return Boolean.TRUE;
            }
        };
        service.submit(callable);
    }

    private void pauseRandomPlay(MenuItem item) {
        pauseRandomPlay = true;
        item.setTitle(R.string.open_random_play);
        isRandomAutoPlay = false;
//        executeThread = 1;
//        speckCondition.signal();
    }
}