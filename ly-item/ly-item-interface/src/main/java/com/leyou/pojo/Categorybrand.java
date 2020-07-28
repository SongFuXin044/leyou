package com.leyou.pojo;

import javax.persistence.Column;
import java.io.Serializable;

public class Categorybrand implements Serializable {
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "brand_id")
    private Long brandId;

    private static final long serialVersionUID = 1L;
}