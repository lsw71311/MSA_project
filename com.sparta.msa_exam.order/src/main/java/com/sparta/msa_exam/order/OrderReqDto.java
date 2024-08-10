package com.sparta.msa_exam.order;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReqDto {
    private String name;
    private List<Long> productIds;
}
