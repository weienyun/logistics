package com.sxt.controller;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.sxt.pojo.Role;
import com.sxt.pojo.User;
import com.sxt.service.IUserService;
import com.sxt.vo.UserVo;

/**
 * 用户控制层
 */
@Controller
@RequestMapping("/user")
public class UserController {

	// @Autowired
	@Resource
	private IUserService userService;
	
	
	/*
	 * 查询
	 */
	@RequestMapping("/queryPage")
	// @ResponseBody
	public String queryPage(UserVo vo, Model model) {
		//pageModel 分页后的信息（userlist pageNum pageSize ...）
		PageInfo<User> pageInfo =userService.query(vo);
		model.addAttribute("pageModel", pageInfo);
		return "user/user";
	}
	/**
	 * 添加修改用户先取得roles 返回修改信息页面updateUser.jsp
	 * 如果有select==1 代表查看用户信息  userId不为空则为修改  为空添加
	 * @return
	 * @throws Exception
	 */
	@RequiresRoles("操作员")//当前登录用户需要"管理员角色才能访问"
	@RequestMapping("/goAddOrUpdate")
	public String goAddOrUpdate(Integer userId, Model model, Integer select) throws Exception {
		//userId不为空 修改用户
		if (userId!=null && userId>0) {
			User user = userService.queryById(userId);
			// 查询用户所拥有角色
			List<Role> roles = userService.queryRolesByUserId(userId);
			model.addAttribute("roles", roles);
			model.addAttribute("user", user);
			//如果有select==1 代表查看用户信息
			if (select!=null&&select == 1) {
				model.addAttribute("select", 1);
			}
		}
			List<Role> roles2 = userService.getRoles();
			model.addAttribute("list", roles2);
			return "user/updateUser";
		
	}

	/**
	 * 添加修改用户
	 * userId不为空 修改用户 为空 添加用户
	 * @return
	 * @throws Exception
	 */
	@RequiresRoles("操作员")//当前登录用户需要"管理员角色才能访问"
	@RequestMapping("/addOrUpdateUser")
	public String addOrUpdateUser(Integer userId, UserVo vo, Model model) throws Exception {
		if (userId!=null&&userId>0) {
			userService.updateUser(vo,userId);
			PageInfo<User> pageInfo = userService.query(vo);
			model.addAttribute("pageModel", pageInfo);
			return "user/user";
		}else{
			//UUID uuid = UUID.randomUUID(); uuid通用唯一识别
			int num = (int) Math.random()*1000;
			String salt = "abc"+num;
			//MD5加密 盐值 salt迭代次数3
			Md5Hash md = new Md5Hash(vo.getUser().getPassword(),salt,3);
			vo.getUser().setPassword(md.toString());
			vo.getUser().setU1(salt);
			userService.addUser(vo);
			PageInfo<User> pageInfo = userService.query(vo);
			model.addAttribute("pageModel", pageInfo);
			return "user/user";
		}
	}

	/**
	 * 根据userId删除用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequiresRoles("操作员")//当前登录用户需要"管理员角色才能访问"
	@RequestMapping("/deleteUser")
	public String updateUser(Integer[] userId, Model model) throws Exception {
		System.out.println(userId);
		for (int i = 0; i < userId.length; i++) {
			userService.deleteUser(userId[i]);
		}
		UserVo vo = new UserVo();
		PageInfo<User> pageInfo = userService.query(vo);
		model.addAttribute("pageModel", pageInfo);
		return "user/user";
	}

}