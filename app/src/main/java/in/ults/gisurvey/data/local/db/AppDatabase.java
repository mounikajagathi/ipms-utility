package in.ults.gisurvey.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import in.ults.gisurvey.data.local.db.converter.BasementAreaConverter;
import in.ults.gisurvey.data.local.db.converter.FloorAreaConverter;
import in.ults.gisurvey.data.local.db.converter.MemberDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.RoofTypeConverter;
import in.ults.gisurvey.data.local.db.converter.StringListConverter;
import in.ults.gisurvey.data.local.db.converter.SurveyorDetailsConverter;
import in.ults.gisurvey.data.local.db.converter.VehicleDetailsConverter;
import in.ults.gisurvey.data.local.db.dao.PropertyDao;
import in.ults.gisurvey.data.local.db.dao.SurveyDao;
import in.ults.gisurvey.data.local.db.dao.SurveyWithPropertyDao;
import in.ults.gisurvey.data.model.db.Dashboard;
import in.ults.gisurvey.data.model.db.Property;
import in.ults.gisurvey.data.model.db.Survey;

@Database(entities = {Survey.class, Property.class}, version = 1)
@TypeConverters({StringListConverter.class, MemberDetailsConverter.class, RoofTypeConverter.class, FloorAreaConverter.class, VehicleDetailsConverter.class, BasementAreaConverter.class, SurveyorDetailsConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SurveyDao surveyDao();
    public abstract PropertyDao propertyDao();
    public abstract SurveyWithPropertyDao surveyWithPropertyDao();
}
