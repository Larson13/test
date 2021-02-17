package com.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.common.CustomException;
import com.reggie.common.R;
import com.reggie.entity.Category;
import com.reggie.entity.Dish;
import com.reggie.entity.Setmeal;
import com.reggie.mapper.CategoryMapper;
import com.reggie.service.CategoryService;
import com.reggie.service.DishService;
import com.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>  implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    @Override
    public R<String> remove(Long id) {

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(queryWrapper);
        if(count>0){

            throw new CustomException("当前分类下关联了口味，不能删除");
        }
        LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();

        queryWrapper1.eq(Setmeal::getCategoryId,id);

        int count1 = setmealService.count(queryWrapper1);
        if(count1>0){

            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        this.removeById(id);
        return R.success("删除成功");
    }
}
