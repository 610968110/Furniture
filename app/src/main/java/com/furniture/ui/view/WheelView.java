package com.furniture.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lbx.xtoollib.phone.xLogUtil;

public class WheelView extends ScrollView {

    private static final String TAG = "hhp";
    private Context context;
    private LinearLayout views;
    List<String> items;
    public static final int OFF_SET_DEFAULT = 1;
    // 偏移量（需要在最前面和最后面补全）
    int offset = OFF_SET_DEFAULT;
    // 每页显示的数量
    int displayItemCount;
    int initialY;
    Runnable scrollerTask;
    int newCheck = 50;
    int selectedIndex = 1;
    int itemHeight = 0;

    public WheelView(Context context) {
        super(context);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private List<String> getItems() {
        return items;
    }

    public void setItems(String... list) {
        if (null == items) {
            items = new ArrayList<>();
        }
        items.clear();
        if (list != null) {
            Collections.addAll(items, list);
        }

        // 前面和后面补全
        for (int i = 0; i < offset; i++) {
            items.add(0, "");
            items.add("");
        }
        initData();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    private void init(Context context) {
        this.context = context;
        xLogUtil.e("init：" + this.context);
        this.setVerticalScrollBarEnabled(false);

        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);

        scrollerTask = () -> {
            int newY = getScrollY();
            if (initialY - newY == 0) { // stopped
                final int remainder = initialY % itemHeight;
                final int divided = initialY / itemHeight;
                if (remainder == 0) {
                    selectedIndex = divided + offset;
                    onSeletedCallBack();
                } else {
                    if (remainder > itemHeight / 2) {
                        WheelView.this.post(() -> {
                            WheelView.this.smoothScrollTo(0, initialY - remainder + itemHeight);
                            selectedIndex = divided + offset + 1;
                            onSeletedCallBack();
                        });
                    } else {
                        WheelView.this.post(() -> {
                            WheelView.this.smoothScrollTo(0, initialY - remainder);
                            selectedIndex = divided + offset;
                            onSeletedCallBack();
                        });
                    }

                }

            } else {
                initialY = getScrollY();
                WheelView.this.postDelayed(scrollerTask, newCheck);
            }
        };

    }


    private void initData() {
        displayItemCount = offset * 2 + 1;
        views.removeAllViews();
        for (String item : items) {
            views.addView(createView(item));
        }
        refreshItemView(0);
    }

    private TextView createView(String item) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setSingleLine(true);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setText(item);
        tv.setGravity(Gravity.CENTER);
        int padding = dip2px(15);
        tv.setPadding(padding, padding, padding, padding);
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv);
            views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * displayItemCount));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
            this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
        }
        return tv;
    }


    /**
     * 更改Item字体颜色
     *
     * @param y
     */
    private void refreshItemView(int y) {
        int position = y / itemHeight + offset;
        int remainder = y % itemHeight;//相对于一个item移动的距离
        int divided = y / itemHeight;

        if (remainder == 0) {
//       移动距离不到1个item
            position = divided + offset;
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }

        int childSize = views.getChildCount();
        for (int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) views.getChildAt(i);
            if (null == itemView) {
                return;
            }
            if (position == i) {
                itemView.setTextColor(Color.parseColor("#0288ce"));//蓝色
            } else {
                itemView.setTextColor(Color.parseColor("#bbbbbb"));//灰色
            }
        }
    }

    /**
     * 获取选中区域的边界
     */
    int[] selectedAreaBorder;

    private int[] obtainSelectedAreaBorder() {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }

    private int scrollDirection = -1;
    private static final int SCROLL_DIRECTION_UP = 0;
    private static final int SCROLL_DIRECTION_DOWN = 1;

    Paint paint;
    int viewWidth;

    @Override
    public void setBackgroundDrawable(Drawable background) {
        if (context == null) {
            return;
        }
        if (viewWidth == 0 ) {
            viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        }

        if (null == paint) {
            paint = new Paint();
            paint.setColor(Color.parseColor("#83cde6"));
            paint.setStrokeWidth(dip2px(1f));
        }

        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                canvas.drawLine(viewWidth * 1 / 6, obtainSelectedAreaBorder()[0], viewWidth * 5 / 6, obtainSelectedAreaBorder()[0], paint);
                canvas.drawLine(viewWidth * 1 / 6, obtainSelectedAreaBorder()[1], viewWidth * 5 / 6, obtainSelectedAreaBorder()[1], paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter cf) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        super.setBackgroundDrawable(background);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        refreshItemView(t);
        if (t > oldt) {
            // Log.d(TAG, "向下滚动");
            scrollDirection = SCROLL_DIRECTION_DOWN;
        } else {
            // Log.d(TAG, "向上滚动");
            scrollDirection = SCROLL_DIRECTION_UP;

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xLogUtil.e("onSizeChanged:");
        viewWidth = w;
        setBackgroundDrawable(null);
    }

    @Override
    public void fling(int velocityY) {
        //加快停下的速度
        super.fling(velocityY / 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            initialY = getScrollY();
            this.postDelayed(scrollerTask, newCheck);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 选中回调
     */
    private void onSeletedCallBack() {
        if (null != onWheelViewListener) {
            onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
        }

    }

    public void setSeletion(int position) {
        final int p = position;
        selectedIndex = p + offset;
        this.post(new Runnable() {
            @Override
            public void run() {
                WheelView.this.smoothScrollTo(0, p * itemHeight);
            }
        });

    }

    public String getSeletedItem() {
        return items.get(selectedIndex);
    }

    public int getSeletedIndex() {
        return selectedIndex - offset;
    }


    private OnWheelViewListener onWheelViewListener;

    public OnWheelViewListener getOnWheelViewListener() {
        return onWheelViewListener;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }

    private int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }

    public static interface OnWheelViewListener {
        public void onSelected(int selectedIndex, String item);
    }
}
