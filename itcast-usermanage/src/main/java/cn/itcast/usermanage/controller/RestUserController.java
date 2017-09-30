package cn.itcast.usermanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.impl.UserService;

/**
 * restfull风格的请求测试
 * @ClassName: RestUserController 
 * @Description: TODO
 * @author zxl
 * @date 2017年7月23日 下午11:09:52
 */
@Controller
@RequestMapping("test")
public class RestUserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value="{id}",method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> queryUserById(@PathVariable("id") Long id){
        try {
            User user=userService.queryUserById(id);
            //int s=1/0;
            if(null==user){
                //资源不存在 响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            //资源存在 响应200
            //return ResponseEntity.status(HttpStatus.OK).body(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            //服务出错 响应500
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> saveUser(User user){
        try {
            boolean bool=userService.saveUser(user);
            if(bool){
                //新增成功 ，响应状态吗204
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务出错 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    };
    
    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void>  updateUser(User user){
        try {
            boolean bool=userService.updateUser(user);
            if(bool){
                //更新成功，响应状态码204
               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      //服务出错 响应500
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    @RequestMapping(method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@RequestParam(value="id",defaultValue="0")Long id){
        try {
            if(id.longValue()==0){
                //没有传递参数，响应状态码400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            boolean bool=userService.deleteUser(id);
            if(bool){
              //删除成功，响应状态码204
               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //服务出错 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
