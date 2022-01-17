package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import edu.gdut.imis.courseMutualSelection.vo.Result;

/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-16 17:36:38
 */
public interface UserTopicService {

    Result select(Long topicId);

    Result selectStatus(Long topicId);

    Result getSelect();

    Result uploadReport(Long userTopicId, String reportURL);
}

