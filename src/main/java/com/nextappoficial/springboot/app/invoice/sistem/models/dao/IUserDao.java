package com.nextappoficial.springboot.app.invoice.sistem.models.dao;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.UserAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<UserAuth, Long> {

    public UserAuth findByUsername(String username);

    @Query("select u from UserAuth u where u.username = ?1")
    public UserAuth findByUsernameDos(String username);
}
