package com.doublev2v.integralmall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doublev2v.foundation.core.service.PagingService;
import com.doublev2v.integralmall.auth.role.RoleService;
import com.doublev2v.integralmall.auth.user.UserDtoService;
import com.doublev2v.integralmall.auth.user.dto.UserDto;
import com.doublev2v.integralmall.util.RequestResult;

@Controller
@RequestMapping("/admin/user")
public class UserController extends CommonController<UserDto> {
	
	@Autowired
	private UserDtoService service;
	@Autowired
	private RoleService roleService;
	@Override
	protected PagingService<UserDto, String> getService() {
		return service;
	}

	@Override
	protected String getBasePath() {
		return "admin/user/";
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ModelAndView add(UserDto t) {
		service.add(t);
		return index();
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView add() {
		String viewPath=getBasePath()+"add";
		ModelAndView view=new ModelAndView(viewPath);
		view.addObject("roles", roleService.findAll());
		return loadAuths(view);
	}
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id) {
		String viewPath=getBasePath()+"edit";
		ModelAndView view=new ModelAndView(viewPath);
		view.addObject("t", service.findOne(id));
		view.addObject("roles", roleService.findAll());
		return loadAuths(view);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ModelAndView info(@PathVariable String id) {
		String viewPath=getBasePath()+"info";
		ModelAndView view=new ModelAndView(viewPath);
		view.addObject("t", service.findOne(id));
		return loadAuths(view);
	}
	@RequestMapping(value="/getlistdata",method=RequestMethod.GET)
	@ResponseBody
	public String getUsers(@RequestParam(defaultValue="1") Integer page, @RequestParam(defaultValue="12") Integer size) {
		return RequestResult.success(service.findPage(page, size)).toJson();
	}
}