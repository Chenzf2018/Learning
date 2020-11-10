package mybatis;

import mybatis.entity.Role;
import mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import mybatis.dao.RoleDao;

public class RoleTest {
    public static void main(String[] args) {
        testRoleMapper();
    }

    private static void testRoleMapper() {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            RoleDao roleDao = sqlSession.getMapper(RoleDao.class);
            Role role = roleDao.getRole(1L);
            System.out.println(role.getRoleName());
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
