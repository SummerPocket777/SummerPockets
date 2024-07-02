package com.sp.to;

import lombok.Data;

@Data
public class BookTo {
    private long bookId;
    private long bookNumber;
    private String bookDate;
    private String bookName;
    private String bookPhone;
    private long isStatus;
    private long isDelete;
    private long consumerId;
}
