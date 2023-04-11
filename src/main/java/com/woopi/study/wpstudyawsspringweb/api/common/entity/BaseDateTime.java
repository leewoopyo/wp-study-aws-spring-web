package com.woopi.study.wpstudyawsspringweb.api.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BaseDateTime {

    /**
     * 생성한 사용자 : UserID
     */
    @CreatedBy
    @Setter
    @Comment("생성자")
    @Column(name = "CREATED_BY", updatable = false, length = 36)
    protected String createdBy;

    /**
     * 생성 년월일시분초 : Asia/Seoul TimeZone LocalDateTime
     */
    //@CreatedDate
    @CreationTimestamp
    @Comment("생성 일시")
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    /**
     * 갱신한 사용자 : UserID
     */
    @LastModifiedBy
    @Comment("수정자")
    @Column(name = "UPDATED_BY", length = 36, insertable = false)
    protected String updatedBy;

    /**
     * 갱신 년월일시분초
     */
    //@LastModifiedDate
    @UpdateTimestamp
    @Comment("수정 일시")
    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;

}
