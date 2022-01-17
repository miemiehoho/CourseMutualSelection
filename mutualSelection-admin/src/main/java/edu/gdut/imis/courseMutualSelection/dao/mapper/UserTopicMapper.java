package edu.gdut.imis.courseMutualSelection.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.pojo.UserTopic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * 学生选题表
 *
 * @author miemiehoho
 * @date 2022-01-17 02:34:17
 */
@Component
public interface UserTopicMapper extends BaseMapper<UserTopic> {

    IPage<UserTopic> listUserTopic(Page<UserTopic> page, Long teacherId, Integer topicStatus);
}
