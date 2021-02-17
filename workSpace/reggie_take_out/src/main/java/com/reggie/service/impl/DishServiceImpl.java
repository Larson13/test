package com.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.entity.Dish;
import com.reggie.entity.DishDto;
import com.reggie.entity.DishFlavor;
import com.reggie.mapper.DishMapper;
import com.reggie.service.DishFlavorService;
import com.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        //保存菜品
        log.info("新增菜品 : ${}",dishDto);
        this.save(dishDto);
        Long id = dishDto.getId();
        log.info("新增菜品id  :{}",id);

        //获取菜品品味
        List<DishFlavor> flavors = dishDto.getFlavors();
       //设置口味id
       flavors=flavors.stream().map((item ->{
           item.setDishId(id);
           return item;
       } )).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavor

        dishFlavorService.saveBatch(flavors);



    }
}
