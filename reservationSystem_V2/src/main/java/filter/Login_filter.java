package filter;
import entities.TableTableEntity;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controllers.CustController;

import java.io.IOException;

@WebFilter(urlPatterns = {"/user_home.xhtml"})
public class Login_filter implements Filter {

    @Inject
    CustController user_login;

    public static final String LOGIN_PAGE = "/user_login.xhtml";
//    public static final String LOGIN_PAGE2 = "/login_r.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //CustController user_login = (CustController) httpServletRequest.getSession().getAttribute("CustController");

        if(user_login != null)
        {
            if(user_login.isLoggedIn())
            {
                filterChain.doFilter(servletRequest,servletResponse);
            }
            else
            {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
            }
        }
        else
        {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}