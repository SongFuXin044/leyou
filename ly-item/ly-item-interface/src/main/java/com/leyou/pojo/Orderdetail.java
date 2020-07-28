package com.leyou.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_order_detail")
public class Orderdetail implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "num")
    private Integer num;

    @Column(name = "title")
    private String title;

    @Column(name = "own_spec")
    private String ownSpec;

    @Column(name = "price")
    private Long price;

    @Column(name = "image")
    private String image;

    private static final long serialVersionUID = 1L;
}