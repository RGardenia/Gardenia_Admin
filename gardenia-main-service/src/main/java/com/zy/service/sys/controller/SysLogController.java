package com.zy.service.sys.controller;

import com.zy.common.utils.Result;
import com.zy.common.utils.query.QueryGenerator;
import com.zy.service.sys.entity.SysLog;
import com.zy.service.sys.service.SysLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import jakarta.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zy-栀
 * @create 2023-02-44 13:57:03
 * @describe 系统日志表   前端控制器
 */
@Api(tags = "系统日志表")
@RestController
@RequestMapping("/sys/sysLog01")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

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
    @ApiOperation(value = "系统日志表-分页列表查询", notes = "系统日志表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<SysLog>> queryPageList(SysLog sysLog,
                                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                               HttpServletRequest req) {
        QueryWrapper<SysLog> queryWrapper = QueryGenerator.initQueryWrapper(sysLog, req.getParameterMap());
        Page<SysLog> page = new Page<SysLog>(pageNo, pageSize);
        IPage<SysLog> pageList = sysLogService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param sysLog01
     * @return
     */
    @ApiOperation(value = "系统日志表-添加", notes = "系统日志表-添加")
    //@RequiresPermissions("${entityPackage}:${tableName}:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody SysLog sysLog01) {
        sysLogService.save(sysLog01);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sysLog01
     * @return
     */
    @ApiOperation(value = "系统日志表-编辑", notes = "系统日志表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody SysLog sysLog01) {
        sysLogService.updateById(sysLog01);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "系统日志表-通过id删除", notes = "系统日志表-通过id删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        sysLogService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "系统日志表-批量删除", notes = "系统日志表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sysLogService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "系统日志表-通过id查询")
    @ApiOperation(value = "系统日志表-通过id查询", notes = "系统日志表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Object> queryById(@RequestParam(name = "id", required = true) String id) {
        SysLog sysLog = sysLogService.getById(id);
        if (sysLog == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(sysLog);
    }

}
