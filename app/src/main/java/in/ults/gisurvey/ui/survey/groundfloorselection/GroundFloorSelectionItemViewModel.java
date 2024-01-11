package in.ults.gisurvey.ui.survey.groundfloorselection;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

public class GroundFloorSelectionItemViewModel {

    private final ObservableField<String> floorNumber = new ObservableField<>("");
    private final ObservableField<Integer> floorImageType = new ObservableField<>(-1);
    private final ObservableBoolean isSelected = new ObservableBoolean(false);

    GroundFloorSelectionItemViewModel() {
    }

    public ObservableField<String> getFloorNumber() {
        return floorNumber;
    }

    public ObservableField<Integer> getFloorImageType() {
        return floorImageType;
    }

    void setFloorNumber(String floorNumber) {
        this.floorNumber.set(floorNumber);
    }

    void setFloorImageType(int floorImageType) {
        this.floorImageType.set(floorImageType);
    }

    public ObservableBoolean getIsSelected() {
        return isSelected;
    }

    void setIsSelected(boolean isSelected) {
        this.isSelected.set(isSelected);
    }
}
