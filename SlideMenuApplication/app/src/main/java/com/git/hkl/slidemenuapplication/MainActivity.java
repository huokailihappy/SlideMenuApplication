package com.git.hkl.slidemenuapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.git.hkl.slidemenuapplication.adapter.Data;
import com.git.hkl.slidemenuapplication.adapter.LetterAdapter;
import com.git.hkl.slidemenuapplication.list.LetterView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里是根据房间号做的对比,如果要做联系人直接传名字这个参数就可以了
 */
public class MainActivity extends AppCompatActivity {

    private XRecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LetterView letterView;
    private LetterAdapter adapter;
    private List<Data> listData = new ArrayList<>();
    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();

    }

    private void initData() {
        listData.add(new Data("杀生丸", "W101"));
        listData.add(new Data("孙悟空", "W201"));
        listData.add(new Data("张三丰", "W301"));
        listData.add(new Data("郭靖", "W111"));
        listData.add(new Data("黄蓉", "W131"));
        listData.add(new Data("黄老邪", "W141"));
        listData.add(new Data("天山童姥", "W111"));
        listData.add(new Data("于万亭", "W231"));
        listData.add(new Data("陈家洛", "W211"));
        listData.add(new Data("陈圆圆", "W401"));
        listData.add(new Data("郭芙", "s101"));
        listData.add(new Data("郭襄", "a101"));
        listData.add(new Data("林平之", "101"));
        listData.add(new Data("林远图", "201"));
        listData.add(new Data("灭绝师太", "%W101"));
        listData.add(new Data("鸠摩智", "s201"));
        listData.add(new Data("段誉", "W101"));
        listData.add(new Data("王语嫣", "s"));
        listData.add(new Data("梅超风", "w"));
        listData.add(new Data("猪八戒", "22"));
        listData.add(new Data("沙和尚", "ee"));
        listData.add(new Data("唐僧", "你好"));
        listData.add(new Data("女儿国国王", "cc"));
        listData.add(new Data("赵丽颖", "pp"));
        listData.add(new Data("刘诗诗", "cc"));
        adapter.setData(listData);
        letterView.setName(adapter.getCharacterList());
    }

    private void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.contact_list);
        letterView = (LetterView) findViewById(R.id.letter_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setArrowImageView(R.drawable.svg_arrow_down);
        recyclerView.setLoadingMoreEnabled(false);
        header = LayoutInflater.from(this).inflate(R.layout.header_search_view, (ViewGroup) recyclerView.getParent(), false);
        recyclerView.addHeaderView(header);
        adapter = new LetterAdapter(this, listData);
        recyclerView.setAdapter(adapter);

        adapter.setData(listData);
        letterView.setName(adapter.getCharacterList());

    }

    private void initListener() {
        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character), 0);
                Toast.makeText(MainActivity.this,character,Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                adapter.setData(listData);
                letterView.setName(adapter.getCharacterList());
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }
}
