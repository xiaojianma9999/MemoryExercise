package com.xiaojianma.memoryexercise.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.xiaojianma.memoryexercise.R;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaseActivity extends Activity implements TextToSpeech.OnInitListener {

    private static final String TAG = "BaseActivity";

    // TTS对象
    protected TextToSpeech mTextToSpeech;

    protected float speechRate = 1.0f;

    protected String speechRateText = "1.0X";

    protected ExecutorService service = Executors.newSingleThreadExecutor();

    protected volatile Lock lock = new ReentrantLock();

    // 1、朗读文本 2、自动播放设置页面 3、随机播放设置页面
    protected volatile int executeThread = 1;

    protected volatile Condition speckCondition = lock.newCondition();

    protected volatile Condition autoPlayCondition = lock.newCondition();

    protected volatile Condition randomPlayCondition = lock.newCondition();

    protected volatile boolean isAutoPlay = false;

    protected volatile boolean isRandomAutoPlay = false;

    protected volatile boolean pauseAutoPlay = false;

    protected volatile boolean pauseRandomPlay = false;

    protected Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTextToSpeech();
    }

    private void initTextToSpeech() {
        // 参数Context,TextToSpeech.OnInitListener
        mTextToSpeech = new TextToSpeech(this, this);
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        mTextToSpeech.setPitch(1.0f);
        // 设置语速
        mTextToSpeech.setSpeechRate(speechRate);
    }

    @Override
    protected void onDestroy() {
        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
            mTextToSpeech = null;
        }
        service.shutdownNow();
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            /*
                使用的是小米手机进行测试，打开设置，在系统和设备列表项中找到更多设置，
            点击进入更多设置，在点击进入语言和输入法，见语言项列表，点击文字转语音（TTS）输出，
            首选引擎项有三项为Pico TTs，科大讯飞语音引擎3.0，度秘语音引擎3.0。其中Pico TTS不支持
            中文语言状态。其他两项支持中文。选择科大讯飞语音引擎3.0。进行测试。

                如果自己的测试机里面没有可以读取中文的引擎，
            那么不要紧，我在该Module包中放了一个科大讯飞语音引擎3.0.apk，将该引擎进行安装后，进入到
            系统设置中，找到文字转语音（TTS）输出，将引擎修改为科大讯飞语音引擎3.0即可。重新启动测试
            Demo即可体验到文字转中文语言。
             */
            // setLanguage设置语言
            int result = mTextToSpeech.setLanguage(Locale.CHINA);
            // TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失
            // TextToSpeech.LANG_NOT_SUPPORTED：不支持
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void speechText(String text) {
        lock.lock();
        try {
//            while (executeThread != 1) {
//                Log.w(TAG, "speechText await");
//                speckCondition.await();
//            }
            if (mTextToSpeech != null) {
                /*
                TextToSpeech的speak方法有两个重载。
                // 执行朗读的方法
                speak(CharSequence text,int queueMode,Bundle params,String utteranceId);
                // 将朗读的的声音记录成音频文件
                synthesizeToFile(CharSequence text,Bundle params,File file,String utteranceId);
                第二个参数queueMode用于指定发音队列模式，两种模式选择
                （1）TextToSpeech.QUEUE_FLUSH：该模式下在有新任务时候会清除当前语音任务，执行新的语音任务
                （2）TextToSpeech.QUEUE_ADD：该模式下会把新的语音任务放到语音任务之后，
                等前面的语音任务执行完了才会执行新的语音任务
                */
                speechRate = getSpeechRate();
                mTextToSpeech.setSpeechRate(speechRate);
                mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
            if (isAutoPlay) {
                executeThread = 2;
                autoPlayCondition.signal();
            } else if (isRandomAutoPlay) {
                executeThread = 3;
                randomPlayCondition.signal();
            }
        } catch (Exception e) {
            Log.e(TAG, "speechText error: " + e.toString());
        } finally {
            lock.unlock();
        }

    }

    private float getSpeechRate() {
        if (getString(R.string.point_five_rate).equals(speechRateText)) {
            return 0.5f;
        } else if (getString(R.string.point_seventy_five_rate).equals(speechRateText)) {
            return 0.75f;
        } else if (getString(R.string.one_rate).equals(speechRateText)) {
            return 1.0f;
        } else if (getString(R.string.one_point_twenty_five_rate).equals(speechRateText)) {
            return 1.25f;
        } else if (getString(R.string.one_point_five_rate).equals(speechRateText)) {
            return 1.5f;
        } else if (getString(R.string.one_point_seventy_five_rate).equals(speechRateText)) {
            return 1.75f;
        } else if (getString(R.string.two_rate).equals(speechRateText)) {
            return 2.0f;
        }
        return 1.0f;
    }

    protected void initActionBar(String title) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setTitle(title);
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(true);
//            View layoutActionbar = LayoutInflater.from(this).inflate(R.layout.layout_titlebar, null);
//            TextView titleText = layoutActionbar.findViewById(R.id.text_title);
//            titleText.setText(title);
//            actionBar.setCustomView(layoutActionbar);
        }
    }

//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.back:
//                finish();
//                break;
//            case R.id.menu:
//                break;
//            default:
//                break;
//        }
//    }

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
                this.finish();
            case R.id.auto_play:
                CharSequence title = item.getTitle();
                if (title.equals(getString(R.string.auto_play))) {
                    autoPlay(item);
                } else if (title.equals(getString(R.string.pause))) {
                    pauseAutoPlay(item);
                }
                break;
            case R.id.random_play:
                CharSequence state = item.getTitle();
                if (state.equals(getString(R.string.open_random_play))) {
                    randomPlay(item);
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

    protected void pauseAutoPlay(MenuItem item) {
        pauseAutoPlay = true;
        item.setTitle(R.string.auto_play);
        isAutoPlay = false;
//        executeThread = 1;
//        speckCondition.signal();
    }

    protected void autoPlay(MenuItem item) {
        item.setTitle(R.string.pause);
        // 先暂停随机自动播放
        if (menu != null) {
            pauseRandomPlay(menu.getItem(1));
        }
        pauseAutoPlay = false;
        isAutoPlay = true;
        executeThread = 2;
    }

    protected void pauseRandomPlay(MenuItem item) {
        pauseRandomPlay = true;
        item.setTitle(R.string.open_random_play);
        isRandomAutoPlay = false;
//        executeThread = 1;
//        speckCondition.signal();
    }

    protected void randomPlay(MenuItem item) {
        item.setTitle(R.string.close_random_play);
        // 先暂停自动播放
        if (menu != null) {
            pauseAutoPlay(menu.getItem(0));
        }
        pauseRandomPlay = false;
        isRandomAutoPlay = true;
        executeThread = 3;
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
                Toast.makeText(BaseActivity.this, "当前播放速度为：" + speechRateText, Toast.LENGTH_SHORT).show();
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
}