package com.gxuwz.medical.domain.vo;
/**
 * 自动填充组件填充DTO数据模型
 * @author 演示
 *
 */
public class AutocompleteModel {

	private String itemId;
	private String itemName;
	private String otherName;
	
	public AutocompleteModel(){
		
	}

	public AutocompleteModel(String itemId, String itemName, String otherName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.otherName = otherName;
	}

	public AutocompleteModel(String itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
}
