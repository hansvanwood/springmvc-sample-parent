package com.example.service;

import com.example.common.ResultCode;
import com.example.common.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    // Service 层代码示例（非 Controller 层，此处仅展示异常用法）
    public String getMovieDetail(Integer movieId) {
//        Movie movie = movieDao.selectById(movieId);
//        if (movieId == null) {
        // 主动抛出业务异常，由全局异常处理器统一处理
        throw new BusinessException(ResultCode.NOT_FOUND, "电影不存在，ID: " + movieId);
//        }
        // ... 构建响应
    }
}
