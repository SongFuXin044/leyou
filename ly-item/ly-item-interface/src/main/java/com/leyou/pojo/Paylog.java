package com.leyou.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_pay_log")
public class Paylog implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "total_fee")
    private Long totalFee;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "pay_type")
    private Boolean payType;

    @Column(name = "bank_type")
    private String bankType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "pay_time")
    private Date payTime;

    @Column(name = "closed_time")
    private Date closedTime;

    @Column(name = "refund_time")
    private Date refundTime;

    private static final long serialVersionUID = 1L;
}