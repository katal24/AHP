package filters;


import model.User;
import sp.BaseController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dawid on 28.05.16.
 */
public class loginFilters implements Filter {
    public static User session;
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        session = (User) req.getSession().getAttribute("bean1");
        System.out.println("****************** jestem w filter:  " + session);
        if(session != null) {
            System.out.println("username:" + session.getUsername());
        }
        String url = req.getRequestURI();

//        if(session == null || !session.isLogged()){

            if(!BaseController.logged && url.indexOf("index") >=0 ){
                resp.sendRedirect(req.getServletContext().getContextPath()+ "/#/login");
            System.out.println("****************** jestem w ifie nr 1. url: "+ url);

            if(url.indexOf("#/createSurvey.html") >=0 ){
                resp.sendRedirect(req.getServletContext().getContextPath()+ "#/login.html");
            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        } else{
            if(url.indexOf("login.html")>=0 && session.isLogged()){
                resp.sendRedirect(req.getServletContext().getContextPath() + "/createSurvey.html");
            }
// else if(url.indexOf("logout.xhtml") >= 0){
//                req.getSession().removeAttribute("bean1");
//                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
//            }
            else{
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }





    }

    public void destroy() {

    }
}
