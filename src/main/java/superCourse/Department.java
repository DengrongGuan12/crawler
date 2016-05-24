package superCourse;

import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by I322233 on 5/10/2016.
 */
public class Department {
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    private String schoolId;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;


    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
    private ArrayList<String> courses = new ArrayList<String>();


    public void requestCourses(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID="+User.cookie);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","209");
        int page = 0;
        String params = "";
        String coursesJson = "";
        while(true){
            params = "platform=1&tagId="+id+"&beginYear=2015&phoneVersion=23&phoneBrand=Android&index=&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId="+schoolId+"&type=0&page="+page+"&channel=advertising&term=1&";
            coursesJson = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseNameListByTagV2.action",params,headers);
                    JSONObject raw = new JSONObject(coursesJson);
            JSONObject data = raw.getJSONObject("data");
            JSONArray courseList = data.getJSONArray("courseList");
            if(courseList.length() == 0){
                break;
            }
            for(int i = 0;i<courseList.length();i++){
                JSONObject course = courseList.getJSONObject(i);
                String courseName = course.getString("name");
                if(courses.contains(courseName)){
                    continue;
                }
                System.out.println("add course:"+courseName);
                courses.add(courseName);
            }
            page++;
        }
        page = 0;
        while (true){
            params = "platform=1&tagId="+id+"&beginYear=2015&phoneVersion=23&phoneBrand=Android&index=&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId="+schoolId+"&type=0&page="+page+"&channel=advertising&term=2&";
            coursesJson = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseNameListByTagV2.action",params,headers);
            JSONObject raw = new JSONObject(coursesJson);
            JSONObject data = raw.getJSONObject("data");
            JSONArray courseList = data.getJSONArray("courseList");
            if(courseList.length() == 0){
                break;
            }
            for(int i = 0;i<courseList.length();i++){
                JSONObject course = courseList.getJSONObject(i);
                String courseName = course.getString("name");
                if(courses.contains(courseName)){
                    continue;
                }
                System.out.println("add course:"+courseName);
                courses.add(courseName);
            }
            page++;
        }

    }

}
