package com.taotao.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;

/**
 * 
 * @ClassName: BaseService 
 * @Description: service 基础类
 * @author zxl
 * @date 2017年8月6日 下午6:50:39
 */
public abstract class BaseService<T extends BasePojo> {
   
    //public abstract Mapper<T> getMapper();//spring3  用抽象方法注入mapper
    @Autowired
    private Mapper<T> mapper;//spring4 新增对泛型注入的新特性
    
    
    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    public T queryById(Long id){
        return this.mapper.selectByPrimaryKey(id);
    }
    
    /**
     * 查询所有数据
     * @return
     */
    public List<T> queryAll(){
        return this.mapper.select(null);
    }
    
    /**
     * 根据条件查询一条数据
     * @param record
     * @return
     */
    public T queryOne(T record){
        return this.mapper.selectOne(record);
    }
    
    /**
     * 根据条件查询数据列表
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record){
        return this.mapper.select(record);
    }
    
    /**
     * 分页查询数据列表
     * @param page
     * @param rows
     * @param record
     * @return
     */
    public PageInfo<T> queryPageListByWhere(Integer page,Integer rows,T record){
        //设置分页参数
        PageHelper.startPage(page, rows);
        List<T> list=this.mapper.select(record);
        return new PageInfo<T>(list);
    }
    
    /**
     * 新增数据
     * @param t
     * @return
     */
    public Integer save(T t){
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return this.mapper.insert(t);
    }
    
    /**
     * 有选择的保存，选择不为null的字段作为插入的字段
     * @param t
     * @return
     */
    public Integer saveSelective(T t){
        t.setCreated(new Date());
        t.setUpdated(t.getCreated());
        return this.mapper.insertSelective(t);
    }
    /**
     * 更新数据
     * @param t
     * @return
     */
    public Integer update(T t){
        t.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(t);
    }
    
    /**
     * 有选择的更新，选择不为null的字段作为插入的字段
     * @param t
     * @return
     */
    public Integer updateSelective(T t){
        t.setUpdated(new Date());
        return this.mapper.updateByPrimaryKeySelective(t);
    }
    
    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    public Integer deleteById(Long id){
        return this.mapper.deleteByPrimaryKey(id);
    }
    
    /**
     * 批量删除
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz,String property,List<Object> values){
        Example example =new Example(clazz);
        Criteria criteria=example.createCriteria();
        criteria.andIn(property, values);
        return this.mapper.deleteByExample(example);
    }
    
    /**
     * 根据条件删除数据
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record){
        return this.mapper.delete(record);
    }
}
