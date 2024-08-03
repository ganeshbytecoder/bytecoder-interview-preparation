package com.gschoudhary.Bytecoder.comments;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommentDto {

    @JsonProperty("title")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("sub_heading")
    private String subHeading;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("is_published")
    private boolean isPublished;

    @JsonProperty("id")
    private int examId;

}
