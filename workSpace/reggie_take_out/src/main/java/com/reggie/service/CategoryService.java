package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.common.R;
import com.reggie.entity.Category;

public interface CategoryService extends IService<Category> {
    R<String> remove(Long id);
}
