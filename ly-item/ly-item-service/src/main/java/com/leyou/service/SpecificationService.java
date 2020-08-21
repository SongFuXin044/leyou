package com.leyou.service;

import com.leyou.mapper.SpecgroupMapper;
import com.leyou.mapper.SpecparamMapper;
import com.leyou.pojo.Specgroup;
import com.leyou.pojo.Specparam;
import org.springframework.stereotype.Service;

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
        return specgroups;
    }

    /**
     * 根据分组ID查询规格参数
     * @param gid
     * @return
     */
    public List<Specparam> queryParamByGid(Long gid) {
        Specparam specparam = new Specparam();
        specparam.setGroupId(gid);
        List<Specparam> specparams = specparamMapper.select(specparam);
        return specparams;
    }

    /**
     * 添加分组
     * @param specgroup
     */
    public void addSpecGroup(Specgroup specgroup) {
        int result = specgroupMapper.insertSelective(specgroup);
    }

    /**
     * 修改分组
     * @param specgroup
     */
    public void UpdateSpecGroup(Specgroup specgroup) {
        int result = specgroupMapper.updateByPrimaryKeySelective(specgroup);
    }

    public void deleteSpecGroupByCid(Long id) {
        int result = specgroupMapper.deleteByPrimaryKey(id);
    }

    public void addSpecParam(Specparam specparam) {
        int result = specparamMapper.insertSelective(specparam);
    }

    public void UpdateSpecParam(Specparam specparam) {
        int result = specparamMapper.updateByPrimaryKeySelective(specparam);
    }

    public void deleteSpecParamByid(Long id) {
        int result = specparamMapper.deleteByPrimaryKey(id);
    }
}
