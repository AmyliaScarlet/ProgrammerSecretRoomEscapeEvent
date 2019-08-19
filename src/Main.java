import amyliascarlet.lib.Log;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        //DoEvent_SwitchOpen();

        //DoEvent_SwitchClose();
    }

    //最初灯是开着的情况
    public static void DoEvent_SwitchOpen(){
        //总共被放出来的次数
        int nTimes = 0;
        int nTimes99 = 0;
        boolean isPut = false;

        int nCount = 0;
        //是否全部放出来过
        boolean isAllOut = false;

        TreeMap tm = new TreeMap();

        House house = new House(true);
        //程序员被抓啦
        Programmer pro = new Programmer(0);
        Person[] Persons = new Person[100];
        Persons[0] = pro;
        //还抓了其他99个人
        for(int i=1;i<100;i++){
            Person p = new Person(i);
            Persons[i] = p;
        }

        //魔鬼游戏开始了
        while (!isAllOut) {
            //有人被放出来了
            nTimes++;

            //放出来的人对应的编号
            int o = ChoseOutId();

            //如此一来这人就被放出过了
            Persons[o].Out();
            Log.i(o+"出来了");

            //看看灯开关状态
            nCount = ProcessingScheme(nCount, tm, house, Persons, o);

            if(((Programmer)Persons[0]).getCloseSwitchTimes() ==99 && !isPut){
                nTimes99 = nTimes;
                isPut=true;
            }
            if(((Programmer)Persons[0]).getCloseSwitchTimes() ==100){
                isAllOut = true;
            }else {
                Log.i("关灯次数"+((Programmer)Persons[0]).getCloseSwitchTimes());
            }
        }
        Log.i(nTimes99+"次放出后达到99次关灯,总共放出"+nTimes+"次通关");
        //Log.i(tm);

        /*
        *  原作中程序员困惑的地方就在这里 nTimes一定比nTimes99大 但大多少谁也不知道
        *  nTimes次的到来是遥遥无期的，因此程序员陷入迷茫
        * */

    }

    //最初灯是关着的情况
    public static void DoEvent_SwitchClose(){
        //总共被放出来的次数
        int nTimes = 0;
        int nCount = 0;
        //是否全部放出来过
        boolean isAllOut = false;

        TreeMap tm = new TreeMap();

        House house = new House(false);
        //程序员被抓啦
        Programmer pro = new Programmer(0);
        Person[] Persons = new Person[100];
        Persons[0] = pro;
        //还抓了其他99个人
        for(int i=1;i<100;i++){
            Person p = new Person(i);
            Persons[i] = p;
        }

        //魔鬼游戏开始了
        while (!isAllOut) {
            //有人被放出来了
            nTimes++;

            //放出来的人对应的编号
            int o = ChoseOutId();

            //如此一来这人就被放出过了
            Persons[o].Out();
            Log.i(o+"出来了");

            nCount = ProcessingScheme(nCount, tm, house, Persons, o);

            if(((Programmer)Persons[0]).getCloseSwitchTimes() ==99){
                isAllOut = true;
            }else {
                Log.i("关灯次数"+((Programmer)Persons[0]).getCloseSwitchTimes());
            }
        }
        Log.i("总共放出"+nTimes+"次通关");
        //Log.i(tm);
    }

    //随机选择出来的人
    private static int ChoseOutId(){
        return (int)(Math.random()*101 - 1);
    }

    //逃脱方案
    private static int ProcessingScheme(int nCount, TreeMap tm, House house, Person[] persons, int o) {
        //看看灯开关状态
        if(house.getSwitchStaus()){
            //灯开着
            if(persons[o].isProgrammer()){
                //程序员看到灯开着就去关掉并且记一次数
                ((Programmer) persons[o]).closeSwitch(house);
            }else{
                //其他人这时候不要碰
                Log.i(o+"什么都没做回去了");
            }
        }else{
            //灯关着
            if(persons[o].isProgrammer()){
                //程序员不开灯
                Log.i("程序员什么都没做回去了");

            }else{
                //是其他人的话按要求将灯打开 且只能打开一次 之后就不要碰开关了
                if(!persons[o].isOpenSwitch()){
                    persons[o].openSwitch(house);
                    nCount++;
                    tm.put(nCount,o);
                    Log.i("编号"+ o +"第"+nCount+"个打开了灯");

                }else {
                    Log.i(o+"开过了就不再开了直接回去了，当前开过灯的人数："+nCount);
                }
            }
        }
        return nCount;
    }


}
