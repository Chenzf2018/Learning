package dao;

import bean.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    public HeroDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaeecurd?characterEncoding=UTF-8",
                                                                        "root", "admin");
    }

    public int getTotal() {
        int total = 0;
        try(Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            String sql = "SELECT COUNT(*) FROM hero";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                total = resultSet.getInt(1);
            }
            System.out.println("Total: " + total);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public void add(Hero hero) {
        String sql = "INSERT INTO hero VALUES(null, ?, ?, ?)";
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, hero.name);
            preparedStatement.setFloat(2, hero.hp);
            preparedStatement.setInt(3, hero.damage);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                hero.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            String sql = "DELETE FROM hero WHERE id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Hero hero) {
        String sql = "UPDATE hero SET name = ?, hp = ?, damage = ? WHERE id = ?";
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, hero.name);
            preparedStatement.setFloat(2, hero.hp);
            preparedStatement.setInt(3, hero.damage);
            preparedStatement.setInt(4, hero.id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Hero get(int id) {
        Hero hero = null;
        try(Connection connection = getConnection(); Statement statement = connection.createStatement();) {
            String sql = "SELECT * FROM hero WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                hero = new Hero();
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);

                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                hero.id = id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hero;
    }

    public List<Hero> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Hero> list(int start, int count) {
        List<Hero> heroes = new ArrayList<>();
        String sql = "SELECT * FROM hero ORDER BY id DESC LIMIT ?, ?";
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hero hero = new Hero();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                float hp = resultSet.getFloat("hp");
                int damage = resultSet.getInt(4);

                hero.id = id;
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                heroes.add(hero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return heroes;
    }
}
