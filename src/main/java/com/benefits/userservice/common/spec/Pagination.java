package com.benefits.userservice.common.spec;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination{
    private Integer page;
    private Integer size;
    private Integer currentElements;
    private Long totalElements;
    private Integer totalPage;

    public static <T> Pagination toPagination(Page<T> list){
        return Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();
    }
}
