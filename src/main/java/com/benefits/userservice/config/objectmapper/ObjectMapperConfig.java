package com.benefits.userservice.config.objectmapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper(){

        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); // 8버전 이후에 나온 클래스들을 처리 해주기 위해서 (Optional같은)
        objectMapper.registerModule(new JavaTimeModule()); // local date같은 애들
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 모르는 json field가 있더라도 익셉션 무시하고 파싱
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // 비어있는 객체 직렬화 익셉션 무시 그냥 비어있는 객체 만듦

        // 날짜 관련 직렬화 disable
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 스네이크 케이스 디폴트 (busrfresh messageconverter 관련.. 사용 할거면 직접 응답 객체에 SnakeCaseStrategy 적용하기로..)
        // 환경마다 응답 케이스를 다르게 가져가면 프론트에서 맵핑하기 까다로움 - 하나로 통일 SnakeCase 쓰던가 아니면 안쓰던가
        // objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        return objectMapper;
    }

}