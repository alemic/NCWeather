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

public class CityListActivity extends Activity {
	private ListView cityListView;

	private ArrayList<String> cityList;
	private ArrayAdapter<String> cityAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		cityListView = (ListView) findViewById(R.id.myListView);

		// ��ȡ��һ��������ʡ������
		Intent provinceIntent = this.getIntent();
		String province = provinceIntent.getStringExtra("province");

		// ��ȡ������Ϣ���б��
		cityList = new ArrayList<String>();
		this.loadCity(province);

		// ���б��ʼ����������
		cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, cityList);

		// �ø��б����������
		cityListView.setAdapter(cityAdapter);
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
	}

	/** ���ݴ����ʡ�����б�����ӳ��С� */
	private ArrayList<String> loadCity(String province) {

		if (province.equals("����")) {
			cityList.add("�Ϸ�");
			cityList.add("�ߺ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ɽ");
			cityList.add("����");
			cityList.add("ͭ��");
			cityList.add("����");
			cityList.add("��ɽ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("Ȫ��");
			cityList.add("����");
			cityList.add("��ƽ");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("������");
			cityList.add("���");
			cityList.add("����");
			cityList.add("��ˮ");
			cityList.add("����");
			cityList.add("��Ҵ");
			cityList.add("ƽ��");
			cityList.add("��Ȫ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("¤��");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("�㶫")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("�麣");
			cityList.add("��ͷ");
			cityList.add("�ع�");
			cityList.add("��ɽ");
			cityList.add("����");
			cityList.add("տ��");
			cityList.add("ï��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("÷��");
			cityList.add("��β");
			cityList.add("��Դ");
			cityList.add("����");
			cityList.add("��Զ");
			cityList.add("��ݸ");
			cityList.add("��ɽ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�Ƹ�");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("���Ǹ�");
			cityList.add("����");
			cityList.add("���");
			cityList.add("����");
			cityList.add("��ɫ");
			cityList.add("����");
			cityList.add("�ӳ�");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����ˮ");
			cityList.add("����");
			cityList.add("��˳");
			cityList.add("ͭ��");
			cityList.add("�Ͻ�");
			cityList.add("ǭ����");
			cityList.add("ǭ����");
			cityList.add("ǭ��");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("�ӱ�")) {
			cityList.add("ʯ��ׯ");
			cityList.add("��ɽ");
			cityList.add("�ػʵ�");
			cityList.add("����");
			cityList.add("��̨");
			cityList.add("����");
			cityList.add("�żҿ�");
			cityList.add("�е�");
			cityList.add("����");
			cityList.add("�ȷ�");
			cityList.add("��ˮ");
		} else if (province.equals("������")) {
			cityList.add("������");
			cityList.add("�������");
			cityList.add("����");
			cityList.add("�׸�");
			cityList.add("˫Ѽɽ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ľ˹");
			cityList.add("��̨��");
			cityList.add("ĵ����");
			cityList.add("�ں�");
			cityList.add("�绯");
			cityList.add("���˰���");
		} else if (province.equals("����")) {
			cityList.add("֣��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("ƽ��ɽ");
			cityList.add("����");
			cityList.add("�ױ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("���");
			cityList.add("���");
			cityList.add("���");
			cityList.add("����Ͽ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�ܿ�");
			cityList.add("פ���");
		} else if (province.equals("����")) {
			cityList.add("�人");
			cityList.add("��ʯ");
			cityList.add("�差");
			cityList.add("ʮ��");
			cityList.add("����");
			cityList.add("�˲�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("Т��");
			cityList.add("�Ƹ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ʩ");
		} else if (province.equals("����")) {
			cityList.add("��ɳ");
			cityList.add("����");
			cityList.add("��̶");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�żҽ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("¦��");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("�Ͼ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ͨ");
			cityList.add("���Ƹ�");
			cityList.add("����");
			cityList.add("�γ�");
			cityList.add("����");
			cityList.add("��");
			cityList.add("̩��");
			cityList.add("��Ǩ");
		} else if (province.equals("����")) {
			cityList.add("�ϲ�");
			cityList.add("������");
			cityList.add("Ƽ��");
			cityList.add("�Ž�");
			cityList.add("����");
			cityList.add("ӥ̶");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�˴�");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ƽ");
			cityList.add("��Դ");
			cityList.add("ͨ��");
			cityList.add("��ɽ");
			cityList.add("��ԭ");
			cityList.add("�׳�");
			cityList.add("�ӱ�");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ɽ");
			cityList.add("��˳");
			cityList.add("��Ϫ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("Ӫ��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�̽�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��«��");
		} else if (province.equals("���ɹ�")) {
			cityList.add("���ͺ���");
			cityList.add("��ͷ");
			cityList.add("�ں�");
			cityList.add("���");
			cityList.add("ͨ��");
			cityList.add("������˹");
			cityList.add("���ױ���");
			cityList.add("�����׶�");
			cityList.add("�����첼");
			cityList.add("�˰�");
			cityList.add("���ֹ���");
			cityList.add("������");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("ʯ��ɽ");
			cityList.add("����");
			cityList.add("��ԭ");
			cityList.add("����");
		} else if (province.equals("�ຣ")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("ͭ��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("μ��");
			cityList.add("�Ӱ�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("ɽ��")) {
			cityList.add("����");
			cityList.add("�ൺ");
			cityList.add("�Ͳ�");
			cityList.add("��ׯ");
			cityList.add("��Ӫ");
			cityList.add("��̨");
			cityList.add("Ϋ��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("̩��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�ĳ�");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("ɽ��")) {
			cityList.add("̫ԭ");
			cityList.add("��ͬ");
			cityList.add("��Ȫ");
			cityList.add("����");
			cityList.add("����");
			cityList.add("˷��");
			cityList.add("����");
			cityList.add("�˳�");
			cityList.add("����");
			cityList.add("�ٷ�");
			cityList.add("����");
		} else if (province.equals("�Ĵ�")) {
			cityList.add("�ɶ�");
			cityList.add("�Թ�");
			cityList.add("��֦��"); 
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��Ԫ");
			cityList.add("����");
			cityList.add("�ڽ�");
			cityList.add("��ɽ");
			cityList.add("�ϳ�");
			cityList.add("�˱�");
			cityList.add("�㰲");
			cityList.add("����");
			cityList.add("üɽ");
			cityList.add("�Ű�");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��ɽ");
		} else if (province.equals("̨��")) {
			cityList.add("̨��");
			cityList.add("����");
			cityList.add("��¡");
			cityList.add("̨��");
			cityList.add("̨��");
			cityList.add("����");
			cityList.add("����");
		} else if (province.equals("�½�")) {
			cityList.add("��³ľ��");
			cityList.add("��������");
			cityList.add("��³��");
			cityList.add("����");
			cityList.add("����");
			cityList.add("������");
			cityList.add("��ʲ");
			cityList.add("�������տ¶�����");
			cityList.add("���������ɹ�");
			cityList.add("����");
			cityList.add("���������ɹ�");
			cityList.add("���������");
			cityList.add("����");
			cityList.add("����̩");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("ɽ��");
			cityList.add("�տ���");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��֥");
		} else if (province.equals("����")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("��Ϫ");
			cityList.add("��ɽ");
			cityList.add("��ͨ");
			cityList.add("����");
			cityList.add("�ն�");
			cityList.add("�ٲ�");
			cityList.add("��ɽ");
			cityList.add("���");
			cityList.add("��˫����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("�º�");
			cityList.add("ŭ��");
			cityList.add("����");
		} else if (province.equals("�㽭")) {
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("����");
			cityList.add("��");
			cityList.add("����");
			cityList.add("��ɽ");
			cityList.add("̨��");
			cityList.add("��ˮ");
		}

			return this.cityList;
	}
}
