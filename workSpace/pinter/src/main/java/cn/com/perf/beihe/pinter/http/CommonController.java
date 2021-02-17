package cn.com.perf.beihe.pinter.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.perf.beihe.pinter.http.base.BaseController;
import cn.com.perf.beihe.pinter.mode.*;
import cn.com.perf.beihe.pinter.servlet.UserService;
import cn.com.perf.beihe.pinter.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/com"})
public class CommonController extends BaseController {
    @Autowired
    UserService userService;
    @GetMapping({"/getSku"})
    public RestResponse<Object> getSku(Integer id) {
        if (id == null)
            return wrap("1", "skuId不能为空");
        if (id.intValue() <= 0)
            return wrap("1", "skuId不合法");
                    Sku sku = new Sku();
        sku.setSkuId(id);
        sku.setSkuName("ptest-" + id);
        sku.setPrice(String.valueOf((int)(Math.random() * 1000.0D)));
        sku.setStock(Integer.valueOf((int)(Math.random() * 1000.0D)));
        sku.setBrand("testfan");
        return wrap(sku);
    }

    @PostMapping({"/login"})
    public RestResponse<Object> login(String userName, String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            return wrap("1", "用户名或密码不能为空");
        try {
            Thread.sleep(5L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "success");
    }

    @PostMapping(value = {"/register"}, headers = {"Content-type=application/json"})
    public RestResponse<Object> register(@RequestBody User user) {
        if (user == null)
            return wrap("1", "注册失败");
        try {
            Thread.sleep(10L);
            userService.insert(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "注册成功");
    }

    @PostMapping({"/buy"})
    public RestResponse<Object> buy(String param) {
        if (StringUtils.isBlank(param))
            wrap("1", "param is null", Boolean.valueOf(false));
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "success", param);
    }

    @PostMapping(value = {"/userInfo"}, headers = {"Content-type=application/json"})
    public RestResponse<Object> getUserInfo(@RequestBody UserQuery query) {
        String serverOptCode = "testfan";
        if (StringUtils.isBlank(query.getPhoneNum()) ||
                StringUtils.isBlank(query.getOptCode()) ||
                StringUtils.isBlank(query.getTimestamp()) ||
                StringUtils.isBlank(query.getSign()))
            return wrap("1", "param is null");
        try {
            Thread.sleep(15L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long clientTime = Long.valueOf(Long.parseLong(query.getTimestamp()));
        Long nowTime = Long.valueOf(System.currentTimeMillis());
        if (nowTime.longValue() - clientTime.longValue() > 60000L)
            return wrap("1", "timestamp is vaild");
        String serverSign = Md5Utils.md5(query.getPhoneNum() + serverOptCode + query.getTimestamp());
        if (serverSign.equals(query.getSign().toUpperCase())) {
            User user = new User();
            user.setPhoneNum(query.getPhoneNum());
            user.setAge(Integer.valueOf((int)(Math.random() * 100.0D)));
            user.setAddress("中国");
                    user.setEmail("shuneng@163.com");
            user.setGender(Integer.valueOf(1));
            user.setId(Integer.valueOf((int)(Math.random() * 10000.0D)));
            user.setUserName("性能测试");
            return wrap(user);
        }
        return wrap("1", "invaild sign data");
    }

    @GetMapping({"/userList"})
    public RestResponse<Object> getUserList(Integer genderType) {
        if (genderType == null)
            return wrap("1", "param is null");
        if (genderType.intValue() < 0 || genderType.intValue() > 1)
            return wrap("1", "invalid genderType,0 or 1");
        try {
            Thread.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Integer.valueOf(i));
            user.setUserName("性能测试"+ i);
                    user.setPhoneNum("1378888" + (int)(Math.random() * 1000.0D + 1000.0D));
            user.setAge(Integer.valueOf((int)(Math.random() * 100.0D)));
            user.setAddress("中国北京");
                    user.setEmail("beihe@testfan.com");
            user.setGender(genderType);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userList.add(user);
        }
        return wrap(userList);
    }

    @GetMapping({"/phone/{phoneId}"})
    public RestResponse<Object> getPhone(@PathVariable Integer phoneId) {
        if (phoneId == null)
            return wrap("1", "phoneId不能为空");
        if (phoneId.intValue() <= 0)
            return wrap("1", "phoneId不合法");
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Phone phone = new Phone();
        phone.setId(phoneId);
        phone.setRandomBrand();
        phone.setSystemVersion("12.0");
        phone.setRandomColor();
        phone.setRandomMemorySize();
        phone.setCpuCore("8核");
                phone.setPrice("8848");
        phone.setDesc("1");
                phone.setOperator("df");
                        phone.setScreenSize("5.5");
        phone.setBatterySize("10000ma");
        phone.setPhoneSize("100*200*300");
        phone.setCamera("1200W");
        return wrap(phone);
    }

    @PostMapping(value = {"/phone"}, headers = {"Content-type=application/json"})
    public RestResponse<Object> postPhone(@RequestBody Phone phone) {
        if (phone == null)
            return wrap("1", "参数不能为空");
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "提交成功");
    }

    @PutMapping(value = {"/phone"}, headers = {"Content-type=application/json"})
    public RestResponse<Object> putPhone(@RequestBody Phone phone) {
        if (phone == null)
            return wrap("1", "参数不能为空");
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "提交成功");
    }

    @DeleteMapping(value = {"/phone"}, headers = {"Content-type=application/json"})
    public RestResponse<Object> deletePhone(@RequestBody Phone phone) {
        if (phone == null)
            return wrap("1", "参数不能为空");
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wrap("0", "提交成功");
    }
}
