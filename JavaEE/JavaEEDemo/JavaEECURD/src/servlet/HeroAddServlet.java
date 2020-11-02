package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeroAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 使用UTF-8的方式获取浏览器传过来的中文
            request.setCharacterEncoding("UTF-8");

            Hero hero = new Hero();
            hero.setName(request.getParameter("name"));
            hero.setHp(Float.parseFloat(request.getParameter("hp")));
            hero.setDamage(Integer.parseInt(request.getParameter("damage")));

            new HeroDAO().add(hero);

            response.sendRedirect("/listHero");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
