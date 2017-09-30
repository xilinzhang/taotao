package com.taotao.manage.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.ItemParamService;

@Controller
@RequestMapping("item/param")
public class ItemParamController {

    private final Logger LOGGER=LoggerFactory.getLogger(ItemParamController.class);
    
    @Autowired
    private ItemParamService itemParamService;
    
    @RequestMapping(value="{itemCatId}",method=RequestMethod.GET)
    public ResponseEntity<ItemParam> queryByItemCatId(@PathVariable("itemCatId") Long itemCatId){
      try {
            ItemParam record=new ItemParam();
            record.setItemCatId(itemCatId);
            ItemParam itemParam=itemParamService.queryOne(record);
            if(null==itemParam){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    /**
     * 新增规格参数模板
     * @param itemParam
     * @param itemCatId
     * @return
     */
    @RequestMapping(value="{itemCatId}",method=RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(ItemParam itemParam,@PathVariable("itemCatId") Long itemCatId){
        try {
             itemParam.setItemCatId(itemCatId);
             this.itemParamService.save(itemParam);
             return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryItemParamList(
            @RequestParam(value="page",defaultValue="1") Integer page,
            @RequestParam(value="rows",defaultValue="30") Integer rows){
            try {
                if(LOGGER.isDebugEnabled()){
                    LOGGER.info("查询商品规格参数模板列表,page={},rows={}",page,rows);
                }
                PageInfo<ItemParam> pageInfo=itemParamService.queryPageList(page,rows);
                EasyUIResult easyUIResult=new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
                return ResponseEntity.ok(easyUIResult);
            } catch (Exception e) {
                LOGGER.info("查询商品规格参数模板列表失败");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
