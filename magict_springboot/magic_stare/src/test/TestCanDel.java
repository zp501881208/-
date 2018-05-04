import com.magict.magic.MagicStareApplication;
import com.magict.magic.entity.WxUser;
import com.magict.magic.service.WxUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ZP
 * @date 2018/4/2213:29
 * @description:
 */
@SpringBootTest(classes = MagicStareApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCanDel {
    @Autowired
    WxUserService wxUserService;

    @Test
    public void getWxUserById(){
        WxUser wxUser = wxUserService.selectByPrimaryKey(1);
        System.out.println(wxUser.getOpenid());

    }

}
