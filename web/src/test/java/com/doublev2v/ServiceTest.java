package com.doublev2v;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.doublev2v.integralmall.merchandise.Merchandise;
import com.doublev2v.integralmall.merchandise.MerchandiseService;
import com.doublev2v.integralmall.order.IntegralOrderRepository;
import com.doublev2v.integralmall.util.Constant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-shiro-test.xml",
									"classpath:applicationContext.xml"})
public class ServiceTest {
	@Autowired
	private DefaultSecurityManager securityManager;//注入securityManager
	@Autowired
	private IntegralOrderRepository r;
	@Autowired
	private MerchandiseService m;
	
	@Before
	public void init(){
		//设置securityManager
		SecurityUtils.setSecurityManager(securityManager);
	}
	@Test
	public void test(){
		Pageable page=new PageRequest(1, 12);
		Page<Merchandise> list=m.findPage(page,Constant.VIRTUAL, null, null,null);
		for(Merchandise m:list.getContent()){
			System.out.println(m);
		}
	}
}
