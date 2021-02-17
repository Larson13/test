package cn.com.perf.beihe.pinter.http;

import cn.com.perf.beihe.pinter.dao.PayMapper;
import cn.com.perf.beihe.pinter.dao.UserMapper;
import cn.com.perf.beihe.pinter.http.base.BaseController;
import cn.com.perf.beihe.pinter.mode.Order;
import cn.com.perf.beihe.pinter.mode.Phone;
import cn.com.perf.beihe.pinter.mode.RestResponse;
import cn.com.perf.beihe.pinter.mode.User;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/case"})
public class CaseController extends BaseController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PayMapper payMapper;

    private static Logger logger = Logger.getLogger(cn.com.perf.beihe.pinter.http.CaseController.class);

    private static Object obj1 = new Object();

    private static Object obj2 = new Object();

    private static Object obj3 = new Object();

    private static Object[] objs = new Object[2];

    @GetMapping({"/block"})
    public RestResponse<Object> block(Integer id) {
        if (id == null)
            return wrap("1", "参数为空");
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int randNum = (int)(Math.random() * 100.0D);
        if (randNum > 99) {
            logger.error("用户["+ id + "]参与抽奖,抽奖结果为:恭喜你获得了【特等奖】");
        } else {
            logger.info("用户["+ id + "]参与抽奖,抽奖结果为:很遗憾没有中奖,请继续努力");
        }
        logger.info("{\"Category\":[{\"categoryId\":1,\"categoryName\":\"user1\",\"categoryImage\":\"/upload/yinpin.jpg\"},{\"categoryId\":2,\"categoryName\":\"user2\",\"categoryImage\":\"/upload/shiping.jpg\"},{\"categoryId\":3,\"categoryName\":\"user3\",\"categoryImage\":\"/upload/jiullei.jpg\"}],\"recommend\":{\"id\":11,\"productName\":\"user4+user5+user6+user7\",\"filenameSmall\":\"/upload/ty_ltpj_small.jpg\",\"productPrice\":48,\"productCost\":47.5}}");
        logger.info("{\"Category\":[{\"categoryId\":1,\"categoryName\":\"user1\",\"categoryImage\":\"/upload/yinpin.jpg\"},{\"categoryId\":2,\"categoryName\":\"user2\",\"categoryImage\":\"/upload/shiping.jpg\"},{\"categoryId\":3,\"categoryName\":\"user3\",\"categoryImage\":\"/upload/jiullei.jpg\"}],\"recommend\":{\"id\":11,\"productName\":\"user4+user5+user6+user7\",\"filenameSmall\":\"/upload/ty_ltpj_small.jpg\",\"productPrice\":48,\"productCost\":47.5}}");
        logger.info("{\"Category\":[{\"categoryId\":1,\"categoryName\":\"user1\",\"categoryImage\":\"/upload/yinpin.jpg\"},{\"categoryId\":2,\"categoryName\":\"user2\",\"categoryImage\":\"/upload/shiping.jpg\"},{\"categoryId\":3,\"categoryName\":\"user3\",\"categoryImage\":\"/upload/jiullei.jpg\"}],\"recommend\":{\"id\":11,\"productName\":\"user4+user5+user6+user7\",\"filenameSmall\":\"/upload/ty_ltpj_small.jpg\",\"productPrice\":48,\"productCost\":47.5}}");
        return wrap("0", "success");
    }

    @GetMapping({"/dead"})
    @ResponseBody
    public RestResponse<Object> dead() {
        Object[] objects = getObjs();
        String msg = null;
        synchronized (objects[0]) {
            try {
                Thread.sleep(100L);
                synchronized (objects[1]) {
                    msg = "success";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wrap("0", msg);
    }

    public static Object[] getObjs() {
        int noIndex = (int)(Math.random() * 6.0D);
        switch (noIndex) {
            case 0:
                objs[0] = obj1;
                objs[1] = obj2;
                break;
            case 1:
                objs[0] = obj1;
                objs[1] = obj3;
                break;
            case 2:
                objs[0] = obj2;
                objs[1] = obj3;
                break;
            case 3:
                objs[0] = obj2;
                objs[1] = obj1;
                break;
            case 4:
                objs[0] = obj3;
                objs[1] = obj1;
                break;
            case 5:
                objs[0] = obj3;
                objs[1] = obj2;
                break;
        }
        return objs;
    }

    @GetMapping({"/cpu"})
    @ResponseBody
    public RestResponse<Object> cpu(Integer type) {
        if (type == null)
            return wrap("1", "参数为空");
                    Phone phone = createPhone();
        String data = null;
        if (type.intValue() == 1) {
            Gson gson = new Gson();
            for (int i = 0; i < 1000; i++)
                data = gson.toJson(phone);
        } else if (type.intValue() == 2) {
            for (int i = 0; i < 1000; i++)
                data = JSON.toJSONString(phone);
        }
        return wrap(data);
    }

    @GetMapping({"/time"})
    @ResponseBody
    public RestResponse<Object> time(Integer id) {
        if (id == null)
            return wrap("1","参数为空");
                    getDataFromDb();
        getDataFromRedis();
        getDataFromOuter();
        process();
        return wrap();
    }

    @GetMapping({"/slow"})
    @ResponseBody
    public RestResponse<Object> slow(String userName, String email) throws InterruptedException {

        if (userName == null)
            return wrap("1", "参数为空");
                    User user = null;
        if (email == null) {
            user = this.userMapper.selectByUserName(userName);

        } else {
            User queryUser = new User();
            queryUser.setUserName(userName);
            queryUser.setEmail(email);
            user = this.userMapper.selectByUserNameAndEmail(queryUser);
        }
        return wrap(user);
    }

    @PostMapping(value = {"/pay"}, headers = {"Content-type=application/json"})
    @ResponseBody
    public RestResponse<Object> pay(@RequestBody Order order) {
        if (order == null)
            return wrap("1", "参数为空");
        if (order.getOrderId() == null)
            return wrap("1", "参数为空");
        try {
            this.payMapper.insert(order);
        } catch (Exception e) {
            logger.error("数据异常："+ order);
            return wrap("1","数据异常");
        }
        return wrap(order.getId());
    }

    public Phone createPhone() {
        Phone phone = new Phone();
        phone.setId(Integer.valueOf((int)Math.random() * 1000));
        phone.setBrand("iPhone");
        phone.setSystemVersion("12.0");
        phone.setColor("black");
        phone.setMemorySize("128GB");
        phone.setCpuCore("8核");
                phone.setPrice("8848");
        phone.setDesc("全新iPhone");
                phone.setOperator("中国移动/联通/电信");
                        phone.setScreenSize("5.5");
        phone.setBatterySize("10000ma");
        phone.setPhoneSize("100*200*300");
        phone.setCamera("1200W");
        return phone;
    }

    public void getDataFromDb() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromRedis() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getDataFromOuter() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}