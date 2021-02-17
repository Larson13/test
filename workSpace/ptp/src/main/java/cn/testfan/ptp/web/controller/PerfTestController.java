package cn.testfan.ptp.web.controller;

import cn.testfan.ptp.model.PerfMq;
import cn.testfan.ptp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PerfTestController {



    @GetMapping(value = "/perf/test")
    @ResponseBody
    public String getPerfTest() throws InterruptedException {

        Thread.sleep(2000);
        CommonUtils.changeFistCap("234342");
        return PerfMq.builder().build().getPerf();
    }

}
