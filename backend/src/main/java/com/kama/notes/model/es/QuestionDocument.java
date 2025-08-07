package com.kama.notes.model.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Document(indexName = "question") // ES 中的索引名
public class QuestionDocument {

    @Id
    private Integer questionId;

    private Integer categoryId;
    private String title;
    private Integer difficulty;
    private String examPoint;
    private Integer viewCount;

    private Long createdAt;
    private Long updatedAt;
}
