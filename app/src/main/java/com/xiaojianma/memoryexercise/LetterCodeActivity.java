package com.xiaojianma.memoryexercise;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class LetterCodeActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private String[] data = {"A a——苹果；海军帽",
            "B b——笔；爸",
            "C c——月亮",
            "D d——弓；弟",
            "E e——鹅；饿；姨",
            "F f——父；斧",
            "G g——哥；鸽",
            "H h——椅子；喝",
            "I i——蜡烛；爱",
            "J j——钩子",
            "K k——机枪",
            "L l——棒；棍；长",
            "M m——麦当劳；",
            "N n——门；泥；你",
            "O o——圆、太阳、蛋",
            "P p——旗；皮",
            "Q q——球；企鹅",
            "R r——人；小草；",
            "S s——蛇；师；是",
            "T  t——伞把； 踢、题、提、体；",
            "U u——杯子；油；有",
            "V v——山谷；胜利",
            "W w—— 屋；乌；雾；巫",
            "X  x——剪刀；错误",
            "Y y——撑衣杆；药",
            "Z z——闪电；子"};

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
}