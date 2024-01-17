package com.zy.service.sys.controller;

import com.zy.common.utils.Result;
import com.zy.common.utils.query.QueryGenerator;
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

import com.zy.service.sys.entity.SysUser;
import com.zy.service.sys.mapper.SysUserMapper;
import com.zy.service.sys.service.SysUserService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表   前端控制器
 */
@Api(tags = "用户表")
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页列表查询
     *
     * @param sysUser  列表查询
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value = "用户表-分页列表查询", notes = "用户表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<SysUser>> queryPageList(SysUser sysUser,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req) {
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "用户表-添加", notes = "用户表-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "用户表-编辑", notes = "用户表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户表-通过id删除", notes = "用户表-通过id删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        sysUserService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "用户表-批量删除", notes = "用户表-批量删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sysUserService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "用户表-通过id查询")
    @ApiOperation(value = "用户表-通过id查询", notes = "用户表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Object> queryById(@RequestParam(name = "id", required = true) String id) {
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(sysUser);
    }

}
