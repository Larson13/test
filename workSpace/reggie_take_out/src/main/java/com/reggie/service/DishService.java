package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.entity.Dish;
import com.reggie.entity.DishDto;

public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
}
