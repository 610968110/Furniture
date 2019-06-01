package com.furniture.adapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.furniture.R;
import com.furniture.bean.ActionBean;
import com.furniture.bean.EditActionNameBean;
import com.furniture.bean.action1.GoHomeAction;
import com.furniture.bean.action1.OutHomeAction;
import com.furniture.databinding.ItemRoomConfigBinding;
import com.furniture.ui.view.MyEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import lbx.xtoollib.XTools;
import lbx.xtoollib.base.BaseDataAdapter;

import static com.furniture.constant.DoMain.GO_HOME_SAVE;
import static com.furniture.constant.DoMain.OUT_HOME_SAVE;

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
 * @date 2018/9/27.
 */

public class RoomConfigItemAdapter extends BaseDataAdapter<ActionBean, ItemRoomConfigBinding, BaseDataAdapter.BaseHolder> {

    private int editPos = -1;
    private Context mContext;
    private ActionBean mEditingBean;
    private MyEditText mEditingText;
    private final String ROOM;

    public RoomConfigItemAdapter(Context context, String room, List<ActionBean> list) {
        super(context, list);
        mContext = context;
        ROOM = room;
    }

    @Override
    public BaseHolder getHolder(View view, ViewDataBinding binding) {
        return new BaseHolder(view, binding);
    }

    @Override
    public int itemLayout() {
        return R.layout.item_room_config;
    }

    @Override
    public void dataBinding(ItemRoomConfigBinding binding, int position, ActionBean entity, BaseHolder baseHolder) {
        binding.setBean(entity);
        View root = binding.getRoot();
        MyEditText editText = root.findViewById(R.id.et_name);
        if (position == editPos) {
            editText.edit(true);
            mEditingBean = entity;
            mEditingText = editText;
        } else {
            editText.edit(false);
        }
    }

    @Override
    public boolean itemClickEnable() {
        return true;
    }

    @Override
    public boolean itemLongClickEnable() {
        return true;
    }

    public boolean isEditing() {
        return editPos != -1;
    }

    public void saveName() {
        if (editPos != -1 && mEditingBean != null && mEditingText != null) {
            editPos = -1;
            mEditingBean.setOtherName(mEditingText.getText().toString());
            if (mContext instanceof Activity) {
                XTools.SoftInputUtil().hintSoftInput((Activity) mContext);
            }
            notifyDataSetChanged();
            if (mEditingBean instanceof GoHomeAction) {
                XTools.SpUtil().putString(GO_HOME_SAVE, mEditingBean.getOtherName());
            } else if (mEditingBean instanceof OutHomeAction) {
                XTools.SpUtil().putString(OUT_HOME_SAVE, mEditingBean.getOtherName());
            }
            EventBus.getDefault().post(new EditActionNameBean(ROOM, mEditingBean));
        }
    }

    public void edit(int position) {
        editPos = position;
        notifyDataSetChanged();
    }
}
