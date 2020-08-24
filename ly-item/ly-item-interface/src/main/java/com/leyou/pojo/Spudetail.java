package com.leyou.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_spu_detail")
public class Spudetail implements Serializable {
    @Id
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