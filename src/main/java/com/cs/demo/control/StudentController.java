package com.cs.demo.control;

import com.cs.demo.entity.Active;
import com.cs.demo.entity.StudentMessage;
import com.cs.demo.entity.UserLike;
import com.cs.demo.mapper.ActivePictureMapper;
import com.cs.demo.service.impl.*;
import com.cs.demo.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/7 22:26
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    ActiveServiceImpl activeService;

    @Autowired
    ActivePictureMapper activePictureMapper;

    @Autowired
    UploadServiceImpl uploadService;

    @Autowired
    StudentMessageServiceImpl studentMessageService;

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    ActiveCollectServiceImpl activeCollectService;

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @ApiOperation("将活动信息保存到数据库")
    @ApiImplicitParams({@ApiImplicitParam(name = "picture",dataType = "MultipartFile",allowMultiple = true),
            @ApiImplicitParam(name = "annex",dataType = "MultipartFile")})
    @PostMapping("/active/save")
    public JsonResult saveActive(@ApiParam("标题") @RequestParam(value = "title",required = false)String title,
                                 @ApiParam("内容") @RequestParam(value = "content",required = false)String content,
                                 @ApiParam("图片（可多张）") @RequestParam(value = "picture") MultipartFile[] picture,
                                 @ApiParam("金额范围")@RequestParam(value = "money",required = false)String money,
                                 @ApiParam("分类")@RequestParam(value = "category",required = false)String category,
                                 @ApiParam("时间") @RequestParam(value = "activeTime",required = false)String activeTime,
                                 @ApiParam("地址")@RequestParam(value = "address",required = false)String address,
                                 @ApiParam("附件") @RequestParam("annex") MultipartFile annex,
                                 @ApiParam("姓名")@RequestParam(value = "name",required = false)String name,
                                 @ApiParam("电话")@RequestParam(value = "phone",required = false)String phone,
                                 @ApiParam("社交方式")@RequestParam(value = "social",required = false)String social,
                                 @ApiParam("社交账号")@RequestParam(value = "socialNumber",required = false)String socialNumber,
                                 @ApiParam("是否公开（1或0）")@RequestParam(value = "isPublic",required = false)int isPublic,
                                 HttpServletRequest request) throws IOException{

        String annex1 = uploadService.getPic(request,annex);
        Active active = new Active();
        active.setTitle(title);
        active.setContent(content);
        active.setMoney(money);
        active.setCategory(category);
        active.setAddress(address);
        active.setAnnex(annex1);
        active.setName(name);
        active.setPhone(phone);
        active.setActiveTime(activeTime);
        active.setSocial(social);
        active.setSocialNumber(socialNumber);
        active.setIsPublic(isPublic);
        activeService.saveActive(active);
        int activeId = activeService.getMaxId();

        int res = 0;

        for (MultipartFile file : picture){

            String picture1 = uploadService.getPic(request,file);

            res = activePictureMapper.savePicture(activeId,picture1);
        }
        return JsonResult.ok(res);
    }

    @ApiOperation("更改活动信息")
    @PostMapping("/active/update")
    public JsonResult updateActive(@ApiParam("标题") @RequestParam("userName")String title,
                                   @ApiParam("内容") @RequestParam("content")String content,
                                   @ApiParam("图片") @RequestParam("picture") MultipartFile picture,
                                   @ApiParam("金额范围")@RequestParam("money")String money,
                                   @ApiParam("分类")@RequestParam("category")String category,
                                   @ApiParam("时间") @RequestParam("activeTime")String activeTime,
                                   @ApiParam("地址")@RequestParam("address")String address,
                                   @ApiParam("附件") @RequestParam("annex") MultipartFile annex,
                                   @ApiParam("姓名")@RequestParam("name")String name,
                                   @ApiParam("电话")@RequestParam("phone")String phone,
                                   @ApiParam("社交方式")@RequestParam("social")String social,
                                   @ApiParam("社交账号")@RequestParam("socialNumber")String socialNumber,
                                   @ApiParam("是否公开（1或0）")@RequestParam("isPublic")int isPublic,
                                   @ApiParam("活动id")@RequestParam("activeId")int activeId,
                                   HttpServletRequest request)throws IOException {
        String picture1 = uploadService.getPic(request,picture);
        String annex1 = uploadService.getPic(request,annex);
        int res = activeService.updateActive(title, content, money, category, activeTime, address,
                annex1, name, phone, social,socialNumber, isPublic,activeId);
        return JsonResult.ok(res);
    }

    @ApiOperation("删除活动信息")
    @PostMapping("/active/delete")
    public JsonResult deleteMessage(@ApiParam("活动id")@RequestParam("activeId")int activeId){
        int res = activeService.deleteActive(activeId);
        return JsonResult.ok(res);
    }

    @ApiOperation("完善个人资料")
    @PostMapping("/message/save")
    public JsonResult saveMessage(@ApiParam("用户名") @RequestParam("userName")String userName,
                                  @ApiParam("昵称")@RequestParam("nickName")String nickName,
                                  @ApiParam("头像") @RequestParam("avatar") MultipartFile avatar,
                                  @ApiParam("性别")@RequestParam("sex")String sex,
                                  @ApiParam("姓名")@RequestParam("name")String name,
                                  @ApiParam("个人简介")@RequestParam("introduction")String introduction,
                                  @ApiParam("地址")@RequestParam("address")String address,
                                  @ApiParam("学校")@RequestParam("school") String school,
                                  @ApiParam("社团")@RequestParam("society")String society,
                                  @ApiParam("社交方式") @RequestParam("social")String social,
                                  @ApiParam("社交账号") @RequestParam("socialNumber")String socialNumber,
                                  HttpServletRequest request) throws IOException{
        String avatar1 = uploadService.getPic(request,avatar);
        int res = studentMessageService.saveStudentMessage(userName,nickName,avatar1,sex,name,introduction,address,
                school,society,social,socialNumber);
        return JsonResult.ok(res);
    }

    @ApiOperation("修改个人资料")
    @PostMapping("message/update")
    public JsonResult updateMessage(@ApiParam("昵称") @RequestParam("userName")String nickName,
                                    @ApiParam("头像") @RequestParam("avatar") MultipartFile avatar,
                                    @ApiParam("性别")@RequestParam("sex")String sex,
                                    @ApiParam("姓名")@RequestParam("name")String name,
                                    @ApiParam("个人简介")@RequestParam("introduction")String introduction,
                                    @ApiParam("地址")@RequestParam("address")String address,
                                    @ApiParam("学校")@RequestParam("school") String school,
                                    @ApiParam("社团")@RequestParam("society")String society,
                                    @ApiParam("社交方式") @RequestParam("social")String social,
                                    @ApiParam("社交账号") @RequestParam("socialNumber")String socialNumber,
                                    @ApiParam("用户名") @RequestParam("userName")String userName,
                                    HttpServletRequest request) throws IOException{
        String avatar1 = uploadService.getPic(request,avatar);
        int res = studentMessageService.updateStudentMessage(nickName,avatar1,sex,name,introduction,address,
                school,society,social,socialNumber,userName);
        return JsonResult.ok(res);
    }

    @ApiOperation("查看用户资料")
    @PostMapping("/message/get")
    public JsonResult getMessage(@ApiParam("用户名")@RequestParam("userName") String userName){
        StudentMessage studentMessage = studentMessageService.getStudentMessageById(userName);
        return JsonResult.ok(studentMessage);
    }

    @ApiOperation("对动态点赞,并返回点赞列表")
    @PostMapping("/dynamic/like")
    public JsonResult likeDynamic(@ApiParam("动态id")@RequestParam("userId")String userId,
                                  @ApiParam("点赞者昵称")@RequestParam("nickName")String nickName){

        redisService.saveKey1(userId,nickName);
        List<UserLike> userLikeList = redisService.getLikedFromRedis();
        return JsonResult.ok(userLikeList);
    }

    @ApiOperation("对动态取消点赞,并返回点赞列表")
    @PostMapping("/dynamic/unlike")
    public JsonResult unlikeDynamic(@ApiParam("动态id")@RequestParam("userId")String userId,
                                  @ApiParam("点赞者昵称")@RequestParam("nickName")String nickName){

        redisService.deleteKey1(userId,nickName);
        List<UserLike> userLikeList = redisService.getLikedFromRedis();
        return JsonResult.ok(userLikeList);
    }

    @ApiOperation("收藏活动,返回收藏数")
    @PostMapping("/active/collect")
    public JsonResult collectActive(@ApiParam("用户名")@RequestParam("userName")String userName,
                                  @ApiParam("活动id")@RequestParam("activeId")String activeId){

        redisService.saveKey2(userName,activeId);
        int size =  redisService.getCollectFromRedis().size();
        return JsonResult.ok(size);
    }

    @ApiOperation("取消收藏,返回收藏数")
    @PostMapping("/active/unCollect")
    public JsonResult unCollectActive(@ApiParam("用户名")@RequestParam("userName")String userName,
                                      @ApiParam("活动id")@RequestParam("activeId")String activeId){

        redisService.deleteKey2(userName,activeId);
        int size =  redisService.getCollectFromRedis().size();
        return JsonResult.ok(size);
    }

    @ApiOperation("获取我的收藏")
    @PostMapping("/active/listByUserName")
    public JsonResult listByUserName(@ApiParam("用户名")@RequestParam("userName")String userName,
                                     @ApiParam("当前页数") @RequestParam("currentPage")int currentPage,
                                     @ApiParam("每页大小") @RequestParam("pageSize")int pageSize){

        PageHelper.startPage(currentPage,pageSize,"activeId desc");
        List<Active> activeList = activeCollectService.listActiveByUserNameByPage(userName);
        return JsonResult.ok(activeList);
    }
}