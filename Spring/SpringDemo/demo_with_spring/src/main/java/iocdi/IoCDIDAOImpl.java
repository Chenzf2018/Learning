package iocdi;

public class IoCDIDAOImpl implements IoCDIDAO {
    @Override
    public void save(String name) {
        System.out.println("实现控制反转（IoCDIDAOImpl）：name = " + name);
    }
}
