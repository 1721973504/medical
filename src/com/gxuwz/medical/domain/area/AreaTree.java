package com.gxuwz.medical.domain.area;

import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.domain.area.Area;
import com.gxuwz.medical.domain.area.AreaTreeNode;
/**
 * 行政区域树
 * @author 演示
 *
 */
public class AreaTree {


	private List<AreaTreeNode> nodeList;

	/**
	 * 构造根节点为id 菜单树
	 * 
	 * @param id
	 */
	public AreaTree(String id) throws Exception {
		nodeList = new ArrayList<AreaTreeNode>();
		Area area = new Area(id);
		List<Area> areaList = area.children();
		for (Area m : areaList) {
			nodeList.add(tree(m, true));
		}
		
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	public void visit(AreaTreeNode node,List<AreaTreeNode>nodes){
		List<AreaTreeNode>children=node.getChildren();
		nodes.add(node);
		if(children!=null&&!children.isEmpty()){
			for(AreaTreeNode e:node.getChildren()){
				visit(e,nodes);
			}
		}
	}
	
	public List<AreaTreeNode>getChildNodeList(){
		List<AreaTreeNode> allNodes=new ArrayList<AreaTreeNode>();
		for(AreaTreeNode node:this.nodeList){
		
			visit(node, allNodes);
		}
		return allNodes;			
	}
	
	/**
	 * 生成权限树
	 * 
	 * @param id
	 */
	public AreaTreeNode tree(Area area,boolean recursive) throws Exception {
		try {

			AreaTreeNode node = new AreaTreeNode(area);
			List<Area> subAreaList = area.children();
			if (!subAreaList.isEmpty()) {
				if (recursive) {// 递归查询子节点
					List<AreaTreeNode> children = new ArrayList<AreaTreeNode>();
					for (Area m : subAreaList) {
						AreaTreeNode t = tree(m, true);
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

	public List<AreaTreeNode> getNodeList() {
		return nodeList;
	}


}
