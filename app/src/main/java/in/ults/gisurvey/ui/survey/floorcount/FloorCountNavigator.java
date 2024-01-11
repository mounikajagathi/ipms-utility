package in.ults.gisurvey.ui.survey.floorcount;

import in.ults.gisurvey.ui.base.BaseNavigator;

public interface FloorCountNavigator extends BaseNavigator {

    void navigateToNextPage();

    void saveFloorPropertyCount();

    void increaseFloorCount();

    void decreaseFloorCount();

    void increasePropertyCount();

    void decreasePropertyCount();

    void setCount(int floorCount, int propertyCount);

}
