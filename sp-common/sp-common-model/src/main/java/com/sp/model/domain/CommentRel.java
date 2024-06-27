package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 评论关系表
 * @TableName comment_rel
 */
@TableName(value ="comment_rel")
@Data
public class CommentRel implements Serializable {
    /**
     * 关系ID，不为空
     */
    @TableId
    private Long id;

    /**
     * 父级评论ID
     */
    private Long parentId;

    /**
     * 子级评论ID，不为空
     */
    private Long childId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}