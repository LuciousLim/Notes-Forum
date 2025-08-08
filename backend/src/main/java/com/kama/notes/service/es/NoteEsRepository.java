package com.kama.notes.service.es;

import com.kama.notes.model.es.NoteDocument;
import com.kama.notes.model.es.QuestionDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface NoteEsRepository extends ElasticsearchRepository<NoteDocument, Long> {

    @Query("{\"match\": {\"searchText\": \"?0\"}}")
    List<NoteDocument> searchByKeyword(String keyword);
}
