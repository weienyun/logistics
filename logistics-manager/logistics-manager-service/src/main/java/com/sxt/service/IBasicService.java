package com.sxt.service;

import java.util.List;

import com.sxt.pojo.BasicData;

public interface IBasicService {
	
	public List<BasicData> query(BasicData bd);
        
    public void addBasicData(BasicData bd);
    
    public void deleteBasicData(int id);
    
    public void updateBasicData(BasicData bd);

	public BasicData queryById(Integer baseId);
	/**
	 * 查询所有父数据
	 * @return
	 */
	public List<BasicData> queryParents();
}
