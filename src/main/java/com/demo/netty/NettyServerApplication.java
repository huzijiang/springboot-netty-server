package com.demo.netty;

import com.demo.netty.server.BootNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author huzj
 */
@SpringBootApplication
@EnableAsync
public class NettyServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application=new SpringApplication(NettyServerApplication.class);

        application.run(args);

        System.out.println("Hello,Netty server!");
    }

    @Async
    @Override
    public void run(String... args) throws  Exception{
        new BootNettyServer().bind(8888);
    }
}


