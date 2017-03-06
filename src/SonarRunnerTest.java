import org.junit.*;

import java.util.ArrayList;

/**
 * Created by astan on 3/2/2017.
 */
public class SonarRunnerTest {

    @Test
    public void testInit(){
        SonarRunner sr = new SonarRunner();

        System.out.println(sr.projectName);
        System.out.println(sr.repositoryURL);
        System.out.println(sr.repositoryType);
        System.out.println(sr.sonarRunnerHome);

        sr.initEntries();
        System.out.println(sr.projectLog.get(26816));
        System.out.println(sr.projectLog.get(26817));
        System.out.println(sr.projectLog.get(26818));

        //String time = sr.convertTime();
        //System.out.println(time);
        sr.convertTimeOfEntries();


        sr.executeAnalysis();



    }
}
