package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.UserTopicVo;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserTopicParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gdut.imis.courseMutualSelection.dao.mapper.UserTopicMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import edu.gdut.imis.courseMutualSelection.service.UserTopicService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserTopicServiceImpl implements UserTopicService {

    @Autowired
    private UserTopicMapper userTopicMapper;

    @Override
    public Result list(PageParams pageParams) {
        Page<UserTopic> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        IPage<UserTopic> userTopicIPage = userTopicMapper.listUserTopic(page, pageParams.getTeacherId(), pageParams.getTopicStatus());
        List<UserTopic> userTopicList = userTopicIPage.getRecords();
        return Result.success(copyList(userTopicList));
    }

    @Override
    public Result commit(UserTopicParam userTopicParam) {
        UserTopic userTopic = new UserTopic();
        BeanUtils.copyProperties(userTopicParam, userTopic);
        userTopicMapper.updateById(userTopic);
        return Result.success(null);
    }

    private List<UserTopicVo> copyList(List<UserTopic> userTopicList) {
        List<UserTopicVo> list = new ArrayList<>();
        for (UserTopic userTopic : userTopicList) {
            list.add(copy(userTopic));
        }
        return list;
    }

    private UserTopicVo copy(UserTopic userTopic) {
        UserTopicVo topicVo = new UserTopicVo();
        BeanUtils.copyProperties(userTopic, topicVo);
        topicVo.setId(String.valueOf(userTopic.getId()));
        topicVo.setTopicId(String.valueOf(userTopic.getTopicId()));
        return topicVo;
    }
}