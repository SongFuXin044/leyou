package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/26 13:57
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumsException {
    CATEGORY_LIST_NOT_FOUND(404, "商品分类为空,未找到商品....."),
    CATEGORY_ADD_ERROR(500,"新增分类失败,请稍后再试"),
    DELETE_CATEGORY_ERROR(500,"删除失败,请稍后再试"),
    UPDATE_CATEGORY_ERROR(500,"修改失败,请稍后再试")
    ;
    public int code; //状态码
    public String msg;//错误信息
}