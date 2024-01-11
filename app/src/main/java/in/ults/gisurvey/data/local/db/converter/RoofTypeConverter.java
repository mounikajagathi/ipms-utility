package in.ults.gisurvey.data.local.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import in.ults.gisurvey.data.model.db.BuildingDetailsRoofItem;
import in.ults.gisurvey.data.model.db.MemberDetailsItem;

/**
 * Created by Mohammed Shafeeq on 2/26/2019.
 */

/**
 * to convert gson to json
 */
public class RoofTypeConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<BuildingDetailsRoofItem> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<BuildingDetailsRoofItem>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ArrayListToString(ArrayList<BuildingDetailsRoofItem> someObjects) {
        return gson.toJson(someObjects);
    }
}