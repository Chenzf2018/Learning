import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class RegisterServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取单值参数name：" + request.getParameter("name"));

        String[] hobbies = request.getParameterValues("hobbies");
        System.out.println("获取具有多值的参数hobbies: " + Arrays.asList(hobbies));

        System.out.println("通过getParameterMap遍历所有参数：");
        Map<String, String[]> parameters = request.getParameterMap();

        Set<String> parameterNames = parameters.keySet();
        for (String parameterName : parameterNames) {
            String[] value = parameters.get(parameterName);
            System.out.println(parameterName + " : " + Arrays.asList(value));
        }
    }
}
