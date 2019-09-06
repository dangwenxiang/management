package com.example.manage;

import com.example.manage.web.UserController;


/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-09-03 16:58
 **/
public class ProxyDemo {
    //java反射就是在程序运行时，对于任意一个类，都能清楚的知道这个类的属性和方法，也可以任意的调用这个类的属性和方法。这种动态的获取信息(包括私有变量和方法)
    //动态的调用的功能称为java的反射机制。
    //产生对目标接口的一个代理
    //UserController user = Proxy.newProxyInstance();
    //所谓代理模式，是指客户端(Client)并不直接调用实际的对象(RealSubject)，而是通过调用代理(ProxySubject)，来间接的调用实际的对象。
    public static void main(String[] args) {
        UserController userController = new UserController();
        Class user = userController.getClass();
        System.out.println(user.getName());
    }


}
