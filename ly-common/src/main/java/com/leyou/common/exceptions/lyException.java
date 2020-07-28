package com.leyou.common.exceptions;

import com.leyou.common.enums.EnumsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/26 13:58
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class lyException  extends RuntimeException{
    public EnumsException enumsException;
}
