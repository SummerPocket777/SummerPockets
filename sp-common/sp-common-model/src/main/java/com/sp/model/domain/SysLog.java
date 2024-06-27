package com.sp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统日志表
 * @TableName sys_log
 */
@TableName(value ="sys_log")
@Data
public class SysLog implements Serializable {
    /**
     * 日志id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商铺id
     */
    private Long businessId;

    /**
     * 操作内容
     */
    private String logContent;

    /**
     * 操作结果
     */
    private String logResult;

    /**
     * 入参字符串
     */
    private String inputParams;

    /**
     * 出参 json字符串
     */
    private String outputParams;

    /**
     * 日志发生事件，用时间戳便于排序
     */
    private Date createTime;

    /**
     * 0未删除 1删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}