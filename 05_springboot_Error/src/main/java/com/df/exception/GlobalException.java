package com.df.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Loser
 * @date 2021年11月09日 10:44
 */
@Component
public class GlobalException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        //判断不同的异常类型,做不同的异常页面跳转
        if (ex instanceof NullPointerException){
                mv.setViewName("error1");
        }
        if (ex instanceof ArithmeticException){
                mv.setViewName("error2");
        }
        mv.addObject("msg",ex.toString());
        return mv;
    }
}
