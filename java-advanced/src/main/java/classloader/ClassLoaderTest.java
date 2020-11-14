package classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.DriverManager;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        String rootDir = "/Users/lvbing/classloader/";
        String className = "classloader.Animal";
//        myClassLoaderTest(rootDir, className);
//        myURLClassLoaderTest(rootDir, className);
        mathTest(rootDir);
    }

    private static void myClassLoaderTest(String rootDir, String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //新建一个类加载器
        MyClassLoader cl = new MyClassLoader(rootDir);
        //加载类，得到Class对象
        Class<?> clazz = cl.loadClass(className);
        //得到类的实例
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println("loadClass->hashCode:" + clazz.hashCode());
    }

    private static void myURLClassLoaderTest(String rootDir, String className) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //创建自定义文件类加载器
        File file = new File(rootDir);
        //File to URI
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};

        MyURLClassLoader loader = new MyURLClassLoader(urls);

        //加载指定的class文件
        Class<?> clazz = loader.loadClass(className);
        //得到类的实例
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println("loadClass->hashCode:" + clazz.hashCode());
    }

    private static void mathTest(String rootDir) throws IllegalAccessException, InstantiationException {
        String className = "java.lang.Math";
        //新建一个类加载器
        MyClassLoader cl = new MyClassLoader(rootDir);
        //注意这里用的findClass方法，为了避开缓存
        Class<?> clazz = cl.findClass(className);
        //得到类的实例
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
        System.out.println("loadClass->hashCode:" + clazz.hashCode());
    }
}