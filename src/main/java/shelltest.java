import java.io.BufferedReader;
import java.io.InputStreamReader;

public class shelltest {


    public static void main(String[] args) {
        try {
            String shpath="sh /home/liu/demo-classes.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {

                sb.append(line).append("\r\n");
            }
            String result = sb.toString();
            System.out.println(result);
            System.out.println(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }






}
