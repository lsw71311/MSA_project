package com.sparta.msa_exam.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqDto {

        private Long product_id;
        private String name;
        private Integer supply_price;
}
