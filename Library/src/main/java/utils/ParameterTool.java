package utils;
import beans.Book;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParameterTool {
    public static String[] toStringArry(String str)
    {
        str=str.substring(1,str.length()-1);
        String[] result = str.split(",");
        return result;
    }
}
