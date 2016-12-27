package com.git.hkl.slidemenuapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.git.hkl.slidemenuapplication.R;
import com.git.hkl.slidemenuapplication.base.MyApplication;
import com.git.hkl.slidemenuapplication.list.ContactComparator;
import com.git.hkl.slidemenuapplication.list.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * 项目名称：SlideMenuApplication
 * 类描述：
 * 创建人：霍凯丽
 * 创建时间：2016/11/30 10:34
 */
public class LetterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Data> listData; // 联系人名称字符串数组
    private List<String> stringData; // 联系人名称List（转换成拼音）
    private List<TextData> resultList; // 最终结果（包含分组的字母）

    public List<String> getCharacterList() {
        return characterList;
    }

    private List<String> characterList; // 字母List

    public enum ITEM_TYPE {
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    public LetterAdapter(Context context, List<Data> listData) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        handleTextData();
    }

    public void setData(List<Data> listData) {
        this.listData = listData;
        handleTextData();
        notifyDataSetChanged();

    }

    private void handleTextData() {
        stringData = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < listData.size(); i++) {
            String pinyin = Utils.getPingYin(listData.get(i).getRoomNumber());
            map.put(pinyin, listData.get(i).getRoomNumber());
            stringData.add(pinyin);
        }
        Collections.sort(stringData, new ContactComparator());

        resultList = new ArrayList<>();
        characterList = new ArrayList<>();

        for (int i = 0; i < stringData.size(); i++) {

            String roomNumber = stringData.get(i);
            String image = listData.get(i).getImage();
            String name = listData.get(i).getmName();
            if (TextUtils.isEmpty(roomNumber))
                return;
            String character = (roomNumber.charAt(0) + "").toUpperCase(Locale.ENGLISH);
            if (roomNumber.length() >= 2) {
                String character1 = (roomNumber.charAt(1) + "").toUpperCase(Locale.ENGLISH);
                String characterName = character + character1;
                if (!characterList.contains(characterName)) {
                    if (ContactComparator.isChara(character.hashCode())) { // 是字母数字
                        characterList.add(characterName);
                        resultList.add(new TextData(name, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal(), image, characterName));
                    } else {
                        if (!characterList.contains("#")) {
                            characterList.add("#");
                            resultList.add(new TextData(name, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal(), image, "#"));
                        }
                    }
                }
            } else {
                if (!characterList.contains(character)) {
                    if (ContactComparator.isChara(character.hashCode())) { // 是字母数字
                        characterList.add(character);
                        resultList.add(new TextData(name, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal(), image, character));
                    } else {
                        if (!characterList.contains("#")) {
                            characterList.add("#");
                            resultList.add(new TextData(name, ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal(), image, "#"));
                        }
                    }
                }
            }
            resultList.add(new TextData(name, ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal(), image, map.get(roomNumber)));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()) {
            return new CharacterHolder(mLayoutInflater.inflate(R.layout.item_character, parent, false));
        } else {
            return new TextDataHolder(mLayoutInflater.inflate(R.layout.item_contact, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CharacterHolder) {
            ((CharacterHolder) holder).roomNumber.setText(resultList.get(position).getRoomNumber());
        } else if (holder instanceof TextDataHolder) {
            ((TextDataHolder) holder).roomNumber.setText(resultList.get(position).getRoomNumber());
            ((TextDataHolder) holder).name.setText(resultList.get(position).getmName());
            if (TextUtils.isEmpty(resultList.get(position).getImage())) {
                ((TextDataHolder) holder).image.setImageDrawable(MyApplication.getContext().getResources().getDrawable(R.mipmap.icon_avatar));

            }else{
                ((TextDataHolder) holder).image.setImageURI(Uri.parse(resultList.get(position).getImage()));
            }
            ((TextDataHolder) holder).roomNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, resultList.get(position).getRoomNumber(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getmType();
    }

    @Override
    public int getItemCount() {
        return resultList == null ? 0 : resultList.size();
    }

    //字母
    public class CharacterHolder extends RecyclerView.ViewHolder {
        private TextView roomNumber;

        CharacterHolder(View view) {
            super(view);
            roomNumber = (TextView) view.findViewById(R.id.character);
        }
    }

    //名字
    public class TextDataHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView roomNumber;
        private SimpleDraweeView image;

        TextDataHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.contact_name);
            roomNumber = (TextView) view.findViewById(R.id.contact_flor);
            image = (SimpleDraweeView) view.findViewById(R.id.iv_avatar);
        }
    }

    public int getScrollPosition(String character) {
        if (characterList.contains(character)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).getRoomNumber().equals(character)) {
                    return i;
                }
            }
        }

        return -1; // -1不会滑动
    }
}
