package com.sxt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.pojo.Role;
import com.sxt.service.IRoleService;

/**
 * 角色 控制层
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    //@Autowired
    @Resource
    private IRoleService roleService;
    
    
    /*
     * 查询
     */
    @RequestMapping("/query")
    //@ResponseBody
    public String query(Role role,Model model){
        List<Role> list = roleService.query(role);
//        for (Role role2 : list) {
//			System.out.println(role2.toString());
//		}
        model.addAttribute("list", list);
        return "role/role";
    }
   
}