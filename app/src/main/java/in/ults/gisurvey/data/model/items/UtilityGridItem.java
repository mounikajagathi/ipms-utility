package in.ults.gisurvey.data.model.items;

import android.graphics.drawable.Drawable;

public class UtilityGridItem {
    private int id;
    private String name;
    private int img;
    public UtilityGridItem() {

    }

    public UtilityGridItem(int id, String name, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
