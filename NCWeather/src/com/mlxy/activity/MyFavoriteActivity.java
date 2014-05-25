package com.mlxy.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mlxy.database.DatabaseManager;
import com.mlxy.ncweather.R;

/** 常用城市列表。
 * 
 * @author mlxy
 * */
public class MyFavoriteActivity extends Activity {
	private ListView cityListView;
	private Button addNewButton;

	private DatabaseManager db;
	
	private ArrayList<String> cityList;
	private ArrayAdapter<String> cityAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view_with_button);

		// 新建数据库管理器。
		db = new DatabaseManager(this);
		
		// 初始化所有组件和属性。
		this.initButton();
		this.initArrayList();
		this.initListAdapter();
		this.initListView();
	}

	/** 从数据库中读取城市并向列表里载入。 */
	private void loadCity() {
		Cursor cities = db.selectAll();
		
		//如果数据库是空的就直接停止载入城市。
		if (!cities.moveToFirst()) {
			return;
		}
		
		// 否则就清空列表然后开始载入。
		cityList.clear();
		
		do {
			cityList.add(cities.getString(1));
		} while (cities.moveToNext());
	}
	
	/** 删除城市。*/
	private void deleteCity(int position) {
		String cityName = cityList.get(position);
		cityList.remove(position);
		db.delete(cityName);
	}
	
	/** 加入新城市。*/
	private void addCity(String cityName) {
		cityList.add(cityName);
		db.insert(cityName);
	}
	
	/** 弹对话框提示新增常用城市。*/
	private void addNewCity() {
		String message = "请一定输入确实存在的城市名，城市名不带“市”字，如“北京”“上海”。\n\n"
				+ "暂不支持地级市以下的行政区域。\n\n"
				+ "支持部分外国城市，但是详细列表我也不知道，新浪不告诉我。\n\n"
				+ "如果输入了不支持的城市名会导致无法打开应用，在系统设置内清除数据即可恢复。";
		
		final EditText editText = new EditText(this);
		
		new AlertDialog.Builder(this)
			.setTitle("新增常用城市")
			.setMessage(message)
			.setView(editText)
			.setNegativeButton("取消", null)
			.setPositiveButton("新增", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String cityName = editText.getText().toString();
					
					if (cityName.equals("")){return;}
					
					addCity(cityName);
					refreshList();
				}
			})
			.show();
	}
	
	/** 初始化按钮。*/
	private void initButton() {
		// 初始化按钮。
		addNewButton = (Button) findViewById(R.id.buttonAddNew);
		addNewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewCity();
			}
		});
	}
	
	/** 初始化列表。*/
	private void initArrayList() {
		// 创建列表对象。
		cityList = new ArrayList<String>();
		// 读取城市信息到列表里。
		this.loadCity();
	}
	
	/** 初始化列表适配器。*/
	private void initListAdapter() {
		// 向适配器中塞入列表。
		cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, cityList);
	}
	
	/** 初始化列表视图。*/
	private void initListView() {
		// 获取列表对象。
		cityListView = (ListView) findViewById(R.id.favoriteListView);
		
		// 给列表视图绑定适配器。
		cityListView.setAdapter(cityAdapter);
		
		// 给列表视图绑定项目点击监听器。
		cityListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 把选择的城市名传回上一级。
				Intent intent = new Intent();
				intent.putExtra("city", cityList.get(position));

				setResult(1, intent);
				finish();
			}
		});
		
		// 给列表视图绑定项目长按监听器。
		cityListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				String message = String.format("确定要删除\"%s\"吗？", cityList.get(position));
				
				// 弹对话框确认删除。
				new AlertDialog.Builder(MyFavoriteActivity.this)
					.setTitle("删除确认")
					.setMessage(message)
					.setNegativeButton("取消", null)
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							deleteCity(position);
							refreshList();
						}
					})
					.show();
				return true;
			}
		});
	}
	
	/** 刷新列表内容。*/
	private void refreshList() {
		cityListView.invalidateViews();
	}
}
