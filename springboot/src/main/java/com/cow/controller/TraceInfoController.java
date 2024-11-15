package com.cow.controller;

import com.alibaba.fastjson.JSONObject;
import com.cow.dao.TraceInfoDao;
import com.cow.entity.TraceInfo;
import com.cow.vo.Result;
import com.cow.vo.TraceInfoVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trace")

public class TraceInfoController {
    @Autowired
    private TraceInfoDao traceInfoDao;

    @GetMapping("/info")
    public Result getTraceInfoById(@Param("id") String id) {
        if (id == null || id.trim().isEmpty()) {
            return Result.fail("Invalid ID,ID is null");
        }
        try {
            TraceInfo traceInfo = traceInfoDao.selectById(id);

            if (traceInfo != null) {
                TraceInfoVO traceInfoVO = new TraceInfoVO(traceInfo);
                return Result.ok(traceInfoVO);
            } else {
                return Result.fail("TraceInfo not found");
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            return Result.fail("error: " + e.getMessage());
        }
    }

}
