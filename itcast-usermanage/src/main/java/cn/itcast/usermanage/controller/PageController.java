package cn.itcast.usermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @ClassName: PageController 
 * @Description: 通用的页面跳转
 * @author zxl
 * @date 2017年7月23日 下午2:47:32
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 具体页面跳转
     * @param pageName
     * @return 视图名
     */
    @RequestMapping(value="{pageName}",method=RequestMethod.GET)
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
