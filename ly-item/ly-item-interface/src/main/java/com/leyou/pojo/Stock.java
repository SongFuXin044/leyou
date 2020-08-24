package com.leyou.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_stock")
public class Stock implements Serializable {
    @Id
    @Column(name = "sku_id")
    private Long skuId;

    @Column(name = "seckill_stock")
    private Integer seckillStock;

    @Column(name = "seckill_total")
    private Integer seckillTotal;

    @Column(name = "stock")
    private Integer stock;

    private static final long serialVersionUID = 1L;
}