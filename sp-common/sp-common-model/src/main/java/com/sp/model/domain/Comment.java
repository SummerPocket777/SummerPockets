package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论ID，自增，不为空
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容，不为空
     */
    private String content;

    /**
     * 用户ID，不为空
     */
    private Long consumerUserId;

    /**
     * 菜品ID，不为空
     */
    private Long dishId;

    /**
     * 评论的点赞数，默认为0
     */
    private Integer commentLike;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0表示未删除，1表示已删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}