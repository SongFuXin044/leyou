package com.leyou.web;

import com.leyou.pojo.Specgroup;
import com.leyou.pojo.Specparam;
import com.leyou.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Resource
    private SpecificationService specificationService;


    /**
     * 根据分类ID查询分组
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<Specgroup>> querySpecGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(specificationService.querySpecGroupByCid(cid));
    }

    /**
     * 查询分组
     *
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<Specparam>> queryParamByGid(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "cid", required = false) Long cid){
        return ResponseEntity.ok(specificationService.queryParamByGid(gid,cid,searching));
    }

    /**
     * 添加规格
     *
     * @param specparam
     * @return
     */
    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody Specparam specparam) {
        System.out.println("specparam = " + specparam);
        specificationService.addSpecParam(specparam);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 添加修改规格
     *
     * @param specparam
     * @return
     */
    @PutMapping("param")
    public ResponseEntity<Void> UpdateSpecParam(@RequestBody Specparam specparam) {
        System.out.println("specparam = " + specparam);
        specificationService.UpdateSpecParam(specparam);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 添加分组
     *
     * @param specgroup
     * @return
     */
    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroup(@RequestBody Specgroup specgroup) {
        System.out.println("specgroup = " + specgroup);
        specificationService.addSpecGroup(specgroup);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 修改分组
     *
     * @param specgroup
     * @return
     */
    @PutMapping("group")
    public ResponseEntity<Void> UpdateSpecGroup(@RequestBody Specgroup specgroup) {
        System.out.println("specgroup = " + specgroup);
        specificationService.UpdateSpecGroup(specgroup);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除分组
     *
     * @param id
     * @return
     */
    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteSpecGroupByCid(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        specificationService.deleteSpecGroupByCid(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除规格
     *
     * @param id
     * @return
     */
    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteSpecParamid(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        specificationService.deleteSpecParamByid(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
