package com.cherry.product.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Description:
 * Author: cherry
 * Date: Created in 2018/7/25 下午5:29
 */
public interface StreamInput {
    @Input("cherryOutput")
    SubscribableChannel input();
}