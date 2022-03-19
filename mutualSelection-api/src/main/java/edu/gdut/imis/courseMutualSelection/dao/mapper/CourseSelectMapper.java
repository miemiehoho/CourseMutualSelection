package edu.gdut.imis.courseMutualSelection.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseSelect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * 可选课程表
 *
 * @author miemiehoho
 * @date 2022-03-16 14:51:07
 */
@Component
public interface CourseSelectMapper extends BaseMapper<CourseSelect> {

}
