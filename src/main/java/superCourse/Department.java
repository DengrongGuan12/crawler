package superCourse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by I322233 on 5/10/2016.
 */
public class Department {
    public String getSchoolId() {
        return schoolId;
    }

    private String schoolName;

    private String coursesString = "";

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
    private Map<Integer,Course> idCourse = new HashMap<Integer, Course>();

//    private Map<String,Course> nameCourseMap = new HashMap<String, Course>();
//    public Map<String,Course> getNameCourseMap(){
//        return this.nameCourseMap;
//    }
    private List<Course> courseList = new ArrayList<Course>();
    public List<Course> getCourseList(){
        return this.courseList;
    }
    public void requestCourses(Map<String,Integer> courseGrade,Map<String,String> courseListMap){
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
            params = "platform=1&tagId="+id+"&beginYear=2012&phoneVersion=23&phoneBrand=Android&index=&versionNumber=7.5.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId="+schoolId+"&type=0&page="+page+"&channel=OfficialMarket&term=1&";
            coursesJson = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseNameListByTagV2.action",params,headers);
            System.out.println(coursesJson);
            JSONObject raw = new JSONObject(coursesJson);
            JSONObject data = raw.optJSONObject("data");
            if(data == null){
                continue;
            }
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
//                String coursesList = "";
//                if(courseListMap.containsKey(courseName)){
//                    coursesList = courseListMap.get(courseName);
//                }else{
//                    Course course1 = new Course();
//                    course1.setName(courseName);
//                    course1.setSchoolId(schoolId);
//                    course1.setTerm(1);
//                    //请求课程的详细信息需要
//                    course1.getCourseListByName();
//                    coursesList = course1.getCourseList();
//                    courseListMap.put(courseName,coursesList);
//                }
//                coursesString+=coursesList+",";

                //请求年级的时候需要
                Course course1 = new Course();
                course1.setName(courseName);
                course1.setSchoolId(schoolId);
                course1.setTerm(1);
                if(courseGrade.containsKey(courseName)){
                    int grade = courseGrade.get(courseName);
                    course1.setGrade(grade);
                }else {
                    course1.calCourseGrade();
                    courseGrade.put(courseName,course1.getGrade());
                }
                this.courseList.add(course1);
                courses.add(courseName);

                System.out.println("add course:"+courseName);
            }
            page++;
        }
        page = 0;
        while (true){
            params = "platform=1&tagId="+id+"&beginYear=2012&phoneVersion=23&phoneBrand=Android&index=&versionNumber=7.5.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId="+schoolId+"&type=0&page="+page+"&channel=OfficialMarket&term=2&";
            coursesJson = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseNameListByTagV2.action",params,headers);
            JSONObject raw = new JSONObject(coursesJson);
            JSONObject data = raw.optJSONObject("data");
            if(data == null){
                continue;
            }
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
//                String coursesList = "";
//                if(courseListMap.containsKey(courseName)){
//                    coursesList = courseListMap.get(courseName);
//                }else{
//                    Course course1 = new Course();
//                    course1.setName(courseName);
//                    course1.setSchoolId(schoolId);
//                    course1.setTerm(1);
//                    //请求课程的详细信息需要
//                    course1.getCourseListByName();
//                    coursesList = course1.getCourseList();
//                    courseListMap.put(courseName,coursesList);
//                }
//                coursesString+=coursesList+",";

                //请求年级
                Course course1 = new Course();
                course1.setName(courseName);
                course1.setSchoolId(schoolId);
                course1.setTerm(2);
                if(courseGrade.containsKey(courseName)){
                    int grade = courseGrade.get(courseName);
                    course1.setGrade(grade);
                }else{
                    course1.calCourseGrade();
                    courseGrade.put(courseName,course1.getGrade());
                }
                this.courseList.add(course1);
                courses.add(courseName);
                System.out.println("add course:"+courseName);
            }
            page++;
        }

    }

    public void writeToExcel(){
        //写入专业课
        System.out.println("write to excel!");
        try{
            File file =new File("C:\\Users\\dengrong\\course\\"+schoolName);
//如果文件夹不存在则创建
            if  (!file .exists()  && !file .isDirectory())
            {
                file .mkdir();
            }
            String filePath = "C:\\Users\\dengrong\\course\\"+schoolName+"\\"+name+".xls";
            File f = new File(filePath);
            WritableWorkbook wwb = null;
            WritableSheet ws = null;
            Label dL = null;
            Label cL = null;
            Label gL = null;
            if (!f.exists()) {
                f.createNewFile();
            }
            wwb = Workbook.createWorkbook(f);
            ws = wwb.createSheet("Sheet 1",0);
            dL = new Label(0,0,"院系");
            cL = new Label(1,0,"课程");
            gL = new Label(2,0,"年级");
            ws.addCell(dL);
            ws.addCell(cL);
            ws.addCell(gL);
            int i1 = 1;
            for (Course course:
                    this.courseList) {
                dL = new Label(0,i1,this.name);
                cL = new Label(1,i1,course.getName());
                gL = new Label(2,i1,course.getGrade()+"");
                ws.addCell(dL);
                ws.addCell(cL);
                ws.addCell(gL);
                i1++;
            }
            wwb.write();
            wwb.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeToTxt(){
        System.out.println("write to txt!");
        try {
            String filePath = "C:\\Users\\dengrong\\course\\"+schoolName+"\\"+name+".txt";;
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            output.write(coursesString);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
