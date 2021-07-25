package com.xiaojianma.memoryexercise.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.xiaojianma.memoryexercise.R;
import com.xiaojianma.memoryexercise.utils.DataUtils;

import java.util.Random;
import java.util.concurrent.Callable;

public class LetterCodeActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private String[] data = DataUtils.getLetterData();

    // TTS对象
    private TextToSpeech mTextToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_code);
        initActionBar(getString(R.string.letter_code));
        Button numberBtn = findViewById(R.id.number_btn);
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                LetterCodeActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                speechText(data[i]);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                speechText(data[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                speechText(data[i]);
                return false;
            }
        });
    }

    protected void autoPlay(MenuItem item) {
        super.autoPlay(item);
        Callable<Boolean> callable = new Callable<Boolean>() {
            @Override
            public Boolean call() {
                int playSize = data.length;
                int index = 0;
                while (!pauseAutoPlay) {
                    index %= playSize;
                    speechText(data[index]);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "run: autoPlay()" + e.toString());
                    }
                    index++;
                }
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
                int playSize = data.length;
                Random random = new Random();
                int index;
                while (!pauseRandomPlay) {
                    index = random.nextInt(playSize);
                    speechText(data[index]);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "run: randomPlay()" + e.toString());
                    }
                }
                return Boolean.TRUE;
            }
        };
        service.submit(callable);
    }
}