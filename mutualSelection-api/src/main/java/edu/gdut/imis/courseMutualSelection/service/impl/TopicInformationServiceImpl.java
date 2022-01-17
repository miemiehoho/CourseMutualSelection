package edu.gdut.imis.courseMutualSelection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.gdut.imis.courseMutualSelection.dao.pojo.TopicInformation;
import edu.gdut.imis.courseMutualSelection.vo.Result;
import edu.gdut.imis.courseMutualSelection.vo.TopicInformationVo;
import edu.gdut.imis.courseMutualSelection.vo.params.PageParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gdut.imis.courseMutualSelection.dao.mapper.TopicInformationMapper;
import edu.gdut.imis.courseMutualSelection.service.TopicInformationService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TopicInformationServiceImpl implements TopicInformationService {

    @Autowired
    private TopicInformationMapper topicInformationMapper;

    @Override
    public Result listTopic(PageParams pageParams) {
        LambdaQueryWrapper<TopicInformation> queryWrapper = new LambdaQueryWrapper<>();
        Page<TopicInformation> page = new Page<>(pageParams.getPage(), pageParams.getPage_size());
        Page<TopicInformation> topicPage = topicInformationMapper.selectPage(page, queryWrapper);
        List<TopicInformation> topicInformationList = topicPage.getRecords();
        return Result.success(copyList(topicInformationList));
    }

    @Override
    public TopicInformation findTopic(Long topicId) {
        TopicInformation topicInformation = topicInformationMapper.selectById(topicId);
        return topicInformation;
    }

    @Override
    public void addSelect(TopicInformation topicInformation) {
        topicInformation.setSelectedNum(topicInformation.getSelectedNum() + 1);
        topicInformationMapper.updateById(topicInformation);
    }


    private List<TopicInformationVo> copyList(List<TopicInformation> topicInformationList) {
        List<TopicInformationVo> list = new ArrayList<>();
        for (TopicInformation topicInformation : topicInformationList) {
            list.add(copy(topicInformation));
        }
        return list;
    }

    private TopicInformationVo copy(TopicInformation topicInformation) {
        TopicInformationVo topicInformationVo = new TopicInformationVo();
        BeanUtils.copyProperties(topicInformation, topicInformationVo);
        topicInformationVo.setId(String.valueOf(topicInformation.getId()));
        return topicInformationVo;
    }
}