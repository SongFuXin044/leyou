package com.leyou.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.EnumsException;
import com.leyou.common.exceptions.lyException;
import com.leyou.common.pojo.PageResult;
import com.leyou.mapper.*;
import com.leyou.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {
    @Resource
    private SpuMapper spuMapper;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private SpudetailMapper spudetailMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private StockMapper stockMapper;

    /**
     * 分页查询商品列表
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        //分页
        PageHelper.startPage(page,rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        System.out.println(StringUtils.isNotBlank(key));

        //模糊查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }

        //上下架处理
        if (saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }

        //默认排序  按照最后上架时间排序
        example.setOrderByClause(" last_update_time DESC");

        //开始查询
        List<Spu> spus = spuMapper.selectByExample(example);

        //解析分类名称和商品名称
        loadCategoryAndBrandName(spus);

        //解析分页结果
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);

        return new PageResult<>(spuPageInfo.getTotal(),spus);
    }

    /**
     *解析分类名称和商品名称
     * @param spus
     */
    private void loadCategoryAndBrandName(List<Spu> spus) {
        spus.forEach(spu -> {
            //先获取Brand表的名字
            spu.setBname(brandMapper.selectByPrimaryKey(spu.getBrandId()).getName());
            //土方法  查询分类
//            String cname = categoryMapper.selectByPrimaryKey(spu.getCid1()).getName()+"/"+categoryMapper.selectByPrimaryKey(spu.getCid2()).getName()+"/"+ categoryMapper.selectByPrimaryKey(spu.getCid3()).getName();
//            System.out.println("cname = " + cname);
//            spu.setCname(cname);
            //流化操作 jdk1.8新语法  一个类中只取一个变量的时候可以采用
            List<Category> categories = categoryMapper.selectByIdList(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            String cname = categories.stream().map(Category::getName).collect(Collectors.joining("/"));
            spu.setCname(cname);
        });
    }

    /**
     * 商品新增
     * @param spu
     */
    @Transactional
    public void SaveGoods(Spu spu) {
        // 1. 添加spu
        int count = 0;
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(false);
        count = spuMapper.insertSelective(spu);
        if (count == 0){
            throw new lyException(EnumsException.SPU_INSRT_ERROR);
        }
        // 2. 新增Detail表
        // 2.1 从spu实例中去除Detail对象
        Spudetail spudetail = spu.getSpuDetail();
        // 2.2 Detail表中没有自增主键, 存储的主键就是spu对应的id
        spudetail.setSpuId(spu.getId());
        System.out.println("SPU ID "+spu.getId());
        count = spudetailMapper.insertSelective(spudetail);
        if (count == 0){
            throw new lyException(EnumsException.SPUDETAIL_INSRT_ERROR);
        }
        //3.新增sku和库存
        SaveStockAndSkus(spu);
    }

    /**
     * 新增sku和库存
     * @param spu
     */
    private void SaveStockAndSkus(Spu spu) {
        List<Sku> skus = spu.getSkus();
        skus.forEach(sku ->{
            //1.1 像sku表添加数据
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());
            int result = skuMapper.insertSelective(sku);
            if (result == 0){
                throw new lyException(EnumsException.SKU_INSRT_ERROR);
            }
            //1.2 处理库存
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            result = stockMapper.insertSelective(stock);
            if (result == 0){
                throw new lyException(EnumsException.STOCK_INSERT_ERROR);
            }
        });
    }

    /**
     * 查询商品详情
     * @param spuId
     * @return
     */
    public Spudetail querySpuDetailBySpuId(Long spuId) {
        Spudetail spudetail = spudetailMapper.selectByPrimaryKey(spuId);
        System.out.println("spudetail = " + spudetail);
        if (spudetail == null){
            throw new lyException(EnumsException.SELECT_SPUDETAILBYSPUID_ERROR);
        }
        return spudetail;
    }

    /**
     * 根据SpuId来查询SKU表信息
     * @param spuId
     * @return
     */
    public List<Sku> querySkusBySpuId(Long spuId) {
        System.out.println("spuId = " + spuId);
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(sku);
        System.out.println("skus = " + skus);
        //处理库存
        skus.forEach( s -> {
            Integer stock = stockMapper.selectByPrimaryKey(s.getId()).getStock();
            System.out.println("stock = " + stock);
            s.setStock(stock);
        });
        System.out.println("skus = " + skus);
        return skus;
    }

    /**
     * 修改商品信息
     * @param spu
     */
    @Transactional
    public void updateGoods(Spu spu) {
        if (spu.getId() == null){
            throw new lyException(EnumsException.SPU_NOT_FOUND);
        }
        //查询SKU表
        List<Sku> skus = skuMapper.select(Sku.builder().spuId(spu.getId()).build());
        if (!CollectionUtils.isEmpty(skus)){
            skus.forEach(sku -> {
                //删除sku表信息 Sku表可能是多条信息
                int count = skuMapper.deleteByPrimaryKey(sku.getId());
                //删除Stock表信息
                count = stockMapper.deleteByPrimaryKey(sku.getId());
                if (count == 0){
                    throw new lyException(EnumsException.DELETE_STOCKADNSKUS_ERROR);
                }
            });
        }
        //修改spu表
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count == 0){
            throw new lyException(EnumsException.UPDATE_SPU_ERROR);
        }
        //修改SpuDetail表
        Spudetail spuDetail = spu.getSpuDetail();
        count = spudetailMapper.updateByPrimaryKeySelective(spuDetail);
        if (count == 0){
            throw new lyException(EnumsException.UPDATE_SPUDEATIL_ERROR);
        }
        //新增sku和库存
        SaveStockAndSkus(spu);
    }

    /**
     * 删除商品
     * @param spuId
     */
    @Transactional
    public void DeleteGoods(Long spuId) {
        //先删除sku和Stock
        List<Sku> skus = skuMapper.select(Sku.builder().spuId(spuId).build());
        if (!CollectionUtils.isEmpty(skus)){
            skus.forEach(sku -> {
                //删除sku表信息 Sku表可能是多条信息
                int count = skuMapper.deleteByPrimaryKey(sku.getId());
                //删除Stock表信息
                count = stockMapper.deleteByPrimaryKey(sku.getId());
                if (count == 0){
                    throw new lyException(EnumsException.DELETE_STOCKADNSKUS_ERROR);
                }
            });
        }
        //删除SpuDetail表
        int count = spudetailMapper.deleteByPrimaryKey(spuId);
        if (count == 0){
            throw new lyException(EnumsException.DELETE_SPUDETAIL_ERROR);
        }
        //删除Spu表
        count = spuMapper.deleteByPrimaryKey(spuId);
        if (count == 0){
            throw new lyException(EnumsException.DELETE_SPU_ERROR);
        }
    }

    /**
     * 上下架
     * @param spuId
     * @param saleable
     */
    public void updateSaleable(Long spuId, Boolean saleable) {
        Spu spu = new Spu();
        spu.setId(spuId);
        spu.setSaleable(saleable);
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count == 0){
            throw new lyException(EnumsException.UPDATE_SPU_ERROR);
        }
    }
}
