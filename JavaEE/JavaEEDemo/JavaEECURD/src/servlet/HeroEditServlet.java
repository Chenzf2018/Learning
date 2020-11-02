package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeroEditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Hero hero = new HeroDAO().get(id);

            StringBuffer stringBuffer = new StringBuffer();
            response.setContentType("text/html; charset=UTF-8");

            stringBuffer.append("<!DOCTYPE html>");
            stringBuffer.append("<form action='updateHero' method='post'>");
            stringBuffer.append("名字：<input type='text' name='name' value='%s' > <br>");
            stringBuffer.append("血量：<input type='text' name='hp'  value='%f' > <br>");
            stringBuffer.append("伤害：<input type='text' name='damage'  value='%d' > <br>");
            stringBuffer.append("<input type='hidden' name='id' value='%d'>");
            stringBuffer.append("<input type='submit' value='更新'>");
            stringBuffer.append("</form>");

            String html = String.format(stringBuffer.toString(), hero.getName(), hero.getHp(), hero.getDamage(), hero.getId());
            response.getWriter().write(html);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
