package com.mlxy.activity;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mlxy.ncweather.R;

/** 省份列表。
 * 
 * @author mlxy
 * */
public class ProvinceListActivity extends Activity {
	/** 省份列表视图。*/
	private ListView provinceListView;
	
	/** 省份列表。*/
	private ArrayList<String> provinceList;
	
	/** 适配器，准备塞入列表视图里。*/
	private ArrayAdapter<String> provinceAdapter;
	
	/** 直辖市在列表里的下标。*/
	private int[] municipalityIndex = {2,3,4,25,29,30};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		provinceListView = (ListView) findViewById(R.id.myListView);
		
		// 读取省份信息到列表里。
		provinceList = new ArrayList<String>();
		this.loadProvince();
		
		// 用列表初始化适配器。
		provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, provinceList);
		
		// 用给列表绑定适配器。
		provinceListView.setAdapter(provinceAdapter);
		provinceListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				for (int i = 0; i < municipalityIndex.length; i++) {
					// 如果是直辖市。
					if (position == municipalityIndex[i]) {
						// 直接包装好传回上一级。
						Intent intent = new Intent();
						intent.putExtra("city", provinceList.get(position));
						
						setResult(2, intent);
						finish();
						return;
					}
				}
				
				// 包装好选择的省份，启动城市列表并等待返回值。
				Intent intent = new Intent();
				intent.putExtra("province", provinceList.get(position));
				if (position == 0) {
					intent.setClass(ProvinceListActivity.this, MyFavoriteActivity.class);
				} else {
					intent.setClass(ProvinceListActivity.this, CityListActivity.class);
				}
				
				startActivityForResult(intent, 1);
			}
		});
	}
	
	/** 向列表里添加各省份，按拼音排序。*/
	private void loadProvince() {
		provinceList.add("常用");
		provinceList.add("安徽");
		provinceList.add("澳门");
		provinceList.add("北京");
		provinceList.add("重庆");
		provinceList.add("福建");
		provinceList.add("甘肃");
		provinceList.add("广东");
		provinceList.add("广西");
		provinceList.add("贵州");
		provinceList.add("海南");
		provinceList.add("河北");
		provinceList.add("黑龙江");
		provinceList.add("河南");
		provinceList.add("湖北");
		provinceList.add("湖南");
		provinceList.add("江苏");
		provinceList.add("江西");
		provinceList.add("吉林");
		provinceList.add("辽宁");
		provinceList.add("内蒙古");
		provinceList.add("宁夏");
		provinceList.add("青海");
		provinceList.add("陕西");
		provinceList.add("山东");
		provinceList.add("上海");
		provinceList.add("山西");
		provinceList.add("四川");
		provinceList.add("台湾");
		provinceList.add("天津");
		provinceList.add("香港");
		provinceList.add("新疆");
		provinceList.add("西藏");
		provinceList.add("云南");
		provinceList.add("浙江");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		
		// 得到城市列表传回的城市名，包装好传回上一级。
		String city = data.getStringExtra("city");
		
		Intent intent = new Intent();
		intent.putExtra("city", city);
		
		setResult(2, intent);
		finish();
	}
}
