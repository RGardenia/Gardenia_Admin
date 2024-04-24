package com.zy.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceMainApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello, World!");
    }

    @DisplayName("测试 WebFlux 接口🌰")
    @Test
    void testWebFlux() throws Exception {

        Flux.just(1, 2, 3)
                .transformDeferredContextual((flux, context) -> {
                    System.out.println("context = " + context.get("prefix"));
                    return flux.map(i -> i + "==>" + context.get("prefix"));
                })
//                , context -> context.put("key", "value"))
                .contextWrite(Context.of("prefix", "Gardenia_"))
                .subscribe(v -> System.out.println("接收到的数据：" + v));

//        Flux.range(1, 100)
//                .buffer(10)
//                .publishOn(Schedulers.newParallel("parallel", 3))
//                .parallel(3)
//                .runOn(Schedulers.newParallel("parallel"))
//                .log()
//                .flatMap(Flux::fromIterable)
//                .collectSortedList(Integer::compareTo)
//                .subscribe(v -> System.out.println("接收到的数据：" + v));
//
//        List<Integer> block = Flux.just(1, 2, 3)
//                .map(i -> i + 9)
//                .collectList()
//                .block();
//        System.out.println(block);

//        // 单播   只能一个消费者
//        Sinks.many().unicast();
//        // 多播   多个消费者
//        Sinks.many().multicast();
//        // 重放   多个消费者，且消费者可以重复消费数据
//        Sinks.many().replay().all();
//
//        //  限流   只能有一个消费者，且消费者可以重复消费数据
//        Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer(new LinkedBlockingQueue<>(9));
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                objectMany.tryEmitNext(i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//        objectMany.asFlux().subscribe(System.out::println);
//
//        // 默认订i阅者,从订阅的那一刻开始接元素
//        Sinks.Many<Object> objectManyMulticast = Sinks.many().multicast().onBackpressureBuffer();
//
//        // 发布者 数据 重放
//        Sinks.Many<Object> limit = Sinks.many().replay().limit(3);
//
//        System.in.read();

//        Flux<Integer> map = Flux.just(1, 0, 3)
//                .map(i -> 100 / i);
//
//        map.onErrorReturn(NullPointerException.class, 0)
//                .onErrorStop()
//                .doOnError(err -> {
//                    System.out.println("\ndoOnError:\nerr 已被记录 = " + err);
//                })
//                .onErrorContinue((err, val) -> {
//                    System.out.println("\nonErrorContinue:\nerr = " + err);
//                    System.out.println("val = " + val);
//                    System.out.println("发现 " + val + " 有问题了，继续执行其他的，我会记录这个问题 \n");
//                })
//                .doFinally(signalType -> {
//                    System.out.println("流信号：" + signalType);
//                })
//                .subscribe(v -> System.out.println("v : " + v),
//                        err -> System.out.println("err : " + err),
//                        () -> System.out.println("complete ~"));
//
//        map.onErrorReturn(NullPointerException.class, 0)
//                .onErrorResume(err -> Mono.just(0))
//                .onErrorMap(err -> new NullPointerException(err.getMessage() + ": 又炸了..."))
//                .onErrorComplete()
//                .subscribe(v -> System.out.println("v : " + v),
//                        err -> System.out.println("err : " + err),
//                        () -> System.out.println("complete ~"));

//        System.in.read();

//        AtomicInteger atomicInteger = new AtomicInteger();
//        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6)
//                .transformDeferred(v -> atomicInteger.incrementAndGet() == 1 ? v : v.delayElements(Duration.ofMillis(100)));
//        flux.subscribe(v -> System.out.println("接收到的数据：" + v));
//
//        Flux.just("gardenia_zy", "asd_asd")
//                .flatMap(v -> {
//                    String[] s = v.split("_");
//                    return Flux.fromArray(s);
//                })
//                .zipWith(Flux.just(1, 2, 3, 4))
//                .log()
//                .map(tuple -> tuple.getT1() + " " + tuple.getT2())
//                .log()
//                .subscribe();
//
//        Flux.just(1, 2, 3, 4, 5, 6)
//                .concatMap(s -> Flux.just(s * 10, 1) )
//                .log()
//                .subscribe();
    }

//    @Autowired
//    KafkaTemplate kafkaTemplate;

    @DisplayName("测试 Kafka 接口🌰")
    @Test
    void tetsKafaka() throws ExecutionException, InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        CompletableFuture[] futures = new CompletableFuture[100];
        for (int i = 0; i < 100; i++) {
//            CompletableFuture send = kafkaTemplate.send("news", "order.create." + i, "订单创建了：" + i);
//            futures[i] = send;
        }
        CompletableFuture.allOf(futures).join();
        watch.stop();
        System.out.println("总耗时：" + watch.getTotalTimeMillis());
    }

    // 表示单条规则的类
    public static class Rule {
        private String ruleContent;

        private String ruleOrder;
    }

    @DisplayName("测试外部接口🌰")
    @Test
    void testApiAvailability() {
        String url = "http://120.26.59.7:23262/docxFile2rule";

        // 指定要上传的文件路径
        String filePath = "D:\\Documents\\WeChat Files\\wxid_sxbldpuy9ydo22\\FileStorage\\File\\2024-04/北京市网络预约出租汽车经营服务管理实施细则.docx";

        // 创建一个HttpRequest对象，并设置为POST方法
        HttpRequest request = HttpRequest.post(url);

        // 使用multipart方式添加文件。第一个参数为服务端接收的参数名，第二个参数是文件的路径
        request.form("file", FileUtil.file(filePath), "北京市网络预约出租汽车经营服务管理实施细则.docx");

        // 发送请求并接收响应
        HttpResponse response = request.execute();

        // 检查响应状态码是否为200
        assertEquals(200, response.getStatus(), "API不可用");

        // 使用HuTool的JSON工具解析响应内容
        String body = response.body();
//        System.out.println("Response Body: " + body);

        // 举例解析JSON响应
        if (JSONUtil.isJson(body)) {
            Object result = JSONUtil.parse(body);

            // 将Object转换为Map，以便于访问其内部结构
            Map<String, Rule> resultMap = (Map<String, Rule>) result;

            // 从Map中获取ruleList
            List<Map<String, Rule>> ruleList = (List<Map<String, Rule>>) resultMap.get("ruleList");


            for (Map<String, Rule> rule : ruleList) {
                System.out.println(rule.get("rule_content") + ": " + rule.get("rule_order"));
            }

            // 这里你可以进一步检查解析出的结果
            assertNotNull(result, "响应内容为空");
        } else {
            fail("响应内容不是有效的JSON");
        }
    }

}
