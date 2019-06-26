package com.sxt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.pojo.Role;
import com.sxt.service.IRoleService;

/**
 * 角色 控制层
 * @author 波波烤鸭
 *
 * dengpbs@163.com
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    
    @Resource
    private IRoleService roleService;

    @RequestMapping("/query")
    @ResponseBody
    public List<Role> query(Role role,Model model){
        List<Role> list = roleService.query(role);
        model.addAttribute("list", list);
        return list;
    }
}