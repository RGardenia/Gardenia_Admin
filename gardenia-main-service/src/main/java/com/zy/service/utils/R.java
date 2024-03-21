package com.zy.service.utils;

import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyu
 */
@Data
public class R {

    @SchemaProperty(name = "是否成功")
    private Boolean success;

    @SchemaProperty(name = "返回码")
    private Integer code;

    @SchemaProperty(name = "返回消息")
    private String message;

    @SchemaProperty(name = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 构造器私有化
     */
    private R() {
    }

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object name) {
        this.data.put(key, name);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
