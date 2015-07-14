package com.example.adapter;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.entity.LendList;
import com.example.testview.R;

public class HistoryAdapter extends BaseAdapter{
	private Activity m_act = null;
	private LinkedList<String> m_data = null;
	
	public HistoryAdapter(Activity act){
		m_act = act;
		m_data = new LinkedList<String>();
	}
	
	public void fakedata(){
		/*
		m_data.clear();
		
		for(int i=0;i<5;i++){
			String tem = i+"---book";
			m_data.add(tem);
		}
		*/
		
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return m_data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(arg1==null){
			final LayoutInflater inflater = (LayoutInflater) m_act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arg1 = inflater.inflate(R.layout.historyitem, null);
			holder = new ViewHolder(arg1);
			arg1.setTag(holder);
		}else{
			holder = (ViewHolder)arg1.getTag();
		}
		holder.setData(m_data.get(arg0));
		return arg1;
	}
	
	private class ViewHolder {
		private TextView m_tv;
		public ViewHolder(View _view){
			m_tv = (TextView)_view.findViewById(R.id.item_bookname);
		}	
		public void setData(String _data) {
		//	System.out.println(_data.getbookname()+"   "+_data.getauthor()+"  "+_data.getisbn());
			m_tv.setText(_data);
		}
	}
	
	public void gethistory(){
		
	}
	
	public void setdata(LinkedList<LendList> data){
	//	m_data = data;
		Log.d("test","one adapter Start");
		for(LendList lendlist : data)
		{
			lendlist.print();
			String temp = lendlist.getBookName() + " isComfirm:" +String.valueOf(lendlist.getIsConfirm());
			m_data.add(temp);
		}
	}
	

}
