package com.office2.filter;

import com.office2.model.User;
import com.office2.service.UserService;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
        // Достаём Spring-контекст из ServletContext и получаем UserService
        ServletContext ctx = filterConfig.getServletContext();
        WebApplicationContext springCtx =
                (WebApplicationContext) ctx.getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE
                );
        this.userService = springCtx.getBean(UserService.class);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        User logged = (session != null)
                ? (User) session.getAttribute("loggedUser")
                : null;

        // Если не авторизован через сессию — пробуем cookie
        if (logged == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("rememberMe".equals(c.getName())) {
                        String username = c.getValue();
                        User u = userService.findByUsername(username);
                        if (u != null) {
                            // восстановили пользователя в новую сессию
                            request.getSession(true).setAttribute("loggedUser", u);
                            logged = u;
                        }
                        break;
                    }
                }
            }
        }

        // Если всё ещё нет авторизации — на /login
        if (logged == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // В остальном пропускаем
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // нет ресурсов для очистки
    }
}
