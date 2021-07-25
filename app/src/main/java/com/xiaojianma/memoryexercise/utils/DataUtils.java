package com.xiaojianma.memoryexercise.utils;

import android.content.res.Resources;

import com.xiaojianma.memoryexercise.NumberImage;
import com.xiaojianma.memoryexercise.R;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    private static List<NumberImage> numberList = new ArrayList<>();

    public static synchronized List<NumberImage> getNumberData(Resources res) {
        if (!numberList.isEmpty()) {
            return numberList;
        }
        return initNumberData(res);
    }

    private static List<NumberImage> initNumberData(Resources res) {
        numberList.add(new NumberImage("单0 呼啦圈", res.getDrawable(R.drawable.ic_0)));
        numberList.add(new NumberImage("单1 蜡烛", res.getDrawable(R.drawable.ic_1)));
        numberList.add(new NumberImage("单2 鹅", res.getDrawable(R.drawable.ic_2)));
        numberList.add(new NumberImage("单3 耳朵", res.getDrawable(R.drawable.ic_3)));
        numberList.add(new NumberImage("单4 帆船", res.getDrawable(R.drawable.ic_4)));
        numberList.add(new NumberImage("单5 秤钩", res.getDrawable(R.drawable.ic_5)));
        numberList.add(new NumberImage("单6 勺子", res.getDrawable(R.drawable.ic_6)));
        numberList.add(new NumberImage("单7 镰刀", res.getDrawable(R.drawable.ic_7)));
        numberList.add(new NumberImage("单8 眼镜", res.getDrawable(R.drawable.ic_8)));
        numberList.add(new NumberImage("单9 口哨", res.getDrawable(R.drawable.ic_9)));
        numberList.add(new NumberImage("00 望远镜", res.getDrawable(R.drawable.ic_00)));
        numberList.add(new NumberImage("10 棒球", res.getDrawable(R.drawable.ic_10)));
        numberList.add(new NumberImage("20 自行车", res.getDrawable(R.drawable.ic_20)));
        numberList.add(new NumberImage("30 三轮车", res.getDrawable(R.drawable.ic_30)));
        numberList.add(new NumberImage("40 司令", res.getDrawable(R.drawable.ic_40)));
        numberList.add(new NumberImage("50 武林盟主", res.getDrawable(R.drawable.ic_50)));
        numberList.add(new NumberImage("60 榴莲", res.getDrawable(R.drawable.ic_60)));
        numberList.add(new NumberImage("70 冰淇淋", res.getDrawable(R.drawable.ic_70)));
        numberList.add(new NumberImage("80 巴黎铁塔", res.getDrawable(R.drawable.ic_80)));
        numberList.add(new NumberImage("90 酒瓶", res.getDrawable(R.drawable.ic_90)));
        numberList.add(new NumberImage("01 小树", res.getDrawable(R.drawable.ic_01)));
        numberList.add(new NumberImage("11 筷子", res.getDrawable(R.drawable.ic_11)));
        numberList.add(new NumberImage("21 鳄鱼", res.getDrawable(R.drawable.ic_21)));
        numberList.add(new NumberImage("31 鲨鱼", res.getDrawable(R.drawable.ic_31)));
        numberList.add(new NumberImage("41 司仪 蜥蜴 四姨", res.getDrawable(R.drawable.ic_41)));
        numberList.add(new NumberImage("51 工人 武艺", res.getDrawable(R.drawable.ic_51)));
        numberList.add(new NumberImage("61 儿童", res.getDrawable(R.drawable.ic_61)));
        numberList.add(new NumberImage("71 鸡翼 机翼", res.getDrawable(R.drawable.ic_71)));
        numberList.add(new NumberImage("81 白蚁", res.getDrawable(R.drawable.ic_81)));
        numberList.add(new NumberImage("91 球衣", res.getDrawable(R.drawable.ic_91)));
        numberList.add(new NumberImage("02 铃儿", res.getDrawable(R.drawable.ic_02)));
        numberList.add(new NumberImage("12 椅儿", res.getDrawable(R.drawable.ic_12)));
        numberList.add(new NumberImage("22 双胞胎", res.getDrawable(R.drawable.ic_22)));
        numberList.add(new NumberImage("32 扇儿", res.getDrawable(R.drawable.ic_32)));
        numberList.add(new NumberImage("42 柿儿", res.getDrawable(R.drawable.ic_42)));
        numberList.add(new NumberImage("52 鼓儿", res.getDrawable(R.drawable.ic_52)));
        numberList.add(new NumberImage("62 牛儿", res.getDrawable(R.drawable.ic_62)));
        numberList.add(new NumberImage("72 企鹅", res.getDrawable(R.drawable.ic_72)));
        numberList.add(new NumberImage("82 粑儿", res.getDrawable(R.drawable.ic_82)));
        numberList.add(new NumberImage("92 球儿", res.getDrawable(R.drawable.ic_92)));
        numberList.add(new NumberImage("03 三角板", res.getDrawable(R.drawable.ic_03)));
        numberList.add(new NumberImage("13 医生", res.getDrawable(R.drawable.ic_13)));
        numberList.add(new NumberImage("23 和尚(和尚头上2行3列戒疤)", res.getDrawable(R.drawable.ic_23)));
        numberList.add(new NumberImage("33 星星", res.getDrawable(R.drawable.ic_33)));
        numberList.add(new NumberImage("43 石山", res.getDrawable(R.drawable.ic_43)));
        numberList.add(new NumberImage("53 乌纱帽", res.getDrawable(R.drawable.ic_53)));
        numberList.add(new NumberImage("63 流沙 硫酸", res.getDrawable(R.drawable.ic_63)));
        numberList.add(new NumberImage("73 花旗参", res.getDrawable(R.drawable.ic_73)));
        numberList.add(new NumberImage("83 爬山 芭蕉扇", res.getDrawable(R.drawable.ic_83)));
        numberList.add(new NumberImage("93 旧伞", res.getDrawable(R.drawable.ic_93)));
        numberList.add(new NumberImage("04 小汽车", res.getDrawable(R.drawable.ic_04)));
        numberList.add(new NumberImage("14 钥匙", res.getDrawable(R.drawable.ic_14)));
        numberList.add(new NumberImage("24 闹钟(一天24小时）", res.getDrawable(R.drawable.ic_24)));
        numberList.add(new NumberImage("34 沙子 三丝 三思", res.getDrawable(R.drawable.ic_34)));
        numberList.add(new NumberImage("44 蛇", res.getDrawable(R.drawable.ic_44)));
        numberList.add(new NumberImage("54 巫师", res.getDrawable(R.drawable.ic_54)));
        numberList.add(new NumberImage("64 螺丝", res.getDrawable(R.drawable.ic_64)));
        numberList.add(new NumberImage("74 骑士", res.getDrawable(R.drawable.ic_74)));
        numberList.add(new NumberImage("84 巴士", res.getDrawable(R.drawable.ic_84)));
        numberList.add(new NumberImage("94 救世主", res.getDrawable(R.drawable.ic_94)));
        numberList.add(new NumberImage("05 手套(手有5个手指头）", res.getDrawable(R.drawable.ic_05)));
        numberList.add(new NumberImage("15 鹦鹉", res.getDrawable(R.drawable.ic_15)));
        numberList.add(new NumberImage("25 二胡", res.getDrawable(R.drawable.ic_25)));
        numberList.add(new NumberImage("35 山虎", res.getDrawable(R.drawable.ic_35)));
        numberList.add(new NumberImage("45 师傅", res.getDrawable(R.drawable.ic_45)));
        numberList.add(new NumberImage("55 火车", res.getDrawable(R.drawable.ic_55)));
        numberList.add(new NumberImage("65 尿壶", res.getDrawable(R.drawable.ic_65)));
        numberList.add(new NumberImage("75 西服", res.getDrawable(R.drawable.ic_75)));
        numberList.add(new NumberImage("85 宝物", res.getDrawable(R.drawable.ic_85)));
        numberList.add(new NumberImage("95 酒壶", res.getDrawable(R.drawable.ic_95)));
        numberList.add(new NumberImage("06 手枪(手枪里有6发子弹)", res.getDrawable(R.drawable.ic_06)));
        numberList.add(new NumberImage("16 石榴", res.getDrawable(R.drawable.ic_16)));
        numberList.add(new NumberImage("26 河流(水管）二流子", res.getDrawable(R.drawable.ic_26)));
        numberList.add(new NumberImage("36 山路", res.getDrawable(R.drawable.ic_36)));
        numberList.add(new NumberImage("46 饲料", res.getDrawable(R.drawable.ic_46)));
        numberList.add(new NumberImage("56 蜗牛", res.getDrawable(R.drawable.ic_56)));
        numberList.add(new NumberImage("66 蝌蚪", res.getDrawable(R.drawable.ic_66)));
        numberList.add(new NumberImage("76 汽油", res.getDrawable(R.drawable.ic_76)));
        numberList.add(new NumberImage("86 白鹭 爬楼 八路", res.getDrawable(R.drawable.ic_86)));
        numberList.add(new NumberImage("96 酒楼 旧炉", res.getDrawable(R.drawable.ic_96)));
        numberList.add(new NumberImage("07 锄头", res.getDrawable(R.drawable.ic_07)));
        numberList.add(new NumberImage("17 玉器(切割机）", res.getDrawable(R.drawable.ic_17)));
        numberList.add(new NumberImage("27 耳机", res.getDrawable(R.drawable.ic_27)));
        numberList.add(new NumberImage("37 山鸡", res.getDrawable(R.drawable.ic_37)));
        numberList.add(new NumberImage("47 司机", res.getDrawable(R.drawable.ic_47)));
        numberList.add(new NumberImage("57 武器", res.getDrawable(R.drawable.ic_57)));
        numberList.add(new NumberImage("67 油漆", res.getDrawable(R.drawable.ic_67)));
        numberList.add(new NumberImage("77 机器人", res.getDrawable(R.drawable.ic_77)));
        numberList.add(new NumberImage("87 白旗 白起 白棋", res.getDrawable(R.drawable.ic_87)));
        numberList.add(new NumberImage("97  酒气 旧旗", res.getDrawable(R.drawable.ic_97)));
        numberList.add(new NumberImage("08 溜冰鞋(溜冰鞋有8个轮子）", res.getDrawable(R.drawable.ic_08)));
        numberList.add(new NumberImage("18 腰包", res.getDrawable(R.drawable.ic_18)));
        numberList.add(new NumberImage("28 恶霸", res.getDrawable(R.drawable.ic_28)));
        numberList.add(new NumberImage("38 妇女", res.getDrawable(R.drawable.ic_38)));
        numberList.add(new NumberImage("48 石板", res.getDrawable(R.drawable.ic_48)));
        numberList.add(new NumberImage("58 尾巴", res.getDrawable(R.drawable.ic_58)));
        numberList.add(new NumberImage("68 喇叭", res.getDrawable(R.drawable.ic_68)));
        numberList.add(new NumberImage("78 青蛙", res.getDrawable(R.drawable.ic_78)));
        numberList.add(new NumberImage("88 爸爸 麻花", res.getDrawable(R.drawable.ic_88)));
        numberList.add(new NumberImage("98 酒吧 球拍", res.getDrawable(R.drawable.ic_98)));
        numberList.add(new NumberImage("09 猫(猫有9条命）", res.getDrawable(R.drawable.ic_09)));
        numberList.add(new NumberImage("19 衣钩", res.getDrawable(R.drawable.ic_19)));
        numberList.add(new NumberImage("29 饿囚", res.getDrawable(R.drawable.ic_29)));
        numberList.add(new NumberImage("39 胃泰", res.getDrawable(R.drawable.ic_39)));
        numberList.add(new NumberImage("49 湿狗", res.getDrawable(R.drawable.ic_49)));
        numberList.add(new NumberImage("59 兀鹫", res.getDrawable(R.drawable.ic_59)));
        numberList.add(new NumberImage("69 料酒", res.getDrawable(R.drawable.ic_69)));
        numberList.add(new NumberImage("79 气球", res.getDrawable(R.drawable.ic_79)));
        numberList.add(new NumberImage("89 芭蕉", res.getDrawable(R.drawable.ic_89)));
        numberList.add(new NumberImage("99 双钩", res.getDrawable(R.drawable.ic_99)));
        return numberList;
    }

    private static String[] letterData = {"A a——苹果；海军帽",
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

    public static synchronized String[] getLetterData() {
        return letterData;
    }
}
