package com.andromeda.autismhelper.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class Category{

	@SerializedName("categoryname")
	private String category;

	@SerializedName("items")
	private List<Item> items;

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setItems(List<Item> items){
		this.items = items;
	}

	public List<Item> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Category{" + 
			"category = '" + category + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}