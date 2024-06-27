package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 回复表
 * @TableName comment_reply
 */
@TableName(value ="comment_reply")
@Data
public class CommentReply implements Serializable {
    /**
     * 回复ID，自增，不为空
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 回复内容，不为空
     */
    private String content;

    /**
     * 用户ID
     */
    private Long consumerUserId;

    /**
     * 评论ID，不为空
     */
    private Long commentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0表示未删除，1表示已删除
     */
    private Integer isDelete;

    /**
     * 表示该评论喜欢的人数
     */
    private Integer commentLike;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}