# 参考资料

[廖雪峰Spring](https://www.liaoxuefeng.com/wiki/1252599548343744/1266263217140032)

# Spring引言

Spring最早是由Rod Johnson提出的用来取代EJB的轻量级框架。随着Spring越来越受欢迎，在`Spring Framework`基础上，又诞生了Spring Boot、Spring Cloud、Spring Data、Spring Security等一系列基于Spring Framework的项目。

# 示例

## 未使用Spring

在`D:\Learning\Spring\SpringDemo`目录下创建项目！

该项目展示了，不使用`Spring`时，该如何创建、使用和销毁组件！

1. 创建项目

   ![image-20201103091518019](Spring笔记.assets/image-20201103091518019.png)

   ![image-20201103091622000](Spring笔记.assets/image-20201103091622000.png)

   ![image-20201103091801259](Spring笔记.assets/image-20201103091801259.png)

   ![image-20201103092800496](Spring笔记.assets/image-20201103092800496.png)

2. 完善目录结构

   在`main`目录下新建`java`和`resources`，并分别设置`Mark Directory as`：`Sources Root`和`Resources Root`；或创建好后，点击`Import Changes`

   ![image-20201103092947118](Spring笔记.assets/image-20201103092947118.png)

3. 在`main.java`下新建`init`目录

   - 创建`UserDAO`接口

     ```java
     package init;
     
     public interface UserDAO {
         void save(String name);
     }
     ```

     

   - 创建`UserDAOImpl`实现类

     ```java
     package init;
     
     public class UserDAOImpl implements UserDAO {
         @Override
         public void save(String name) {
             System.out.println("name: " + name);
         }
     }
     ```

     

   - 编写`TestUserDAO`——创建组件对象并使用

     ```java
     package init;
     
     public class TestUserDAO {
         public static void main(String[] args) {
             // 创建组件对象
             UserDAOImpl user = new UserDAOImpl();
     
             // 使用组件
             user.save("China");
     
             // 由JVM销毁对象
         }
     }
     ```

     

## 使用Spring

### 创建项目

与未使用Spring中[创建项目](# 未使用Spring)过程类似：

![image-20201103093945715](Spring笔记.assets/image-20201103093945715.png)

完善项目结构：在`main`目录下新建`java`和`resources`，并分别设置`Mark Directory as`：`Sources Root`和`Resources Root`

### 引入项目依赖

在`pom.xml`中[引入Spring核心和依赖模块](https://mvnrepository.com/search?q=Spring)，并点击`Import Changes`：

```xml
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.3.2.RELEASE</version>
    </dependency>
```



### 创建组件

1. 在`main.java.init`目录下创建`UserDAO`接口：

   ```java
   package init;
   
   public interface UserDAO {
       void save(String name);
   }
   ```

   

2. 在`main.java.init`目录下创建`UserDAOImpl`实现类：

   ```java
   package init;
   
   public class UserDAOImpl implements UserDAO {
       @Override
       public void save(String name) {
           System.out.println("name: " + name);
       }
   }
   ```

   

### 引入Spring框架配置文件——工厂管理

- 工厂管理比手动（`new`）创建要轻量：工厂创建对象默认是`单例模式`，无论从工厂中获取多少个对象，获得的都是`同一个对象`；
- 通过`new`创建对象，每`new`一次，就在`JVM`中创建了一个新的对象！

1. 配置文件名称：任意名称
   
- `applicationContext.xml`或`Spring.xml`
  
2. 配置文件位置：项目中根下任意位置
   
- `resources.init`目录下
  
3. 配置文件内容

   通过`springInit.xml`配置文件来管理组件：**使用`bean`标签来创建组件对象**

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
       
       <!--通过Spring管理组件
                   bean: 用来管理组件对象的创建
                   class: 用来指定管理组件对象的全限定名 包.类
                           肯定是实现类，只有实现类才能创建对象
                   id: 用来指定Spring框架创建的当前组件在Spring框架（容器、工厂）中的（全局）唯一标识，
                       方便获取Spring框架中已经创建好的对象
                       推荐使用当前实现类的接口首字母小写userDAO-->
       <bean class="iocdi.IoCDIDAOImpl" id="ioCDIDAO"/>
       
   </beans>
   ```

   

### 测试

在`main.java.init`下创建`TestSpring.java`

```java
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
```



输出：

```
init.UserDAOImpl@12108b5
name: China
```



# 控制反转与依赖注入

## IoC容器

**容器是一种为某种特定组件的运行提供必要支持的一个`软件环境`。**

例如，Tomcat就是一个Servlet容器，它可以为Servlet的运行提供运行环境。类似Docker这样的软件也是一个容器，它提供了必要的Linux环境以便运行一个特定的Linux进程。

**使用容器运行组件，除了提供一个组件运行环境之外，容器还提供了许多`底层服务`。**

例如，Servlet容器底层实现了TCP连接，解析HTTP协议等非常复杂的服务，如果没有容器来提供这些服务，我们就无法编写像Servlet这样代码简单，功能强大的组件。早期的JavaEE服务器提供的EJB容器最重要的功能就是通过声明式事务服务，使得EJB组件的开发人员不必自己编写冗长的事务处理代码，所以极大地简化了事务处理。

Spring的核心就是提供了一个IoC容器，它可以管理所有轻量级的JavaBean组件，提供的底层服务包括组件的生命周期管理、配置和组装服务、AOP支持，以及建立在AOP基础上的声明式事务服务等。

### IoC原理

**`IoC全称为Inversion of Control（控制反转）`**。控制反转（**控制权力反转**）：将对象的创建由原来(`new`)的方式（在任意地方随意创建）转移到`配置文件`中，交给`Spring工厂`来创建对象。在此基础上，还需通过`DI`的方式维护组件与组件之间的调用关系！

1. 设计一个在线书店，通过`BookService`获取书籍：

   ```java
   public class BookService {
       private HikariConfig config = new HikariConfig();
       private DataSource dataSource = new HikariDataSource(config);
   
       public Book getBook(long bookId) {
           try (Connection conn = dataSource.getConnection()) {
               ...
               return book;
           }
       }
   }
   ```

   为了从数据库查询书籍，`BookService`持有一个`DataSource`。为了实例化一个`HikariDataSource`，又不得不实例化一个`HikariConfig`。

2. 编写`UserService`获取用户：

   ```java
   public class UserService {
       private HikariConfig config = new HikariConfig();
       private DataSource dataSource = new HikariDataSource(config);
   
       public User getUser(long userId) {
           try (Connection conn = dataSource.getConnection()) {
               ...
               return user;
           }
       }
   }
   ```

   因为`UserService`也需要访问数据库，因此，我们不得不也实例化一个`HikariDataSource`。

3. 在处理用户购买的`CartServlet`中，我们需要实例化`UserService`和`BookService`：

   ```java
   public class CartServlet extends HttpServlet {
       private BookService bookService = new BookService();
       private UserService userService = new UserService();
   
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           long currentUserId = getFromCookie(req);
           User currentUser = userService.getUser(currentUserId);
           Book book = bookService.getBook(req.getParameter("bookId"));
           cartService.addToCart(currentUser, book);
           ...
       }
   }
   ```



4. 在购买历史`HistoryServlet`中，也需要实例化`UserService`和`BookService`：

   ```java
   public class HistoryServlet extends HttpServlet {
       private BookService bookService = new BookService();
       private UserService userService = new UserService();
   }
   ```

   

上述**每个组件都采用了通过`new`创建实例并持有的方式**。该方法存在以下**缺点**：

1. 实例化一个组件其实很难，例如，`BookService`和`UserService`要创建`HikariDataSource`，实际上需要读取配置，才能先实例化`HikariConfig`，再实例化`HikariDataSource`。
2. 没有必要让`BookService`和`UserService`分别创建`DataSource`实例，完全可以共享同一个`DataSource`，但谁负责创建`DataSource`，谁负责获取其他组件已经创建的`DataSource`，不好处理。类似的，`CartServlet`和`HistoryServlet`也应当共享`BookService`实例和`UserService`实例，但也不好处理。
3. 很多组件需要销毁以便释放资源，例如`DataSource`，但如果该组件被多个组件共享，如何确保它的使用方都已经全部被销毁？
4. 随着更多的组件被引入，例如，书籍评论，需要共享的组件写起来会更困难，这些**组件的依赖关系会越来越复杂**。



**核心问题**是：

1. 谁负责创建组件？
2. 谁负责根据依赖关系组装组件？
3. 销毁时，如何按依赖顺序正确销毁？



在IoC模式下，**控制权发生了反转**，即**从应用程序转移到了IoC容器，所有组件不再由应用程序自己创建和配置，而是由IoC容器负责**，这样，应用程序只需要直接使用已经创建好并且配置好的组件。

为了能让组件在IoC容器中被“装配”出来，需要某种“**注入**”机制，例如，`BookService`自己并不会创建`DataSource`，而是等待外部通过`setDataSource()`方法来注入一个`DataSource`：

```java
public class BookService {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

不直接`new`一个`DataSource`，而是注入一个`DataSource`，这个小小的改动虽然简单，却带来了一系列**好处**：

1. `BookService`不再关心如何创建`DataSource`，因此，不必编写读取数据库配置之类的代码；
2. `DataSource`实例被注入到`BookService`，同样也可以注入到`UserService`，因此，共享一个组件非常简单；



### 控制反转过程

在项目`demo_with_spring`中的`java`目录下新建`iocdi`

1. `IoCDIDAO`

   - 创建`IoCDIDAO`接口并实现

     ```java
     package iocdi;
     
     public interface IoCDIDAO {
         void save(String name);
     }
     
     package iocdi;
     
     public class IoCDIDAOImpl implements IoCDIDAO {
         @Override
         public void save(String name) {
             System.out.println("实现控制反转（IoCDIDAOImpl）：name = " + name);
         }
     }
     ```

     

   - 在`resources`下新建`iocdi`目录，然后创建`springIoCDI.xml`，管理`DAO`组件

     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <!--通过Spring管理组件
             bean: 用来管理组件对象的创建
             class: 用来指定管理组件对象的全限定名 包.类
             id: 用来指定Spring框架创建的当前组件在Spring框架（容器、工厂）中的（全局）唯一标识，
                 方便获取Spring框架中已经创建好的对象
                 推荐使用当前实现类的接口首字母小写userDAO-->
         <bean class="iocdi.IoCDIDAOImpl" id="ioCDIDAO"/>
     
     </beans>
     ```

     

   - 在`java.iocdi`下创建`TestIoCDI.java`

     ```java
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
             IoCDIDAOImpl ioCDIDAO = (IoCDIDAOImpl) context.getBean("ioCDIDAO");
     
             System.out.println(ioCDIDAO);
             ioCDIDAO.save("Spring");
         }
     }
     
     ```

     

   - 测试结果

     ```
     iocdi.IoCDIDAOImpl@12108b5
     实现控制反转（IoCDIDAOImpl）：name = Spring
     ```

     

2. `IoCDIService`

   - 创建`IoCDIService`接口并实现

     ```java
     package iocdi;
     
     public interface IoCDIService {
         void save(String name);
     }
     
     package iocdi;
     
     public class IoCDIServiceImpl implements IoCDIService {
         @Override
         public void save(String name) {
             System.out.println("实现控制反转（IoCDIServiceImpl）：name = " + name);
         }
     }
     ```

     

   - 在`resources/iocdi`目录下`springIoCDI.xml`中管理`Service`组件

     ```xml
     <bean class="iocdi.IoCDIServiceImpl" id="ioCDIService"/>
     ```

     

   - 修改`TestIoCDI.java`

     ```java
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
             IoCDIServiceImpl ioCDIService = (IoCDIServiceImpl) context.getBean("ioCDIService");
     
             System.out.println(ioCDIService);
             ioCDIService.save("Spring");
         }
     }
     ```

     

   - 测试结果

     ```
     iocdi.IoCDIServiceImpl@1a5c39e
     实现控制反转（IoCDIServiceImpl）：name = Spring
     ```

     







## DI

IoC又称为**依赖注入（DI：Dependency Injection）**，它解决了一个最主要的问题：将**`组件的创建、配置`**与**`组件的使用`**相分离，并且，**由IoC容器负责管理组件的生命周期**。

**依赖注入**让**`调用类对某一接口实现类的依赖关系由第三方（容器或协作类）注入`**， 以移除调用类对某一接口实现类的依赖。

通过DI，对象的依赖关系将由**系统中负责协调各对象的第三方组件在创建对象的时候进行设定**。对象无需自行创建或管理它们的依赖关系。**依赖注入会将所依赖的关系自动交给目标对象，而不是让对象自己去获取依赖**。

因为IoC容器要负责实例化所有的组件，因此，有必要**`告诉容器如何创建组件，以及各组件的依赖关系`**。一种最简单的配置是**`通过XML文件来实现`**，例如：

```xml
<beans>
    <bean id="dataSource" class="HikariDataSource"/>
    
    <bean id="bookService" class="BookService">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    
    <bean id="userService" class="UserService">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```

上述XML配置文件指示IoC容器创建3个`JavaBean组件`，并**把id为`dataSource`的组件通过属性`dataSource`（即调用`setDataSource()`方法）注入到另外两个组件中**。

在Spring的IoC容器中，我们把所有组件统称为JavaBean，即**配置一个组件就是配置一个Bean**。



### 基于控制反转的依赖注入

Spring不仅要**`创建对象`**，还要在创建对象时**`维护组件与组件的依赖关系`**！

**依赖注入**：解决**`组件之间的调用`**关系问题；**为组件中成员变量完成赋值过程**。

**语法**：

- 组件对象中需要哪个组件，就将该组件声明为**成员变量**并提供公开的`SET`方法
- 在Spring的**配置文件**里对应的`组件标签`内使用`property`完成属性的`赋值操作`



基于[控制反转过程](# 控制反转过程)中的代码，实现`Service`组件对`DAO`组件的调用：

1. 原始方式

   - 在`iocdi.IoCDIServiceImpl`中创建`IoCDIDAOImpl`对象：==第7、12行==

     ```java
     package iocdi;
     
     public class IoCDIServiceImpl implements IoCDIService {
         /**
          * 依赖DAO组件
          */
         private IoCDIDAO ioCDIDAO = new IoCDIDAOImpl();
         
         @Override
         public void save(String name) {
             System.out.println("实现控制反转（IoCDIServiceImpl）：name = " + name);
             ioCDIDAO.save("Spring");
         }
     }
     ```

     

   - 运行`TestIoCDI.java`

     ```
     iocdi.IoCDIServiceImpl@1a5c39e
     实现控制反转（IoCDIServiceImpl）：name = Spring
     实现控制反转（IoCDIDAOImpl）：name = Spring
     ```



2. 工厂中已经有了`IoCDIDAO`对象，无需再使用`new`方式创建，`依赖注入即向需要的对象赋值`

   - 组件对象中需要哪个组件，就将该组件声明为成员变量并提供公开的`SET`方法（`ALT`+`INS`）

     ```java
     package iocdi;
     
     public class IoCDIServiceImpl implements IoCDIService {
         /**
          * 依赖DAO组件
          */
         private IoCDIDAO ioCDIDAO;
     
         /**
          * 公开的Set方法
          * @param ioCDIDAO 注入的组件
          */
         public void setIoCDIDAO(IoCDIDAO ioCDIDAO) {
             this.ioCDIDAO = ioCDIDAO;
             System.out.println("实现依赖注入");
         }
     
         @Override
         public void save(String name) {
             System.out.println("实现控制反转（IoCDIServiceImpl）：name = " + name);
             ioCDIDAO.save("Spring");
         }
     }
     ```

     此时运行会出现`空指针`（`private DeptDAO deptDAO;`）！

     

   - 在Spring的配置文件里对应的`组件标签`内完成属性的`赋值操作`

     创建应用组件之间协作的行为通常称为**装配**（wiring）。Spring有多种装配bean的方式，**采用XML是很常见的一种装配方式**。
   
     ```xml
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
     
         <!--通过Spring管理组件
             bean: 用来管理组件对象的创建
             class: 用来指定管理组件对象的全限定名 包.类
             id: 用来指定Spring框架创建的当前组件在Spring框架（容器、工厂）中的（全局）唯一标识，
                 方便获取Spring框架中已经创建好的对象
                 推荐使用当前实现类的接口首字母小写userDAO-->
         <bean class="iocdi.IoCDIDAOImpl" id="ioCDIDAO"/>
     
         <bean class="iocdi.IoCDIServiceImpl" id="ioCDIService">
             <!--依赖的注入
                 property: 用来给组件中的属性进行赋值
                 name: 用来指定给组件中哪个属性名进行赋值
                 ref: 用来指定赋值对象在工厂中的唯一标识，即DAO组件bean的id-->
             <property name="ioCDIDAO" ref="ioCDIDAO"/>
         </bean>
     </beans>
     ```
     
     Spring容器是通过**读取XML文件**后**使用`反射`**完成的。


​     

   - 运行结果
   
     Spring通过应用上下文（Application Context）装载bean的定义并把它们组装起来。Spring应用上下文全权负责对象的创建和组装。
     
     ```java
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
             IoCDIServiceImpl ioCDIService = (IoCDIServiceImpl) context.getBean("ioCDIService");
     
             System.out.println(ioCDIService);
          	ioCDIService.save("Spring");
         }
}
     ```
   
     创建一个Spring的IoC容器实例，然后加载配置文件，让Spring容器为我们创建并装配好配置文件中指定的所有Bean
  
     ```java
