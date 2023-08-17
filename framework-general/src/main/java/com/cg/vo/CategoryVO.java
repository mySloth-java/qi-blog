package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryVO {
    //唯一标识
    private Long id;
    //分类名称
    private String name;
    //分类描述
    private String description;
}
