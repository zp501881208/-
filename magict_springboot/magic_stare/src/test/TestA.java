import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author ZP
 * @date 2018/4/12
 * @description:斗地主发牌
 */
public class TestA {
    public static void main(String[] args) {
        //创建Map集合，键存索引，值存花色和点数组成的字符串
        HashMap<Integer, String> hashmap = new HashMap<Integer,String>();

        //创建ArrayList集合，存储map集合的索引
        ArrayList<Integer> indexs = new ArrayList<Integer>();

        // 给牌盒中添加元素，花色
        String[] colors={"♣","♥","♠","♦"};


        //存储点数，按斗地主的规则，从大到小，3~2
        String[] numbers={"3","4","5","6","7","8","9","10","J","Q","K","A","2",};

        //建立索引
        int index = 0;

        //在集合HashMap中存储键（索引值）和值（花色和点数），并把索引存放到ArrayList集合中
        for (String number : numbers) {
            for (String color : colors) {
                hashmap.put(index, (color+number));
                indexs.add(index);
                index++;
            }
        }

        //额外的把大王和小王放到HashMap中，并把索引加到ArrayList集合中
        indexs.add(index);
        hashmap.put(index, "小王");
        index++;
        indexs.add(index);
        hashmap.put(index, "大王");

        //洗牌（洗索引），利用集合工具类Collections的shuffle()实现洗牌功能
        Collections.shuffle(indexs);

        //发牌发索引，但是发完索引之后必须让索引有序，即玩家手中的牌有序
        TreeSet<Integer> treeset1 = new TreeSet<Integer>();
        TreeSet<Integer> treeset2 = new TreeSet<Integer>();
        TreeSet<Integer> treeset3 = new TreeSet<Integer>();
        TreeSet<Integer> dipai = new TreeSet<Integer>();

        //按照顺序发牌，最后三张为底牌
        for (int i = 0; i < indexs.size(); i++) {
            if(i>=indexs.size()-3){
                dipai.add(indexs.get(i));
            }else   if(i%3==0){
                treeset1.add(indexs.get(i));
            }else   if(i%3==1){
                treeset2.add(indexs.get(i));
            }else   if(i%3==2){
                treeset3.add(indexs.get(i));
            }
        }

        System.out.println("                欢迎来到欢乐斗地主！！！");
        //调用lookPoker()方法，看牌
        lookPoker("Ag1",treeset1,hashmap);
        lookPoker("Ag2",treeset2,hashmap);
        lookPoker("Ag3",treeset3,hashmap);
        lookPoker("dipai",dipai,hashmap);

    }

    private static void lookPoker(String name, TreeSet<Integer> treeset,
                                  HashMap<Integer, String> hashmap) {
        //根据获取到的索引，取出牌
        System.out.print(name+"的牌：  ");
        for (Integer index : treeset) {
            System.out.print(hashmap.get(index)+"  ");
        }
        System.out.println();

    }

}
