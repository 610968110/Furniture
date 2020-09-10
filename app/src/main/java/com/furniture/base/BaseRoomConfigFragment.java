package com.furniture.base;

import android.support.annotation.CallSuper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.furniture.Config;
import com.furniture.R;
import com.furniture.adapter.RoomConfigItemAdapter;
import com.furniture.bean.ActionBean;
import com.furniture.bean.EditActionNameBean;
import com.furniture.bean.action1.GoHomeAction;
import com.furniture.bean.action1.OutHomeAction;
import com.furniture.bean.json.AllState;
import com.furniture.constant.Device;
import com.furniture.impl.IModeAction;
import com.furniture.ui.activity.RoomConfigActivity;
import com.furniture.ui.view.SelectTitleView;
import com.furniture.utils.GsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;
import lbx.xtoollib.phone.xLogUtil;

import static com.furniture.constant.Device.SCE;

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
 * @date 2018/9/25.
 */

public abstract class BaseRoomConfigFragment extends BaseFragment {

    @BindView(R.id.stv_top)
    SelectTitleView mTopTitleView;
    @BindView(R.id.stv_bottom)
    SelectTitleView mBottomTitleView;
    @BindView(R.id.rv_top)
    RecyclerView mTopView;
    @BindView(R.id.rv_bottom)
    RecyclerView mBottomView;
    private int mTopSelectItemPos = -1;

    private RoomConfigItemAdapter mTopAdapter;
    private RoomConfigItemAdapter mBottomAdapter;
    private List<ActionBean> mTopList;
    private List<ActionBean> mBottomList;
    private AllState mAllState = new AllState();
    private String mDevice = "";
    private boolean change;
    private ActionBean mTopSelect;
    private boolean isHome;
    private static Map<String, Integer> mMap = new HashMap<>();

