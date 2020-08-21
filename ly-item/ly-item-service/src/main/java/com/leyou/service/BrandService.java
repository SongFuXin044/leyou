package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.EnumsException;
import com.leyou.common.exceptions.lyException;
import com.leyou.common.pojo.PageResult;
import com.leyou.mapper.BrandMapper;
import com.leyou.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/8/3 15:38
 */
@Service
public class BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 品牌管理
     * @param page
     * @param rows
     * @param desc
     * @param key
     * @param sortBy
     * @return
     */
    public PageResult<Brand> queryBrandByPage(
            Integer page,
            Integer rows,
            Boolean desc,
            String key,
            String sortBy
    ){
        //处理分页
        PageHelper.startPage(page,rows);
        //创建Example实例
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        /**
         * 条件处理
         * 如果输入关键词key就根据key来查找
         * 如果输入letter就把letter转换为大写通过首字母来查询
         * WHERE 'name' LIKE "%x%" OR letter =='x'
         * ORDER BY id DESC
         */
        if (StringUtils.isNotBlank(key)){
            System.out.println("key = " + key);
            //条件过滤
            criteria.orLike("name","%"+key+"%").orEqualTo("letter",key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)){
            System.out.println("desc = " + desc);
            System.out.println("sortBy = " + sortBy);
            //三元运算符来判断是升序还是降序
            example.setOrderByClause(sortBy+(desc?" desc":" asc"));
        }
        //开始查询
        List<Brand> brandList = brandMapper.selectByExample(example);
        System.out.println("brandList = " + brandList);
        //判断 如果查询到的数据为空就抛出自定义异常
        if (CollectionUtils.isEmpty(brandList)){
            throw new lyException(EnumsException.LIST_BRAND_NOT_FOUND);
        }
        //分页数据封装
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        //返回PageResult类
        return new PageResult<>(pageInfo.getTotal(),brandList);
    }

    /**
     * 添加商品信息
      * @param brand
     * @param cids
     */
    public void InsertBrand(Brand brand, List<Long> cids) {
        //先向tb_brand中添加商品信息
        int result = brandMapper.insert(brand);
        if (result != 1){
            //如果添加失败就返回一个自定义的异常
            throw new lyException(EnumsException.INSERT_BRAND_ERROR);
        }
        SaveBrandAndCategory(brand.getId(), cids);
    }

    /**
     * 提取出来的公用方法  对分类表进行处理
     * @param bid
     * @param cids
     */
    private void SaveBrandAndCategory(Long bid, List<Long> cids) {
        //前端传回来的分类信息父类ID
        for (Long cid : cids) {
            System.out.println("cid = " + cid);
            //自定义的MyBatis方法  对tb_CateGoryBrand表进行数据绑定
            int count = brandMapper.InsertBrandCategory(cid, bid);
            System.out.println("count = " + count);
            if (count != 1){
                //数据绑定失败就抛出自定义异常
                throw new lyException(EnumsException.INSERT_CATEGORY_BRAND_ERROR);
            }
        }
    }

    /**
     *
     * @param brand
     * @param cids
     */
    @Transactional
    public void UpdateBrand(Brand brand, List<Long> cids) {
        //对brand进行修改
        int result = brandMapper.updateByPrimaryKeySelective(brand);
        //对分类表进行处理
        //1.先删除分类表原来的关系
        result = brandMapper.deleteBrandCategoryByBid(brand.getId());
        if (result == 0 ){
            throw new lyException(EnumsException.UPDATE_CATEGORY_BRAND_ERROR);
        }
        //2.添加新的分类关系
        SaveBrandAndCategory(brand.getId(),cids);
    }

    /**
     * 删除商品
     * @param bid
     */
    @Transactional
    public void DeleteBrand(Long bid) {
        //1.先删除brand表信息
        int result = brandMapper.deleteByPrimaryKey(bid);
        //2.删除BrandCategory表关联
        result = brandMapper.deleteBrandCategoryByBid(bid);
        if (result == 0 ){
            throw new lyException(EnumsException.DELETE_BRAND_CATEGORY_ERROR);
        }
    }
}
