package com.woopi.study.wpstudyawsspringweb.api.posts.entity;

import com.woopi.study.wpstudyawsspringweb.api.common.BaseDatetimeExtended;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posts extends BaseDatetimeExtended implements Serializable {

    //    @Id
    //    @Column(name = "ID", updatable = false, nullable = false, unique = true, length = 40)
    //    @ApiModelProperty(notes = "ID")
    //    @GeneratedValue(generator = "uuid2")
    //    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //    private String id;

    @Id
    @Comment("고유 아이디 값")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 500, nullable = false)
    @Comment("제목")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Comment("내용")
    private String content;

    @Comment("글쓴이")
    private String author;


}
