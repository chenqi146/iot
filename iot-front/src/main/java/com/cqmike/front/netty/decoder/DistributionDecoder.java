package com.cqmike.front.netty.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: iot
 * @ClassName: DistributionDecoder
 * @Description: 消息分发处理类
 * @Author: chen qi
 * @Date: 2020/3/7 10:54
 * @Version: 1.0
 **/
@Slf4j
public class DistributionDecoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
