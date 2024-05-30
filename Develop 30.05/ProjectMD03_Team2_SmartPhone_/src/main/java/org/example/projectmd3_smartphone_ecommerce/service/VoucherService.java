package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.dao.impl.VoucherDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Vouchers;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class VoucherService {
    @Autowired
    private VoucherDaoImpl voucherDao;

    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;

    public boolean addNew() {
        Date startDate = new Date();
        // Use Calendar to add 3 days to the start date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date endDate = calendar.getTime();
        AuthenResponse authenResponse = (AuthenResponse) session.getAttribute("userLogin");
        return this.voucherDao.addNew(Vouchers.builder().
                code(UUID.randomUUID().toString()).
                amount(1).
                description("").
                discountPercent(5).
                status("ACTIVE")
                .startDate(startDate).endDate(endDate).createdAt(new Date())
                .users(userService.findByIdV2(authenResponse.getUserId())).
                build());
    }

    public boolean deleteVoucher(Integer voucherId) {
        return this.voucherDao.deleteVouchers(voucherId);
    }

}
