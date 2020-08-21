package com.leyou.web;

import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/28 14:42
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 查询所有分类
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryByPid(Long pid){
        System.out.println("pid = " + pid);
        return ResponseEntity.ok(categoryService.queryByPid(pid));
    }

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<Void> AddCategory(@RequestBody Category category){
        System.out.println("category = " + category);
        categoryService.AddCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("delete")
    public ResponseEntity<Void> DeleteCategory(Long id){
        System.out.println("id = " + id);
        categoryService.DeleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @PostMapping("edit")
    public ResponseEntity<Void> UpdateCategory(@RequestBody Category category){
        System.out.println("category = " + category);
        categoryService.UpdateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid) {
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
