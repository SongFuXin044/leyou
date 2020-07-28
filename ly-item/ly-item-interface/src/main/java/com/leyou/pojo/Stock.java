package com.leyou.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_stock")
public class Stock implements Serializable {
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