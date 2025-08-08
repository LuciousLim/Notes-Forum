package com.kama.notes.service.es;

import com.kama.notes.model.es.NoteDocument;
import com.kama.notes.model.es.QuestionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface QuestionEsRepository extends ElasticsearchRepository<QuestionDocument, Long> {

    List<QuestionDocument> findByTitleContaining(String keyword);
}

