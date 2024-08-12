package com.sparta.msa_exam.order;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResDto implements Serializable {
    private String name;
    private List<Long> productIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
