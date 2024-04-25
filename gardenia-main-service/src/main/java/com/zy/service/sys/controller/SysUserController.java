package com.zy.service.sys.controller;

import com.zy.common.utils.ApiResponse;
import com.zy.service.sys.service.SysUserService;
import io.netty.handler.codec.http.HttpMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zy.service.sys.entity.SysUser;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表
 */
@Tag(name = "用户表", description = "用户管理")
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
     * @return
     */
    @Operation(summary = "用户表-分页列表查询", description = "用户表-分页列表查询")
    @GetMapping(value = "/list")
    public Mono<ApiResponse<List<SysUser>>> queryPageList(SysUser sysUser,
                                                          @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
//        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
//        IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);
        Flux<SysUser> userInfo = sysUserService.getAllUserInfo();
        return userInfo.collectList().map(user -> ApiResponse.success(user));
    }

    /**
     * 添加
     *
     * @param sysUser
     * @return
     */
    @Operation(summary = "用户表-添加", description = "用户表-添加")
    @PostMapping(value = "/add")
    public Mono<ApiResponse> add(@RequestBody SysUser sysUser) {
        Mono<SysUser> userMono = sysUserService.saveUser(sysUser);
        return userMono.thenReturn(ApiResponse.success());
    }

    /**
     * 编辑
     * @param sysUser
     * @return
     */
    @Operation(summary = "用户表-编辑", description = "用户表-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Mono<ApiResponse> edit(@RequestBody SysUser sysUser) {
        Mono<SysUser> userMono = sysUserService.updateUser(sysUser);
        return userMono.thenReturn(ApiResponse.success());
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @Operation(summary = "用户表-通过 id 删除", description = "用户表-通过 id 删除")
    @DeleteMapping(value = "/delete")
    public Mono<ApiResponse> delete(@RequestParam(name = "id", required = true) String id) {
        Mono<Void> userMono = sysUserService.deleteUser(id);
        return userMono.thenReturn(ApiResponse.success());
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
    public Mono<ApiResponse<SysUser>> queryById(@RequestParam(name = "id", required = true) String id){
//                                  ServerWebExchange exchange,
//                                  WebSession webSession,
//                                  HttpMethod method,
//                                  HttpEntity<String> entity,
//                                  @RequestBody String s
        Mono<SysUser> user = sysUserService.getUserById(id);
        Mono<ApiResponse<SysUser>> userMono = user.map(ApiResponse::success);
        return userMono;

//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        String name = method.name();
//
//        Object gardenia = webSession.getAttribute("Gardenia");
//        webSession.getAttributes().put("Gardenia", "zy");

    }

}
