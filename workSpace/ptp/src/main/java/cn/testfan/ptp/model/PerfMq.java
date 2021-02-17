package cn.testfan.ptp.model;

import cn.testfan.ptp.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.skywalking.apm.toolkit.trace.Trace;

@Data
@Builder
public class PerfMq {


    @Trace
    public String getPerf() throws InterruptedException {
        Thread.sleep(3000);
        return DateUtil.getIncreDayString("yyyy-MM-dd",0);
    }
}
