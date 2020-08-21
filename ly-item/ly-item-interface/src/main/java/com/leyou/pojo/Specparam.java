package com.leyou.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tb_spec_param")
public class Specparam implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "cid")
    private Long cid;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name")
    private String name;

    @Column(name = "`numeric`")
    private Boolean numeric;

    @Column(name = "unit")
    private String unit;

    @Column(name = "generic")
    private Boolean generic;

    @Column(name = "searching")
    private Boolean searching;

    @Column(name = "segments")
    private String segments;

    private static final long serialVersionUID = 1L;
}