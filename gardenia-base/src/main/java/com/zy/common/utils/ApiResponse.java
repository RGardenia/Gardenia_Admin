package com.zy.common.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @Author Gardenia
 * @Description TODO
 * @Date 2024/4/25 13:55
 * @Version 1.0
 */



@Data
@Builder
public class ApiResponse<T> {
    @Schema(name = "code", description = "状态码")
    private Integer code;

    @Schema(name = "msg", description = "消息结果")
    private String msg;

    @Schema(name = "data", description = "数据")
    private T data;

    /**
     * 成功统一响应格式
     *
     * @param
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success() {
        ApiResponse<T> apiResponse = new ApiResponse<>(200, "成功", null);
        return apiResponse;
    }

    /**
     * 成功统一响应格式
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>(200, "成功", data);
        return apiResponse;
    }

    /**
     * 失败统一响应格式
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResponse<T> fail(Integer code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
