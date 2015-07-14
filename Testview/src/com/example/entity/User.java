package com.example.entity;

import java.io.Serializable;

import android.util.Log;

public class User implements Serializable{
	private String _id;
	private String password;
	private String name;
	private String school;
	private String profession;
	private String room;
	private String house;
	private String email;
	private String phone;
	public User(){
		_id="";
		password="";
		name = "";
		school ="";
		profession="";
		room = "";
		house = "";
		email = "";
		phone = "";
	}
	public User(String s,String p,String n,String sc,String pr,String r,String h,String e,String ph){
		_id=s;
		password=p;
		name = n;
		school = sc;
		profession=pr;
		room = r;
		house = h;
		email = e;
		phone = ph;
	}
	public User(User u)
	{
		_id=u.get_ID();
		password=u.getPassword();
		name = u.getName();
		school = u.getSchool();
		profession = u.getProfession();
		room = u.getRoom();
		house=u.getHouse();
		email=u.getEmail();
		phone=u.getPhone();
	}
	public void UserCopy(User u){
		_id=u.get_ID();
		password=u.getPassword();
		name = u.getName();
		school = u.getSchool();
		profession = u.getProfession();
		room = u.getRoom();
		house=u.getHouse();
		email=u.getEmail();
		phone=u.getPhone();
	}
	

	
	public void setPassword(String pw_){
		password = pw_;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setName(String name_){
		name=name_;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSchool(String school_){
		school = school_;
	}
	
	public String getSchool(){
		return school;
	}
	
	public void setProfession(String profession_){
		profession=profession_;
	}
	
	public String getProfession(){
		return profession;
	}
	
	public void setRoom(String room_){
		room = room_+"";
	}
	
	public String getRoom(){
		return room;
	}
	
	public void setHouse(String house_){
		house=house_;
	}
	
	public String getHouse(){
		return house;
	}
	
	public void setEmail(String email_){
		email=email_;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setPhone(String phone_){
		phone=phone_;
	}
	
	public String getPhone(){
		return phone;
	}

	public String get_ID() {
		// TODO Auto-generated method stub
		return _id;
	}

	public void set_ID(String string) {
		_id = string;
		// TODO Auto-generated method stub
	}
	public void print()
	{
		Log.d("test","start print user data");
		Log.d("test",get_ID());
		Log.d("test",getPassword());
		Log.d("test",getName());
		Log.d("test",getSchool());
		Log.d("test",getProfession());
		Log.d("test",getRoom());
		Log.d("test",getHouse());
		Log.d("test",getEmail());
		Log.d("test",getPhone());
		Log.d("test","end print user data");
	}
}
