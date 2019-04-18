package com.kys.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.kys.service.UserService;
import com.kys.service.UserServiceImpl;

public class JDKProxy {
	public static void main(String[] args) {
		// 调用代理后的对象
		UserService proxyService = (UserService) new ProxyFactory().getInstance(new UserServiceImpl());
		int count = proxyService.update(1, "张三");
		System.out.println("返回结果："+count);
	}
}

/**
 * 代理工厂类
 */
class ProxyFactory {
	// 被代理对象
	private Object target;
	
	public Object getInstance(Object target) {
		// 赋值代理类
		this.target = target;
		
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("代理开始。。。");
				Object invoke = method.invoke(target, args);
				System.out.println("代理结束。。。");
				return invoke;
			}
		});
	}
	
}