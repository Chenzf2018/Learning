package com.chenzf.dao;

import com.chenzf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chenzf
 *
 * Repository定义一个DAO Bean
 */
@Repository
public class UserDAO {

    private  final static String MATCH_COUNT_SQL = "SELECT COUNT(*) FROM table_user WHERE user_name = ? AND password = ?";

    private  final static String UPDATE_LOGIN_INFO_SQL = "UPDATE table_user SET last_visit = ?, last_ip = ?, credits = ? WHERE user_id = ?";

    /**
     * 自动注人JdbcTemplate的Bean
     */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据用户名和密码获取匹配的用户数
     * @param userName 用户名
     * @param password 密码
     * @return 等于1表示用户名／密码正确；等于0表示用户名或密码错误
     */
    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,
                new Object[]{userName, password}, Integer.class);
    }

    /**
     * 根据用户名获取User对象
     * @param userName 用户名
     * @return User对象
     */
    public User findUserByUserName(final String userName) {

        String sqlStr = " SELECT user_id, user_name, credits"
                + " FROM table_user WHERE user_name = ?";

        final User user = new User();

        /**
         * query(String sql, Object[] args, RowCallbackHandler rch)
         * sqlStr：查询的SQL语句，允许使用带"?"的参数占位符
         * args：SQL语句中占位符对应的参数数组
         * rch：查询结果的处理回调接口。该回调接口有方法processRow(ResultSet rs)
         *           负责将查询的结果从ResultSet装载到类似于领域对象的对象实例中。
         */
        jdbcTemplate.query(sqlStr, new Object[] { userName },
                // 通过匿名内部类的方式定义了一个RowCallbackHandler回调接口实例，将ResultSet转换为User对象
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });

        return user;
    }

    /**
     * 更新用户积分、最后登录IP及最后登录时间
     * @param user 用户
     */
    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] { user.getLastVisit(),
                user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
