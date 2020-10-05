package br.com.se.entity.core.security;


import br.com.se.entity.core.generic.GenericEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@Getter
@Setter
public class Password extends GenericEntity implements Comparable<Password> {
    private String password;
    private String idUser;
    private boolean initial;

    public Password(String idUser, String password, boolean initial) {
        super();
        this.password = password;
        this.idUser = idUser;
        this.initial = initial;
    }

    public Password() {
        this.initial = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        if (!super.equals(o)) return false;
        Password password1 = (Password) o;
        return Objects.equals(getPassword(), password1.getPassword()) &&
                Objects.equals(getIdUser(), password1.getIdUser());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getPassword(), getIdUser());
    }

    @Override
    public int compareTo(Password password) {
        return this.getPassword().compareTo(password.getPassword());
    }
}
