//package com.allion.issuetracker.service;
//
//import com.allion.issuetracker.model.User;
//import io.github.resilience4j.ratelimiter.RateLimiter;
//import io.github.resilience4j.ratelimiter.RateLimiterConfig;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class WebClientRateLimit
//{
//    private static final AtomicInteger COUNTER = new AtomicInteger(0);
//
//    private final WebClient webClient;
//    private final RateLimiter rateLimiter;
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public WebClientRateLimit()
//    {
//        this.webClient = WebClient.create();
//
//        // enables 3 requests every 5 seconds
//        this.rateLimiter = RateLimiter.of("my-rate-limiter",
//                RateLimiterConfig.custom()
//                        .limitRefreshPeriod(Duration.ofSeconds(120))
//                        .limitForPeriod(1)
//                        .timeoutDuration(Duration.ofMinutes(3)) // max wait time for a request, if reached then error
//                        .build());
//    }
//
//    public WebClientRateLimit(int i){
//
//        // enables 3 requests every 5 seconds
//        this.webClient = WebClient.create();
//        this.rateLimiter = RateLimiter.of("my-rate-limiter",
//                RateLimiterConfig.custom()
//                        .limitRefreshPeriod(Duration.ofSeconds(120))
//                        .limitForPeriod(1)
//                        .timeoutDuration(Duration.ofMinutes(3)) // max wait time for a request, if reached then error
//                        .build());
//    }
//
//    public Mono<?> call()
//    {
//        return webClient.get()
//                .uri("https://jsonplaceholder.typicode.com/todos/1")
//                .retrieve()
//                .toEntity(User.class)
//               .doOnSubscribe(s -> System.out.println(COUNTER.incrementAndGet() + " - " + LocalDateTime.now()
//                       + " - call triggered"))
//                .transformDeferred(RateLimiterOperator.of(rateLimiter));
//    }
//
////    public Mono<?> callFromRest()
////    {
////        return restTemplate.exchange()..getForEntity("https://jsonplaceholder.typicode.com/todos/1",User.class)
////               .doOnSubscribe(s -> System.out.println(COUNTER.incrementAndGet() + " - " + LocalDateTime.now()
////            + " - call triggered"))
////            .transformDeferred(RateLimiterOperator.of(rateLimiter));
////    }
//
//    public static void main(String[] args)
//    {
//        WebClientRateLimit webClientRateLimit = new WebClientRateLimit();
//
//        long start = System.currentTimeMillis();
//
//        Flux.range(1, 3)
//                .flatMap(x -> webClientRateLimit.call())
//                .blockLast();
//
//        System.out.println("Elapsed time in seconds: " + (System.currentTimeMillis() - start) / 1000d);
//    }
//}
