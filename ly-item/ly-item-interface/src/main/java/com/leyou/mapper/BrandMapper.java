package com.leyou.mapper;

import com.leyou.common.pojo.BaseMapper;
import com.leyou.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand,Long> {
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int InsertBrandCategory(@Param("cid")Long cid,@Param("bid")Long bid);

    @Delete("DELETE FROM tb_category_brand where brand_id = #{bid}")
    int deleteBrandCategoryByBid(Long bid);

    @Select("SELECT * FROM tb_brand WHERE id in (SELECT brand_id FROM tb_category_brand WHERE category_id = #{cid} )")
    List<Brand> queryBrandByCid(Long cid);

}