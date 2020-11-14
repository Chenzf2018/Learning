package controller;

import entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器的开发方式有多种，比如实现HttpRequestHandler接口、Controller接口的方式、还有注解的方式
 * 建议使用注解的方式
 * 注解的注意事项
 *  1. 类上加上@Controller注解
 *  2. 类上或者方法上面要加上@RequestMapping(必须)
 *
 * @author Chenzf
 */

@Controller
public class ItemController {
    /**
     * RequestMapping填写的是url
     * @return ModelAndView
     *                  Model标识的是数据类型，View最终要展示给用户的视图
     */
    @RequestMapping("queryItem")
    public ModelAndView queryItem() {
        // 用静态数据模型
        List<Item> itemList = new ArrayList<>();

        Item item1 = new Item();
        item1.setName("苹果手机");
        item1.setPrice(5000);
        item1.setDetail("iphoneX苹果手机！");
        itemList.add(item1);

        Item item2 = new Item();
        item2.setName("华为手机");
        item2.setPrice(6000);
        item2.setDetail("华为5G网速就是快！");
        itemList.add(item2);

        ModelAndView modelAndView = new ModelAndView();
        // 设置数据模型，相当于request的setAttribute方法。实质上，底层确实也是转成了request()
        //先将k/v数据放入map中，最终根据视图对象不同，再进行后续处理
        modelAndView.addObject("itemList", itemList);

        // 设置View视图
        modelAndView.setViewName("item-list");

        return modelAndView;
    }
}