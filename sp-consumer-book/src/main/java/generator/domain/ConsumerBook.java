package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName consumer_book
 */
@TableName(value ="consumer_book")
@Data
public class ConsumerBook implements Serializable {
    private Integer bookId;

    private Integer bookNumber;

    private Date bookDate;

    private String bookName;

    private String bookPhone;

    private Integer isStatus;

    private Integer isDelete;

    private Integer consumerId;

    private Date bookCreatedate;

    private static final long serialVersionUID = 1L;
}