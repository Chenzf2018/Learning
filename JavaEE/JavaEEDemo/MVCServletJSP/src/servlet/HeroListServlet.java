package servlet;

import bean.Hero;
import dao.HeroDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HeroListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Hero> heroes = new HeroDAO().list();
            request.setAttribute("heroes", heroes);
            request.getRequestDispatcher("listHero.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
