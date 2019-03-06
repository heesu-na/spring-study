//package com.demo.test.user.interceptor;
//
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class UserInterceptor extends HandlerInterceptorAdapter {
//    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        if(request.getSession().getAttribute("id") == null){
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
//    }
//}
