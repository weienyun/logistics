package com.sxt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxt.mapper.BasicDataMapper;
import com.sxt.pojo.BasicData;
import com.sxt.pojo.BasicDataExample;
import com.sxt.service.IBasicService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class BasicServiceImpl implements IBasicService{
	@Resource
	private BasicDataMapper mapper;
	@Resource
	private Jedis jedis;
	
	private static final String key_Prefix = "basciData:";
	
	@Override
	public List<BasicData> query(BasicData bd) {
		// TODO Auto-generated method stub
		//查看jedis缓存是否有  （basicData2 baseId 2 parentId 1 basename 开发部  basedesc 开发部）
		Set<String> keys = jedis.keys(key_Prefix+"*");
		List<BasicData> list = new ArrayList<BasicData>();
		if (keys!=null && keys.size()>0) {
			//从缓存拿并加入list
			for (String key : keys) {
				BasicData data = new BasicData();
				data.setBaseId(Integer.parseInt(jedis.hget(key, "baseId")));
				data.setParentId(parseStringtoInteger(jedis.hget(key, "parentId")));
				data.setBaseName(jedis.hget(key,"baseName"));
				data.setBaseDesc(jedis.hget(key, "baseDesc"));
				list.add(data);
			}
		}else{
			//表示缓存没数据从数据库拿并添加
			BasicDataExample example = new BasicDataExample();
			list = mapper.selectByExample(example);
			//将数据添加到redis
			for (BasicData data : list) {
				jedis.hset(key_Prefix+data.getBaseId(),"baseId", data.getBaseId()+"");
				jedis.hset(key_Prefix+data.getBaseId(),"parentId", data.getParentId()+"");
				jedis.hset(key_Prefix+data.getBaseId(),"baseName", data.getBaseName());
				jedis.hset(key_Prefix+data.getBaseId(),"baseDesc", data.getBaseDesc()+"");
			}
		}
		return list;
	}

	private Integer parseStringtoInteger(String parentId) {
		try {
			return Integer.parseInt(parentId);
		} catch (Exception e) {
			System.out.println("转换失败 parentId为空");
		}
		return null;
	}

	@Override
	public void addBasicData(BasicData bd) {
		// TODO Auto-generated method stub
		//清空缓存
		this.clearRedis(key_Prefix);
		//添加
		mapper.insert(bd);
	}

	@Override
	public void deleteBasicData(int id) {
		// TODO Auto-generated method stub
		//清空缓存
		this.clearRedis(key_Prefix);
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateBasicData(BasicData bd) {
		// TODO Auto-generated method stub
		//清空缓存
		this.clearRedis(key_Prefix);
		mapper.updateByPrimaryKeySelective(bd);
	}

	@Override
	public BasicData queryById(Integer baseId) {
		// TODO Auto-generated method stub
		//先从缓存中拿
		String key = key_Prefix+baseId;
		//判断key是否存在
		Boolean bool = jedis.exists(key);
		BasicData data = new BasicData();
		if (bool) {
			//存在
			data.setBaseId(Integer.parseInt(jedis.hget(key, "baseId")));
			if ("".equals(jedis.hget(key, "parentId"))) {
				data.setParentId(null);
			}else{
				data.setParentId(Integer.parseInt(jedis.hget(key, "parentId")));
			}
			data.setBaseName(jedis.hget(key,"baseName"));
			data.setBaseDesc(jedis.hget(key, "baseDesc"));
		}else{
			//不存在
			data = mapper.selectByPrimaryKey(baseId);
		}
		return data;
	}

	@Override
	public List<BasicData> queryParents() {
		// TODO Auto-generated method stub
		BasicDataExample example =new BasicDataExample();
		example.createCriteria().andParentIdIsNull();
		return mapper.selectByExample(example);
	}
	/*
	 * 清空缓存
	 */
	public void clearRedis(String keyPrefix){
		//获取所有key keys basicData*
		Set<String> keys = jedis.keys(keyPrefix+"*");
		for (String key : keys) {
			jedis.del(key);
		}
	}
	
}
