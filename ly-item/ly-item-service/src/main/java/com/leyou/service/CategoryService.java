package com.leyou.service;

import com.leyou.common.enums.EnumsException;
import com.leyou.common.exceptions.lyException;
import com.leyou.mapper.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/28 14:41
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 根据父级Id进行分类查询
     * @param pid
     * @return
     */
    public List<Category> queryByPid(Long pid){
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categories = categoryMapper.select(category);
        if (categories.isEmpty()){
            throw new lyException(EnumsException.CATEGORY_LIST_NOT_FOUND);
        }
        return categories;
    }

    /**
     * 新增分类
     * @param category
     */
    @Transactional
    public void AddCategory(Category category){
        int result = categoryMapper.insertSelective(category);
        if (result == 0){
            throw new lyException(EnumsException.CATEGORY_ADD_ERROR);
        }
    }

    /**
     * 根据ID删除分类
     * @param id
     */
    @Transactional
    public void DeleteCategory(Long id){
        int result = categoryMapper.deleteByPrimaryKey(id);
        if (result == 0){
            throw new lyException(EnumsException.DELETE_CATEGORY_ERROR);
        }
    }

    /**
     * 修改分类
     * @param category
     */
    @Transactional
    public void UpdateCategory(Category category){
        int result = categoryMapper.updateByPrimaryKeySelective(category);
        if (result == 0){
            throw new lyException(EnumsException.UPDATE_CATEGORY_ERROR);
        }
    }
}
