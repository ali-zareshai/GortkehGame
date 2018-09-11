package Utils;

import android.content.Context;

import com.kavireletronic.ali.gortkehgame.R;

public class LevelGame {
    public static String getLevelSp(String level){
        switch (level){
            case "1":
                return "asan_sp";
            case "2":
                return "medium_sp";
            case "3":
                return "hard_sp";
            case "4":
                return "pro_sp";
        }
        return "--";
    }
    public static int getEmtiazLevel(String level){
        switch (level){
            case "1":
                return 5;
            case "2":
                return 10;
            case "3":
                return 15;
            case "4":
                return 20;
        }
        return 0;
    }

    public static String nameLevel(String level, Context context){
        switch (level){
            case "1":
                return context.getString(R.string.asan);
            case "2":
                return context.getString(R.string.medium);
            case "3":
                return context.getString(R.string.hard);
            case "4":
                return context.getString(R.string.pro);
        }
        return "";
    }
}