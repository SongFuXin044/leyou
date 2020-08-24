package com.leyou.common.pojo;

import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

@RegisterMapper
public interface BaseMapper<T,PK> extends Mapper<T>,SelectByIdListMapper<T,PK> , DeleteByIdListMapper<T,PK> , InsertListMapper<T> {
}
