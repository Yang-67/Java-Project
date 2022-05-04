package beans;

public class Major {
    String majorId;

    public Major() {
    }

    public Major(String majorId, String collegeId, String majorName) {
        this.majorId = majorId;
        this.collegeId = collegeId;
        this.majorName = majorName;
    }

    public String getMajorId() {
        return majorId;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId='" + majorId + '\'' +
                ", collegeId='" + collegeId + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    String collegeId;
    String majorName;

}