ApplicationContext context = new ClassPathXmlApplicationContext("/iocdi/SpringIoCDI.xml");
     ```
   
     
     
     ```
     实现依赖注入
  iocdi.IoCDIServiceImpl@bf3bbf
     实现控制反转（IoCDIServiceImpl）：name = Spring
   实现控制反转（IoCDIDAOImpl）：name = Spring
     ```
     
     



### 初识ApplicationContext与BeanFactory

从**创建`Spring容器`**的代码可知：

```java
ApplicationContext context = new ClassPathXmlApplicationContext("/iocdi/SpringIoCDI.xml");
```

Spring容器就是`ApplicationContext`，它是一个**接口**，有很多实现类，这里我们选择`ClassPathXmlApplicationContext`，表示它会自动从`classpath`中查找指定的XML配置文件。

获得了`ApplicationContext`的实例，就获得了IoC容器的引用。从`ApplicationContext`中我们可以**根据Bean的ID获取Bean**：

```java
IoCDIServiceImpl ioCDIService = (IoCDIServiceImpl) context.getBean("ioCDIService");
```

但更多的时候我们**根据Bean的类型获取Bean的引用**：

```java
IoCDIServiceImpl ioCDIService = context.getBean(IoCDIServiceImpl.class);
```



Spring还提供另一种IoC容器叫`BeanFactory`，使用方式和`ApplicationContext`类似：

```java
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("/iocdi/SpringIoCDI.xml"));
IoCDIServiceImpl ioCDIService = factory.getBean(IoCDIServiceImpl.class);
```

`BeanFactory`和`ApplicationContext`的区别在于，`BeanFactory`的实现是**按需创建，即第一次获取Bean时才创建这个Bean**；而`ApplicationContext`会**一次性创建所有的Bean**。

实际上，`ApplicationContext`接口是从`BeanFactory`接口继承而来的，并且，`ApplicationContext`提供了一些额外的功能，包括国际化支持、事件和通知机制等。通常情况下，我们**总是使用`ApplicationContext`**，很少会考虑使用`BeanFactory`。





# ApplicationContext与BeanFactory

Spring通过一个**`配置文件描述`Bean及Bean之间的依赖关系**，利用Java语言的**`反射功能实例化Bean`**并**`建立Bean之间的依赖关系`**。

**Bean工厂**（`com.springframework.beans.factory.BeanFactory`）是Spring框架最核心的接口，它提供了高级IoC的配置机制。**BeanFactory**使管理不同类型的Java对象成为可能

**应用上下文**（`com.springframework.context.ApplicationContext`）**建立在BeanFactory基础之上，提供了更多面向应用的功能**，它提供了国际化支持和框架事件体系，更**易于创建实际应用**。

一般**称BeanFactory为`IoC容器`**，而**称ApplicationContext为应用上下文**或**`Spring容器`**。

- `BeanFactory`是Spring框架的基础设施，**面向Spring本身**； 
- `ApplicationContext`**面向使用Spring框架的开发者**，几乎所有的应用场合都可以**直接使用ApplicationContext**而非**底层的BeanFactory**。



















































