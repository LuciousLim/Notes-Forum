package com.kama.notes.config;

import com.kama.notes.mapper.NoteMapper;
import com.kama.notes.mapper.QuestionMapper;
import com.kama.notes.mapper.UserMapper;
import com.kama.notes.model.entity.Note;
import com.kama.notes.model.entity.Question;
import com.kama.notes.model.es.NoteDocument;
import com.kama.notes.model.es.QuestionDocument;
import com.kama.notes.service.es.NoteEsRepository;
import com.kama.notes.service.es.QuestionEsRepository;
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
    private NoteMapper noteMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionEsRepository questionEsRepository;

    @Autowired
    private NoteEsRepository noteEsRepository;

    @Value("${search.sync.enable}")
    private boolean isSyncEnable;

    @PostConstruct
    public void importDataToEs(){
        if(!isSyncEnable){
            log.info("ES初始同步已关闭");
            return;
        }

        importAllQuestionsToES();
        importAllNotesToES();
    }

    public void importAllQuestionsToES() {
        List<Question> all = questionMapper.findAll();
        List<QuestionDocument> esDocs = all.stream().map(q -> {
            QuestionDocument doc = new QuestionDocument();
            BeanUtils.copyProperties(q, doc);
            return doc;
        }).toList();
        questionEsRepository.saveAll(esDocs);
        log.info("成功同步到 ES 的题目数量: {}", esDocs.size());
    }

    public void importAllNotesToES() {
        List<Note> all = noteMapper.findAll();
        List<NoteDocument> esDocs = all.stream().map(note -> {
            NoteDocument doc = new NoteDocument();
            BeanUtils.copyProperties(note, doc);
            String authorName = userMapper.findById(doc.getAuthorId()).getUsername();
            doc.setAuthorName(authorName);
            doc.setSearchText(doc.getContent() + " " + authorName);
            return doc;
        }).toList();
        noteEsRepository.saveAll(esDocs);
        log.info("成功同步到 ES 的笔记数量: {}", esDocs.size());
    }

}
