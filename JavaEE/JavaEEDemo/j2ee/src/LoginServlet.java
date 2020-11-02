import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        System.out.println("LoginServlet构造方法被调用。。。");
    }

    @Override
    public void init() {
        System.out.println("LoginServlet继承了init(ServletConfig)方法！");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {

            request.setCharacterEncoding("UTF-8");

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            String html = null;
            if ("祖峰".equals(name) && "123".equals(password)) {
                request.getRequestDispatcher("success.html").forward(request, response);
            } else {
                response.setStatus(301);
                response.sendRedirect("fail.html");
            }

            System.out.println("name: " + name + "; password: " + password);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
