package com.taotao.manage.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.ItemCatResult;
import com.taotao.manage.service.ItemCatService;

@Controller
@RequestMapping("api/item/cat")
public class ApiItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    
    //private static final ObjectMapper MAPPER=new ObjectMapper();
    /**
     * 对外提供接口 查询商品类目数据 (jsonp解决跨域问题)
     * @return
     */
/*    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<String> queryItemCat(@RequestParam("callback") String callback){
        try {
            ItemCatResult itemCatResult=this.itemCatService.queryAllToTree();
            String json=MAPPER.writeValueAsString(itemCatResult);
            if(StringUtils.isEmpty(callback)){
                return ResponseEntity.ok(json);
            }
            return ResponseEntity.ok(callback+"("+json+");");
        } catch (Exception e) {
           e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }*/
    /**
     * 对外提供接口 查询商品类目数据 (jsonp解决跨域问题)
     *   将jsonp返回的数据格式（js脚本） 通过转换器抽象出来  
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<ItemCatResult> queryItemCat(){
        try {
            ItemCatResult itemCatResult=this.itemCatService.queryAllToTree();
            if(itemCatResult==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemCatResult);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
