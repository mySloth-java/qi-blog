package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页实体返回封装
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    //分页数据实体
    private List rows;
    //统计数
    private Integer total;
}
