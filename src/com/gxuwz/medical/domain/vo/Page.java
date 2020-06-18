package com.gxuwz.medical.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.gxuwz.medical.config.Constant;
/**
 * 
 * @author demo
 *
 */
public class Page {

	private int total;
	private int pages;
	
	private int pageNo;
	private int limit;
	private List datas;

	public Page(int total, int pageNo, int limit, List datas) {
		super();
		this.total = total;
		this.pageNo = pageNo;
		this.limit = limit;
		this.datas = datas;
	
		if(this.total%this.limit==0){
			this.pages=this.total/this.limit;
		}else{
			this.pages=(this.total/this.limit)+1;
		}
		
	}
	
	public List getDatas() {
		return datas;
	}	
	
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * 是否有下一页
	 * @return
	 */
	public boolean hasNext() {

		return this.pageNo<this.pages;
	}
   /**
    * 是否有上一页
    * @return
    */
	public boolean hasPre() {

		return (this.pageNo-1)>=1;
	}
	public int prePage(){
		return (this.pageNo-1);
	}
	public int lastPage(){
		return (this.pageNo+1);
	}
    /**
     * 是否第一页
     * @return
     */
	public boolean isFirst() {
		return (this.pageNo==1);
	}
	 /**
     * 是否最后一页
     * @return
     */
	public boolean isLast() {
		return (this.pageNo==this.pages);
	}
	
	public boolean isCurrent(int p) {
		return (this.pageNo==p);
	}
	/**
	 * 生成分页数
	 * @return
	 */
	public  List<Integer> linkLumbers() {
		int avg = Constant.PAGED / 2;
		int start = this.pageNo - avg;
		if (start <= 0) {
			start = 1;
		}
		int end = start + Constant.PAGED - 1;
		if (end > this.pages) {
			end = this.pages;
		}
		
		if ((end - start) <Constant.PAGED) {
			start = end - Constant.PAGED;
			start=start<=0?1:start;
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = start; i <= end; i++) {
			result.add(new Integer(i));
		}
		return result;
	}

}
