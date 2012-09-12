package com.example.wallmobile;

import java.util.Vector;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class StatusUpdates extends ListActivity {
	Vector<Vector<String>> all_msgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> list = new ArrayAdapter<String>(this,
				R.layout.list_item);
		all_msgs = FetchData.createList();
		for (Vector<String> link : all_msgs) {
			list.add(link.get(0));
		}
		setListAdapter(list);
		ListView lv = getListView();
		final LinearLayout layout = new LinearLayout(this);
		LayoutInflater inflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final PopupWindow pw = new PopupWindow(inflater.inflate(
				R.layout.popup_example, null, false), 100, 100, true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
				 Toast.makeText(getApplicationContext(),
				 all_msgs.get(position).get(1), Toast.LENGTH_SHORT).show();
			}
		});

	}

}
