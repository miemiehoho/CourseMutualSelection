package edu.gdut.imis.courseMutualSelection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gdut.imis.courseMutualSelection.dao.pojo.TopicInformation;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;

/**
 * 选题表
 *
 * @author miemiehoho
 * @date 2022-01-16 17:36:38
 */
public interface TopicInformationService {

    /**
     * 获取所有选题
     *
     * @param pageParams
     * @return
     */
    Result listTopic(PageParams pageParams);

    TopicInformation findTopic(Long topicId);

    void addSelect(TopicInformation topicInformation);
}

