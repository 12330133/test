package com.example.entity;

import java.io.Serializable;

import android.util.Log;

public class LendList implements Serializable {
	public String _id;// same as book.get_ID() as a primary key
	public String bookID;
	public String bookName;
	public String belongerID;
	public String lenderID;
	public Boolean isconfirm;
	
	public LendList(){}
	public LendList(String id_,String bookid,String bn, String belongerID_,String lenderID_,Boolean isconfirm_)
	{
		_id = id_;
		bookID = bookid;
		bookName = bn;
		belongerID = belongerID_;
		lenderID = lenderID_;
		isconfirm = isconfirm_;
	}
	public LendList(LendList otherlendlist)
	{
		_id = otherlendlist.get_ID();
		bookID = otherlendlist.getBookID();
		bookName = otherlendlist.getBookName();
		belongerID = otherlendlist.getBelongerID();
		lenderID= otherlendlist.getLenderID();
		isconfirm = otherlendlist.getIsConfirm();
	}
	public void setBookID(String bookid)
	{
		bookID = bookid;
	}
	public String getBookID()
	{
		return bookID;
	}
	public String getBookName()
	{
		return bookName;
	}
	public void setBookName(String bn)
	{
		bookName = bn;
	}
	public String getBelongerID() {
		// TODO Auto-generated method stub
		return belongerID;
	}
	public String getLenderID() {
		// TODO Auto-generated method stub
		return lenderID;
	}
	public String get_ID() {
		// TODO Auto-generated method stub
		return _id;
	}
	public void set_ID(String string) {
		_id = string;
		// TODO Auto-generated method stub
		
	}
	public void setBelongerID(String string) {
		belongerID = string;
		// TODO Auto-generated method stub
		
	}
	public void setLenderID(String string) {
		lenderID = string;
		// TODO Auto-generated method stub
	}
	public void setIsConfirm(String bool)
	{
		if(bool.equals("true"))
			isconfirm = true;
		else
			isconfirm = false;
	}
	public Boolean getIsConfirm()
	{
		return isconfirm;
	}
	public void print()
	{
		Log.d("test","_id: "+_id);
		Log.d("test","bookName: "+bookName);
		Log.d("test","belongerID: "+belongerID);
		Log.d("test","lenderID: "+lenderID);
		Log.d("test","iscomfirm: "+isconfirm);
		
	}

}
