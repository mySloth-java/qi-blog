package com.cg.mapper;

import com.cg.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {

    //动态id查询分类信息
    List<Category> GetCategoryByIds(@Param("ids") List<Integer> ids);
}
