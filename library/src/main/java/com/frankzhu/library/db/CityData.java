package com.frankzhu.library.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:19
 * Description: 城市数据结构
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class CityData implements Parcelable {
    public String provinceId;//关联的省市ID
    public String cityId;//城市ID
    public String cityName;//城市名称
    public ArrayList<DistrictData> districts;//区/县列表数据
    public boolean isExpend;//是否展开，用于保存展开状态

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.provinceId);
        dest.writeString(this.cityId);
        dest.writeString(this.cityName);
        dest.writeSerializable(this.districts);
        dest.writeByte(isExpend ? (byte) 1 : (byte) 0);
    }

    public CityData() {
    }

    private CityData(Parcel in) {
        this.provinceId = in.readString();
        this.cityId = in.readString();
        this.cityName = in.readString();
        this.districts = (ArrayList<DistrictData>) in.readArrayList(DistrictData.class.getClassLoader());
        this.isExpend = in.readByte() != 0;
    }

    public static final Parcelable.Creator<CityData> CREATOR = new Parcelable.Creator<CityData>() {
        public CityData createFromParcel(Parcel source) {
            return new CityData(source);
        }

        public CityData[] newArray(int size) {
            return new CityData[size];
        }
    };
}
