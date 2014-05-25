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

/** ���ó����б�
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

		// �½����ݿ��������
		db = new DatabaseManager(this);
		
		// ��ʼ��������������ԡ�
		this.initButton();
		this.initArrayList();
		this.initListAdapter();
		this.initListView();
	}

	/** �����ݿ��ж�ȡ���в����б������롣 */
	private void loadCity() {
		Cursor cities = db.selectAll();
		
		//������ݿ��ǿյľ�ֱ��ֹͣ������С�
		if (!cities.moveToFirst()) {
			return;
		}
		
		// ���������б�Ȼ��ʼ���롣
		cityList.clear();
		
		do {
			cityList.add(cities.getString(1));
		} while (cities.moveToNext());
	}
	
	/** ɾ�����С�*/
	private void deleteCity(int position) {
		String cityName = cityList.get(position);
		cityList.remove(position);
		db.delete(cityName);
	}
	
	/** �����³��С�*/
	private void addCity(String cityName) {
		cityList.add(cityName);
		db.insert(cityName);
	}
	
	/** ���Ի�����ʾ�������ó��С�*/
	private void addNewCity() {
		String message = "��һ������ȷʵ���ڵĳ��������������������С��֣��硰���������Ϻ�����\n\n"
				+ "�ݲ�֧�ֵؼ������µ���������\n\n"
				+ "֧�ֲ���������У�������ϸ�б���Ҳ��֪�������˲������ҡ�\n\n"
				+ "��������˲�֧�ֵĳ������ᵼ���޷���Ӧ�ã���ϵͳ������������ݼ��ɻָ���";
		
		final EditText editText = new EditText(this);
		
		new AlertDialog.Builder(this)
			.setTitle("�������ó���")
			.setMessage(message)
			.setView(editText)
			.setNegativeButton("ȡ��", null)
			.setPositiveButton("����", new DialogInterface.OnClickListener() {
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
	
	/** ��ʼ����ť��*/
	private void initButton() {
		// ��ʼ����ť��
		addNewButton = (Button) findViewById(R.id.buttonAddNew);
		addNewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewCity();
			}
		});
	}
	
	/** ��ʼ���б�*/
	private void initArrayList() {
		// �����б����
		cityList = new ArrayList<String>();
		// ��ȡ������Ϣ���б��
		this.loadCity();
	}
	
	/** ��ʼ���б���������*/
	private void initListAdapter() {
		// ���������������б�
		cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, cityList);
	}
	
	/** ��ʼ���б���ͼ��*/
	private void initListView() {
		// ��ȡ�б����
		cityListView = (ListView) findViewById(R.id.favoriteListView);
		
		// ���б���ͼ����������
		cityListView.setAdapter(cityAdapter);
		
		// ���б���ͼ����Ŀ�����������
		cityListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ��ѡ��ĳ�����������һ����
				Intent intent = new Intent();
				intent.putExtra("city", cityList.get(position));

				setResult(1, intent);
				finish();
			}
		});
		
		// ���б���ͼ����Ŀ������������
		cityListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				String message = String.format("ȷ��Ҫɾ��\"%s\"��", cityList.get(position));
				
				// ���Ի���ȷ��ɾ����
				new AlertDialog.Builder(MyFavoriteActivity.this)
					.setTitle("ɾ��ȷ��")
					.setMessage(message)
					.setNegativeButton("ȡ��", null)
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
	
	/** ˢ���б����ݡ�*/
	private void refreshList() {
		cityListView.invalidateViews();
	}
}
