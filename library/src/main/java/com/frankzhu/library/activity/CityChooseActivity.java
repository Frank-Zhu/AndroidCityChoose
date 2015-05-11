package com.frankzhu.library.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.frankzhu.library.R;
import com.frankzhu.library.db.CityData;
import com.frankzhu.library.db.DistrictData;
import com.frankzhu.library.db.ProvinceData;
import com.frankzhu.library.holder.CityHolder;
import com.frankzhu.library.holder.DistrictHolder;
import com.frankzhu.library.holder.ProvinceHolder;
import com.frankzhu.library.view.SideBarView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;


public class CityChooseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FrameLayout mFlContentView;
    private SideBarView mSideBarView;
    private TextView mTvLetter;
    private ArrayList<ProvinceData> mProvinces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        loadProvinceData();
        setUpViewComponent();
    }

    /**
     * 加载省份数据
     */
    private void loadProvinceData() {
        for (int i = 0; i < 5; i++) {
            ProvinceData provinceData = new ProvinceData();
            provinceData.cities = new ArrayList<>();
            provinceData.provinceId = i + "";
            provinceData.provinceName = "省份" + i;

            for (int j = 0; j < 5; j++) {
                CityData cityData = new CityData();
                cityData.districts = new ArrayList<>();
                cityData.cityId = j + "";
                cityData.provinceId = i + "";
                cityData.cityName = "城市" + j;

                for (int k = 0; k < 5; k++) {
                    DistrictData districtData = new DistrictData();
                    districtData.cityId = j + "";
                    districtData.provinceId = i + "";
                    districtData.districtId = k + "";
                    districtData.districtName = "区域" + k;
                    cityData.districts.add(districtData);
                }

                provinceData.cities.add(cityData);
            }
            mProvinces.add(provinceData);
        }
    }

    private void setUpViewComponent() {
        mFlContentView = (FrameLayout) findViewById(R.id.fl_content_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setUpTreeView();
        setUpRecyclerView();
        setUpSideBarView();
    }

    private void setUpSideBarView() {
        mSideBarView = (SideBarView) findViewById(R.id.side_bar_view);
        mTvLetter = (TextView) findViewById(R.id.tv_letter);
        mSideBarView.setTextView(mTvLetter);
    }

    private void setUpRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CityChooseAdapter adapter = new CityChooseAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.addItems(mProvinces);
    }

    private void setUpTreeView() {
        TreeNode root = TreeNode.root();
        for (ProvinceData province : mProvinces) {
            TreeNode provinceNode = new TreeNode(province).setViewHolder(new ProvinceHolder(this));
            for (CityData city : province.cities) {
                TreeNode cityNode = new TreeNode(city).setViewHolder(new CityHolder(this));
                for (DistrictData district : city.districts) {
                    TreeNode districtNode = new TreeNode(district).setViewHolder(new DistrictHolder(this));
                    districtNode.setClickListener(new TreeNode.TreeNodeClickListener() {
                        @Override
                        public void onClick(TreeNode treeNode, Object district) {

                        }
                    });
                    cityNode.addChild(districtNode);
                }
                provinceNode.addChild(cityNode);
            }
            provinceNode.setExpanded(true);//默认展开省份一级数据
            root.addChild(provinceNode);
        }
        AndroidTreeView tView = new AndroidTreeView(this, root);
        mFlContentView.addView(tView.getView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_city_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
