package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.UserTopicParam;

/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
public interface UserTopicService {

    Result list(PageParams pageParams);

    Result commit(UserTopicParam userTopicParam);
}

