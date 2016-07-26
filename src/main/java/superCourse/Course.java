package superCourse;

import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequest;

import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by dengrong on 2016/7/23.
 */
public class Course {
    private String name;
    private String schoolId;
    private int courseId = 0;
    private int term = 1;
    private int grade=0;//年级
    private String courseList = "";
    public String getCourseList(){
        return courseList;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    private List<Integer> studentIdList = new ArrayList<Integer>();
    private Map<Integer,Integer> stuNum = new HashMap<Integer, Integer>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    private void calCourseId(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID="+User.cookie);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","174");
        String nameUrl = URLEncoder.encode(name);
        String params = "phoneVersion=23&day=0&schoolId="+schoolId+"&platform=1&sectionEnd=0&phoneBrand=Android&name="+nameUrl+"&versionNumber=7.5.0&startYear=2015&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&type=0&page=0&channel=OfficialMarket&term="+term+"&sectionStart=0&";
        String aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getTagCourseListByNameV2.action",
                params,
                headers);
//        System.out.println(aca);
        JSONObject raw = new JSONObject(aca);
        courseList = "{"+name+":"+aca+"}";
        JSONObject data = raw.getJSONObject("data");
        JSONArray courseList = data.getJSONArray("courseList");
        //目前只取第一个课程作为course的id
        if(courseList.length() > 0){
            JSONObject courseData = courseList.getJSONObject(0);
            courseId = courseData.optInt("courseId");
        }
    }
    public void getCourseListByName(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID="+User.cookie);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","174");
        String nameUrl = URLEncoder.encode(name);
        int page = 0;
        this.courseList = "{"+name+":[";
        while(true){
            String params = "phoneVersion=23&day=0&schoolId="+schoolId+"&platform=1&sectionEnd=0&phoneBrand=Android&name="+nameUrl+"&versionNumber=7.5.0&startYear=2015&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&type=0&page="+page+"&channel=OfficialMarket&term="+term+"&sectionStart=0&";
            String aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getTagCourseListByNameV2.action",
                    params,
                    headers);
//        System.out.println(aca);
            JSONObject raw = new JSONObject(aca);
//        courseList = "{"+name+":"+aca+"}";
            JSONObject data = raw.getJSONObject("data");
            JSONArray courseList = data.getJSONArray("courseList");
            for(int i = 0;i<courseList.length();i++){
                JSONObject c = courseList.getJSONObject(i);
                this.courseList+=c.toString()+",";
            }
            boolean hasMore = data.getBoolean("hasMoreBool");
            if(hasMore){
                page++;
                continue;
            }else{
                break;
            }
        }
        this.courseList = this.courseList.substring(0,this.courseList.length()-1);
        this.courseList+="]}";
    }

    private void requestClassmates(){
        if(courseId != 0){
            HttpRequest httpRequest = new HttpRequest();
            Map<String,String> headers = new HashMap<String, String>();
            headers.put("Cookie","JSESSIONID="+User.cookie);
            headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
            headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
            headers.put("Host","120.55.151.61");
            headers.put("Connection","Keep-Alive");
            headers.put("Accept-Encoding","gzip");
            headers.put("Content-Length","174");
            String params = "platform=1&versionNumber=7.5.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&courseId="+courseId+"&channel=OfficialMarket&phoneVersion=23&phoneBrand=Android&";
            String aca = httpRequest.sendPost("http://120.55.151.61/V2/Classmate/getClassmateInfoV2.action",
                    params,
                    headers);
            JSONObject raw = new JSONObject(aca);
            JSONObject data = raw.getJSONObject("data");
            JSONArray studentList = data.getJSONArray("studentList");
            //目前只取第一个课程作为course的id
            if(studentList.length() > 0){
                for(int i = 0;i<studentList.length();i++){
                    JSONObject student = studentList.getJSONObject(i);
                    studentIdList.add(student.getInt("id"));
                }
            }
        }
    }

    private void requestStudentDetail(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID="+User.cookie);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","174");
        for(Integer id:studentIdList){
            String params = "platform=1&versionNumber=7.5.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&studentId="+id+"&channel=OfficialMarket&phoneVersion=23&phoneBrand=Android&";
            String aca = httpRequest.sendPost("http://120.55.151.61/V2/Student/getInfoByIdV4.action",
                    params,
                    headers);
            try{
                JSONObject raw = new JSONObject(aca);
                JSONObject data = raw.getJSONObject("data");
                int grade = data.getInt("grade");
                Integer num = stuNum.get(grade);
                if(num == null){
                    num = 1;
                }else{
                    num++;
                }
                stuNum.put(grade,num);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>(stuNum.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Integer>>()
                {
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
                    {
                        if ((o2.getValue() - o1.getValue())>0)
                            return 1;
                        else if((o2.getValue() - o1.getValue())==0)
                            return 0;
                        else
                            return -1;
                    }
                }
        );
        Map.Entry<Integer,Integer> max = list_Data.get(0);
        this.grade = 16-max.getKey();
//        System.out.println(16-max.getKey());
    }

    public void calCourseGrade(){
        this.calCourseId();
        this.requestClassmates();
        this.requestStudentDetail();
    }

}
