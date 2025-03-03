package com.tdd.learn.spring.subwayApiV1.respository;

import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

// Reactive Repositories are not supported by JPA;
//public interface PassengerDataRepository extends ReactiveCrudRepository<PassengerData, Long> {
//    Mono<PassengerData> findByStation(String station);
//}

