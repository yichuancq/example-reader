package user;

import com.example.reader.ReaderApplication;
import com.example.reader.domain.user.UserService;
import com.example.reader.domain.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/10/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReaderApplication.class)
public class TestUser {


    private final static Logger log = LoggerFactory.getLogger(TestUser.class);

    @Autowired
    private UserService userService;

    @Test
    public void queryUser() {

        User user = userService.queryUser("");
        System.out.println("" + user);
    }


}
