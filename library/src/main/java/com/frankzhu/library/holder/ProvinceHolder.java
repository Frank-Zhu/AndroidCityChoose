package com.frankzhu.library.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.frankzhu.library.R;
import com.frankzhu.library.db.ProvinceData;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/9 17:25
 * Description: 省份View Holder
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/9      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ProvinceHolder extends TreeNode.BaseNodeViewHolder<ProvinceData> {
    private LayoutInflater mLayoutInflater;

    public ProvinceHolder(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View createNodeView(TreeNode treeNode, ProvinceData provinceData) {
        View rootView = mLayoutInflater.inflate(R.layout.item_province, null, false);
        return rootView;
    }

    @Override
    public int getContainerStyle() {
        return R.style.TreeNodeCityStyle;
    }
}
