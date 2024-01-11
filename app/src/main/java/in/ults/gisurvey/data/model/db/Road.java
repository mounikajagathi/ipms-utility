package in.ults.gisurvey.data.model.db;

import androidx.room.ColumnInfo;


public class Road {

    @ColumnInfo(name = "near_road")
    public String nearRoad = "";

    @ColumnInfo(name = "road_type")
    public String roadType = "";

    @ColumnInfo(name = "road_width")
    public String roadWidth = "";

    public String getNearRoad() {
        return nearRoad;
    }

    public String getRoadType() {
        return roadType;
    }

    public String getRoadWidth() {
        return roadWidth;
    }
}
