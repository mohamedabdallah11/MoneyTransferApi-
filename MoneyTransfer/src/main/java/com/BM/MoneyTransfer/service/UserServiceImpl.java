package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dao.UserDao;
import com.BM.MoneyTransfer.entity.Authority;
import com.BM.MoneyTransfer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        System.out.println(user);

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getAuthorities());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority role : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(tempAuthority);
        }
        return authorities;
    }
}
