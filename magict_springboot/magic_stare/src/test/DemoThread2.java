import com.magict.magic.MagicStareApplication;
import com.magict.magic.entity.WxUser;
import com.magict.magic.mapper.WxUserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * @author ZP
 * @date 2018/4/1921:09
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagicStareApplication.class)
public class DemoThread2 implements Runnable{

    private static WxUserMapper wxUserMapper;

    @Autowired
    public  void setWxUserMapper(WxUserMapper wxUserMapper) {
        DemoThread2.wxUserMapper = wxUserMapper;
    }

    private String name;

    public DemoThread2(String name){
        this.name = name;
    }

    public void updateWxUser(String city){
        WeekendSqls<WxUser> wxUserWeekendSqls = WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,"dwefwft454756");
        Example example = Example.builder(WxUser.class).setForUpdate(true).andWhere(wxUserWeekendSqls).build();
        WxUser wxUser = wxUserMapper.selectOneByExample(example);
        if(null!=wxUser){
            System.out.println("bukong");
        }else{
            System.out.println("====空======");
        }

        System.out.println("---------"+wxUser.getCity());
        WxUser update = new WxUser();
        update.setCity(city);
        wxUserMapper.updateByExampleSelective(update,example);
        System.out.println(city+"=====");
    }

    @Override
    public void run() {
        System.out.println("线程："+Thread.currentThread().getName()+"开始"+name);
        int time = (int)(Math.random()*1000);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateWxUser(name);

        System.out.println("线程："+Thread.currentThread().getName()+"结束"+name);
    }
}
