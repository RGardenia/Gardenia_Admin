//package com.zy.common.utils.query.param;
//
//import cn.hutool.core.collection.CollectionUtil;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Data
//@EqualsAndHashCode(callSuper = true)
//@Schema(name = "可排序查询参数对象")
//public abstract class OrderQueryParam extends QueryParam {
//    private static final long serialVersionUID = 57714391204790143L;
//
//    @Schema(description = "排序")
//    private List<OrderItem> orders;
//
//    public void defaultOrder(OrderItem orderItem) {
//        this.defaultOrders(Arrays.asList(orderItem));
//    }
//
//    public void defaultOrders(List<OrderItem> orderItems) {
//        if (CollectionUtil.isEmpty(orderItems)) {
//            return;
//        }
//        this.orders = orderItems;
//    }
//
//}
