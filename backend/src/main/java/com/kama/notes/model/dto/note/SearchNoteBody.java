package com.kama.notes.model.dto.note;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SearchNoteBody {
    @NotNull(message = "keyword 不能为空")
    @NotEmpty(message = "keyword 不能为空")
    @Length(min = 1, max = 32, message = "keyword 长度在 1 和 32 范围内")
    private String keyword;
}
