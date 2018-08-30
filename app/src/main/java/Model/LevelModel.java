package Model;

public class LevelModel {
    private String name,level,ahdad,argame;

    public LevelModel() {
    }

    public LevelModel(String name, String level, String ahdad, String argame) {
        this.name = name;
        this.level = level;
        this.ahdad = ahdad;
        this.argame = argame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAhdad() {
        return ahdad;
    }

    public void setAhdad(String ahdad) {
        this.ahdad = ahdad;
    }

    public String getArgame() {
        return argame;
    }

    public void setArgame(String argame) {
        this.argame = argame;
    }
}
