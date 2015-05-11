package com.frankzhu.library.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frankzhu.library.R;
import com.frankzhu.library.db.CityData;
import com.frankzhu.library.db.DistrictData;
import com.frankzhu.library.db.ProvinceData;
import com.frankzhu.library.holder.CityHolder;
import com.frankzhu.library.holder.DistrictHolder;
import com.frankzhu.library.holder.ProvinceHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/5/11 23:15
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/5/11      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class CityChooseAdapter extends RecyclerView.Adapter<CityChooseAdapter.ProvinceViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<ProvinceData> mProvinces = new ArrayList<>();

    public CityChooseAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addItems(ArrayList<ProvinceData> provinces) {
        if (provinces != null && provinces.size() > 0) {
            mProvinces.addAll(provinces);
            notifyItemRangeInserted(0, provinces.size());
        }
    }

    @Override
    public ProvinceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProvinceViewHolder(mLayoutInflater.inflate(R.layout.item_city_choose, parent, false), this);
    }

    @Override
    public void onBindViewHolder(ProvinceViewHolder holder, int position) {
        holder.bindViewData(getItemData(position));
    }

    public ProvinceData getItemData(int position) {
        return mProvinces == null ? null : (position >= mProvinces.size() ? null : mProvinces.get(position));
    }

    @Override
    public int getItemCount() {
        return mProvinces == null ? 0 : mProvinces.size();
    }

    public static class ProvinceViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        LinearLayout mLlCity;
        CityChooseAdapter mCityChooseAdapter;

        ProvinceViewHolder(View view, CityChooseAdapter adapter) {
            super(view);
            mCityChooseAdapter = adapter;
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mLlCity = (LinearLayout) view.findViewById(R.id.ll_city);
        }

        public void bindViewData(ProvinceData provinceData) {
            if (provinceData != null) {
                mTvName.setText(provinceData.provinceName);
            }
            setUpTreeView(mLlCity, provinceData);
        }

        private void setUpTreeView(LinearLayout contentView, ProvinceData province) {
            contentView.removeAllViews();
            TreeNode root = TreeNode.root();
            root.setViewHolder(new ProvinceHolder(mCityChooseAdapter.mContext));
            for (CityData city : province.cities) {
                TreeNode cityNode = new TreeNode(city).setViewHolder(new CityHolder(mCityChooseAdapter.mContext));
                for (DistrictData district : city.districts) {
                    TreeNode districtNode = new TreeNode(district).setViewHolder(new DistrictHolder(mCityChooseAdapter.mContext));
                    districtNode.setClickListener(new TreeNode.TreeNodeClickListener() {
                        @Override
                        public void onClick(TreeNode treeNode, Object district) {

                        }
                    });
                    cityNode.addChild(districtNode);
                }
                cityNode.setExpanded(city.isExpend);
                root.addChild(cityNode);
            }
            if(root.getViewHolder() != null) {
                root.getViewHolder().setContainerStyle(R.style.TreeNodeCityStyle);
            }
            AndroidTreeView tView = new AndroidTreeView(mCityChooseAdapter.mContext, root);
            contentView.addView(tView.getView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
