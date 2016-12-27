package com.git.hkl.slidemenuapplication.adapter;

import java.io.Serializable;

/**
 * 项目名称：SlideMenuApplication
 * 类描述：
 * 创建人：霍凯丽
 * 创建时间：2016/11/30 10:38
 */
public class TextData implements Serializable {
    private String mName;
    private int mType;
    private String image;
    private String roomNumber;


    public TextData(String mName, int mType, String image, String roomNumber) {
        this.mName = mName;
        this.mType = mType;
        this.image = image;
        this.roomNumber = roomNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
