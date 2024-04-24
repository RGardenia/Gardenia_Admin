package com.zy.service.sys.controller;

import com.zy.service.sys.entity.SysLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @auther zy-栀
 * @create 2023-02-44 13:57:03
 * @describe 系统日志表   前端控制器
 */
@Tag(name = "系统日志表")
@RestController
@RequestMapping("/sys/sysLog01")
public class SysLogController {

//    @Autowired
//    private SysLogService sysLogService;

    /**
     * 分页列表查
     *
     * @param sysLog 列表查询
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "系统日志表-分页列表查询")
//    @Operation(summary = "系统日志表-分页列表查询", description = "系统日志表-分页列表查询")
//    @GetMapping(value = "/list")
//    public Flux<IPage<SysLog>> queryPageList(SysLog sysLog,
//                                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
//                                             HttpServletRequest req) {
//        QueryWrapper<SysLog> queryWrapper = QueryGenerator.initQueryWrapper(sysLog, req.getParameterMap());
//        Page<SysLog> page = new Page<SysLog>(pageNo, pageSize);
//        IPage<SysLog> pageList = sysLogService.page(page, queryWrapper);
//        return Flux.just(pageList);
//    }

    /**
     * 添加
     *
     * @param sysLog01
     * @return
     */
    @Operation(summary = "系统日志表-添加", description = "系统日志表-添加")
    //@RequiresPermissions("${entityPackage}:${tableName}:add")
    @PostMapping(value = "/add")
    public Flux<String> add(@RequestBody SysLog sysLog01) {
//        sysLogService.save(sysLog01);
        return Flux.just("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sysLog01
     * @return
     */
    @Operation(summary = "系统日志表-编辑", description = "系统日志表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Flux<String> edit(@RequestBody SysLog sysLog01) {
//        sysLogService.updateById(sysLog01);
        return Flux.just("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @Operation(summary = "系统日志表-通过id删除", description = "系统日志表-通过id删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:delete")
    @DeleteMapping(value = "/delete")
    public Flux<String> delete(@RequestParam(name = "id", required = true) String id) {
//        sysLogService.removeById(id);
        return Flux.just("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Operation(summary = "系统日志表-批量删除", description = "系统日志表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Flux<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.sysLogService.removeByIds(Arrays.asList(ids.split(",")));
        return Flux.just("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "系统日志表-通过id查询")
    @Operation(summary = "系统日志表-通过id查询", description = "系统日志表-通过id查询")
    @GetMapping(value = "/queryById")
    public Flux<Object> queryById(@RequestParam(name = "id", required = true) String id) {
//        SysLog sysLog = sysLogService.getById(id);
//        if (sysLog == null) {
//            return Flux.just("未找到对应数据");
//        }
        return Flux.empty();
    }

}
