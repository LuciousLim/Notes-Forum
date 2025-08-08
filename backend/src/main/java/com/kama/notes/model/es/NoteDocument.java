package com.kama.notes.model.es;

import com.kama.notes.model.vo.note.NoteVO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@Document(indexName = "note")
public class NoteDocument {
    @Id
    private Integer noteId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String searchText;

    private Long authorId;
    private String authorName;
    private Integer questionId;
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private Integer collectCount;
    private Long createdAt;
    private Long updatedAt;
}
