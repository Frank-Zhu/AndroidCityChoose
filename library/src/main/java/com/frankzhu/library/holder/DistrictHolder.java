package com.frankzhu.library.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.frankzhu.library.R;
import com.frankzhu.library.db.DistrictData;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:26
 * Description: 区域View Holder
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DistrictHolder extends TreeNode.BaseNodeViewHolder<DistrictData> {
    private LayoutInflater mLayoutInflater;

    public DistrictHolder(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View createNodeView(TreeNode treeNode, DistrictData districtData) {
        TextView tvNameView = (TextView) mLayoutInflater.inflate(R.layout.item_district, null, false);
        tvNameView.setText(districtData.districtName);
        return tvNameView;
    }
}
