package com.leyou.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_sku")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "title")
    private String title;

    @Column(name = "images")
    private String images;

    @Column(name = "price")
    private Long price;

    @Column(name = "indexes")
    private String indexes;

    @Column(name = "own_spec")
    private String ownSpec;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Transient
    private Integer stock;

    private static final long serialVersionUID = 1L;
}