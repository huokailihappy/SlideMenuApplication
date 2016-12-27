package com.git.hkl.slidemenuapplication.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.git.hkl.slidemenuapplication.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目名称：SlideMenuApplication
 * 类描述：  字母排序列表
 * 创建人：霍凯丽
 * 创建时间：2016/11/30 10:14
 */
public class LetterView extends LinearLayout {
    private Context mContext;
    private CharacterClickListener mListener;
    private List<String> name = new ArrayList<>();

    public LetterView(Context context) {
        super(context);
        mContext = context;
        setOrientation(VERTICAL);
        initView(name);
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(VERTICAL);
        initView(name);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOrientation(VERTICAL);
        initView(name);
    }

    private void initView(List<String> name) {
        removeAllViews();
        for (int i = 0; i < name.size(); i++) {
            String character = name.get(i);
            TextView tv = buildTextLayout(character);
            addView(tv);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private TextView buildTextLayout(final String character) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);

        TextView tv = new TextView(mContext);
        tv.setLayoutParams(layoutParams);
        tv.setGravity(Gravity.CENTER);
        tv.setClickable(true);

        tv.setText(character);
        tv.setTextColor(mContext.getResources().getColor(R.color.white));
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.clickCharacter(character);
                }
            }
        });
        return tv;
    }

    private ImageView buildImageLayout() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);

        ImageView iv = new ImageView(mContext);
        iv.setLayoutParams(layoutParams);

        iv.setBackgroundResource(R.mipmap.delete);

        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // mListener.clickArrow();
                }
            }
        });
        return iv;
    }

    public void setCharacterListener(CharacterClickListener listener) {
        mListener = listener;
    }

    public int getAllDataSize() {
        return name.size();
    }

    public String getData(int i) {
        return name.get(i);
    }

    public void setName(List<String> name) {
        this.name = name;
        initView(name);
    }

    public interface CharacterClickListener {
        void clickCharacter(String character);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float thisHeight = this.getHeight();
        float nowHeight;
        float distance;
        int location;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                nowHeight = event.getY();
                distance = thisHeight / this.getAllDataSize();
                location = (int) (nowHeight / distance);
                if (mListener != null) {
                    mListener.clickCharacter(getData(location));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                distance = thisHeight / this.getAllDataSize();
                nowHeight = event.getY();
                location = (int) (nowHeight / distance);
                if (mListener != null) {
                    mListener.clickCharacter(getData(location));
                }
                break;
        }
        return true;
    }


}