package com.git.hkl.slidemenuapplication.adapter;

import java.io.Serializable;

/**
 * 项目名称：SlideMenuApplication
 * 类描述：
 * 创建人：霍凯丽
 * 创建时间：2016/12/27 14:31
 */
public class Data implements Serializable {
    private String mName;
    private String image;
    private String roomNumber;

    public Data(String mName, String roomNumber) {
        this.mName = mName;
        this.roomNumber = roomNumber;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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
