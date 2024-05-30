package org.example.projectmd3_smartphone_ecommerce.config;

import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        AuthenResponse authenResponse = (AuthenResponse) httpSession.getAttribute("userLogin");
        if (authenResponse == null) {
            response.sendRedirect("/auth/login");
        } else {
            List<Roles> userRoles = authenResponse.getRoles();
            for (Roles role:userRoles) {
                if (role.getRoleName().equals("ADMIN")) {
                    return true;
                }
            }
            response.sendRedirect("/notfound/");
        }
        return false;
    }
}