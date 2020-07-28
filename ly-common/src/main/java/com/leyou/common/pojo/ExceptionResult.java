package com.leyou.common.pojo;

import com.leyou.common.enums.EnumsException;
import lombok.Data;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/26 14:08
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(EnumsException em) {
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
