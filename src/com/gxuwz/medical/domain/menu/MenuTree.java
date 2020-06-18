package com.gxuwz.medical.domain.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * 菜单树组件
 * @author 演示
 *
 */
public class MenuTree {

	private List<MenuTreeNode> nodeList;

	/**
	 * 构造根节点为id 菜单树
	 * 
	 * @param id
	 */
	public MenuTree(String id) throws Exception {
		nodeList = new ArrayList<MenuTreeNode>();
		Menu menu = new Menu(id);
		List<Menu> menuList = menu.children();
		for (Menu m : menuList) {
			nodeList.add(tree(m, true));
		}
		
	}
	/**
	 * 构造带角色的权限树
	 * @param id
	 * @param roleid
	 * @throws Exception
	 */
	public MenuTree(String id,String roleid) throws Exception {
		nodeList = new ArrayList<MenuTreeNode>();
		Menu menu = new Menu(id);
		List<Menu> menuList = menu.children();
		for (Menu m : menuList) {
			nodeList.add(tree(m,roleid, true));
		}
		
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public void visit(MenuTreeNode node,List<MenuTreeNode>nodes){
		List<MenuTreeNode>children=node.getChildren();
		nodes.add(node);
		if(children!=null&&!children.isEmpty()){
			for(MenuTreeNode e:node.getChildren()){
				visit(e,nodes);
			}
		}
	}
	
	public List<MenuTreeNode>getChildNodeList(){
		List<MenuTreeNode> allNodes=new ArrayList<MenuTreeNode>();
		for(MenuTreeNode node:this.nodeList){
		
			visit(node, allNodes);
		}
		return allNodes;			
	}
	/**
	 * 生成权限树
	 * 
	 * @param id
	 */
	public MenuTreeNode tree(Menu menu, String roleid,boolean recursive) throws Exception {
		try {

			MenuTreeNode node = new MenuTreeNode(menu);
			List<Menu> subMenuList = menu.children();
			if (!subMenuList.isEmpty()) {
				if (recursive) {// 递归查询子节点
					List<MenuTreeNode> children = new ArrayList<MenuTreeNode>();
					for (Menu m : subMenuList) {
						
						MenuTreeNode t = tree(m,roleid, true);
						//如果角色包含该菜单则默认选中
						if(roleid!=null){
						   t.setChecked(m.hasRole(roleid));
						}
						children.add(t);
					}
					node.setChildren(children);
				}
			}
			return node;
		} catch (Exception e) {
			throw new Exception("recursive create the node of tree failed!", e);
		}
	}
	/**
	 * 生成权限树
	 * 
	 * @param id
	 */
	public MenuTreeNode tree(Menu menu,boolean recursive) throws Exception {
		try {

			MenuTreeNode node = new MenuTreeNode(menu);
			List<Menu> subMenuList = menu.children();
			if (!subMenuList.isEmpty()) {
				if (recursive) {// 递归查询子节点
					List<MenuTreeNode> children = new ArrayList<MenuTreeNode>();
					for (Menu m : subMenuList) {
						MenuTreeNode t = tree(m,null, true);
						children.add(t);
					}
					node.setChildren(children);
				}
			}
			return node;
		} catch (Exception e) {
			throw new Exception("recursive create the node of tree failed!", e);
		}
	}

	public List<MenuTreeNode> getNodeList() {
		return nodeList;
	}
}
