package com.lx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.common.QueryPageParam;
import com.lx.common.Result;
import com.lx.entity.User;
import com.lx.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lixiang
 * @since 2023-04-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //查询
    @GetMapping("/list")
    public Result list(){
        return Result.success(userService.list());
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    //修改
    @PutMapping("/mod")
    public Result mod(@RequestBody User user){
        userService.updateById(user);
        return Result.success();
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public Result saveOrMod(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.success();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.removeById(id);
        return Result.success();
    }

    //模糊查询
    @PostMapping("/listQuery")
    public Result listQuery(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }
        return Result.success(userService.list(lambdaQueryWrapper));
    }

    //分页查询
    @PostMapping("/pageQuery")
    public Result pageQuery(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String name = (String)param.get("name");

        Page<User> page = new Page<>(query.getPageNum(),query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }

        IPage result = userService.page(page,lambdaQueryWrapper);

        return Result.success(result);
    }
}
