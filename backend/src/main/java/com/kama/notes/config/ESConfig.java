package com.kama.notes.config;

import com.kama.notes.mapper.QuestionListMapper;
import com.kama.notes.mapper.QuestionMapper;
import com.kama.notes.model.entity.Question;
import com.kama.notes.model.entity.QuestionList;
import com.kama.notes.model.es.QuestionDocument;
import com.kama.notes.service.QuestionEsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Configuration
public class ESConfig {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionEsRepository questionEsRepository;

    @Value("${search.sync.enable}")
    private boolean isSyncEnable;

    @PostConstruct
    public void importAllQuestionsToES() {
        if(!isSyncEnable){
            log.info("ES初始同步已关闭");
            return;
        }

        List<Question> all = questionMapper.findAll();
        List<QuestionDocument> esDocs = all.stream().map(q -> {
            QuestionDocument doc = new QuestionDocument();
            BeanUtils.copyProperties(q, doc);
            return doc;
        }).toList();
        questionEsRepository.saveAll(esDocs);
        log.info("成功同步到 ES 的文档数量: {}", esDocs.size());
    }

}
