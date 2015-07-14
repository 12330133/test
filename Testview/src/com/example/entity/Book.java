package com.example.entity;

import java.io.Serializable;

import android.util.Log;

public class Book implements Serializable{
	private String _id;
	private String bookName;
	private String ISBN;
	private String belongerID;
	private String author;
	private String info;
	private boolean islend;
	public Book(){}
	public Book(String bn,String i,String a,String im,boolean is,String be){
		bookName = bn;
		ISBN = i;
		belongerID = be;
		author = a;
		info = im;
		islend = is;
	}
	public Book(Book book){
		_id = book._id;
		bookName = book.bookName;
		ISBN = book.ISBN;
		belongerID = book.belongerID;
		author = book.author;
		info = book.info;
		islend = book.islend;
	}
	
	public void setBookName(String a){
		bookName = a;
	}
	
	public String getBookName(){
		return bookName;
	}
	
	public void setISBN(String a){
		ISBN = a;
	}
	
	public String getISBN(){
		return ISBN;
	}
	
	public void setAuthor(String a){
		author = a;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setBelongerID(String i){
		belongerID=i+"";
	}
	
	public String getBelongerID(){
		return belongerID;
	}
	
	public void setInfo(String a){
		info = a;
	}
	
	public String getInfo(){
		return info;
	}
	
	public void setIsLend(String bool){
		if(bool.equals("true"))
			islend=true;
		else
			islend = false;
	}
	
	public boolean getIsLend(){
		return islend;
	}

	public String get_ID() {
		// TODO Auto-generated method stub
		return _id;
	}
	public void set_ID(String string) {
		_id = string;
		// TODO Auto-generated method stub
		
	}
	public void print ()
	{
		Log.d("test","----start print book----");
		Log.d("test",get_ID());
		Log.d("test",getBookName());
		Log.d("test",getISBN());
		Log.d("test",getAuthor());
		Log.d("test",getBelongerID());
		Log.d("test",getInfo());
		Log.d("test",String.valueOf(getIsLend()));
		Log.d("test","----end print book----");
	}
}
