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

		// 获取上一级传来的省份名。
		Intent provinceIntent = this.getIntent();
		String province = provinceIntent.getStringExtra("province");

		// 读取城市信息到列表里。
		cityList = new ArrayList<String>();
		this.loadCity(province);

		// 用列表初始化适配器。
		cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, cityList);

		// 用给列表绑定适配器。
		cityListView.setAdapter(cityAdapter);
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
	}

	/** 根据传入的省份向列表里添加城市。 */
	private ArrayList<String> loadCity(String province) {

		if (province.equals("安徽")) {
			cityList.add("合肥");
			cityList.add("芜湖");
			cityList.add("蚌埠");
			cityList.add("淮南");
			cityList.add("马鞍山");
			cityList.add("淮北");
			cityList.add("铜陵");
			cityList.add("安庆");
			cityList.add("黄山");
			cityList.add("滁州");
			cityList.add("阜阳");
			cityList.add("宿州");
			cityList.add("巢湖");
			cityList.add("六安");
			cityList.add("亳州");
			cityList.add("池州");
			cityList.add("宣城");
		} else if (province.equals("福建")) {
			cityList.add("福州");
			cityList.add("厦门");
			cityList.add("莆田");
			cityList.add("三明");
			cityList.add("泉州");
			cityList.add("漳州");
			cityList.add("南平");
			cityList.add("龙岩");
			cityList.add("宁德");
		} else if (province.equals("甘肃")) {
			cityList.add("兰州");
			cityList.add("嘉峪关");
			cityList.add("金昌");
			cityList.add("白银");
			cityList.add("天水");
			cityList.add("武威");
			cityList.add("张掖");
			cityList.add("平凉");
			cityList.add("酒泉");
			cityList.add("庆阳");
			cityList.add("定西");
			cityList.add("陇南");
			cityList.add("临夏");
			cityList.add("甘南");
		} else if (province.equals("广东")) {
			cityList.add("广州");
			cityList.add("深圳");
			cityList.add("珠海");
			cityList.add("汕头");
			cityList.add("韶关");
			cityList.add("佛山");
			cityList.add("江门");
			cityList.add("湛江");
			cityList.add("茂名");
			cityList.add("肇庆");
			cityList.add("惠州");
			cityList.add("梅州");
			cityList.add("汕尾");
			cityList.add("河源");
			cityList.add("阳江");
			cityList.add("清远");
			cityList.add("东莞");
			cityList.add("中山");
			cityList.add("潮州");
			cityList.add("揭阳");
			cityList.add("云浮");
		} else if (province.equals("广西")) {
			cityList.add("南宁");
			cityList.add("柳州");
			cityList.add("桂林");
			cityList.add("梧州");
			cityList.add("北海");
			cityList.add("防城港");
			cityList.add("钦州");
			cityList.add("贵港");
			cityList.add("玉林");
			cityList.add("百色");
			cityList.add("贺州");
			cityList.add("河池");
			cityList.add("来宾");
			cityList.add("崇左");
		} else if (province.equals("贵州")) {
			cityList.add("贵阳");
			cityList.add("六盘水");
			cityList.add("遵义");
			cityList.add("安顺");
			cityList.add("铜仁");
			cityList.add("毕节");
			cityList.add("黔西南");
			cityList.add("黔东南");
			cityList.add("黔南");
		} else if (province.equals("海南")) {
			cityList.add("海口");
			cityList.add("三亚");
		} else if (province.equals("河北")) {
			cityList.add("石家庄");
			cityList.add("唐山");
			cityList.add("秦皇岛");
			cityList.add("邯郸");
			cityList.add("邢台");
			cityList.add("保定");
			cityList.add("张家口");
			cityList.add("承德");
			cityList.add("沧州");
			cityList.add("廊坊");
			cityList.add("衡水");
		} else if (province.equals("黑龙江")) {
			cityList.add("哈尔滨");
			cityList.add("齐齐哈尔");
			cityList.add("鸡西");
			cityList.add("鹤岗");
			cityList.add("双鸭山");
			cityList.add("大庆");
			cityList.add("伊春");
			cityList.add("佳木斯");
			cityList.add("七台河");
			cityList.add("牡丹江");
			cityList.add("黑河");
			cityList.add("绥化");
			cityList.add("大兴安岭");
		} else if (province.equals("河南")) {
			cityList.add("郑州");
			cityList.add("开封");
			cityList.add("洛阳");
			cityList.add("平顶山");
			cityList.add("焦作");
			cityList.add("鹤壁");
			cityList.add("新乡");
			cityList.add("安阳");
			cityList.add("濮阳");
			cityList.add("许昌");
			cityList.add("漯河");
			cityList.add("三门峡");
			cityList.add("南阳");
			cityList.add("商丘");
			cityList.add("信阳");
			cityList.add("周口");
			cityList.add("驻马店");
		} else if (province.equals("湖北")) {
			cityList.add("武汉");
			cityList.add("黄石");
			cityList.add("襄樊");
			cityList.add("十堰");
			cityList.add("荆州");
			cityList.add("宜昌");
			cityList.add("荆门");
			cityList.add("鄂州");
			cityList.add("孝感");
			cityList.add("黄冈");
			cityList.add("咸宁");
			cityList.add("随州");
			cityList.add("恩施");
		} else if (province.equals("湖南")) {
			cityList.add("长沙");
			cityList.add("株洲");
			cityList.add("湘潭");
			cityList.add("衡阳");
			cityList.add("邵阳");
			cityList.add("岳阳");
			cityList.add("常德");
			cityList.add("张家界");
			cityList.add("益阳");
			cityList.add("郴州");
			cityList.add("永州");
			cityList.add("怀化");
			cityList.add("娄底");
			cityList.add("湘西");
		} else if (province.equals("江苏")) {
			cityList.add("南京");
			cityList.add("无锡");
			cityList.add("徐州");
			cityList.add("常州");
			cityList.add("苏州");
			cityList.add("南通");
			cityList.add("连云港");
			cityList.add("淮安");
			cityList.add("盐城");
			cityList.add("扬州");
			cityList.add("镇江");
			cityList.add("泰州");
			cityList.add("宿迁");
		} else if (province.equals("江西")) {
			cityList.add("南昌");
			cityList.add("景德镇");
			cityList.add("萍乡");
			cityList.add("九江");
			cityList.add("新余");
			cityList.add("鹰潭");
			cityList.add("赣州");
			cityList.add("吉安");
			cityList.add("宜春");
			cityList.add("抚州");
			cityList.add("上饶");
		} else if (province.equals("吉林")) {
			cityList.add("长春");
			cityList.add("吉林");
			cityList.add("四平");
			cityList.add("辽源");
			cityList.add("通化");
			cityList.add("白山");
			cityList.add("松原");
			cityList.add("白城");
			cityList.add("延边");
		} else if (province.equals("辽宁")) {
			cityList.add("沈阳");
			cityList.add("大连");
			cityList.add("鞍山");
			cityList.add("抚顺");
			cityList.add("本溪");
			cityList.add("丹东");
			cityList.add("锦州");
			cityList.add("营口");
			cityList.add("阜新");
			cityList.add("辽阳");
			cityList.add("盘锦");
			cityList.add("铁岭");
			cityList.add("朝阳");
			cityList.add("葫芦岛");
		} else if (province.equals("内蒙古")) {
			cityList.add("呼和浩特");
			cityList.add("包头");
			cityList.add("乌海");
			cityList.add("赤峰");
			cityList.add("通辽");
			cityList.add("鄂尔多斯");
			cityList.add("呼伦贝尔");
			cityList.add("巴彦淖尔");
			cityList.add("乌兰察布");
			cityList.add("兴安");
			cityList.add("锡林郭勒");
			cityList.add("阿拉善");
		} else if (province.equals("宁夏")) {
			cityList.add("银川");
			cityList.add("石嘴山");
			cityList.add("吴忠");
			cityList.add("固原");
			cityList.add("中卫");
		} else if (province.equals("青海")) {
			cityList.add("西宁");
			cityList.add("海东");
			cityList.add("海北");
			cityList.add("黄南");
			cityList.add("海南");
			cityList.add("果洛");
			cityList.add("玉树");
			cityList.add("海西");
		} else if (province.equals("陕西")) {
			cityList.add("西安");
			cityList.add("铜川");
			cityList.add("宝鸡");
			cityList.add("咸阳");
			cityList.add("渭南");
			cityList.add("延安");
			cityList.add("汉中");
			cityList.add("榆林");
			cityList.add("安康");
			cityList.add("商洛");
		} else if (province.equals("山东")) {
			cityList.add("济南");
			cityList.add("青岛");
			cityList.add("淄博");
			cityList.add("枣庄");
			cityList.add("东营");
			cityList.add("烟台");
			cityList.add("潍坊");
			cityList.add("威海");
			cityList.add("济宁");
			cityList.add("泰安");
			cityList.add("日照");
			cityList.add("莱芜");
			cityList.add("临沂");
			cityList.add("德州");
			cityList.add("聊城");
			cityList.add("滨州");
			cityList.add("菏泽");
		} else if (province.equals("山西")) {
			cityList.add("太原");
			cityList.add("大同");
			cityList.add("阳泉");
			cityList.add("长治");
			cityList.add("晋城");
			cityList.add("朔州");
			cityList.add("晋中");
			cityList.add("运城");
			cityList.add("忻州");
			cityList.add("临汾");
			cityList.add("吕梁");
		} else if (province.equals("四川")) {
			cityList.add("成都");
			cityList.add("自贡");
			cityList.add("攀枝花"); 
			cityList.add("泸州");
			cityList.add("德阳");
			cityList.add("绵阳");
			cityList.add("广元");
			cityList.add("遂宁");
			cityList.add("内江");
			cityList.add("乐山");
			cityList.add("南充");
			cityList.add("宜宾");
			cityList.add("广安");
			cityList.add("达州");
			cityList.add("眉山");
			cityList.add("雅安");
			cityList.add("巴中");
			cityList.add("资阳");
			cityList.add("阿坝");
			cityList.add("甘孜");
			cityList.add("凉山");
		} else if (province.equals("台湾")) {
			cityList.add("台北");
			cityList.add("高雄");
			cityList.add("基隆");
			cityList.add("台中");
			cityList.add("台南");
			cityList.add("新竹");
			cityList.add("嘉义");
		} else if (province.equals("新疆")) {
			cityList.add("乌鲁木齐");
			cityList.add("克拉玛依");
			cityList.add("吐鲁番");
			cityList.add("哈密");
			cityList.add("和田");
			cityList.add("阿克苏");
			cityList.add("喀什");
			cityList.add("克孜勒苏柯尔克孜");
			cityList.add("巴音郭楞蒙古");
			cityList.add("昌吉");
			cityList.add("博尔塔拉蒙古");
			cityList.add("伊犁哈萨克");
			cityList.add("塔城");
			cityList.add("阿勒泰");
		} else if (province.equals("西藏")) {
			cityList.add("拉萨");
			cityList.add("昌都");
			cityList.add("山南");
			cityList.add("日喀则");
			cityList.add("那曲");
			cityList.add("阿里");
			cityList.add("林芝");
		} else if (province.equals("云南")) {
			cityList.add("昆明");
			cityList.add("曲靖");
			cityList.add("玉溪");
			cityList.add("保山");
			cityList.add("昭通");
			cityList.add("丽江");
			cityList.add("普洱");
			cityList.add("临沧");
			cityList.add("文山");
			cityList.add("红河");
			cityList.add("西双版纳");
			cityList.add("楚雄");
			cityList.add("大理");
			cityList.add("德宏");
			cityList.add("怒江");
			cityList.add("迪庆");
		} else if (province.equals("浙江")) {
			cityList.add("杭州");
			cityList.add("宁波");
			cityList.add("温州");
			cityList.add("嘉兴");
			cityList.add("湖州");
			cityList.add("绍兴");
			cityList.add("金华");
			cityList.add("衢州");
			cityList.add("舟山");
			cityList.add("台州");
			cityList.add("丽水");
		}

			return this.cityList;
	}
}
