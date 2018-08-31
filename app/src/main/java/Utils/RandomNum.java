package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNum {
    private static Random random=new Random();;
    private static List<Integer> numbers=new ArrayList<>();
    private static List<String> finallist=new ArrayList<>();
    private static List<Boolean> ALAMAT=new ArrayList<>();

    private static int tehdadArgam=2;
    private static int tehdadAhdad=5;
    private static int level_game=1;
    private static int javab=0;

    public static List<String> getInit(int argam,int ahdad,int level){
        tehdadAhdad=ahdad;
        tehdadArgam= argam;
        level_game=level;

        numbers=null;
        finallist=null;
        ALAMAT=null;

        numbers=new ArrayList<>();
        finallist=new ArrayList<>();
        ALAMAT=new ArrayList<>();

        javab=0;

        return getRandomAmal();
    }

    public static int getJavab(){
        return javab;
    }









    //++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private static int getRandomNum(){
        switch (tehdadArgam){
            case 1:
                return random.nextInt(9)+1;
            case 2:
                return random.nextInt(90)+10;
            case 3:
                return random.nextInt(900)+100;
                default:
                    return 0;
        }
    }



    private static List<Integer> getRandomNums(){
        for (int x=0;x<tehdadAhdad;x++){
            numbers.add(getRandomNum());
        }
        Collections.reverse(numbers);
        return numbers;
    }
    private static List<Boolean> getAlamts(){
        List<Boolean> list=new ArrayList<>();
        for (int x=1;x<numbers.size();x++){
            if (level_game==1){
                list.add(true);
            }else if (level_game==2){
                if (random.nextInt(2)==1){
                    list.add(true);
                }else {
                    list.add(false);
                }
            }
        }
        Collections.reverse(list);
        return list;
    }
    private static List<String> getRandomAmal(){
        getRandomNums();
        ALAMAT=getAlamts();
        for (int x=0;x<ALAMAT.size();x++){
            if (ALAMAT.get(x)){
                sum_(x);
            }else {
                min_(x);
            }
        }
        // reverse list
//        Collections.reverse(finallist);
        return finallist;

    }

    private static void min_(int x) {
        if (x==0){
            javab=numbers.get(0)-numbers.get(1);
            finallist.add(String.valueOf(numbers.get(0)));
            finallist.add("-");
            finallist.add(String.valueOf(numbers.get(1)));
        }else {
            javab=javab-numbers.get(x+1);
            finallist.add("-");
            finallist.add(String.valueOf(numbers.get(x+1)));
        }

    }

    private static void sum_(int x){
        if (x==0){
            javab=numbers.get(1)+numbers.get(0);
            finallist.add(String.valueOf(numbers.get(x)));
//            finallist.add("+");
            finallist.add(String.valueOf(numbers.get(x+1)));

        }else {
            javab=javab+numbers.get(x+1);
//            finallist.add("+");
            finallist.add(String.valueOf(numbers.get(x+1)));
        }


    }
}
