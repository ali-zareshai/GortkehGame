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
    private static List<String> listAhdadManfi=new ArrayList<>();

    private static int tehdadArgam=2;
    private static int tehdadAhdad=5;
    private static int level_game=1;
    private static int javab=0;

    public static List<String> getInit(int argam,int ahdad,int level){
        tehdadAhdad=ahdad;
        tehdadArgam= argam;
        level_game=level;
        reset();
        return passjavab();
    }

    private static List<String> passjavab() {
        while (true){
            listAhdadManfi=removeManfi(getRandomAmal());
            if (javab>0){
                return listAhdadManfi;
            }else {
                reset();
            }
        }
    }

    private static void reset(){
        numbers=null;
        finallist=null;
        ALAMAT=null;
        listAhdadManfi=null;

        numbers=new ArrayList<>();
        finallist=new ArrayList<>();
        ALAMAT=new ArrayList<>();
        listAhdadManfi=new ArrayList<>();

        javab=0;
    }

    private static List<String> removeManfi(List<String> randomAmal) {
        for (int i=0;i<randomAmal.size();i++){
            if (randomAmal.get(i).equals("-")){
                randomAmal.remove(i);
                randomAmal.set(i,"-"+randomAmal.get(i));
            }
        }
        return randomAmal;
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
                if (min_(x)<0){
                    reset();
                    RandomNum.getInit(tehdadArgam,tehdadAhdad,level_game);
                    break;
                }
            }
        }
        // reverse list
//        Collections.reverse(finallist);
        return finallist;

    }

    private static int min_(int x) {
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
        return javab;
//        if (javab<0){
//            System.out.println(String.valueOf(javab)+"====>"+String.valueOf(x));
//            reset();
//            //passjavab();
//            RandomNum.getInit(tehdadArgam,tehdadAhdad,level_game);
//        }

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
