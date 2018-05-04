import com.magict.magic.MagicStareApplication;
import com.magict.magic.entity.WxUser;
import com.magict.magic.mapper.WxUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * @author ZP
 * @date 2018/4/1912:49
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagicStareApplication.class)
public class TestSelectForUpdate extends Thread{
    @Autowired
    WxUserMapper wxUserMapper;


    private String name;
    public TestSelectForUpdate(String name) {
        super(name);
        this.name = name;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "线程运行开始 ");
        test(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + "线程运行结束 ");

    }

    @Test
    public void mainThread(){
        TestSelectForUpdate testSelectForUpdate = new TestSelectForUpdate("A");
        TestSelectForUpdate testSelectForUpdateB = new TestSelectForUpdate("B");
        TestSelectForUpdate testSelectForUpdateC = new TestSelectForUpdate("C");
        testSelectForUpdate.start();
        testSelectForUpdateB.start();
        testSelectForUpdateC.start();
    }

//    @Test
    public void test(String city){
        WeekendSqls<WxUser> wxUserWeekendSqls = WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,"dwefwft454756");
        Example example = Example.builder(WxUser.class).setForUpdate(true).andWhere(wxUserWeekendSqls).build();
        WxUser wxUser = wxUserMapper.selectOneByExample(example);
        System.out.println("====wxUser==="+wxUser.toString());
        WxUser update = new WxUser();
        update.setCity("tianjin");
        wxUserMapper.updateByExampleSelective(update,example);
    }

}
