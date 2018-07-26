package com.cherry.product.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/7/25 下午5:33
 */
@Slf4j
@Component
@EnableBinding(StreamInput.class)
public class MqReceiver {

    @StreamListener("cherryOutput")
    public void process(Object message){
        log.info("receive message:{}",message);
        log.info("--------------------------------------------------");
    }
}
