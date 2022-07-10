package com.demo.netty.adapter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 *
 *
 * I/O 数据读写处理类
 * @Author huzj
 * @Date 2020/9/9 11:32
 * @Version 1.0
 */
public class BootNettyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {

    /**
     * 从客户端 收到新的数据时，该方法会在收到消息是被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("读取消息: read msg:"+msg.toString());
        //回应客户端
        ctx.write(" I got id");
    }

    /**
     * 从客户端 收到 新的数据，读取完成时 调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取消息完成");
        ctx.flush();
    }

    /**
     *
     * 当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出现异常。即将打印异常信息");
        cause.printStackTrace();
        //抛出异常、断开与客户端的连接
        ctx.close();
    }

    /**
     * 客户端与服务端 第一次建立连接时 执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().read();
        InetSocketAddress inetSocketAddress=(InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp=inetSocketAddress.getAddress().getHostAddress();
        System.out.println("当前建立连接的IP地址：channelInactive:"+clientIp);
    }

    /**
     * 客户端与 服务端 断开连接时执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        InetSocketAddress inetSocketAddress=(InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp=inetSocketAddress.getAddress().getHostAddress();
        //断开连接时，必须关闭，否则造成资源浪费，并发量很大情况下 可能造成宕机
        ctx.close();
        System.out.println("当前断开连接的IP地址：channelInactive:"+clientIp);
    }

    /**
     *  服务端 当read 超时 是  会调用这个方法
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        InetSocketAddress inetSocketAddress=(InetSocketAddress)ctx.channel().remoteAddress();
        String clientIp=inetSocketAddress.getAddress().getHostAddress();
        //超时 断开连接
        ctx.close();
        System.out.println("超时断开连接的IP地址：channelInactive:"+clientIp);
    }

    /**
     * 注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("注册上了");
    }

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("结束注册");
    }

    /**
     * channel的写状态变化的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        System.out.println("channel写状态发生变化.");
    }
}
