package com.cg.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cg.entity.Category;
import com.cg.mapper.ArticlesDao;
import com.cg.mapper.CategoryDao;
import com.cg.service.CategoryService;
import com.cg.vo.CategoryVO;
import com.cg.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ArticlesDao articlesDao;

    /**
     * 分类信息查询
     * @return
     */
    @Override
    public ResponseResult GetCategoryByIds() {
        //1、获取到所有非草稿文章的分类id，根据分类id查询所有的存在的分类名称
        List<Integer> categoryIds = articlesDao.GetCategoryIds();

        //2、根据多个id查询分类表获取分类信息并封装结果返回
        List<Category> categories = categoryDao.GetCategoryByIds(categoryIds);

        //3、封装VO返回体
        List<CategoryVO> articlesVOS = BeanUtil.copyToList(categories, CategoryVO.class);

        return ResponseResult.okResult(articlesVOS);
    }
}
