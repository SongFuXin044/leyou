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
    PIRCE_CANNOT_BE_NULL(400, "价格不能为空"),
    ;
    public int code; //状态码
    public String msg;//错误信息
}