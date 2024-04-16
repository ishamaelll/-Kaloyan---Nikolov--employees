import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class EmployeeCollaborationFinder {

    //I assumed that the longest period of time is the lowest
    //of the two employees time spent on the given project
    //that was the only thing that made sense in the task description
    //also i would add some null checks and unit tests
    public static void main(String[] args) {
        String filePath = System.getProperty("user.home") + "/Desktop" +"/input.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int empId = Integer.parseInt(data[0]);
                int projectId = Integer.parseInt(data[1]);
                int numberOfDaysWorked = calculateDaysBetween(data[2], data[3]);
                Map <Integer,EmployeeProject> projectMap = EmployeeProject.getListOfProjects();
                if (projectMap.containsKey(projectId)) {
                    EmployeeProject.updateProjects(empId , projectId , numberOfDaysWorked);
                }
                else {
                    new EmployeeProject(empId, projectId, numberOfDaysWorked);
                }
            }
            int longestCollaborationProjectId = EmployeeProject.getLongestCollaborationProjectId();
            EmployeeProject longestCollabProject = EmployeeProject.findById(longestCollaborationProjectId);
            System.out.println(longestCollabProject.getLongestEmployeeId() + ", " + longestCollabProject.getSecondLongestEmployeeId()
                    + ", " + longestCollabProject.getSecondLongestEmployeeDaysCount());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static int calculateDaysBetween(String dateFrom, String dateTo) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(dateFrom, dateFormat);
        LocalDate date2 = dateTo.equals("NULL") ? LocalDate.now() : LocalDate.parse(dateTo, dateFormat);
        return (int) ChronoUnit.DAYS.between(date1, date2);
    }
}