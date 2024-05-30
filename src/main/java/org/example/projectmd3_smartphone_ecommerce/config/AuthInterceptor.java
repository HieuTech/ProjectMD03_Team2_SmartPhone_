package org.example.projectmd3_smartphone_ecommerce.config;

import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.AuthenService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
public class AuthInterceptor implements HandlerInterceptor {
    private AuthenService authenService;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession httpSession = request.getSession();
//        AuthenResponse authenResponse = (AuthenResponse) httpSession.getAttribute("userLogin");
//        if (authenResponse == null) {
//            response.sendRedirect("/auth/login");
//        } else {
//            Users userLogin = authenService.findById(authenResponse.getUserId());
//            List<UserRoles> userRoles = userLogin.getUserRoles();
//            for (UserRoles userRole : userRoles) {
//                if (userRole.getRole().getRoleName().equals("ADMIN")) {
//                    return true;
//                }
//            }
//            response.sendRedirect("/403");
//        }
//        return false;
//    }
}