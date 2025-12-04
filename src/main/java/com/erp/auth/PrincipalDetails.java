package com.erp.auth;

import com.erp.dto.ManagerDTO;
import com.erp.dto.StoreDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final ManagerDTO manager;
    private StoreDTO store;

    public ManagerDTO getManager() { return manager; }

    public void setStore(StoreDTO store) { this.store = store; }
    public StoreDTO getStore() { return store; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> manager.getRole());
    }

    @Override
    public String getPassword() {
        return manager.getPw();
    }

    @Override
    public String getUsername() {
        return manager.getManagerId();
    }


}

