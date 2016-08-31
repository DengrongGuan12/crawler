package superCourse;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import util.HttpRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by I322233 on 5/10/2016.
 */
public class School {
    private String name;
    private ArrayList<String> allCourses = new ArrayList<String>();//全部不重复课程
    private HashMap<String,ArrayList<String>> courseDepartments = new HashMap<String, ArrayList<String>>();//通适课
    private HashMap<String,Integer> courseGrade = new HashMap<String, Integer>();
    public School(String id,String name){
        this.schoolId = id;
        this.name = name;
    }
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
    private ArrayList<Department> deparmentsList = new ArrayList<Department>();
    private ArrayList<String> departments = new ArrayList<String>();
    private Map<String,String> courseNameListMap = new HashMap<String, String>();
    private String schoolId;
    public void requestDepartments(){
        System.out.println(name+"请求中...");
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID="+User.cookie);
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","178");
        String params = "platform=1&beginYear=2012&phoneVersion=23&phoneBrand=Android&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId="+schoolId+"&channel=OfficialMarket&term=2&";
//        RequestDepartmentsThread requestDepartmentsThread = new RequestDepartmentsThread(httpRequest,params,headers);
//        requestDepartmentsThread.start();
//        try {
//            requestDepartmentsThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while(true){
            String aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseTagsV2.action",
                    params,
                    headers);
            Departments departments = new Departments(aca);
            departments.parse();
            boolean hasMore = false;
            for (Department d:departments.getDepartments()){
                if(this.departments.contains(d.getName())){
                    continue;
                }else{
                    d.setSchoolName(name);
                    this.deparmentsList.add(d);
                    this.departments.add(d.getName());
                    System.out.println("add department:"+d.getName()+";"+d.getId());
                    hasMore = true;
                }
            }
            if(!hasMore){
                break;
            }
        }
        for (Department d:this.deparmentsList
             ) {
            d.setSchoolId(schoolId);
            if(d.getName().equals("研究生院")){
                continue;
            }
            d.requestCourses(courseGrade,courseNameListMap);
            d.writeToExcel();
//            d.writeToTxt();
        }
    }
    public void writeDataToTxt(){
        try {
            String filePath = "C:\\Users\\i322233\\"+name+".txt";
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            output.write("院系 课程\r\n");
            for(Department d:this.deparmentsList){
                ArrayList<String> courses = d.getCourses();
                for(String course:courses){
                    output.write(d.getName()+" "+course+"\r\n");
                }
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDataToExcel(){
        try{
            String filePath = "C:\\Users\\i322233\\"+name+"_专业.xls";
            String filePath2 = "C:\\Users\\i322233\\"+name+"_通识.xls";
            File f = new File(filePath);
            File f2 = new File(filePath2);
            if (!f.exists()) {
                f.createNewFile();
            }
            if(!f2.exists()){
                f2.createNewFile();
            }
            for(Department d:this.deparmentsList){
                ArrayList<String> courses = d.getCourses();
                for (String course:
                     courses) {
                    if(this.courseDepartments.containsKey(course)){
                        this.courseDepartments.get(course).add(d.getName());
                    }else{
                        ArrayList<String> departments = new ArrayList<String>();
                        departments.add(d.getName());
                        this.courseDepartments.put(course,departments);
                    }
                }
            }
            WritableWorkbook wwb = Workbook.createWorkbook(f);
            WritableWorkbook wwb2 = Workbook.createWorkbook(f2);
            WritableSheet ws = wwb.createSheet("Sheet 1",0);
            WritableSheet ws2 = wwb2.createSheet("Sheet 1",0);
            Label dL = new Label(0,0,"院系");
            Label cL = new Label(1,0,"课程");
            Label dl2 = new Label(0,0,"院系");
            Label cl2 = new Label(1,0,"课程");
            ws.addCell(dL);
            ws.addCell(cL);
            ws2.addCell(dl2);
            ws2.addCell(cl2);
            int i1 = 1;
            int i2 = 1;
            for (String course:
                 this.courseDepartments.keySet()) {
                ArrayList<String> departments = this.courseDepartments.get(course);
                if(departments.size() == 1){
                    dL = new Label(0,i1,departments.get(0));
                    cL = new Label(1,i1,course);
                    ws.addCell(dL);
                    ws.addCell(cL);
                    i1++;
                }else{
                    for (String department:departments
                         ) {
                        dL = new Label(0,i2,department);
                        cL = new Label(1,i2,course);
                        ws2.addCell(dL);
                        ws2.addCell(cL);
                        i2++;
                    }
                }
            }
//            int totalCourseNum = 0;
//            for(Department d:this.deparmentsList){
//                ArrayList<String> courses = d.getCourses();
//                totalCourseNum+=courses.size();
//                for(String course:courses){
//                    if(allCourses.contains(course)){
//                        continue;
//                    }
//                    dL = new Label(0,i,d.getName());
//                    cL = new Label(1,i,course);
//                    ws.addCell(dL);
//                    ws.addCell(cL);
//                    i++;
//                    allCourses.add(course);
//                }
//            }
//            System.out.println("total course num: "+totalCourseNum);
//            System.out.println("total distinct course num:"+allCourses.size());
            wwb.write();
            wwb2.write();
            wwb.close();
            wwb2.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeDataToExcel2(){
        try{
            String filePath = "C:\\Users\\dengrong\\"+name+".xls";
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(f);
            WritableSheet ws = wwb.createSheet("Sheet 1",0);
            Label dL = new Label(0,0,"院系");
            Label cL = new Label(1,0,"课程");
            Label gL = new Label(2,0,"年级");
            ws.addCell(dL);
            ws.addCell(cL);
            ws.addCell(gL);
            int i1 = 1;
            for(Department d:this.deparmentsList){
                List<Course> courses = d.getCourseList();
                for (Course course:
                        courses) {
                    dL = new Label(0,i1,d.getName());
                    cL = new Label(1,i1,course.getName());
                    gL = new Label(2,i1,course.getGrade()+"");
                    ws.addCell(dL);
                    ws.addCell(cL);
                    ws.addCell(gL);
                    i1++;
                }
            }
            wwb.write();
            wwb.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    class RequestDepartmentsThread extends Thread{
        HttpRequest httpRequest;
        String params;
        Map<String,String> headers;

        public RequestDepartmentsThread(HttpRequest httpRequest,String params,Map<String,String> headers){
            this.httpRequest = httpRequest;
            this.params = params;
            this.headers = headers;
        }
        public void run(){
            while(true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseTagsV2.action",
                        params,
                        headers);
                Departments departments = new Departments(aca);
                departments.parse();
                boolean hasMore = false;
                for (Department d:departments.getDepartments()){
                    if(School.this.departments.contains(d.getName())){
                        continue;
                    }else{
                        d.setSchoolName(name);
                        School.this.deparmentsList.add(d);
                        School.this.departments.add(d.getName());
                        System.out.println("add department:"+d.getName()+";"+d.getId());
                        hasMore = true;
                    }
                }
                if(!hasMore){
                    break;
                }
            }
        }
    }

}
