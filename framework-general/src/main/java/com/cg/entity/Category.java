package com.cg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (ArticlesClassify)实体类
 *
 * @author makejava
 * @since 2023-08-15 14:04:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    //唯一标识
    private Long id;
    //分类名称
    private String name;
    //分类描述
    private String description;
    //逻辑删除标志(0为否，1为删除)
    private String delFlag;
}

