package com.frankzhu.library.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:21
 * Description: 城市区域数据结构
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DistrictData implements Parcelable {
    public String provinceId;//关联的省市ID
    public String cityId;//关联的城市ID
    public String districtId;//县/区ID
    public String districtName;//县/区名称

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.provinceId);
        dest.writeString(this.cityId);
        dest.writeString(this.districtId);
        dest.writeString(this.districtName);
    }

    public DistrictData() {
    }

    private DistrictData(Parcel in) {
        this.provinceId = in.readString();
        this.cityId = in.readString();
        this.districtId = in.readString();
        this.districtName = in.readString();
    }

    public static final Parcelable.Creator<DistrictData> CREATOR = new Parcelable.Creator<DistrictData>() {
        public DistrictData createFromParcel(Parcel source) {
            return new DistrictData(source);
        }

        public DistrictData[] newArray(int size) {
            return new DistrictData[size];
        }
    };
}
