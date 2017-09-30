package cn.itcast.usermanage.service.impl;

import cn.itcast.usermanage.bean.EasyUIResult;
import cn.itcast.usermanage.pojo.User;

public interface UserService {

    public EasyUIResult queryUserList(Integer page,Integer rows);
    
    public User queryUserById(Long id);
    
    public boolean saveUser(User user);
    
    public boolean updateUser(User user);
    
    public boolean deleteUser(Long id);
}
