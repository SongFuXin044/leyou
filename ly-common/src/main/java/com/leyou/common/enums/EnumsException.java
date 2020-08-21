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
    UPDATE_CATEGORY_ERROR(500,"修改失败,请稍后再试"),
    LIST_BRAND_NOT_FOUND(404,"品牌信息为查询到"),
    INSERT_BRAND_ERROR(500,"新增品牌出错"),
    INSERT_CATEGORY_BRAND_ERROR(500,"新增商品列表出错"),
    IMAGE_TYPE_ERROR(500,"文件类型错误"),
    IMAGE_NOT_FOUND(404,"文件类型为空"),
    IMAGE_UPLOAD_ERROR(500,"图片上传失败"),
    UPDATE_CATEGORY_BRAND_ERROR(500,"修改商品信息失败"),
    DELETE_BRAND_CATEGORY_ERROR(500,"删除商品信息失败")
    ;
    public int code; //状态码
    public String msg;//错误信息
}