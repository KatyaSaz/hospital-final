package ua.sazonova.hospital.entity.enam;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority{
    ADMIN,
    DOCTOR,
    PATIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
