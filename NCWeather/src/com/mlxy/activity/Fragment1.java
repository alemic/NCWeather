package com.mlxy.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlxy.ncweather.R;

public class Fragment1 extends Fragment {
	Bundle bundle;
	String text;
	TextView textView;
	
	@Override
	public void onStart() {
		super.onStart();
		
		// ȡ�ô������洫���Ĳ�����
		bundle = this.getArguments();
		text = bundle.getString("mlxy");
		
		// �õ��ı���ͼ����
		textView = (TextView) this.getView().findViewById(R.id.fragmentContent1);
		
		// ��̬���������С��
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		int textSize = (int) (11 * dm.density);
		
		textView.setTextSize(textSize);
		textView.setText(text);
		textView.setBackgroundColor(Color.WHITE);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ָ��XML�����ļ���
		return inflater.inflate(R.layout.fragment1, null);
	}
}