    @Override
    public int getLayoutID() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return R.layout.fragment_room_config;
    }

    @CallSuper
    @Override
    public void initView(View view) {
        mTopTitleView.setSelectTitleText("场景");
        mBottomTitleView.setSelectTitleText("设备");
        List<ActionBean> centerList = Config.roomActionMap.getCenterList(room());
        String[] selects = new String[centerList.size()];
        for (int i = 0; i < centerList.size(); i++) {
            selects[i] = centerList.get(i).getName() + "模式";
        }
        mTopTitleView.setSelectText(selects);
        mBottomTitleView.setSelectText(selectBottomString());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        int count = 9;
        if (Config.SCREEN_ORIENTATION == Config.SCREEN_PORTRAIT) {
            count = (XTools.WindowUtil().getScreenWidth() - 100) / XTools.ResUtil().getDimen(R.dimen.config_item_size);
        }
        mTopView.setLayoutManager(new GridLayoutManager(getContext(), count));
        mBottomView.setLayoutManager(new GridLayoutManager(getContext(), count));
    }

    @CallSuper
    @Override
    public void initData() {
        mTopList = new ArrayList<>();
        mBottomList = new ArrayList<>();
        mTopAdapter = new RoomConfigItemAdapter(getContext(), room(), mTopList);
        mBottomAdapter = new RoomConfigItemAdapter(getContext(), room(), mBottomList);
        mTopView.setAdapter(mTopAdapter);
        mBottomView.setAdapter(mBottomAdapter);
        xLogUtil.e("Config.roomTopAllAction:" + Config.roomTopAllAction.size());
        mTopList.addAll(Config.roomTopAllAction.get(room()));
        mBottomList.addAll(Config.roomActionMap.getAllList(room()));
        for (int i = 0; i < mTopList.size(); i++) {
            mTopList.get(i).setSelect(false);
        }
        if (Config.AllState != null) {
            mAllState = Config.AllState;
        }
        addData();
        selectTopItem(0, mTopList.get(0));
    }

    private void addData() {
        synchronized (BaseRoomConfigFragment.class) {
            String json = GsonUtil.getInstance().toJson(mAllState);
            if (mMap.isEmpty()) {
                try {
                    JSONObject object = new JSONObject(json);
                    JSONObject params = object.getJSONObject("params");
                    JSONArray devices = params.getJSONArray("devices");
                    for (int i = 0; i < devices.length(); i++) {
                        JSONObject jsonObject = devices.getJSONObject(i);
                        String devid = jsonObject.optString("devid");
                        JSONObject fields = jsonObject.getJSONObject("field");
                        if (devid.contains(Device.HOME)) {
                            Iterator<String> keys = fields.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                if (next.contains("Mode")) {
                                    mMap.put(devid + next, fields.optInt(next, 0));
                                }
                            }
                        } else if (devid.contains(SCE)) {
                            Iterator<String> keys = fields.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                if (!next.contains("Mode")) {
                                    mMap.put(devid + next, fields.optInt(next, 0));
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @CallSuper
    @Override
    public void initListener() {
        mTopTitleView.setOnSelectItem(0, mOnSelectItem);
        mBottomTitleView.setOnSelectItem(1, mOnSelectItem);
        mTopView.setOnClickListener(v -> saveTopName());
        mTopAdapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<ActionBean>() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {
                if (mTopAdapter.isEditing()) {
                    //如果正在改名 保存名字
                    mTopAdapter.saveName();
                    return;
                }
                if (position == mTopSelectItemPos) {
                    return;
                }
                if (change) {
                    XTools.UiUtil().getSystemDialog(getActivity(), "保存", "是否要保存当前状态？",
                            (dialog, which) -> {
                                xLogUtil.e(this, "dialog保存");
                                save();
                            },
                            (dialog, which) -> {
                                change = false;
                                selectTopItem(position, entity);
                            }).show();
                } else {
                    selectTopItem(position, entity);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {
                xLogUtil.e(this, "onItemLongClick edit");
                mTopAdapter.edit(position);
            }
        });
        mBottomAdapter.setOnItemClickListener(new BaseDataAdapter.OnItemClickListener<ActionBean>() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {
                if (mBottomAdapter.isEditing()) {
                    //如果正在改名 保存名字
                    mBottomAdapter.saveName();
                    return;
                }
                change = true;
                entity.setSelect(!entity.isSelect());
                mBottomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(RecyclerView recyclerView, int id, int position, ActionBean entity) {
                mBottomAdapter.edit(position);
            }
        });
    }

    private void selectTopItem(int position, ActionBean entity) {
        mTopSelect = entity;
        mTopSelectItemPos = position;
        mTopTitleView.setSelectText(entity.getName() + "模式");
        if (entity instanceof IModeAction) {

            isHome = ((IModeAction) entity).isHome();
        }
        //选中上边的item
        for (int i = 0; i < mTopList.size(); i++) {
            ActionBean bean = mTopList.get(i);
            bean.setSelect(i == position);
        }
        //如果是各种模式
        if (entity instanceof IModeAction) {
            mDevice = ((IModeAction) entity).getModeDeviceName();
        } else {
            mDevice = "";
        }
        refreshBottomItemSelect(mDevice);
        mTopAdapter.notifyDataSetChanged();
        mBottomAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void changeHomeName(EditActionNameBean bean) {
        ActionBean bean1 = bean.getBean();
        if (bean1 instanceof GoHomeAction) {
            for (ActionBean b : mTopList) {
                if (b instanceof GoHomeAction) {
                    b.setOtherName(bean1.getOtherName());
                }
            }
        } else if (bean1 instanceof OutHomeAction) {
            for (ActionBean b : mTopList) {
                if (b instanceof OutHomeAction) {
                    b.setOtherName(bean1.getOtherName());
                }
            }
        }
        mTopAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        mMap.clear();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    /**
     * 获取下面列表每个item是否是被选择状态
     *
     * @param device 模式名 Dining Viewing等
     */
    private void refreshBottomItemSelect(String device) {
        if (!isHome) {
            for (int i = 0; i < mBottomList.size(); i++) {
                int modeState = 0;
                try {
                    modeState = mMap.get(room() + SCE + device + "[" + (i + 1) + "]");
                } catch (NullPointerException ignored) {
                }
                mBottomList.get(i).setSelect(1 == modeState);
            }
        } else {
            for (int i = 0; i < mBottomList.size(); i++) {
                int modeState = 0;
                try {
                    modeState = mMap.get(room() + device + "[" + (i + 1) + "]");
                } catch (NullPointerException ignored) {
                }
                mBottomList.get(i).setSelect(1 == modeState);
            }
        }
    }

    private SelectTitleView.OnSelectItem mOnSelectItem = new SelectTitleView.OnSelectItem() {
        @Override
        public void onSelect(int tag, int pos, String name) {
            xLogUtil.e(this, "选择:" + tag + "  " + pos + "  " + name);
            onSelectItemClick(tag, pos, name);
        }
    };

    public void save() {
        xLogUtil.e(this, "保存:" + room() + "   change:" + change);
        if (change) {
            change = false;
            FragmentActivity activity = getActivity();
            if (activity instanceof RoomConfigActivity) {
                String s = sendState(room(), mTopSelect, mBottomList);
                if (!TextUtils.isEmpty(s)) {
                    ((RoomConfigActivity) activity).send(s);
                }
            }
        }
    }

    String sendState(String room, ActionBean mTopSelect, List<ActionBean> bottom) {
        if (mTopSelect instanceof IModeAction) {
            String deviceName = ((IModeAction) mTopSelect).getModeDeviceName();
            xLogUtil.e("sendState:" + room + "  " + deviceName);
            //例如Meeting
            return makeJson(deviceName, bottom);
        }
        return "";
    }

    String makeJson(String firstName, List<ActionBean> bottom) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"params\":");
        json.append("{");

        json.append("\"devid\":");
        json.append("\"").append(room()).append(SCE).append("\",");

        json.append("\"procode\":");
        json.append("\"NULL\",");

        json.append("\"field\":");
        json.append("{");

        for (int i = 0; i < bottom.size(); i++) {
            json.append("\"");
            String key = firstName + "[" + i + "]";
            json.append(key);
            json.append("\":");
            int value = bottom.get(i).isSelect() ? 1 : 0;
            json.append(value);
            if (bottom.size() - 1 != i) {
                json.append(",");
            }
        }
        json.append("}");
        json.append("}");

        json.append(",");
        json.append("\"service\":\"deviceControl\",");
        json.append("\"type\":\"SEND\",");
        json.append("\"id\":\"").append(System.currentTimeMillis() / 1000).append("\",");
        json.append("\"uuid\":\"").append(Config.UUID).append("\",");
        json.append("\"timestamp\":").append(System.currentTimeMillis() / 1000);

        json.append("}");
        return json.toString();
    }

    public void saveTopName() {
        xLogUtil.e(this, "saveTopName");
        if (mTopAdapter != null) {
            mTopAdapter.saveName();
        }
    }

    public abstract String room();

    public abstract String[] selectBottomString();

    public abstract void onSelectItemClick(int tag, int pos, String name);
}
