package cn.com.perf.beihe.pinter.http;

import cn.com.perf.beihe.pinter.dao.UserMapper;
import cn.com.perf.beihe.pinter.http.base.BaseController;
import cn.com.perf.beihe.pinter.mode.RestResponse;
import cn.com.perf.beihe.pinter.mode.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/env"})
public class EnvController extends BaseController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping({"/search"})
    @ResponseBody
    public RestResponse<Object> env(Integer id) {
        if (id == null)
            return wrap("1", "参数为空");
                    String json = (String)this.redisTemplate.opsForValue().get("id_" + id);
        if (json == null) {
            User user = this.userMapper.selectByPrimaryKey(id);
            json = JSON.toJSONString(user);
            this.redisTemplate.opsForValue().set("id_" + id, json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        return wrap(jsonObject);
    }
}
