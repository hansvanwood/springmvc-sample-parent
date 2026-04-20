// 包路径: com.example.movie.dto.request.MovieUpdateRequest.java
package com.example.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieUpdateRequest {

    @NotNull(message = "评分不能为空")
    @DecimalMin(value = "0.0", message = "评分不能低于0分")
    @DecimalMax(value = "10.0", message = "评分不能超过10分")
    private Double doubanScore;

    @NotNull(message = "电影时长不能为空")
    @Min(value = 1, message = "时长至少为1分钟")
    private Integer minutes;
}