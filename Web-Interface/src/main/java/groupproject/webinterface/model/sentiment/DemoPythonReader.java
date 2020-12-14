package groupproject.webinterface.model.sentiment;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class DemoPythonReader {
    void runDemoPyCode() {

        ProcessBuilder processBuilder = new ProcessBuilder()
                //replace arg in ".directory(new File(arg))" with full path up to groupproject, eg: arg="C:/Users/username/Covid19-Digital-Footprint/Web-Intrface/src/main/java",
                // or else python exe will not be able to find the py file
                .directory(new File("C:/Users/shaelin/Covid19-Digital-Footprint/Web-Interface/src/main/java"))
                .command("python", "groupproject/webinterface/model/sentiment/demo_py_file.py");
        try {
            Process p = processBuilder.start();


            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuilder buffer = new StringBuilder();

            String line = null;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            int exitCode = p.waitFor();
            System.out.println("Value is: " + buffer.toString());
            System.out.println("Process exit value:" + exitCode);
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
