package org.aji.mall.service.impl;

import org.aji.mall.consts.MallConst;
import org.aji.mall.dao.CategoryMapper;
import org.aji.mall.pojo.Category;
import org.aji.mall.service.ICategoryService;
import org.aji.mall.vo.CategoryVo;
import org.aji.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> findAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryVo> CategoryVoList = new ArrayList<>();
        for (Category category : categories) {
            if(category.getParentId().equals(MallConst.INIT_PARENT_ID)){
                CategoryVo categoryVo = category2CategoryVo(category);
                CategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                CategoryVoList.add(categoryVo);
            }
        }
        findSubCategoryList(CategoryVoList,categories);

        return ResponseVo.success(CategoryVoList);
    }

    private void findSubCategoryList(List<CategoryVo> CategoryVoList, List<Category> categories){
        for (CategoryVo categoryVo : CategoryVoList) {
            List<CategoryVo> subCategoryList = new ArrayList<>();
            for (Category category : categories) {
                if(categoryVo.getId().equals(category.getParentId())) {
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoryList.add(subCategoryVo);
                }
            }
            //list排序，按照sortorder降序排序
            subCategoryList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
            categoryVo.setSubCategoryList(subCategoryList);
            //递归
            findSubCategoryList(subCategoryList,categories);
        }
    }

    private CategoryVo category2CategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
