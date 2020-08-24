package com.leyou.web;

import com.leyou.common.pojo.PageResult;
import com.leyou.pojo.Sku;
import com.leyou.pojo.Spu;
import com.leyou.pojo.Spudetail;
import com.leyou.service.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    /**
     * 商品页面分页
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<Spu>>querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "key",required = false) String key
    ){
        return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,saleable,key));
    }

    /**
     * 商品新增
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> SaveGoods(@RequestBody Spu spu){
        goodsService.SaveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 商品修改
     * @param spu
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu){
        goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 商品删除
     * @param spuId
     * @return
     */
    @DeleteMapping("spu/delete/{spuId}")
    public ResponseEntity<Void> updateGoods(@PathVariable Long spuId){
        System.out.println("spuId = " + spuId);
        goodsService.DeleteGoods(spuId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 上下架
     * @param spuId
     * @return
     */
    @PutMapping("spu/{spuId}/saleable/{saleable}")
    public ResponseEntity<Void> updateSaleable(@PathVariable("spuId") Long spuId,@PathVariable("saleable") Boolean saleable){
        System.out.println("spuId = " + spuId);
        System.out.println("saleable = " + saleable);
        goodsService.updateSaleable(spuId,saleable);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查询SpuId来查询SpuDetail
     * @param SpuId
     * @return
     */
    @GetMapping("spu/detail/{SpuId}")
    public ResponseEntity<Spudetail> querySpuDetailBySpuId(@PathVariable Long SpuId){
        return ResponseEntity.ok(goodsService.querySpuDetailBySpuId(SpuId));
    }

    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(Long SpuId){

        return ResponseEntity.ok(goodsService.querySkusBySpuId(SpuId));
    }

}
