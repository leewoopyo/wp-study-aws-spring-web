package com.woopi.study.wpstudyawsspringweb.api.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BaseDatetimeExtended {

    @Accessors(fluent = true)
    @Column(name = "IS_ACTIVATED", nullable = false, columnDefinition = "BIT DEFAULT 0")
    @Comment("활성화 여부(0:비활성, 1:활성)")
    @Builder.Default
    private boolean isActivated = false;

    public Boolean isActivated() {
        return this.isActivated;
    }

    public void activate() {
        this.isActivated = true;
    }

    public void deactivate() {
        this.isActivated = false;
    }

    @Accessors(fluent = true)
    @Column(name = "IS_DELETED", nullable = false, columnDefinition = "BIT DEFAULT 0")
    @Comment("삭제 여부(0:비삭제, 1:삭제)")
    @Builder.Default
    protected Boolean isDeleted = false;

    public Boolean isDeleted() {
        return this.isDeleted;
    }

    public void delete() {
        this.isActivated = false;
        this.isDeleted   = true;
    }

    public void restore() {
        this.isDeleted = false;
    }
}
