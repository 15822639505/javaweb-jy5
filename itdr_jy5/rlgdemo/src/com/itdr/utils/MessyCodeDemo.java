package com.itdr.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

    @WebFilter(filterName = "MessyCodeDemo", value = "/manage/user/*")
    public class MessyCodeDemo implements Filter {
        public void destroy() {
        }

        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
            //处理乱码
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            chain.doFilter(req, resp);
        }

    public void init(FilterConfig config) throws ServletException {

    }

}
