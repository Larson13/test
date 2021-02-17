package com.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class StompSamplerGui  extends AbstractSamplerGui {

    private final JLabeledTextField serverIp = new JLabeledTextField("ServerIp");
    private final JLabeledTextField port = new JLabeledTextField("Port");
    private final JLabeledTextField path = new JLabeledTextField("Path");
    private final JLabeledTextField username = new JLabeledTextField("Username");
    private final JLabeledTextField password = new JLabeledTextField("Password");
    private final JLabeledTextField destination = new JLabeledTextField("Destination");

    private final JLabeledTextField msg = new JLabeledTextField("Msg");
    private final JLabeledTextField headers = new JLabeledTextField("Headers");
    private final JLabeledTextField type = new JLabeledTextField("Type");

    private void init() {
        log.info("Initializing the UI.");
        setLayout(new BorderLayout());
        setBorder(makeBorder());

        add(makeTitlePanel(), BorderLayout.NORTH);
        JPanel mainPanel = new VerticalPanel();
        add(mainPanel, BorderLayout.CENTER);

        JPanel DPanel = new JPanel();
        DPanel.setLayout(new GridLayout(3, 2));
        DPanel.add(serverIp);
        DPanel.add(port);
        DPanel.add(path);
        DPanel.add(username);
        DPanel.add(destination);
        DPanel.add(password);
        DPanel.add(msg);
        DPanel.add(headers);
        DPanel.add(type);
        JPanel ControlPanel = new VerticalPanel();
        ControlPanel.add(DPanel);
        ControlPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Parameters"));
        mainPanel.add(ControlPanel);
    }

    public StompSamplerGui() {
        super();
        this.init();
    }

    @Override
    public String getLabelResource() {
        throw new IllegalStateException("This shouldn't be called");

    }

    @Override
    public String getStaticLabel() {
        return  "StompClinet Sampler";
    }

    @Override
    public TestElement createTestElement() {
        StompSampler sampler = new StompSampler();
        this.setupSamplerProperties(sampler);
        return sampler;
    }

    private void setupSamplerProperties(StompSampler sampler) {
        this.configureTestElement(sampler);
        sampler.setServerIp(serverIp.getText());
        sampler.setPort(Integer.valueOf(port.getText()));
        sampler.setPath(path.getText());
        sampler.setDestination(destination.getText());
        sampler.setContent(msg.getText());
        sampler.setHeaders(headers.getText());
        sampler.setPassword(password.getText());
        sampler.setUsername(username.getText());
        sampler.setType(type.getText());
    }

    /**
     * 这个方法用于把界面的数据移到Sampler中。
     * @param testElement
     */
    @Override
    public void modifyTestElement(TestElement testElement) {
        StompSampler sampler = (StompSampler) testElement;
        this.setupSamplerProperties(sampler);


    }


    /**
     * 界面与Sampler之间的数据交换
     * @param element
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        StompSampler sampler = (StompSampler) element;
        this.serverIp.setText(sampler.getServerIp());
        this.port.setText(sampler.getPort().toString());
        this.path.setText(sampler.getPath());
        this.destination.setText(sampler.getDestination());
        this.msg.setText(sampler.getContent());
        this.headers.setText(sampler.getHeaders());
        this.password.setText(sampler.getPassword());
        this.username.setText(sampler.getUsername());
        this.type.setText(sampler.getType());
    }

    /**
     * 该方法会在reset新界面的时候调用，这里可以填入界面控件中需要显示的一些缺省的值(就是默认显示值)
     */
    @Override
    public void clearGui() {
        super.clearGui();
        this.serverIp.setText("服务端ip");
        this.port.setText("9099");
        this.path.setText("端点");
        this.destination.setText("Tpoic");
        this.headers.setText("{\"content-type\":\"text/plain\"}");
        this.username.setText("用户名");
        this.password.setText("密码");
        this.msg.setText("发送内容");
        this.type.setText(("请求类型: http or ws"));
    }
}
