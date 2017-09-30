package cn.itcast.usermanage.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.itcast.usermanage.bean.EasyUIResult;
import cn.itcast.usermanage.mapper.UserMapper;
import cn.itcast.usermanage.pojo.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    public EasyUIResult queryUserList(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page, rows);
        //查询user数据
        Example example=new Example(User.class);
        example.setOrderByClause("updated DESC");
        List<User> users=this.userMapper.selectByExample(example);
        //获取分页后的信息
        PageInfo<User> pageInfo=new PageInfo<User>(users);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.insert(user)==1;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user)==1;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteByPrimaryKey(id)==1;
    }
}
