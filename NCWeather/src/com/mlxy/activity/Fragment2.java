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

public class Fragment2 extends Fragment {
	Bundle bundle;
	String text;
	TextView textView;
	
	@Override
	public void onStart() {
		super.onStart();
		
		bundle = this.getArguments();
		text = bundle.getString("mlxy");
		textView = (TextView) this.getView().findViewById(R.id.fragmentContent2);
		
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		int textSize = (int) (11 * dm.density);
		
		textView.setTextSize(textSize);
		textView.setText(text);
		textView.setBackgroundColor(Color.WHITE);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment2, null);
	}
}
