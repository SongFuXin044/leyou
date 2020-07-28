package com.leyou.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_order_status")
public class Orderstatus implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "payment_time")
    private Date paymentTime;

    @Column(name = "consign_time")
    private Date consignTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "close_time")
    private Date closeTime;

    @Column(name = "comment_time")
    private Date commentTime;

    private static final long serialVersionUID = 1L;
}