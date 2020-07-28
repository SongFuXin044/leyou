package com.leyou.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

@Data
@Table(name = "tb_spu_detail")
public class Spudetail implements Serializable {
    @Column(name = "spu_id")
    private Long spuId;

    @Column(name = "generic_spec")
    private String genericSpec;

    @Column(name = "special_spec")
    private String specialSpec;

    @Column(name = "packing_list")
    private String packingList;

    @Column(name = "after_service")
    private String afterService;

    @Column(name = "description")
    private String description;

    private static final long serialVersionUID = 1L;
}