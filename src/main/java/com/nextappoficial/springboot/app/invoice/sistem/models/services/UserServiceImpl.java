package com.nextappoficial.springboot.app.invoice.sistem.models.services;

import com.nextappoficial.springboot.app.invoice.sistem.models.dao.IUserDao;
import com.nextappoficial.springboot.app.invoice.sistem.models.entity.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao userDao;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth user = userDao.findByUsername(username);

        if (user == null) {
            logger.error("Error En El Login: No existe El Usuario " + username + " En El Sistema!");
            throw new UsernameNotFoundException("Error En El Login: No existe El Usuario " + username + " En El Sistema!");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(userRol -> new SimpleGrantedAuthority(userRol.getName()))
                .peek(simpleGrantedAuthority -> logger.info("Role: " + simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), user.getEnabled(),
                true, true, true,
                authorities);
    }

}
