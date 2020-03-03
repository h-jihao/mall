package org.aji.mall.controller;

import org.aji.mall.service.ICategoryService;
import org.aji.mall.vo.CategoryVo;
import org.aji.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/categories")
    private ResponseVo<List<CategoryVo>> categoryList(){
        return categoryService.findAll();
    }

}
