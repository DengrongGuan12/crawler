package superCourse;

/**
 * Created by I322233 on 5/10/2016.
 */
public class SuperCourseMain {
    public static void main(String[] args){
        User user = new User();
        user.login();
        Schools schools = new Schools();
        schools.initSchools();
        schools.requestSchools();
    }
}
