import com.magict.magic.util.DateUtil;

import java.util.*;

/**
 * @author ZP
 * @date 2018/4/1516:13
 * @description:
 */
public class TestB {
    //牌集合
    private static HashMap<Integer, String> hashmap = new HashMap<Integer,String>();
    //创建ArrayList集合，存储map集合的索引
    private static ArrayList<Integer> indexs = new ArrayList<Integer>();
    // 给牌盒中添加元素，花色方草红黑 从小到大
    private static String[] colors={"♦","♣","♥","♠"};
//    private static String[] colors={"方片","草花","红桃","黑桃"};

    //存储点数，按斗地主的规则，从大到小，3~2
    private static String[] numbers={"3","4","5","6","7","8","9","10","J","Q","K","A","2",};
    //建立索引
    private  static int index = 0;
    //玩家手上的牌
    private  static HashMap<Integer,TreeSet<Integer>> userPokers;
    //已发牌数量 发牌发一张少一张
    private static  int cntPoker = 0;

    public static void main(String[] args) {
        Integer[] userIds = {1,4,3,5,9,6,7,2};
//        Integer[] userIds = {1,4,3,5,9,6,7,2,12,16,31};

        getPokers();
        getUserPokers(userIds);
        System.out.println(userIds.length+"个人");
        for (int i=0;i<userIds.length;i++){
            lookPoker(userIds[i]);
        }
    }



    /**
     * 1 给用户创建牌集合
     * 2 发牌,实际存储的是一张牌在整副扑克中的索引
     * @param userIds
     */
    public static void getUserPokers(Integer[] userIds){
        if(null==userPokers){
            userPokers = new HashMap<Integer,TreeSet<Integer>>();
        }
        //用户牌仓库
        for(int i=0;i<userIds.length;i++){
            TreeSet<Integer> treeset = new TreeSet<Integer>();
            userPokers.put(userIds[i],treeset);
        }
        //往仓库发牌
        boolean flagfapai =true;
        do {
            System.out.println("--do-------"+cntPoker);
            for(int i=0;i<userIds.length;i++){
                if (cntPoker>=54){//发完结束
                    flagfapai=false;
                    System.out.println("--发完了-------"+cntPoker);

                    break;
                }
                TreeSet<Integer> treeset = userPokers.get(userIds[i]);//获取牌仓库，往里面加牌
                if(treeset.size()>=5){//满5张结束
                    flagfapai=false;
                    System.out.println("--一个人发满了-------"+cntPoker);
                    break;
                }
                if(flagfapai){
                    treeset.add(indexs.get(cntPoker));
                    cntPoker++;
                }

            }
        }while (flagfapai);

    }

    /**
     * 洗牌
     */
    public static void getPokers(){
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
    }


    /**
     * 看牌
     * @param userId
     */
    private static void lookPoker(Integer userId) {
        //根据获取到的索引，取出牌
        TreeSet<Integer> treeset = userPokers.get(userId);
        System.out.print("【"+treeset.size()+"张】"+userId+"的牌：  ");
        for (Integer index : treeset) {
            System.out.print(hashmap.get(index)+"  ");
        }
        System.out.println("");

    }
}
