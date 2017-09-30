package com.taotao.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;
import com.taotao.manage.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {

    private final Logger LOGGER=LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item,@RequestParam("desc") String desc,
            @RequestParam("itemParams") String itemParams){
        try {
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("新增商品, item={}, desc={},itemParams={}",item,desc,itemParams);
            }
             this.itemService.saveItem(item, desc,itemParams);
            //成功 返回201
             if(LOGGER.isInfoEnabled()){
                 LOGGER.info("新增商品成功，itemId={}",item.getId());
             }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品失败! title="+item.getTitle()+", cid="+item.getCid(),e);
        }
        //出错 返回500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemList(
            @RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="rows",defaultValue="30") Integer rows){
            try {
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info("查询商品列表, page={}, rows={}",page,rows);
                }
                PageInfo<Item> pageInfo=this.itemService.queryPageList(page, rows);
                EasyUIResult easyUIResult=new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
                return ResponseEntity.ok(easyUIResult);
            } catch (Exception e) {
               LOGGER.error("查询商品列表失败!",e);
            }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item,@RequestParam("desc") String desc,
            @RequestParam("itemParams") String itemParams){
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("修改商品, item={}, desc={},itemParams={}",item,desc,itemParams);
        }
        if(StringUtils.isEmpty(item.getTitle())){
            //响应400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        this.itemService.updateItem(item, desc, itemParams);
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("修改商品成功, itemId={}",item.getId());
        }
        //成功 204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
