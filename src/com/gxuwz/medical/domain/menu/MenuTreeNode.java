package com.gxuwz.medical.domain.menu;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 定义与zTree的节点对象有相同属性的Node类
 * @author demo
 *
 */
public class MenuTreeNode {
	
	private String id;
	private String pid;
	private String name;
	@JSONField(serialize=false)
	private List<MenuTreeNode>children;
	private boolean checked;
    private int level;
    private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	private boolean open;
	
	public MenuTreeNode(Menu menu){
		this.id =menu.getMenuid();
		this.name =menu.getMenuname();
		this.pid =menu.getMenupid();
		this.open=true;
		this.level =menu.getLevel();
		this.url =menu.getUrl();
		
	}
	public MenuTreeNode(String id, String name, List<MenuTreeNode> children) {
		super();
		this.id = id;
		this.name = name;
		this.children = children;
	}
	
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MenuTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeNode> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	 
	

}
