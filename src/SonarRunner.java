import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

/**
 * Created by astan on 3/1/2017.
 *
 *
 * Usage: run the SonarRunner to extract data about a project
 *
 * Procedure:
 * 01) provide the home directory where SonarRunner is located
 * 02)
 *
 * V1: l
 */
public class SonarRunner {

    String projectName;
    String repositoryURL; //repository's URL
    RepositoryType repositoryType; //either git or svn
    String sonarRunnerHome; //path to local folder where Sonar Runner is located
    public enum RepositoryType{
        git, svn
    }
    Repository repository;
    Git git;
    ArrayList<String> projectLog; //entries of the "git log", sorted chronologically




    /**
     * Default constructor
     */
    public SonarRunner(){
        init();
        //repository checkout
        //list revisions (per avere l'elenco - fare il sorting mentre leggo) MULTIMAP
        //updateRevision(); per ogni revision nella multimap (ordinata giá), System.out.println("Revision"+revisionDate+revisionHash);
        //finito questo, passo alla revision successiva
    }


    /**
     *
     */
    public void init(){
        this.repositoryURL = "https://github.com/apache/lucene-solr";
        this.sonarRunnerHome = "/usr/SQuaSME/sonar-scanner-2.8/bin";
        this.projectName = "lucene-solr";
        this.repositoryType = RepositoryType.git;
        //
        this.projectLog = initEntries();
    }

    public String convertToShortTime(String rawtime){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String convertedTime = "";
        try {

            Date date = formatter.parse(rawtime);
            System.out.println(date);
            System.out.println(formatter.format(date));
            convertedTime = date.toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedTime;

    }



    public String convertTime(){



        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(1481558211, 0, ZoneOffset.UTC);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
        //System.out.println(formattedDate); // Tuesday,November 1,2011 12:00,AM
    }

    /**
     * List all revisions, and export to file projectname.svn
     */
    public void updateRevision(){

        //SVN or GIT?
        //param Git   | from dd.mm.yyyy
        //param SVN   | from dd.mm.yyyy to dd.mm.yyyy

        //this.nrOfRevisions = countRevisions();
    }

    public ArrayList<String> initEntries(){

        ArrayList<String> entries = new ArrayList();

        ////temporary code
        String fileName = "./tmp/commitsraw.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                entries.add(line);
                //String[] str = line.split(" ");
                //System.out.println(str[0]+" "+str[2]);
                //multimap.put(str[0]+" "+str[1]+" "+str[2],str[3]);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ fileName + "'");
            // Or ex.printStackTrace();
        }

        //sort the ArrayList
        Collections.sort(entries, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        return entries;
    }

    public void updateGit(String hash){
        /*
        git stash save
        git checkout -f HASHCODE
        git stash pop
         */

    }

    public void executeAnalysis(){
        for (String listitem : projectLog) {
            String[] str = listitem.split(" ");
            updateGit(str[1]);
            executeSonarQube(str[0],str[1]);
        }
    }

    private void executeSonarQube(String date, String hash) {
        /*
        launch SonarQube

        #$SONAR_RUNNER_HOME/sonar-runner -Dproject.settings=$project.properties -Dsonar.projectDate=$revisionDate
         */

        String myCommand = this.sonarRunnerHome+" -Dproject.settings="+this.projectName+".properties -Dsonar.projectDate="+date;
        System.out.println(myCommand);
        /*try {
            Runtime.getRuntime().exec(myCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     *
     * @return nr of revisions
     */
    public int countRevisions(){
        int revisions = 0;
        /*
        - read .svn file
        - count revisions
         */
        return revisions;
    }

    /**
     * Runs Sonar Scanner on a selected project
     * for each (or each 50) revisions from X to Y
     * -->(export in file)
     */

    /*public static void runSonar(){
        SonarRunner sonarRunner = new SonarRunner();
        sonarRunner.updateRevision();

        //readRevision
        //important: handle the case when it aborts the
        int nrOfRevisions = sonarRunner.nrOfRevisions;

        //if we want to skip 50 files at a time, can be i+50
        for(int i=0; i<nrOfRevisions;i++){

            *//*
            try
            DO THE ANALYSIS of the REVISION
            catch(errors) and save to file every successful revision check
             *//*
        }


    }*/

}
