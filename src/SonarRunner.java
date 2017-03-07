import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.StashCreateCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

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
        //list revisions (per avere l'elenco - fare il sorting mentre leggo)
        //updateRevision(); per ogni revision (giá ordinata), System.out.println("Revision"+revisionDate+revisionHash);
        //finito questo, passo alla revision successiva
    }

/*
    public void setGitRepo() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File("./tmp/lucene-solr"))///my/git/directory
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
    }
*/

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


    public String convertTime(String time){
        //TODO change ZoneOffset to the proper timezone ?

        Long longTime = Long.parseLong(time);
        //TimeZone timeZone = TimeZone.getTimeZone("Europe/Rome");

        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(longTime, 0, ZoneOffset.UTC);
        //LocalDateTime dateTime = LocalDateTime.ofEpochSecond(1481558211, 0, ZoneOffset.UTC);
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
        //String fileName = "./tmp/commitsraw.txt";
        String fileName = "./tmp/commits2.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                entries.add(line);
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

        //NO NEED TO SORT ANYMORE
/*        //sort the ArrayList
        Collections.sort(entries, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });*/

        return entries;
    }

    public void convertTimeOfEntries(){
        ArrayList<String> convertedEntries = new ArrayList();
        int lngth = this.projectLog.size();
        String convertedTime = "";

        for(int i=0;i<lngth;i++){
            String[] splt = this.projectLog.get(i).split(" ");
            convertedTime = convertTime(splt[0]);
            convertedEntries.add(convertedTime+" "+splt[2]);
        }
        this.projectLog.clear();
        this.projectLog = convertedEntries;

    }

    public void updateGit(String hash) throws IOException, GitAPIException {


        // Open the existing repository
        try (Repository repository = new FileRepositoryBuilder().setGitDir(new File("./tmp/lucene-solr")).build()){

            try (Git git = new Git(repository)){

                RevCommit stash = git.stashCreate().call();
                git.checkout().setForce(true);
                git.stashDrop();

            }
        }

        /*
        git stash save
        git checkout -f HASHCODE
        git stash pop
         */


        /*
        /// MOVE OUTSIDE
        git.stashCreate();
        git.checkout();
        git.apply("git stash save");
        git.stashDrop();*/


    }

    /**
     * Runs the analysis with SonarQube
     */
    public void executeAnalysis(){
        for (String listitem : projectLog) {
            String[] str = listitem.split(" ");
            //updateGit(str[1]);
            executeSonarQube(str[0],str[1]);
        }
    }

    /**
     * Runs SonarQube
     * @param date is the commit date
     * @param hash is the SHA of the commit
     */
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
