package cn.itcast.usermanage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.usermanage.bean.EasyUIResult;
import cn.itcast.usermanage.service.impl.UserServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired 
    private UserServiceImpl userService;
    
    @RequestMapping(value="list",method=RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryUserList(@RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows){
        return userService.queryUserList(page, rows);
    }
}