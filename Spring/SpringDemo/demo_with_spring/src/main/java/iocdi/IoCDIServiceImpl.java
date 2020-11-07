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
