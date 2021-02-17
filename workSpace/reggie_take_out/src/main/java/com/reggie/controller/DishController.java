package com.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.entity.Category;
import com.reggie.entity.Dish;
import com.reggie.entity.DishDto;
import com.reggie.entity.DishFlavor;
import com.reggie.service.CategoryService;
import com.reggie.service.DishFlavorService;
import com.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    DishFlavorService dishFlavorService;

    @Autowired
    CategoryService categoryService;
    @RequestMapping
    public R<String> save(@RequestBody DishDto dishDto){

        dishService.saveWithFlavor(dishDto);

        return R.success("新增成功");
    }

     @RequestMapping("/page")
    public R<Page<DishDto>> page(int page,int pageSize,String name){
        // 构造分页条件对象
        Page<Dish> pageinfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        // 构造查询件对象
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);

        dishService.page(pageinfo,queryWrapper);
        //将数据复制到dto
        BeanUtils.copyProperties(pageinfo,dishDtoPage,"records");
        List<Dish> records = pageinfo.getRecords();
        List<DishDto> dishDtos = records.stream().map((item ->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();
            //查询菜品分类名名
            Category category = categoryService.getById(categoryId);

            if(category !=null){
                dishDto.setCategoryName(category.getName());
            }
            return  dishDto;

        })).collect(Collectors.toList());


        dishDtoPage.setRecords(dishDtos);






        return R.success(dishDtoPage);
    }

    @GetMapping("/{id}")
    public R<DishDto> getDish(@PathVariable Long id){
         //根据id菜品
        Dish dish =dishService.getById(id);
        DishDto dishDto = new DishDto();
        log.info("DsihDto ---------------: {}",dish);
        BeanUtils.copyProperties(dish,dishDto);
        //查品味

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId,id);

        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return R.success(dishDto);
    }

    @PutMapping
    @Transactional
    public R<String> update(@RequestBody DishDto dishDto){
        log.info("DsihDto ---------------: {}",dishDto);
        //修修菜品
        dishService.updateById(dishDto);

        //清理当前菜品对应口味数据---dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
    //添加当前提交过来的口味数据---dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
        return  R.success("修改成功");
    }

}
