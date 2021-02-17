package cn.com.perf.beihe.pinter.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:test.properties"})
public class TestConfig {
    @Value("${name}")
    public String name;
}
