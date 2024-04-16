import java.util.HashMap;
import java.util.Map;

public class EmployeeProject {

    private int projectId;
    private int longestEmployeeId;
    private int longestEmployeeDaysCount ;
    private int secondLongestEmployeeId = -1;
    private int secondLongestEmployeeDaysCount = -1;



    private static final Map<Integer, EmployeeProject> listOfProjects = new HashMap<>();
    private static int longestCollaborationProjectId;
    private static int longestCollaborationDays = -1;

    public EmployeeProject(int employeeId, int projectId, int daysCount) {
        this.projectId = projectId;
        this.longestEmployeeId = employeeId;
        this.longestEmployeeDaysCount = daysCount;
        listOfProjects.put(projectId,this);
    }

    public void setLongestEmployeeId(int longestEmployeeId) {
        this.longestEmployeeId = longestEmployeeId;
    }

    public void setLongestEmployeeDaysCount(int longestEmployeeDaysCount) {
        this.longestEmployeeDaysCount = longestEmployeeDaysCount;
    }

    public void setSecondLongestEmployeeId(int secondLongestEmployeeId) {
        this.secondLongestEmployeeId = secondLongestEmployeeId;
    }

    public void setSecondLongestEmployeeDaysCount(int secondLongestEmployeeDaysCount) {
        this.secondLongestEmployeeDaysCount = secondLongestEmployeeDaysCount;
    }

    public int getLongestEmployeeId() {
        return this.longestEmployeeId;
    }

    public int getSecondLongestEmployeeId() {
        return this.secondLongestEmployeeId;
    }

    public int getSecondLongestEmployeeDaysCount() {
        return this.secondLongestEmployeeDaysCount;
    }

    public static Map<Integer, EmployeeProject> getListOfProjects() {
        return listOfProjects;
    }

    public static int getLongestCollaborationProjectId() {
        return longestCollaborationProjectId;
    }

    public static void updateProjects(int empId, int projectId ,int numberOfDaysWorked) {
        EmployeeProject currProject = listOfProjects.get(projectId);
        if (currProject.longestEmployeeDaysCount<numberOfDaysWorked){

            currProject.setSecondLongestEmployeeId(currProject.longestEmployeeId);
            currProject.setSecondLongestEmployeeDaysCount(currProject.longestEmployeeDaysCount);

            currProject.setLongestEmployeeDaysCount(numberOfDaysWorked);
            currProject.setLongestEmployeeId(empId);
        } else if (currProject.secondLongestEmployeeDaysCount<numberOfDaysWorked) {
            currProject.setSecondLongestEmployeeDaysCount(numberOfDaysWorked);
            currProject.setSecondLongestEmployeeId(empId);
        }

        if (currProject.secondLongestEmployeeId > 0 && currProject.secondLongestEmployeeDaysCount > longestCollaborationDays){
            longestCollaborationDays = currProject.secondLongestEmployeeDaysCount;
            longestCollaborationProjectId = currProject.projectId ;
        }
    }

    public static EmployeeProject findById(int projectId){
        return listOfProjects.get(projectId);
    }

}
