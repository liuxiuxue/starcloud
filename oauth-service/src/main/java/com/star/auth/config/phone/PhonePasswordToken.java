package com.star.auth.config.phone;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author: liuxiuxue
 * @date: 2022/3/12 16:05
 */
public class PhonePasswordToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public PhonePasswordToken(String phone, String password){
        super(null);
        this.principal = phone;
        this.credentials = password;
        setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public PhonePasswordToken(Object principal,Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
