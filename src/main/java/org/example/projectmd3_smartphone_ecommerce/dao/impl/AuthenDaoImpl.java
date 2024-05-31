package org.example.projectmd3_smartphone_ecommerce.dao.impl;

import org.example.projectmd3_smartphone_ecommerce.dao.IAuthenDao;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenEditRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.AuthenRequest;
import org.example.projectmd3_smartphone_ecommerce.dto.request.FormLogin;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Roles;
import org.example.projectmd3_smartphone_ecommerce.entity.UserRoles;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.service.CartService;
import org.example.projectmd3_smartphone_ecommerce.service.OrderService;
import org.example.projectmd3_smartphone_ecommerce.service.UploadService;
import org.example.projectmd3_smartphone_ecommerce.service.WishListService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Repository
public class AuthenDaoImpl implements IAuthenDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UploadService uploadService;

    @Override
    public List<Users> getAll() {
        return userDao.getAll(1,3);
    }

    @Override
    public Users findById(Integer id) {
        return userDao.findByIdV2(id);
    }

    @Override
    public boolean register(AuthenRequest request) {
        Users user = mapper.map(request, Users.class);
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(5)));
        if (request.getUserAvatar().getSize() > 0) {
            String avatarUrl = uploadService.uploadFileToServer(request.getUserAvatar());
            user.setAvatar(avatarUrl);
        }
        if (user.getAvatar() == null) {
            user.setAvatar("https://cdn.iconscout.com/icon/free/png-512/free-avatar-370-456322.png?f=webp&w=256");
        }
        user.setStatus(true);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setIsDeleted(false);
        userDao.addNewUser(user);
        return true;
    }

    @Override
    public boolean update(AuthenEditRequest request) {
        Users user = mapper.map(request, Users.class);
        AuthenResponse response = (AuthenResponse) httpSession.getAttribute("userLogin");
        Users userLogin = findById(response.getUserId());
        user.setId(userLogin.getId());

        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(5)));
        if (request.getUserAvatar().getSize() > 0) {
            String avatarUrl = uploadService.uploadFileToServer(request.getUserAvatar());
            user.setAvatar(avatarUrl);
        } else {
            user.setAvatar(request.getAvatar());
        }
        user.setUpdatedAt(new Date());
        user.setStatus(userLogin.getStatus());
        user.setIsDeleted(userLogin.getIsDeleted());
        user.setCreatedAt(userLogin.getCreatedAt());
        user.setGoogleAccountId(userLogin.getGoogleAccountId());
        userDao.update(user);
        return true;
    }

    @Override
    public boolean login(FormLogin formLogin) {
        try {
            Users user = userDao.getUserByEmail(formLogin.getEmail());

            if (user != null && user.getStatus()) {
                if (BCrypt.checkpw(formLogin.getPassword(), user.getPassword())) {
                    AuthenResponse authenResponse = AuthenResponse.builder().
                            email(user.getEmail())
                            .userName(user.getUserName())
                            .avatar(user.getAvatar())
                            .userId(user.getId())
                            .wishListQuantity(wishListService.findWishListByUserId(user.getId()).size())
                            .orderQuantity(orderService.findOrderByUserId(user.getId()).size())
                            .cartQuantity(cartService.findAllCartByUserId(user.getId()).size()).
                            build();
                    List<UserRoles> userRolesList=user.getUserRoles();
                    List<Roles> roles=new ArrayList<>();
                    for (UserRoles userRoles: userRolesList){
                        roles.add(userRoles.getRole());
                    }
                    authenResponse.setRoles(roles);
                    httpSession.setAttribute("userLogin", authenResponse);
                    return true;
                }
            }
            return false;
        } catch (NoResultException e) {
            return false;
        }
    }


    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override

    @Transactional
    public void block(Users user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            user.setStatus(!user.getStatus());
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> getUserList(int page, int pageSize, String keyword, String sortBy, String sortOrder) {
        return userDao.getUserList(page, pageSize, keyword, sortBy, sortOrder);
    }

    @Override
    public Integer getTotalPages(int pageSize, String keyword) {
        return userDao.getTotalPages(pageSize, keyword);
    }
    @Override
    public boolean addRole(Users users, Roles role) {
        return userDao.addRole(users, role);
    }
    @Override
    public boolean deleteRole(Integer userId, Integer roleId) {
        return userDao.deleteRole(userId,roleId);
    }
}
