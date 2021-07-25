package com.xiaojianma.memoryexercise.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.viewpager2.widget.ViewPager2;

import com.xiaojianma.memoryexercise.R;
import com.xiaojianma.memoryexercise.adapter.ViewPagerAdapter;
import com.xiaojianma.memoryexercise.model.NumberImage;
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

    protected void autoPlay(MenuItem item) {
        super.autoPlay(item);
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

    protected void randomPlay(MenuItem item) {
        super.randomPlay(item);
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
}