package in.ults.gisurvey.data.local.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import in.ults.gisurvey.data.model.api.Surveyor;

/**
 * Created by anuag on 18-11-2020
 **/
public class SurveyorDetailsConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<Surveyor> stringToList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }
        Type listType = new TypeToken<ArrayList<Surveyor>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ArrayListToString(ArrayList<Surveyor> someObjects) {
        return gson.toJson(someObjects);
    }
}
