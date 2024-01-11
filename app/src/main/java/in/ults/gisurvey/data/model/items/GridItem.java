package in.ults.gisurvey.data.model.items;

public class GridItem {
    private String id;
    private String content;
    private boolean completedStatus;
    private  boolean isEditVisible;
    private  boolean isSurveyOpenEdit;
    public GridItem() {

    }

    public GridItem(String id, String content, boolean completedStatus) {
        this.id = id;
        this.content = content;
        this.completedStatus = completedStatus;
    }

    public boolean isSurveyOpenEdit() {
        return isSurveyOpenEdit;
    }

    public void setSurveyOpenEdit(boolean surveyOpenEdit) {
        isSurveyOpenEdit = surveyOpenEdit;
    }

    public boolean isEditVisible() {
        return isEditVisible;
    }

    public void setEditVisible(boolean editVisible) {
        isEditVisible = editVisible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(boolean completedStatus) {
        this.completedStatus = completedStatus;
    }
}
