package com.leyou.web;

import com.leyou.common.pojo.PageResult;
import com.leyou.pojo.Brand;
import com.leyou.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/8/3 15:38
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Resource
    private BrandService brandService;

    /***
     * 商品分页查询
     * @param page
     * @param rows
     * @param desc
     * @param key
     * @param sortBy
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "sortBy",required = false)String sortBy
    ){
        return ResponseEntity.ok(brandService.queryBrandByPage(page,rows,desc,key,sortBy));
    }

    /**
     *添加商品信息
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> InsertBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        brandService.InsertBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 修改商品信息
     * @param brand
     * @param cids
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> UpdateBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        brandService.UpdateBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除商品
     * @param bid
     * @return
     */
    @DeleteMapping("{bid}")
    public ResponseEntity<Void> UpdateBrand(@PathVariable Long bid){
        brandService.DeleteBrand(bid);
        System.out.println("bid = " + bid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据分类Category_ID来查询Brand表信息
     * @param cid
     * @return
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable Long cid){
        System.out.println(cid);
        return ResponseEntity.ok(brandService.queryBrandByCid(cid));
    }
}
