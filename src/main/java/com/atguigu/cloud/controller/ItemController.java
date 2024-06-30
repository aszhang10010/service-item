package com.atguigu.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.cloud.domain.ItemInfo;
import com.atguigu.cloud.service.ItemInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/item")
@Slf4j
@CrossOrigin            // 解决跨域问题
public class ItemController {

    @Autowired
    private ItemInfoService itemInfoService ;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @GetMapping(value = "/findAll")
    public List<ItemInfo> findAll() {

        // 从缓存中进行查询
        String itemInfoJSON = redisTemplate.opsForValue().get("item_info");
        if(!StringUtils.isEmpty(itemInfoJSON)) {
            List<ItemInfo> itemInfoList = JSON.parseArray(itemInfoJSON, ItemInfo.class);
            log.info("从缓存中查询到了数据...");
            return itemInfoList ;
        }

        // 查询数据库
        List<ItemInfo> itemInfos = itemInfoService.list();
        redisTemplate.opsForValue().set("item_info" , JSON.toJSONString(itemInfos)  , 1 , TimeUnit.HOURS);
        log.info("从数据库中查询到了数据...");

        return itemInfos ;
    }

}
