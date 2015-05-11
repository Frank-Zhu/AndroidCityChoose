package com.frankzhu.library.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:18
 * Description: 省份数据结构
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ProvinceData implements Parcelable {
    public String provinceLetter;//省市首字母
    public String provinceId;//省市ID
    public String provinceName;//省市名称
    public ArrayList<CityData> cities;//城市列表

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.provinceLetter);
        dest.writeString(this.provinceId);
        dest.writeString(this.provinceName);
        dest.writeSerializable(this.cities);
    }

    public ProvinceData() {
    }

    private ProvinceData(Parcel in) {
        this.provinceLetter = in.readString();
        this.provinceId = in.readString();
        this.provinceName = in.readString();
        this.cities = (ArrayList<CityData>) in.readArrayList(CityData.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProvinceData> CREATOR = new Parcelable.Creator<ProvinceData>() {
        public ProvinceData createFromParcel(Parcel source) {
            return new ProvinceData(source);
        }

        public ProvinceData[] newArray(int size) {
            return new ProvinceData[size];
        }
    };
}
