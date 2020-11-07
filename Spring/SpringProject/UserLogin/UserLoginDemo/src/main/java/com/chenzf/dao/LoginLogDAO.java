package com.chenzf.dao;

import com.chenzf.entity.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDAO {

    /**
     * 保存登陆日志SQL
     */
    private final static String INSERT_LOGIN_LOG_SQL=
            "INSERT INTO table_login_log(user_id, ip, login_datetime) VALUES(?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 记录用户的登录日志
     * @param loginLog 用户的登录日志
     */
    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }
}
