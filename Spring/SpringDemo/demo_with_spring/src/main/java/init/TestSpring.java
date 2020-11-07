package init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        /*
         * 启动工厂
         * 读取配置文件，根据指定的类创建组件对象，并为其取了唯一的标识
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("/init/SpringInit.xml");

        /*
         * 获取对象
         * 参数：获取工厂中指定的唯一标识
         * context.getBean("UserDAO") 返回Object，需要强制转换
         */
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        System.out.println(userDAO);
        userDAO.save("China");
    }
}
