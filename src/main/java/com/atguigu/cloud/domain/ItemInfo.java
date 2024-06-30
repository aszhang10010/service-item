package com.atguigu.cloud.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName item_info
 */
@TableName(value ="item_info")
@Data
public class ItemInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 商品图片url
     */
    private String url ;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}