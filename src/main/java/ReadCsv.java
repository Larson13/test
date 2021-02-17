import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadCsv {

    public static Map readCsv(String filepath) {
        Map m1 = new HashMap();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(filepath));
            String[] line;
            while ((line = reader.readNext()) != null) {
                m1.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return m1;

    }

    public static void CSVWriter(Map<String, String> map) throws Exception{
        try {

        File csv = new File("d:/writerTest.csv");
        if (!csv.exists()) csv.createNewFile();

        List<String[]> list = new ArrayList();

            for (Map.Entry<String, String> entry : map.entrySet()) {
                String[] test = { entry.getKey(),entry.getValue()};
                list.add(test);
            }




        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv),"UTF-8"),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
      //  writer.writeNext(s1, s1, s1, s1);
     //   writer.writeNext("#","#","#","#");
        writer.writeAll(list);
        writer.flush();
        writer.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void main(String[] args) throws Exception {

       String path ="D:\\进1.csv";
        Map m1 = readCsv(path);
       System.out.println(m1);
        CSVWriter(m1);

    }

}