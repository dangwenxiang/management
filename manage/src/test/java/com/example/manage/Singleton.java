/**
 * @program: pangolin
 * @author: dwx
 * @create: 2019-08-28 11:29
 **/
public class Singleton {
    //手写单例模式
    //饿汉模式
    private static Singleton singleton = new Singleton();

    public Singleton() {
    }

    public static Singleton getSingleton() {
        return singleton;

    }

    //懒汉模式
    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //双重检查
    private static volatile Singleton doubleCheck = null;

    public static Singleton getDoubleCheck() {
        if (doubleCheck == null) {
            synchronized (Singleton.class) {
                if (doubleCheck == null) {
                    doubleCheck = new Singleton();
                }
            }
        }
        return doubleCheck;
    }
}
