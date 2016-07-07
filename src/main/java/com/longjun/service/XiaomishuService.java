package com.longjun.service;

import com.longjun.dao.UserFilteredForActionRepository;
import com.longjun.domain.UserFilteredForAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class XiaomishuService {

    @Autowired
    private UserFilteredForActionRepository userFilteredForActionRepository;

    @Autowired
    private XiaoMiShuSender xiaoMiShuSender;

    public void execute() {

        List<UserFilteredForAction> users = userFilteredForActionRepository.findByStatus(UserFilteredForAction.Status.TO_BE_ACTION
                .getValue());

        users.forEach(userFilteredForAction -> {
            try {
                boolean isSuccess = xiaoMiShuSender.action(String.valueOf(userFilteredForAction.getUserId()));

                save(userFilteredForAction, isSuccess ? UserFilteredForAction.Status.ACTION_COMPLETE : UserFilteredForAction.Status.ACTION_FAILED);
            } catch (Exception e) {

                save(userFilteredForAction, UserFilteredForAction.Status.ACTION_FAILED);
            }
        });
    }

    private void save(UserFilteredForAction userFilteredForAction, UserFilteredForAction.Status status) {
        userFilteredForAction.setStatus(status);
        userFilteredForAction.setActionTime(new Date());
        userFilteredForActionRepository.save(userFilteredForAction);
    }
}
