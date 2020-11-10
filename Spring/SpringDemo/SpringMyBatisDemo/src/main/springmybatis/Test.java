import dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringMyBatis.xml");
        UserDao userDao = (UserDao) context.getBean(UserDao.class);
        userDao.findAllUser().forEach(user -> System.out.println(user));
    }
}
