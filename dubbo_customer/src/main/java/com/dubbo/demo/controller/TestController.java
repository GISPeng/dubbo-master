package com.dubbo.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dubbo.demo.service.TestService;
import com.gooagoo.common.utils.JsonUtils;
import com.spring.dynamicservice.TestBeanService;
import com.spring.model.Coupon;
import com.spring.model.CouponVO;
import com.spring.model.Customer;
import com.spring.utils.SpringBeanUtils;

@Controller
public class TestController {
	
	private final Logger rrfareLogger = LoggerFactory.getLogger("rrfarelog");
	private final Logger gagLogger = LoggerFactory.getLogger("gaglog");
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("/common")
	@ResponseBody
	public String testUrlRewrite(String method,String paramWithChinese){
		// 测试中文问题
		System.out.println(method);
		rrfareLogger.info("这是info日志信息");
		gagLogger.info(paramWithChinese);
		gagLogger.warn("这是warn日志信息");
		return "中文乱码";
	}
	
	
	
	/**
	 * 测试urlRewrite策略
	 * @param method
	 * @return
	 */
	@RequestMapping("/marketingActivity")
	public String testUrlRewrite2(String method){
		System.out.println(method);
		return "login";
	}
	
	/**
	 * 测试dubbo接口是否可用
	 * @return
	 */
	@RequestMapping("/testDubbo")
	@ResponseBody
	public String sayDubbo(){
		String str = testService.testSayDubbo();
		return str;
	}

	/**
	 * 测试根据前台传递BeanName动态获取目标Bean（从Spring容器中）
	 * @param beanName
	 * @return
	 */
	@RequestMapping("/springbeantest")
	@ResponseBody
	public String exeBean(String beanName){
		TestBeanService testBeanService = SpringBeanUtils.getBean(beanName,TestBeanService.class);
		String str = testBeanService.printMsg();
		return str;
	}
	
	/**
	 * 测试前台表单参数包含中文，后台如何正确接收
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/doPost")
	@ResponseBody
	public String getPostParams(String username,String password){
		System.out.println(username);
		System.out.println(password);
		return "POST表单返回参数";
	}
	

	/**
	 * 测试BeanCopy & clone
	 * @return
	 */
	@RequestMapping("/beanUtilsCopyProperties")
	@ResponseBody
	public String doCopy(){
		Customer customer01 = new Customer("liu", 29);
		Customer customer02 = new Customer("wang",28);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer01);
		customerList.add(customer02);
		CouponVO couponVo = new CouponVO();
		couponVo.setCouponId("001");
		couponVo.setShopEntityId("shop001");
		couponVo.setSourceId(100);
		couponVo.setCustomers(customerList);
		// 序列化为json并打印
		System.out.println(JsonUtils.toJson(couponVo));
		
		// 测试clone
		// 注意父类Object的clone方法是protected，在外部无法访问
		// 所以必须在子类中重写clone，并在内部调用父类的clone方法
		// 注意子类必须实现Cloneable接口
		CouponVO couponVoClone = couponVo.clone();
		couponVoClone.setCouponId("002");
		System.out.println(JsonUtils.toJson(couponVo));
		System.out.println(JsonUtils.toJson(couponVoClone));
				
		// 测试BeanUtilsCopyProperties
		Coupon coupon = new Coupon();
		BeanUtils.copyProperties(couponVo, coupon);
		System.out.println(JsonUtils.toJson(coupon));
		
		
		return "copy成功";
	}
}
