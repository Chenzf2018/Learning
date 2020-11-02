package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HeroListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=UTF-8");

            List<Hero> heroes = new HeroDAO().list();

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<table align='center' border='1' cellspacing='0'>");
            stringBuffer.append("<tr><td>id</td><td>name</td><td>hp</td><td>damage</td><td>edit</td><td>delete</td></tr>");
            String strFormat = "<tr><td>%d</td><td>%s</td><td>%f</td><td>%d</td><td><a href='editHero?id=%d'>edit</a></td><td><a href='deleteHero?id=%d'>delete</a></td></tr>";

            for (Hero hero : heroes) {
                String string = String.format(strFormat, hero.getId(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId(), hero.getId());
                stringBuffer.append(string);
            }

            stringBuffer.append("</table>");

            response.getWriter().write(stringBuffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
