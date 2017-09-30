package com.taotao.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParamItem;
@Service
public class ItemService extends BaseService<Item>{

    @Autowired
    private ItemDescService itemDescService;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemParamItemService itemParamItemService;
    
    public void saveItem(Item item,String desc,String itemParams){
        //保存商品信息
        item.setStatus(1);
        item.setId(null);
        super.save(item);
        
        //保存商品描述信息
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        this.itemDescService.save(itemDesc);

        //保存商品规格参数的数据
        ItemParamItem itemParamItem=new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        this.itemParamItemService.save(itemParamItem);
    }
    
    public PageInfo<Item> queryPageList(Integer page,Integer rows){
        Example example=new Example(Item.class);
        example.setOrderByClause("updated DESC");
        //设置分页参数
        PageHelper.startPage(page, rows);
        List<Item> list=this.itemMapper.selectByExample(example);
        return new PageInfo<Item>(list);
    }
    
    public void updateItem(Item item,String desc,String itemParams){
        //强制设置不能修改的字段为null
        item.setStatus(null);
        item.setCreated(null);
        super.updateSelective(item);
        
        //修改商品描述数据
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        this.itemDescService.updateSelective(itemDesc);
        
        //修改商品规格参数数据
        this.itemParamItemService.updateItemParamItem(item.getId(), itemParams);
    }
}
