package com.furniture;

import com.furniture.bean.UserInfo;
import com.furniture.bean.json.AllState;
import com.furniture.function.ActionMap;
import com.furniture.function.ConfigRoomMap;
import com.furniture.function.RoomActionMap;
import com.furniture.function.UserMap;

import lbx.xtoollib.phone.xLogUtil;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2018/8/29.
 */

public class Config {


    public static UserMap DEFAULT_USER_INFO_MAP;

    public static String IP = "";
    public static int PORT = 9696;
    public static UserInfo loginInfo;
    public static ActionMap actionMap = new ActionMap();
    /**
     * 配置页面在这里取值Action
     */
    public static RoomActionMap roomActionMap = new RoomActionMap();
    public static String UUID = "b21adaaa81582330e15a71db5ea59770";
//    public static final boolean DEBUG = XTools.ApkUtil().isApkInDebug(XTools.getApplicationContext());
    public static final boolean DEBUG = true;

    public static ConfigRoomMap roomTopAllAction = new ConfigRoomMap();

    /**
     * 查询全部数据的解析类
     */
    public static AllState AllState;

    public static void init() {
        init(false);
    }

    /**
     * @param initUsers 重新初始化人员
     */
    public static synchronized void init(boolean initUsers) {
        UserMap map = UserMap.getFromLocal();
        if (map == null || map.isEmpty() || initUsers) {
            UserInfo userA = new UserInfo("主人", "admin", "admin", "管理员", R.drawable.pho_wode_touxiang);
            //设置为有管理权限
            userA.setManage(true);
            UserInfo user1 = new UserInfo("成员一", "001", "001", "普通", R.drawable.ph_chengyuan1);
            UserInfo user2 = new UserInfo("成员二", "002", "002", "普通", R.drawable.pho_chengyuan2);
            UserInfo user3 = new UserInfo("成员三", "003", "003", "普通", R.drawable.pho_chengyuan3);
            UserInfo user4 = new UserInfo("成员四", "004", "004", "普通", R.drawable.pho_chengyuan4);
            UserInfo user5 = new UserInfo("成员五", "005", "005", "普通", R.drawable.pho_chengyuan5);
            map = new UserMap();
            DEFAULT_USER_INFO_MAP = map.put(userA, user1, user2, user3, user4, user5).save();
            xLogUtil.e("b初始化新用户" + DEFAULT_USER_INFO_MAP.size());
        } else {
            DEFAULT_USER_INFO_MAP = map;
            xLogUtil.e("本地获取用户" + DEFAULT_USER_INFO_MAP.size());
        }
        // TYPE_NORMAL  TYPE_ONE  TYPE_DEMO_SIMPLE  TYPE_DEMO_JINAN
        APP_TYPE = TYPE_DEMO_SHANGHAI;
        roomTopAllAction.init(APP_TYPE);
    }

    //普通
    public static final int TYPE_NORMAL = 0x010;
    //中粮
    public static final int TYPE_ONE = 0x011;
    //只有一个页面的
    public static final int TYPE_DEMO_SIMPLE = 0x012;
    //济南  需要把 fragment_room 中的ToolBar高度改成济南的
    public static final int TYPE_DEMO_JINAN = 0x013;
    //上海  需要把 fragment_room 中的ToolBar高度改成济南的  济南和上海的一样，只是回家离家按钮的指令不一样
    public static final int TYPE_DEMO_SHANGHAI = 0x014;
    public static int APP_TYPE = TYPE_DEMO_SIMPLE;

    public static int SCREEN_LANDSCAPE = 0;
    public static int SCREEN_PORTRAIT = 1;
    public static int SCREEN_ORIENTATION = SCREEN_PORTRAIT;
}
