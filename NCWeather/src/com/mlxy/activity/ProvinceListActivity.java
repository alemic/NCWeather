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

/** ʡ���б�
 * 
 * @author mlxy
 * */
public class ProvinceListActivity extends Activity {
	/** ʡ���б���ͼ��*/
	private ListView provinceListView;
	
	/** ʡ���б�*/
	private ArrayList<String> provinceList;
	
	/** ��������׼�������б���ͼ�*/
	private ArrayAdapter<String> provinceAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		provinceListView = (ListView) findViewById(R.id.myListView);
		
		// ��ȡʡ����Ϣ���б��
		provinceList = new ArrayList<String>();
		this.loadProvince();
		
		// ���б��ʼ����������
		provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, provinceList);
		
		// �ø��б����������
		provinceListView.setAdapter(provinceAdapter);
		provinceListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// �����ֱϽ�С�
				if (position == 1 || position == 2 || position == 3 || position == 24 || position == 28 || position == 29) {
					// ֱ�Ӱ�װ�ô�����һ����
					Intent intent = new Intent();
					intent.putExtra("city", provinceList.get(position));
					
					setResult(2, intent);
					finish();
					return;
				}
				
				// ��װ��ѡ���ʡ�ݣ����������б��ȴ�����ֵ��
				Intent intent = new Intent();
				intent.putExtra("province", provinceList.get(position));
				intent.setClass(ProvinceListActivity.this, CityListActivity.class);
				
				startActivityForResult(intent, 1);
			}
		});
	}
	
	/** ���б�����Ӹ�ʡ�ݣ���ƴ������*/
	private void loadProvince() {
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("�㶫");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("�ӱ�");
		provinceList.add("������");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("���ɹ�");
		provinceList.add("����");
		provinceList.add("�ຣ");
		provinceList.add("����");
		provinceList.add("ɽ��");
		provinceList.add("�Ϻ�");
		provinceList.add("ɽ��");
		provinceList.add("�Ĵ�");
		provinceList.add("̨��");
		provinceList.add("���");
		provinceList.add("���");
		provinceList.add("�½�");
		provinceList.add("����");
		provinceList.add("����");
		provinceList.add("�㽭");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		
		// �õ������б��صĳ���������װ�ô�����һ����
		String city = data.getStringExtra("city");
		
		Intent intent = new Intent();
		intent.putExtra("city", city);
		
		setResult(2, intent);
		finish();
	}
}
