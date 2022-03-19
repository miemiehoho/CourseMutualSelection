package edu.gdut.imis.courseMutualSelection.service.impl;

import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import edu.gdut.imis.courseMutualSelection.dao.mapper.SemesterMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.Semester;
import edu.gdut.imis.courseMutualSelection.service.SemesterService;


@Service("semesterService")
public class SemesterServiceImpl implements SemesterService {

    @Override
    public Result list(PageParams pageParams) {
        return Result.success(null);
    }
}