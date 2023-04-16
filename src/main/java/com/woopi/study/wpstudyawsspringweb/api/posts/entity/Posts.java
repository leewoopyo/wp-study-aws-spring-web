package com.woopi.study.wpstudyawsspringweb.api.posts.entity;

import com.woopi.study.wpstudyawsspringweb.api.common.entity.BaseDatetimeExtended;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Posts extends BaseDatetimeExtended implements Serializable {
    /* ID를 UUID로 하고자 할 때의 설정 */
    //    @Id
    //    @Column(name = "ID", updatable = false, nullable = false, unique = true, length = 40)
    //    @ApiModelProperty(notes = "ID")
    //    @GeneratedValue(generator = "uuid2")
    //    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //    private String id;

    @Id
    @Comment("고유 아이디 값")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    @Comment("제목")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Comment("내용")
    private String content;

    @Comment("글쓴이")
    private String author;

    public void update (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
