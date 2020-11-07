package com.chenzf.service;

import com.chenzf.dao.LoginLogDAO;
import com.chenzf.dao.UserDAO;
import com.chenzf.entity.LoginLog;
import com.chenzf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chenzf
 *
 * Service：将UserService标注为一个服务层的Bean
 */
@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private LoginLogDAO loginLogDAO;

    @Autowired
    public void setLoginLogDAO(LoginLogDAO loginLogDAO) {
        this.loginLogDAO = loginLogDAO;
    }

    /**
     * 用于检查用户名、密码的正确性；
     * @param userName 用户名
     * @param password 密码
     * @return 是否正确
     */
    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDAO.getMatchCount(userName, password);
        return matchCount > 0;
    }

    /**
     * 以用户名为条件加载User对象
     * @param userName 用户名
     * @return User对象
     */
    public User findUserByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    /**
     * 在用户登录成功后调用，更新用户最后登录时间和IP信息，同时记录用户登录日志
     * @param user 用户
     */
    @Transactional
    public void loginSuccess(User user) {
        user.setCredits( 5 + user.getCredits());

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());

        userDAO.updateLoginInfo(user);
        loginLogDAO.insertLoginLog(loginLog);
    }
}
