package com.zy.service.sys.controller;

import io.netty.handler.codec.http.HttpMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import com.zy.service.sys.entity.SysUser;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表   前端控制器
 */
@Tag(name = "用户表")
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {


    /**
     * 分页列表查询
     *
     * @param sysUser  列表查询
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @Operation(summary = "用户表-分页列表查询", description = "用户表-分页列表查询")
    @GetMapping(value = "/list")
    public Flux<ArrayList<SysUser>> queryPageList(SysUser sysUser,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                HttpServletRequest req) {
//        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
//        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
//        IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);
        return Flux.just(new ArrayList<SysUser>());
    }

    /**
     * 添加
     *
     * @param sysUser
     * @return
     */
    @Operation(summary = "用户表-添加", description = "用户表-添加")
    @PostMapping(value = "/add")
    public Flux<String> add(@RequestBody SysUser sysUser) {
//        sysUserService.save(sysUser);
        return Flux.just("添加成功！");
    }

    /**
     * 编辑
     * @param sysUser
     * @return
     */
    @Operation(summary = "用户表-编辑", description = "用户表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Flux<String> edit(@RequestBody SysUser sysUser) {
//        sysUserService.updateById(sysUser);
        return Flux.just("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @Operation(summary = "用户表-通过id删除", description = "用户表-通过id删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:delete")
    @DeleteMapping(value = "/delete")
    public Flux<String> delete(@RequestParam(name = "id", required = true) String id) {
//        sysUserService.removeById(id);
        return Flux.just("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Operation(summary = "用户表-批量删除", description = "用户表-批量删除")
    //@RequiresPermissions("${entityPackage}:${tableName}:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Flux<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.sysUserService.removeByIds(Arrays.asList(ids.split(",")));
        return Flux.just("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "用户表-通过id查询")
    @Operation(summary = "用户表-通过id查询", description = "用户表-通过id查询")
    @GetMapping(value = "/queryById")
    public Flux<Object> queryById(@RequestParam(name = "id", required = true) String id,
                                  ServerWebExchange exchange,
                                  WebSession webSession,
                                  HttpMethod method,
                                  HttpEntity<String> entity,
                                  @RequestBody String s){
//        SysUser sysUser = sysUserService.getById(id);
//        if (sysUser == null) {
//            return Flux.just("未找到对应数据");
//        }

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String name = method.name();

        Object gardenia = webSession.getAttribute("Gardenia");
        webSession.getAttributes().put("Gardenia", "zy");

        return Flux.empty();
    }

}
