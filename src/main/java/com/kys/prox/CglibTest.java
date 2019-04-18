package com.kys.prox;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {
	public static void main(String[] args) {
		// ���ڽӿڵĵ���
		/*UserService userService = new UserServiceImpl();
		UserService userServiceProxy = (UserService) new CglibProxyFactory().getProxyInstance(userService);
		userServiceProxy.addUser();*/
		
		// ������Ĵ���
		UserAddress target = new UserAddress();
		UserAddress userAddressProxy = (UserAddress) new CglibProxyFactory().getProxyInstance(target);
		userAddressProxy.getAddressInfo(1,"zhangsan");
		userAddressProxy.getUserInfo(55);
	}
}

// Cglib��������
class CglibProxyFactory implements MethodInterceptor {
	// ���������
	private Object target;
	
	/**
	 * �������������
	 * Enhancer����CGLib�е�һ���ֽ�����ǿ���������Է���Ķ�����Ҫ������������չ
	 */
	public Object getProxyInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer(); // ������
		// ���ø���
		enhancer.setSuperclass(this.target.getClass());
		// ���ûص�����
		enhancer.setCallback(this);
		return enhancer.create(); // ��������
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("����ʼ....");
		//Object invoke = method.invoke(this.target, args);
		//Object invoke1 = proxy.invoke(target, args);
		Object invoke = proxy.invokeSuper(obj, args);
		System.out.println("�������....");
		return invoke;
	}
}


