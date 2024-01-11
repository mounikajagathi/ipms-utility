package in.ults.gisurvey.data.local.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import in.ults.gisurvey.data.model.db.BuildingDetailsFloorAreaItem;
import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;

/**
 * Created by Mohammed Shafeeq on 2/26/2019.
 */

/**
 * to convert gson to json
 */
public class FloorAreaConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<BuildingDetailsFloorAreaItem> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<BuildingDetailsFloorAreaItem>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ArrayListToString(ArrayList<BuildingDetailsFloorAreaItem> someObjects) {
        return gson.toJson(someObjects);
    }
}