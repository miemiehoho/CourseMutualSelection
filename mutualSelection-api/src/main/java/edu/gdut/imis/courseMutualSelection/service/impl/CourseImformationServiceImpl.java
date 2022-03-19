package edu.gdut.imis.courseMutualSelection.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edu.gdut.imis.courseMutualSelection.dao.mapper.CourseImformationMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.CourseImformation;
import edu.gdut.imis.courseMutualSelection.service.CourseImformationService;


@Service("courseImformationService")
public class CourseImformationServiceImpl extends ServiceImpl<CourseImformationMapper, CourseImformation> implements CourseImformationService {

}