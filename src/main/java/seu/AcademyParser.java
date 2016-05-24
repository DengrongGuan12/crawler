package seu;

import org.htmlparser.Parser;

import java.util.ArrayList;

/**
 * Created by I322233 on 5/10/2016.
 */
public class AcademyParser {
    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    private String rawHtml = "";
    private ArrayList<String> academyRequestList = new ArrayList<String>();
    public ArrayList<String> getAcademyRequestList(){
        return this.academyRequestList;
    }
    public void parserHtml(){
//        Parser parser = new Parser ("http://www.dangdang.com");


    }


}
