package com.kys.prox;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {
	public static void main(String[] args) {
		// 基于接口的调用
		/*UserService userService = new UserServiceImpl();
		UserService userServiceProxy = (UserService) new CglibProxyFactory().getProxyInstance(userService);
		userServiceProxy.addUser();*/
		
		// 基于类的代理
		UserAddress target = new UserAddress();
		UserAddress userAddressProxy = (UserAddress) new CglibProxyFactory().getProxyInstance(target);
		userAddressProxy.getAddressInfo(1,"zhangsan");
		userAddressProxy.getUserInfo(55);
	}
}

// Cglib代理工厂类
class CglibProxyFactory implements MethodInterceptor {
	// 被代理对象
	private Object target;
	
	/**
	 * 创建代理类对象
	 * Enhancer类是CGLib中的一个字节码增强器，它可以方便的对你想要处理的类进行扩展
	 */
	public Object getProxyInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer(); // 工具类
		// 设置父类
		enhancer.setSuperclass(this.target.getClass());
		// 设置回调方法
		enhancer.setCallback(this);
		return enhancer.create(); // 返回子类
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("代理开始....");
		//Object invoke = method.invoke(this.target, args);
		//Object invoke1 = proxy.invoke(target, args);
		Object invoke = proxy.invokeSuper(obj, args);
		System.out.println("代理结束....");
		return invoke;
	}
}


