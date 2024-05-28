package org.example.projectmd3_smartphone_ecommerce.config;

import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    private SessionFactory sessionFactory;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        Users userLogin = (Users) httpSession.getAttribute("userLogin");
        if (userLogin==null){
            response.sendRedirect("/auth/login");
            return false;
        }else{
            Session session= sessionFactory.getCurrentSession();
            Roles userRole = session.createQuery("SELECT ur.role FROM UserRoles ur WHERE ur.users = :userLogin", Roles.class)
                    .setParameter("userLogin", userLogin)
                    .getSingleResult();
            if (userRole.getRoleName().equals("ADMIN")){
                return true;
            }else {
                response.sendRedirect("/403");
                return false;
            }
        }
    }
}