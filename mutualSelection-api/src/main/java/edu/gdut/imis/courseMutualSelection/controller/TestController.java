//package edu.gdut.imis.courseMutualSelection.controller;
//
//import edu.gdut.imis.courseMutualSelection.test_rabbitmq.MQDirectSender;
//import edu.gdut.imis.courseMutualSelection.test_rabbitmq.MQSender;
//import edu.gdut.imis.courseMutualSelection.test_rabbitmq.MQTopicSender;
//import edu.gdut.imis.courseMutualSelection.vo.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author miemiehoho
// * @date 2022/3/18 10:50
// */
//@Api(value = "测试", tags = "测试")
//@RestController
//@RequestMapping("test")
//public class TestController {
//
//    @Autowired
//    private MQSender mqSender;
//    @Autowired
//    private MQDirectSender mqDirectSender;
//    @Autowired
//    private MQTopicSender mqTopicSender;
//
//
//    @ApiOperation("fanout发消息")
//    @GetMapping("fanout/{msg}")
//    public Result fanoutSend(@PathVariable String msg) {
//        mqSender.send(msg);
//        return Result.success(null);
//    }
//
//    @ApiOperation("directRed发消息")
//    @GetMapping("directRed/{msg}")
//    public Result directRedSend(@PathVariable String msg) {
//        mqDirectSender.sendRed(msg);
//        return Result.success(null);
//    }
//
//    @ApiOperation("directBalck发消息")
//    @GetMapping("directBalck/{msg}")
//    public Result directBlackSend(@PathVariable String msg) {
//        mqDirectSender.sendBlack(msg);
//        return Result.success(null);
//    }
//
//    @ApiOperation("topic01发消息")
//    @GetMapping("topic01/{msg}")
//    public Result topic01Send(@PathVariable String msg) {
//        mqTopicSender.send01(msg);
//        return Result.success(null);
//    }
//
//    @ApiOperation("topic02发消息")
//    @GetMapping("topic02/{msg}")
//    public Result topic02Send(@PathVariable String msg) {
//        mqTopicSender.send02(msg);
//        return Result.success(null);
//    }
//
//}
