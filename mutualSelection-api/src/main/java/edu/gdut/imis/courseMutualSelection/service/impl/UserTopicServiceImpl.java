package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.TopicInformation;
import edu.gdut.imis.courseMutualSelection.dao.pojo.User;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import edu.gdut.imis.courseMutualSelection.enums.ErrorStatus;
import edu.gdut.imis.courseMutualSelection.service.TopicInformationService;
import edu.gdut.imis.courseMutualSelection.utils.UserThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.UserTopicVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gdut.imis.courseMutualSelection.dao.mapper.UserTopicMapper;
import edu.gdut.imis.courseMutualSelection.service.UserTopicService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserTopicServiceImpl implements UserTopicService {
    @Autowired
    private TopicInformationService topicInformationService;

    @Autowired
    private UserTopicMapper userTopicMapper;

    @Override
    public Result select(Long topicId) {
        TopicInformation topicInformation = topicInformationService.findTopic(topicId);
        if (topicInformation == null) {
            return Result.fail(ErrorStatus.TOPIC_NULL.getCode(), ErrorStatus.TOPIC_NULL.getMsg());
        }
        if (topicInformation.getSelectedNum() >= topicInformation.getNumLimit()) {
            return Result.fail(ErrorStatus.SELECT_LIMIT.getCode(), ErrorStatus.SELECT_LIMIT.getMsg());
        }
        topicInformationService.addSelect(topicInformation);
        UserTopic userTopic = new UserTopic();
        userTopic.setUserId(UserThreadLocal.get().getId());
        userTopic.setTopicId(topicId);
        userTopic.setTopicStatus(0);
        userTopicMapper.insert(userTopic);
        return Result.success(null);
    }

    @Override
    public Result selectStatus(Long topicId) {
        UserTopic userTopic = this.userTopicMapper.selectById(topicId);
        return Result.success(userTopic.getTopicStatus());
    }

    @Override
    public Result getSelect() {
        LambdaQueryWrapper<UserTopic> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserTopic::getUserId, UserThreadLocal.get().getId());
        UserTopic userTopic = this.userTopicMapper.selectOne(queryWrapper);
        return Result.success(copy(userTopic));
    }

    @Override
    public Result uploadReport(Long userTopicId, String reportURL) {
        UserTopic userTopic = new UserTopic();
        userTopic.setId(userTopicId);
        userTopic.setOpeningReport(reportURL);
        userTopic.setReportStatus(1);
        this.userTopicMapper.updateById(userTopic);
        return Result.success(null);
    }

    private UserTopicVo copy(UserTopic userTopic) {
        UserTopicVo userTopicVo = new UserTopicVo();
        BeanUtils.copyProperties(userTopic, userTopicVo);
        userTopicVo.setId(String.valueOf(userTopic.getId()));
        userTopicVo.setTopicId(String.valueOf(userTopic.getTopicId()));
        return userTopicVo;
    }
}