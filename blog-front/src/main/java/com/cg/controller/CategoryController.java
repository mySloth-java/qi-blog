package com.cg.controller;

import com.cg.service.CategoryService;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 文章分类信息查询
     * @return
     */
    @GetMapping
    public ResponseResult GetCategoryByIds(){
        return categoryService.GetCategoryByIds();
    }

}
