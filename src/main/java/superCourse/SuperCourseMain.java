package superCourse;

/**
 * Created by I322233 on 5/10/2016.
 * 超级课程表爬取工具，缺少Secret类，因为里面涉及密码= =!
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
