package superCourse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by I322233 on 5/10/2016.
 */
public class Departments {
    private ArrayList<Department> departments = new ArrayList<Department>();
    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    private String rawData;
    public Departments(String data){
        this.rawData = data;
    }
    public void parse(){
        JSONObject jsonObject = new JSONObject(rawData);
        JSONArray departmentsList = jsonObject.getJSONArray("data");
        for(int i = 0;i<departmentsList.length();i++){
            JSONObject department = departmentsList.getJSONObject(i);
            Department d = new Department();
            d.setId(department.getInt("tagIdInt"));
            d.setName(department.getString("nameStr"));
            System.out.println("add department:"+d.getName());
            departments.add(d);
        }
    }
    public ArrayList<Department> getDepartments(){
        return this.departments;
    }

}
