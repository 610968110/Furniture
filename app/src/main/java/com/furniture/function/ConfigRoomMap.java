package com.furniture.function;

import com.furniture.bean.ActionBean;
import com.furniture.bean.action1.EatAction;
import com.furniture.bean.action1.GoHomeAction;
import com.furniture.bean.action1.MeetingGuestsAction;
import com.furniture.bean.action1.MovieAction;
import com.furniture.bean.action1.OutHomeAction;
import com.furniture.bean.action1.ReadAction;
import com.furniture.bean.action1.RomanticAction;
import com.furniture.bean.action1.SleepAction;
import com.furniture.ui.fragment.room.BookRoomFragment;
import com.furniture.ui.fragment.room.DinnerRoomFragment;
import com.furniture.ui.fragment.room.KeTingRoomFragment;
import com.furniture.ui.fragment.room.MasterRoomFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.furniture.Config.TYPE_NORMAL;
import static com.furniture.Config.TYPE_ONE;

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
 * @date 2018/11/1.
 */

public class ConfigRoomMap {

    private static Map<String, ArrayList<ActionBean>> mMap = new HashMap<>();

    public void init(int appType) {
        mMap.clear();
        ArrayList<ActionBean> room1 = new ArrayList<>();
        ArrayList<ActionBean> room2 = new ArrayList<>();
        ArrayList<ActionBean> room3 = new ArrayList<>();
        ArrayList<ActionBean> room4 = new ArrayList<>();
        switch (appType) {
            case TYPE_NORMAL:
            case TYPE_ONE:
                room1.add(new MeetingGuestsAction(null, null, null));
                room1.add(new EatAction(null, null, null));
                room1.add(new ReadAction(null, null, null));
                room1.add(new MovieAction(null, null, null));
                room1.add(new GoHomeAction(null, null, null));
                room1.add(new OutHomeAction(null, null, null));
                mMap.put(KeTingRoomFragment.ROOM, room1);

                room2.add(new SleepAction(null, null, null));
                room2.add(new RomanticAction(null, null, null));
                room2.add(new ReadAction(null, null, null));
                room2.add(new MovieAction(null, null, null));
                room2.add(new GoHomeAction(null, null, null));
                room2.add(new OutHomeAction(null, null, null));
                mMap.put(DinnerRoomFragment.ROOM, room2);

                room3.add(new SleepAction(null, null, null));
                room3.add(new RomanticAction(null, null, null));
                room3.add(new ReadAction(null, null, null));
                room3.add(new MovieAction(null, null, null));
                room3.add(new GoHomeAction(null, null, null));
                room3.add(new OutHomeAction(null, null, null));
                mMap.put(MasterRoomFragment.ROOM, room3);

                room4.add(new SleepAction(null, null, null));
                room4.add(new RomanticAction(null, null, null));
                room4.add(new ReadAction(null, null, null));
                room4.add(new MovieAction(null, null, null));
                room4.add(new GoHomeAction(null, null, null));
                room4.add(new OutHomeAction(null, null, null));
                mMap.put(BookRoomFragment.ROOM, room4);
                break;
//            case TYPE_ONE:
//                break;
            default:
                break;
        }
    }

    public int size() {
        return mMap.keySet().size();
    }

    public ArrayList<ActionBean> get(String room) {
        return mMap.get(room);
    }
}
