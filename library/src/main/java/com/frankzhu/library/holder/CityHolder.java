package com.frankzhu.library.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.frankzhu.library.R;
import com.frankzhu.library.db.CityData;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:26
 * Description: 城市View Holder
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class CityHolder extends TreeNode.BaseNodeViewHolder<CityData> {
    private LayoutInflater mLayoutInflater;
    private CityData mCityData;

    public CityHolder(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View createNodeView(TreeNode treeNode, CityData cityData) {
        mCityData = cityData;
        TextView tvNameView = (TextView) mLayoutInflater.inflate(R.layout.item_city, null, false);
        tvNameView.setText(cityData.cityName);
        return tvNameView;
    }

    @Override
    public void toggle(boolean active) {
        mCityData.isExpend = active;
    }

    @Override
    public int getContainerStyle() {
        return R.style.TreeNodeDistrictStyle;
    }
}
