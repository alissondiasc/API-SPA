package br.com.se.entity.core.generic;

import br.com.se.config.annotations.NotIndex;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public abstract class GenericEntity {
    @Id
    @NotIndex
    public String id;

    @CreatedDate
    private Date createdWithTime;

    @CreatedDate
    private LocalDate createdWithoutTime;

    @LastModifiedDate
    private LocalDate modified;

    @CreatedBy
    private String createdUser;

    @LastModifiedBy
    private String lastModifiedUser;

    @NotIndex
    private String idUnidadeEmUso;

    private Boolean isDeleted;

    private float latitude;

    private float longitude;

    private String deviceModel;

    private String deviceSerial;

    public GenericEntity(String id) {
        this.id = id;
        this.isDeleted = false;
    }

    public GenericEntity() {
        this.isDeleted = false;
    }

    public GenericEntity(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GenericEntity other = (GenericEntity) obj;
        return Objects.equals(this.id, other.id);
    }
}
