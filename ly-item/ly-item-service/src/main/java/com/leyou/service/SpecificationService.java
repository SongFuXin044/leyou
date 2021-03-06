package com.leyou.service;

import com.leyou.common.enums.EnumsException;
import com.leyou.common.exceptions.lyException;
import com.leyou.mapper.SpecgroupMapper;
import com.leyou.mapper.SpecparamMapper;
import com.leyou.pojo.Specgroup;
import com.leyou.pojo.Specparam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpecificationService {
    @Resource
    private SpecgroupMapper specgroupMapper;

    @Resource
    private SpecparamMapper specparamMapper;

    /**
     * 查询分组
     * @param cid
     * @return
     */
    public List<Specgroup> querySpecGroupByCid(Long cid){
        Specgroup specgroup = new Specgroup();
        specgroup.setCid(cid);
        List<Specgroup> specgroups = specgroupMapper.select(specgroup);
        if (CollectionUtils.isEmpty(specgroups)){
            throw new lyException(EnumsException.SELECT_SPECGROUP_LIST_NOTFOUND);
        }
        return specgroups;
    }

    /**
     * 根据分组ID查询规格参数
     *
     * @param gid
     * @param cid
     * @param searching
     * @return
     */
    public List<Specparam> queryParamByGid(Long gid, Long cid, Boolean searching) {
        Specparam specparam = new Specparam();
        specparam.setGroupId(gid);
        specparam.setCid(cid);
        specparam.setSearching(searching);
        List<Specparam> specparams = specparamMapper.select(specparam);
        return specparams;
    }

    /**
     * 添加分组
     * @param specgroup
     */
    public void addSpecGroup(Specgroup specgroup) {
        int result = specgroupMapper.insertSelective(specgroup);
        if (result == 0){
            throw new lyException(EnumsException.ADD_SPECGROUP_ERROR);
        }
    }

    /**
     * 修改分组
     * @param specgroup
     */
    public void UpdateSpecGroup(Specgroup specgroup) {
        int result = specgroupMapper.updateByPrimaryKeySelective(specgroup);
        if (result == 0){
            throw new lyException(EnumsException.UPDATE_SPECGROUP_ERROR);
        }
    }

    public void deleteSpecGroupByCid(Long id) {
        int result = specgroupMapper.deleteByPrimaryKey(id);
        if (result == 0){
            throw new lyException(EnumsException.DELETE_SPECGROUP_ERROR);
        }
    }

    public void addSpecParam(Specparam specparam) {
        int result = specparamMapper.insertSelective(specparam);
        if (result == 0){
            throw new lyException(EnumsException.ADD_SPECPARAM_ERROR);
        }
    }

    public void UpdateSpecParam(Specparam specparam) {
        int result = specparamMapper.updateByPrimaryKeySelective(specparam);
        if (result == 0){
            throw new lyException(EnumsException.UPDATE_SPECPARAM_ERROR);
        }
    }

    public void deleteSpecParamByid(Long id) {
        int result = specparamMapper.deleteByPrimaryKey(id);
        if (result == 0){
            throw new lyException(EnumsException.DELETE_SPECPARAM_ERROR);
        }
    }
}
