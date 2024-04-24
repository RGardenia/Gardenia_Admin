package com.zy.common.utils.query.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


@Schema(name = "分页")
@SuppressWarnings("unchecked")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -1683800405530086022L;

    @Schema(description = "总行数")
    @JSONField(name = "total")
//    @JsonProperty("total")
    private long total = 0;

    @Schema(description = "数据列表")
    @JSONField(name = "records")
//    @JsonProperty("records")
    private List<T> records = Collections.emptyList();

    public Paging() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
