package com.demo.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
        connector.setURIEncoding("UTF-8");

        //WAS에서 접근하는 webapp 폴더 경로 지정
        tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());

        tomcat.setPort(8080);
        tomcat.start();
        tomcat.getServer().await();
    }
}
