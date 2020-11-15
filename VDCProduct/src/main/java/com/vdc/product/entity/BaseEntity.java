package com.vdc.product.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;

    @CreatedDate
    protected ZonedDateTime createdOn;
    @Version
    protected int version;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(getId()).append(getCreatedOn())
                .append(getVersion()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        BaseEntity rhs = (BaseEntity) obj;
        return new EqualsBuilder().append(this.id, rhs.id)
                .append(this.version, rhs.version).append(this.createdOn, rhs.createdOn).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id)
                .append("createdOn", this.createdOn).append("version", this.version).toString();
    }
}
