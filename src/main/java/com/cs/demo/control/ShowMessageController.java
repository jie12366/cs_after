package com.cs.demo.control;

import com.cs.demo.entity.Active;
import com.cs.demo.mapper.ActivePictureMapper;
import com.cs.demo.service.ActiveService;
import com.cs.demo.service.impl.RedisServiceImpl;
import com.cs.demo.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    ActiveService activeService;

    @Autowired
    ActivePictureMapper pictureMapper;

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
    public JsonResult listActiveByPage(@ApiParam("当前页数") @RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                                       @ApiParam("每页大小") @RequestParam(value = "pageSize",defaultValue = "5")int pageSize){

        PageHelper.startPage(currentPage,pageSize,"activeId desc");
        if (currentPage > 0){
            //活动信息的集合
            List<Active> activeList = activeService.listActiveByPage();

            //存放活动信息和活动图片的集合
            List<JSONObject> objectList = new ArrayList<>();

            for (Active active : activeList){
                int id = active.getActiveId();
                //根据id获取出所有图片
                List<String > pictures = pictureMapper.getPictureById(id);
                JSONObject object = new JSONObject();
                object.put("active",active);
                object.put("pictures",pictures);
                objectList.add(object);
            }
            return JsonResult.ok(objectList);
        }
        return null;
    }
}