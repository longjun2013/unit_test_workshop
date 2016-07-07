package com.longjun.dao;


import com.longjun.domain.UserFilteredForAction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserFilteredForActionRepository extends CrudRepository<UserFilteredForAction, Long> {
    List<UserFilteredForAction> findByStatus(Integer status);
}
