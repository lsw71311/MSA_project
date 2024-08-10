package com.sparta.msa_exam.product;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {

        private Long product_id;
        private String name;
        private Integer supply_price;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
