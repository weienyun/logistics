package com.sxt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxt.pojo.BasicData;
import com.sxt.service.IBasicService;

@Controller
@RequestMapping("/basic")
public class BasicController {
	
	@Resource
	private IBasicService basicService;
	/**
	 * 查询基础数据
	 * @param model
	 * @param bd
	 * @return
	 */
	@RequestMapping("/query")
	public String queryBasicData(Model model,BasicData bd){
		List<BasicData> list = basicService.query(bd);
		model.addAttribute("list",list);
		return "basic/basic";
	}
	/**
	 * 添加修改基础数据跳转到信息页面
	 * @return
	 */
	@RequestMapping("/goAddOrUpdate")
	public String goAddOrUpdate(Integer baseId ,Model model){
		if (baseId!=null && baseId>0) {
			BasicData bd = basicService.queryById(baseId);
			model.addAttribute("basic",bd);
		}
		List<BasicData> parents=basicService.queryParents();
		model.addAttribute("parents",parents);
		return "basic/updateBasic";
	}
	@RequestMapping("/saveOrUpdate")
	public String addOrUpdate(BasicData bd,Integer baseId){
		//baseId!=null && baseId>0则为修改
		if (bd.getParentId()==0) {
			bd.setParentId(null);
		}
		if (baseId!=null && baseId>0) {
			basicService.updateBasicData(bd);
		}else{
			basicService.addBasicData(bd);
		}
		return "redirect:/basic/query";
	}
	@RequestMapping("/deleteBasic")
	public String deleteBasic(Integer baseId){
		basicService.deleteBasicData(baseId);
		return "redirect:/basic/query";
	}
}
