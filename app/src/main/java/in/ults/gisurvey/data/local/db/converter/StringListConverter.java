package in.ults.gisurvey.data.local.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Mohammed Shafeeq on 2/26/2019.
 */
/**
 * to convert gson to json
 */
public class StringListConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<String> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ArrayListToString(ArrayList<String> someObjects) {
        return gson.toJson(someObjects);
    }
}