package com.leyou.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_order")
public class Order implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "total_pay")
    private Long totalPay;

    @Column(name = "actual_pay")
    private Long actualPay;

    @Column(name = "promotion_ids")
    private String promotionIds;

    @Column(name = "payment_type")
    private Boolean paymentType;

    @Column(name = "post_fee")
    private Long postFee;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_code")
    private String shippingCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "buyer_message")
    private String buyerMessage;

    @Column(name = "buyer_nick")
    private String buyerNick;

    @Column(name = "buyer_rate")
    private Boolean buyerRate;

    @Column(name = "receiver_state")
    private String receiverState;

    @Column(name = "receiver_city")
    private String receiverCity;

    @Column(name = "receiver_district")
    private String receiverDistrict;

    @Column(name = "receiver_address")
    private String receiverAddress;

    @Column(name = "receiver_mobile")
    private String receiverMobile;

    @Column(name = "receiver_zip")
    private String receiverZip;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "invoice_type")
    private Integer invoiceType;

    @Column(name = "source_type")
    private Integer sourceType;

    private static final long serialVersionUID = 1L;
}