package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.mapper.TopicInformationMapper;
import edu.gdut.imis.courseMutualSelection.dao.pojo.TopicInformation;
import edu.gdut.imis.courseMutualSelection.utils.AdminThreadLocal;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.TopicInformationVo;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import edu.gdut.imis.courseMutualSelection.vo.params.TopicInformationParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gdut.imis.courseMutualSelection.service.TopicInformationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicInformationServiceImpl implements TopicInformationService {

    @Autowired
    private TopicInformationMapper topicInformationMapper;

    @Override
    public Result create(TopicInformationParam topicInformationParam) {
        TopicInformation topicInformation = new TopicInformation();
        BeanUtils.copyProperties(topicInformationParam, topicInformation);
        topicInformation.setTeacherId(AdminThreadLocal.get().getId());
        topicInformation.setSelectedNum(0);
        topicInformationMapper.insert(topicInformation);
        return Result.success(null);
    }

    @Override
    public Result listTopic(PageParams pageParams) {
        Page<TopicInformation> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        LambdaQueryWrapper<TopicInformation> queryWrapper = new LambdaQueryWrapper<>();
        if (pageParams.getTeacherId() != null) {
            queryWrapper.eq(TopicInformation::getTeacherId, pageParams.getTeacherId());
        }
        Page<TopicInformation> topicInformationPage = topicInformationMapper.selectPage(page, queryWrapper);
        List<TopicInformation> records = topicInformationPage.getRecords();
        return Result.success(copyList(records));
    }

    private List<TopicInformationVo> copyList(List<TopicInformation> records) {
        List<TopicInformationVo> list = new ArrayList<>();
        for (TopicInformation topicInformation : records) {
            list.add(copy(topicInformation));
        }
        return list;
    }

    private TopicInformationVo copy(TopicInformation topicInformation) {
        TopicInformationVo topicInformationVo = new TopicInformationVo();
        BeanUtils.copyProperties(topicInformation, topicInformationVo);
        topicInformationVo.setId(String.valueOf(topicInformation.getId()));
        topicInformationVo.setTeacherId(topicInformation.getTeacherId());
        return topicInformationVo;
    }
}