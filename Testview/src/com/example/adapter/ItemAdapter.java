package com.example.adapter;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.Book;
import com.example.testview.R;

public class ItemAdapter extends BaseAdapter {

	private Activity m_act = null;
	private LinkedList<Book> m_data = null;
	
	public ItemAdapter(Activity _act){
		m_act = _act;
		m_data = new LinkedList<Book>();
	}
	
	public void fakeData(LinkedList<Book> data_){
		m_data.clear();
		for(int i=0;i<data_.size();i++)
			m_data.add(data_.get(i));
		notifyDataSetChanged();
		
	}
	
	@Override
	public int getCount() {
		return m_data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return m_data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {  
	       ViewHolder holder;  
	       if (convertView == null) {  
	           final LayoutInflater inflater = (LayoutInflater) m_act  
	                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	          convertView = inflater.inflate(R.layout.listitem, null);  
	          holder = new ViewHolder(convertView); 
	          convertView.setTag(holder);  
	      } else {  
	          holder = (ViewHolder) convertView.getTag();  
	      } 
	       holder.setData(m_data.get(arg0));
	       return convertView; 
	}
	
	private class ViewHolder {
		private TextView m_tv;
		private TextView m_tv2;
		
		public ViewHolder(View _view){
			m_tv = (TextView)_view.findViewById(R.id.ItemTitle);
			m_tv2 = (TextView)_view.findViewById(R.id.ItemText);
		}
		
		public void setData(Book _data) {
			System.out.println(_data.getBookName()+"   "+_data.getAuthor()+"  "+_data.getISBN());
			m_tv.setText(_data.getBookName().toString());
			m_tv2.setText(_data.getAuthor().toString());
		}
	}
	
	public void getbooklist(LinkedList<Book> bl){
		m_data = bl;
	}
}
