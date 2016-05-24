package superCourse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by I322233 on 5/10/2016.
 */
public class Schools {
    private Map<String,String> schoolsMap = new HashMap<String,String>();
    public void initSchools(){
        schoolsMap.put("8006","南航");
//        schoolsMap.put("8005","东南");
//        schoolsMap.put("8007","南财");
//        schoolsMap.put("80014","南中医");
//        schoolsMap.put("8003","南师");
        //8004 南理工
        //80016 金陵科技学院
        //80057 江苏经贸职业技术学院
        //8009 南京工业大学
        //80018 南京审计大学
        //8005 东南大学
//        schoolsMap.put("8004","南理工");
//        schoolsMap.put("80016","金陵科技学院");
//        schoolsMap.put("80057","江苏经贸职业技术学院");
//        schoolsMap.put("8009","南京工业大学");
//        schoolsMap.put("80018","南京审计大学");

    }
    public void requestSchools(){
        for(String id:schoolsMap.keySet()){
            School school = new School(id,schoolsMap.get(id));
            school.requestDepartments();
            school.writeDataToExcel();

        }
    }
}
