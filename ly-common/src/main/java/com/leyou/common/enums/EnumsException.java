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
    DELETE_BRAND_CATEGORY_ERROR(500,"删除商品信息失败"),
    SELECT_SPECGROUP_LIST_NOTFOUND(500,"分组未查询到"),
    SELECT_SPECPARAM_LIST_NOTFOUND(500,"分组未查询到"),
    ADD_SPECGROUP_ERROR(500,"添加分组错误"),
    UPDATE_SPECGROUP_ERROR(500,"修改分组错误"),
    DELETE_SPECPARAM_ERROR(500,"删除规格错误"),
    ADD_SPECPARAM_ERROR(500,"添加规格错误"),
    DELETE_SPECGROUP_ERROR(500,"删除分组失败"),
    UPDATE_SPECPARAM_ERROR(500,"修改规格失败"),
    SPU_INSRT_ERROR(500,"商品信息新增操作失败"),
    SPUDETAIL_INSRT_ERROR(500,"商品详情新增操作失败"),
    SKU_INSRT_ERROR(500,"商品参数新增操作失败"),
    STOCK_INSERT_ERROR(500,"库存新增操作失败"),
    SELECT_SPUDETAILBYSPUID_ERROR(500,"查询错误"),
    SPU_NOT_FOUND(404,"商品ID不存在"),
    DELETE_STOCKADNSKUS_ERROR(500,"删除SKUS和库存出错"),
    UPDATE_SPUDEATIL_ERROR(500,"更新SPUDETAIL出错"),
    UPDATE_SPU_ERROR(500,"更新SPU表错误"),
    DELETE_SPU_ERROR(500,"删除SPU表错误"),
    DELETE_SPUDETAIL_ERROR(500,"删除SPUDETAIL表错误"),
    ;
    public int code; //状态码
    public String msg;//错误信息
}