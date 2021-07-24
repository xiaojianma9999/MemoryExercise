package com.xiaojianma.memoryexercise;

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
        list.add(new NumberImage("单0 呼啦圈", getResources().getDrawable(R.drawable.ic_0)));
        list.add(new NumberImage("单1 蜡烛", getResources().getDrawable(R.drawable.ic_1)));
        list.add(new NumberImage("单2 鹅", getResources().getDrawable(R.drawable.ic_2)));
        list.add(new NumberImage("单3 耳朵", getResources().getDrawable(R.drawable.ic_3)));
        list.add(new NumberImage("单4 帆船", getResources().getDrawable(R.drawable.ic_4)));
        list.add(new NumberImage("单5 秤钩", getResources().getDrawable(R.drawable.ic_5)));
        list.add(new NumberImage("单6 勺子", getResources().getDrawable(R.drawable.ic_6)));
        list.add(new NumberImage("单7 镰刀", getResources().getDrawable(R.drawable.ic_7)));
        list.add(new NumberImage("单8 眼镜", getResources().getDrawable(R.drawable.ic_8)));
        list.add(new NumberImage("单9 口哨", getResources().getDrawable(R.drawable.ic_9)));
        list.add(new NumberImage("00 望远镜", getResources().getDrawable(R.drawable.ic_00)));
        list.add(new NumberImage("10 棒球", getResources().getDrawable(R.drawable.ic_10)));
        list.add(new NumberImage("20 自行车", getResources().getDrawable(R.drawable.ic_20)));
        list.add(new NumberImage("30 三轮车", getResources().getDrawable(R.drawable.ic_30)));
        list.add(new NumberImage("40 司令", getResources().getDrawable(R.drawable.ic_40)));
        list.add(new NumberImage("50 武林盟主", getResources().getDrawable(R.drawable.ic_50)));
        list.add(new NumberImage("60 榴莲", getResources().getDrawable(R.drawable.ic_60)));
        list.add(new NumberImage("70 冰淇淋", getResources().getDrawable(R.drawable.ic_70)));
        list.add(new NumberImage("80 巴黎铁塔", getResources().getDrawable(R.drawable.ic_80)));
        list.add(new NumberImage("90 酒瓶", getResources().getDrawable(R.drawable.ic_90)));
        list.add(new NumberImage("01 小树", getResources().getDrawable(R.drawable.ic_01)));
        list.add(new NumberImage("11 筷子", getResources().getDrawable(R.drawable.ic_11)));
        list.add(new NumberImage("21 鳄鱼", getResources().getDrawable(R.drawable.ic_21)));
        list.add(new NumberImage("31 鲨鱼", getResources().getDrawable(R.drawable.ic_31)));
        list.add(new NumberImage("41 司仪 蜥蜴 四姨", getResources().getDrawable(R.drawable.ic_41)));
        list.add(new NumberImage("51 工人 武艺", getResources().getDrawable(R.drawable.ic_51)));
        list.add(new NumberImage("61 儿童", getResources().getDrawable(R.drawable.ic_61)));
        list.add(new NumberImage("71 鸡翼 机翼", getResources().getDrawable(R.drawable.ic_71)));
        list.add(new NumberImage("81 白蚁", getResources().getDrawable(R.drawable.ic_81)));
        list.add(new NumberImage("91 球衣", getResources().getDrawable(R.drawable.ic_91)));
        list.add(new NumberImage("02 铃儿", getResources().getDrawable(R.drawable.ic_02)));
        list.add(new NumberImage("12 椅儿", getResources().getDrawable(R.drawable.ic_12)));
        list.add(new NumberImage("22 双胞胎", getResources().getDrawable(R.drawable.ic_22)));
        list.add(new NumberImage("32 扇儿", getResources().getDrawable(R.drawable.ic_32)));
        list.add(new NumberImage("42 柿儿", getResources().getDrawable(R.drawable.ic_42)));
        list.add(new NumberImage("52 鼓儿", getResources().getDrawable(R.drawable.ic_52)));
        list.add(new NumberImage("62 牛儿", getResources().getDrawable(R.drawable.ic_62)));
        list.add(new NumberImage("72 企鹅", getResources().getDrawable(R.drawable.ic_72)));
        list.add(new NumberImage("82 粑儿", getResources().getDrawable(R.drawable.ic_82)));
        list.add(new NumberImage("92 球儿", getResources().getDrawable(R.drawable.ic_92)));
        list.add(new NumberImage("03 三角板", getResources().getDrawable(R.drawable.ic_03)));
        list.add(new NumberImage("13 医生", getResources().getDrawable(R.drawable.ic_13)));
        list.add(new NumberImage("23 和尚(和尚头上2行3列戒疤)", getResources().getDrawable(R.drawable.ic_23)));
        list.add(new NumberImage("33 星星", getResources().getDrawable(R.drawable.ic_33)));
        list.add(new NumberImage("43 石山", getResources().getDrawable(R.drawable.ic_43)));
        list.add(new NumberImage("53 乌纱帽", getResources().getDrawable(R.drawable.ic_53)));
        list.add(new NumberImage("63 流沙 硫酸", getResources().getDrawable(R.drawable.ic_63)));
        list.add(new NumberImage("73 花旗参", getResources().getDrawable(R.drawable.ic_73)));
        list.add(new NumberImage("83 爬山 芭蕉扇", getResources().getDrawable(R.drawable.ic_83)));
        list.add(new NumberImage("93 旧伞", getResources().getDrawable(R.drawable.ic_93)));
        list.add(new NumberImage("04 小汽车", getResources().getDrawable(R.drawable.ic_04)));
        list.add(new NumberImage("14 钥匙", getResources().getDrawable(R.drawable.ic_14)));
        list.add(new NumberImage("24 闹钟(一天24小时）", getResources().getDrawable(R.drawable.ic_24)));
        list.add(new NumberImage("34 沙子 三丝 三思", getResources().getDrawable(R.drawable.ic_34)));
        list.add(new NumberImage("44 蛇", getResources().getDrawable(R.drawable.ic_44)));
        list.add(new NumberImage("54 巫师", getResources().getDrawable(R.drawable.ic_54)));
        list.add(new NumberImage("64 螺丝", getResources().getDrawable(R.drawable.ic_64)));
        list.add(new NumberImage("74 骑士", getResources().getDrawable(R.drawable.ic_74)));
        list.add(new NumberImage("84 巴士", getResources().getDrawable(R.drawable.ic_84)));
        list.add(new NumberImage("94 救世主", getResources().getDrawable(R.drawable.ic_94)));
        list.add(new NumberImage("05 手套(手有5个手指头）", getResources().getDrawable(R.drawable.ic_05)));
        list.add(new NumberImage("15 鹦鹉", getResources().getDrawable(R.drawable.ic_15)));
        list.add(new NumberImage("25 二胡", getResources().getDrawable(R.drawable.ic_25)));
        list.add(new NumberImage("35 山虎", getResources().getDrawable(R.drawable.ic_35)));
        list.add(new NumberImage("45 师傅", getResources().getDrawable(R.drawable.ic_45)));
        list.add(new NumberImage("55 火车", getResources().getDrawable(R.drawable.ic_55)));
        list.add(new NumberImage("65 尿壶", getResources().getDrawable(R.drawable.ic_65)));
        list.add(new NumberImage("75 西服", getResources().getDrawable(R.drawable.ic_75)));
        list.add(new NumberImage("85 宝物", getResources().getDrawable(R.drawable.ic_85)));
        list.add(new NumberImage("95 酒壶", getResources().getDrawable(R.drawable.ic_95)));
        list.add(new NumberImage("06 手枪(手枪里有6发子弹)", getResources().getDrawable(R.drawable.ic_06)));
        list.add(new NumberImage("16 石榴", getResources().getDrawable(R.drawable.ic_16)));
        list.add(new NumberImage("26 河流(水管）二流子", getResources().getDrawable(R.drawable.ic_26)));
        list.add(new NumberImage("36 山路", getResources().getDrawable(R.drawable.ic_36)));
        list.add(new NumberImage("46 饲料", getResources().getDrawable(R.drawable.ic_46)));
        list.add(new NumberImage("56 蜗牛", getResources().getDrawable(R.drawable.ic_56)));
        list.add(new NumberImage("66 蝌蚪", getResources().getDrawable(R.drawable.ic_66)));
        list.add(new NumberImage("76 汽油", getResources().getDrawable(R.drawable.ic_76)));
        list.add(new NumberImage("86 白鹭 爬楼 八路", getResources().getDrawable(R.drawable.ic_86)));
        list.add(new NumberImage("96 酒楼 旧炉", getResources().getDrawable(R.drawable.ic_96)));
        list.add(new NumberImage("07 锄头", getResources().getDrawable(R.drawable.ic_07)));
        list.add(new NumberImage("17 玉器(切割机）", getResources().getDrawable(R.drawable.ic_17)));
        list.add(new NumberImage("27 耳机", getResources().getDrawable(R.drawable.ic_27)));
        list.add(new NumberImage("37 山鸡", getResources().getDrawable(R.drawable.ic_37)));
        list.add(new NumberImage("47 司机", getResources().getDrawable(R.drawable.ic_47)));
        list.add(new NumberImage("57 武器", getResources().getDrawable(R.drawable.ic_57)));
        list.add(new NumberImage("67 油漆", getResources().getDrawable(R.drawable.ic_67)));
        list.add(new NumberImage("77 机器人", getResources().getDrawable(R.drawable.ic_77)));
        list.add(new NumberImage("87 白旗 白起 白棋", getResources().getDrawable(R.drawable.ic_87)));
        list.add(new NumberImage("97  酒气 旧旗", getResources().getDrawable(R.drawable.ic_97)));
        list.add(new NumberImage("08 溜冰鞋(溜冰鞋有8个轮子）", getResources().getDrawable(R.drawable.ic_08)));
        list.add(new NumberImage("18 腰包", getResources().getDrawable(R.drawable.ic_18)));
        list.add(new NumberImage("28 恶霸", getResources().getDrawable(R.drawable.ic_28)));
        list.add(new NumberImage("38 妇女", getResources().getDrawable(R.drawable.ic_38)));
        list.add(new NumberImage("48 石板", getResources().getDrawable(R.drawable.ic_48)));
        list.add(new NumberImage("58 尾巴", getResources().getDrawable(R.drawable.ic_58)));
        list.add(new NumberImage("68 喇叭", getResources().getDrawable(R.drawable.ic_68)));
        list.add(new NumberImage("78 青蛙", getResources().getDrawable(R.drawable.ic_78)));
        list.add(new NumberImage("88 爸爸 麻花", getResources().getDrawable(R.drawable.ic_88)));
        list.add(new NumberImage("98 酒吧 球拍", getResources().getDrawable(R.drawable.ic_98)));
        list.add(new NumberImage("09 猫(猫有9条命）", getResources().getDrawable(R.drawable.ic_09)));
        list.add(new NumberImage("19 衣钩", getResources().getDrawable(R.drawable.ic_19)));
        list.add(new NumberImage("29 饿囚", getResources().getDrawable(R.drawable.ic_29)));
        list.add(new NumberImage("39 胃泰", getResources().getDrawable(R.drawable.ic_39)));
        list.add(new NumberImage("49 湿狗", getResources().getDrawable(R.drawable.ic_49)));
        list.add(new NumberImage("59 兀鹫", getResources().getDrawable(R.drawable.ic_59)));
        list.add(new NumberImage("69 料酒", getResources().getDrawable(R.drawable.ic_69)));
        list.add(new NumberImage("79 气球", getResources().getDrawable(R.drawable.ic_79)));
        list.add(new NumberImage("89 芭蕉", getResources().getDrawable(R.drawable.ic_89)));
        list.add(new NumberImage("99 双钩", getResources().getDrawable(R.drawable.ic_99)));
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