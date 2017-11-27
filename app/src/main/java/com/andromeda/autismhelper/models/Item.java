package com.andromeda.autismhelper.models;

import com.google.gson.annotations.SerializedName;
public class Item{

	@SerializedName("itemname")
	private String itemname;

	@SerializedName("url")
	private String url;

	public void setItemname(String itemname){
		this.itemname = itemname;
	}

	public String getItemname(){
		return itemname;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Item{" + 
			"itemname = '" + itemname + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}