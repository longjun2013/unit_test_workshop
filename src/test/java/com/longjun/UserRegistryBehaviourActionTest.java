package com.longjun;

import com.longjun.dao.UserFilteredForActionRepository;
import com.longjun.domain.UserFilteredForAction;
import com.longjun.service.XiaomishuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static com.longjun.domain.UserFilteredForAction.Status.TO_BE_ACTION;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@Transactional()
@TransactionConfiguration(defaultRollback = true)
public class UserRegistryBehaviourActionTest {

    @Autowired
    private XiaomishuService service;

    @Autowired
    private UserFilteredForActionRepository userFilteredForActionRepository;

    @Test
    public void should_mark_status_to_complete_when_xiaomishu_send_result_is_true() throws KeyManagementException, NoSuchAlgorithmException,
            KeyStoreException, IOException {
        //Given
        UserFilteredForAction userFilteredForAction = userFilteredForActionRepository.save(new UserFilteredForAction(111, new Date(),
                TO_BE_ACTION, null));

        //When
        service.execute();

        //Then
        assertThat(userFilteredForActionRepository.findOne(userFilteredForAction.getId()).getStatus(), is
                (UserFilteredForAction.Status.ACTION_COMPLETE));
    }
}