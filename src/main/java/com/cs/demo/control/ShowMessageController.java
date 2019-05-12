package com.cs.demo.control;

import com.cs.demo.entity.Active;
import com.cs.demo.service.impl.ActiveServiceImpl;
import com.cs.demo.service.impl.RedisServiceImpl;
import com.cs.demo.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/22 15:51
 */
@RestController
public class ShowMessageController {

    private final static String DEFAULT_COUNT = "1";

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    ActiveServiceImpl activeService;

    @ApiOperation("更新浏览量，+1")
    @PostMapping("/active/read")
    public JsonResult readActive(String activeId){
        String readCount = redisService.get(activeId);
        if (StringUtils.isBlank(readCount)){
            redisService.set(activeId,DEFAULT_COUNT);
            readCount = DEFAULT_COUNT;
        }else {
            readCount = redisService.incr(activeId);
        }
        return JsonResult.ok(readCount);
    }

    @ApiOperation("分页取出数据")
    @GetMapping("/active/listByPage")
    public JsonResult listActiveByPage(@ApiParam("当前页数") @RequestParam("currentPage")int currentPage,
                                       @ApiParam("每页大小") @RequestParam("pageSize")int pageSize){
        if (currentPage > 0){
            List<Active> activeList = activeService.listActiveByPage(currentPage,pageSize);
            return JsonResult.ok(activeList);
        }
        return null;
    }
}