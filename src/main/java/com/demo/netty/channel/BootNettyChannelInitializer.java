package com.demo.netty.channel;

import com.demo.netty.adapter.BootNettyChannelInboundHandlerAdapter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 通道初始化
 *
 * @Author huzj
 * @Date 2020/9/9 11:20
 * @Version 1.0
 */
public class BootNettyChannelInitializer<SocketChannel> extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        /** encoder  编码器
         * ChannelOutboundHandler，依照逆序执行
         */
        channel.pipeline().addLast("encoder",new StringEncoder());

        /**
         * decoder  解码器
         * 属于ChannelInboundHandler，依照顺序执行
         */
        channel.pipeline().addLast("decoder",new StringDecoder());

        /**
         * 自定义
         */
        channel.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());

    }

}
