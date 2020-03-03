package org.aji.mall.service;

import org.aji.mall.vo.CategoryVo;
import org.aji.mall.vo.ResponseVo;

import java.util.List;

public interface ICategoryService {

    ResponseVo<List<CategoryVo>> findAll();
}
