package org.example.projectmd3_smartphone_ecommerce.service;

import org.example.projectmd3_smartphone_ecommerce.config.EmailManagement;
import org.example.projectmd3_smartphone_ecommerce.dao.impl.VoucherDaoImpl;
import org.example.projectmd3_smartphone_ecommerce.dto.response.AuthenResponse;
import org.example.projectmd3_smartphone_ecommerce.entity.Users;
import org.example.projectmd3_smartphone_ecommerce.entity.Vouchers;
import org.example.projectmd3_smartphone_ecommerce.entity.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VoucherService {
    @Autowired
    private VoucherDaoImpl voucherDao;

    @Autowired
    private HttpSession session;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    public boolean deleteVoucherByVoucherCode(String voucherCode){
        return voucherDao.deleteVoucherByVoucherCode(voucherCode);

    }

    public boolean addNew(String email) {
        Users user = userService.findByEmail(email);
        if (user == null) {
            return false;
        }

        if (voucherDao.findByUserId(user.getId()) != null) {
            System.out.println("Code"+ voucherDao.findByUserId(user.getId()).getCode());
            return false;
        }

        String voucherCode = UUID.randomUUID().toString();
        Date startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date endDate = calendar.getTime();

        try {
            mailService.sendMail(
                    EmailManagement.SENDER,
                    email,
                    EmailManagement.CONFIRM_VOUCHER_SUBJECT,
                    EmailManagement.voucherSuccessNotification(email, voucherCode, startDate, endDate)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        Vouchers voucher = Vouchers.builder()
                .code(voucherCode)
                .amount(1)
                .description("")
                .discountPercent(5)
                .status("ACTIVE")
                .startDate(startDate)
                .endDate(endDate)
                .createdAt(new Date())
                .users(user)
                .build();

        return voucherDao.addNew(voucher);
    }

    public boolean checkVoucherCode(String voucherCode){
        return this.voucherDao.checkVoucherCode(voucherCode);
    }

    public boolean deleteVoucher(Integer voucherId) {
        return this.voucherDao.deleteVouchers(voucherId);
    }

    public List<Vouchers> getAllVouchers() {
        return voucherDao.getAllV2();
    }
}
