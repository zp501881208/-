import com.magict.magic.MagicStareApplication;
import com.magict.magic.entity.WxUser;
import com.magict.magic.mapper.WxUserMapper;
import com.magict.magic.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

/**
 * @author ZP
 * @date 2018/4/1716:50
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagicStareApplication.class)
public class TestJunit {
    @Autowired
    MessageService messageService;
    @Autowired
    WxUserMapper wxUserMapper;

    @Test
    public void testA(){
//        List<MessageVo> messages = messageService.getMessages();
//        messages.forEach(messageVo -> {
//            System.out.println("message:"+messageVo.getMessage()+"=======wxUserId:"+messageVo.getWxUserId());
//        });
        DemoThread demoThread1 = new DemoThread("A");
        DemoThread demoThread2 = new DemoThread("B");
        DemoThread demoThread3 = new DemoThread("C");

        new Thread(new DemoThread("A")).start();
        new Thread(new DemoThread("B")).start();
        new Thread(new DemoThread("C")).start();
//        Thread t2 = new Thread(demoThread2);
//        Thread t3 = new Thread(demoThread3);
//        t2.start();
//        t1.start();
//        t3.start();
//        updateWxUser("===");
//        t1.run();//A
//        t2.run();//B
//        t3.run();//C
//        demoThread1.run();
//        demoThread3.run();
//        demoThread2.run();

    }

/*    public void updateWxUser(String city){
        WeekendSqls<WxUser> wxUserWeekendSqls = WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,"dwefwft454756");
        Example example = Example.builder(WxUser.class).setForUpdate(true).andWhere(wxUserWeekendSqls).build();
//        Example example = Example.builder(WxUser.class).andWhere(wxUserWeekendSqls).build();
        WxUser wxUser = wxUserMapper.selectOneByExample(example);
        WxUser update = new WxUser();
        update.setCity(city);
        wxUserMapper.updateByExampleSelective(update,example);
        System.out.println(city+"=====");
    }*/

    public class DemoThread  implements Runnable{
        private String name;

        public DemoThread(String name){
            this.name = name;
        }
        @Override
        public void run() {
//            Example example = Example.builder(WxUser.class).setForUpdate(true).andWhere(wxUserWeekendSqls).build();
            System.out.println("线程："+Thread.currentThread().getName()+"开始"+name);
//            WeekendSqls<WxUser> wxUserWeekendSqls = WeekendSqls.<WxUser>custom().andEqualTo(WxUser::getOpenid,"dwefwft454756");
//            Example example = Example.builder(WxUser.class).andWhere(wxUserWeekendSqls).build();
//            WxUser wxUser = wxUserMapper.selectOneByExample(example);

            if(name.equals("B")){
                try {
                    Thread.sleep(3);
                    System.out.println("B睡眠:");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Exception");
                }
            }
//            WxUser update = new WxUser();
//            update.setCity(name);
//            wxUserMapper.updateByExampleSelective(update,example);
            System.out.println(name+"=====");

            System.out.println("线程："+Thread.currentThread().getName()+"结束"+name);

        }
    }


}
