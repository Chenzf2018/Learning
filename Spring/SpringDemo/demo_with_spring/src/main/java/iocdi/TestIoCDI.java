package iocdi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIoCDI {
    public static void main(String[] args) {
        /*
         * 启动工厂
         * 读取配置文件，根据指定的类创建组件对象，并为其取了唯一的标识
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("/iocdi/SpringIoCDI.xml");

        /*
         * 获取对象
         * 参数：获取工厂中指定的唯一标识
         * context.getBean("ioCDIDAO") 返回Object，需要强制转换
         */
        IoCDIServiceImpl ioCDIService = context.getBean(IoCDIServiceImpl.class);

        System.out.println(ioCDIService);
        ioCDIService.save("Spring");
    }
}
