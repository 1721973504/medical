package com.gxuwz.medical.domain.area;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 行政区域树Node节点
 * @author 演示
 *
 */
public class AreaTreeNode {

	private String id;
	private String pid;
	private String name;
	@JSONField(serialize=false)
	private List<AreaTreeNode>children;
	private boolean checked;
    private int level;
    private String url;
	private boolean open;
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
	public List<AreaTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<AreaTreeNode> children) {
		this.children = children;
	}
	public AreaTreeNode(Area area){
		this.id =area.getAreacode();
		this.name =area.getAreaname();
		this.pid =area.getParent().getAreacode();
		this.open=true;
		
	}
	public AreaTreeNode(String id, String name, List<AreaTreeNode> children) {
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
