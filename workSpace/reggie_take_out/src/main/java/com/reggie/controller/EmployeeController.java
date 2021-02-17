package com.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.entity.Employee;
import com.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private Employee emp;

    @RequestMapping("/login")
    public R<Employee> login(HttpSession httpSession, @RequestBody Employee employee) {
        log.info("登陆帐帐号： {}", employee);

        // 将页面提交的密码password进行md5加密处理,
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        //根据页面提交的用户名username查询数据库中员工数据信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());

        Employee emp = employeeService.getOne(queryWrapper);
        //如果没有查询到, 则返回登录失败结果
        if (emp == null) {

            return R.error("帐号不存在，请重新输入");
        }
        //如果没有查询到, 则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("密码错误，请重新输入");
        }
        // 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("该帐号已禁用");

        }
        //6、登录成功，将员工id存入Session并返回登录成功结果
        httpSession.setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @RequestMapping("/logout")
    public R<String> logout(HttpSession httpSession) {
        //1.获取session中的employee
        Object employee = httpSession.getAttribute("employee");
        log.info("从session中获取： {}", employee);
        //删除sesion
        httpSession.removeAttribute("employee");
        return R.success("登出成功");
    }

    @RequestMapping
    public R<String> save(HttpSession httpSession, @RequestBody Employee employee) {
        log.info("新增用户: {}", employee);

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
      //  employee.setCreateTime(LocalDateTime.now());
      //  employee.setUpdateTime(LocalDateTime.now());

        Long employeeId = (Long) httpSession.getAttribute("employee");
       // employee.setCreateUser(employeeId);
       // employee.setUpdateUser(employeeId);
        //初始化用户状态
        employee.setStatus(1);
//        //查询用户是否存在
//        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(employee.getUsername() !=null,Employee::getUsername,employee.getUsername());
//        int count = employeeService.count(queryWrapper);
//        if(count >=1){
//            return   R.error("用户已存在");
//
//        }
        log.info("新增用户");
        employeeService.save(employee);

        return R.success("新增成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        Page pageInfo = new Page<>(page, pageSize);
         //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    @PutMapping
    public R<String>  update(HttpSession session,@RequestBody Employee employee){
        log.info("修改employee : {}",employee);
        Long empId = (Long)session.getAttribute("employee");
      //  employee.setUpdateTime(LocalDateTime.now());
       // employee.setUpdateUser(empId);
        employeeService.updateById(employee);

        return  R.success("修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee>  getById (@PathVariable Long id){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId,id);
        Employee employee = employeeService.getOne(queryWrapper);
        log.info("用 id 查到的 employee : {}",employee);
        return  R.success(employee);
    }
}
