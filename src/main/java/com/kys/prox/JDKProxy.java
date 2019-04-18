package com.kys.prox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.kys.service.UserService;
import com.kys.service.UserServiceImpl;

public class JDKProxy {
	public static void main(String[] args) {
		// ���ô����Ķ���
		UserService proxyService = (UserService) new ProxyFactory().getInstance(new UserServiceImpl());
		int count = proxyService.update(1, "����");
		System.out.println("���ؽ����"+count);
	}
}

/**
 * ��������
 */
class ProxyFactory {
	// ���������
	private Object target;
	
	public Object getInstance(Object target) {
		// ��ֵ������
		this.target = target;
		
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("����ʼ������");
				Object invoke = method.invoke(target, args);
				System.out.println("�������������");
				return invoke;
			}
		});
	}
	
}