import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

    HashMap projectLog;
    //Multimap<String, String> revisions; //Multimap containing KEY=nrOfRevision/revisionDate VALUES (rev1
    //int nrOfRevisions;




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
        this.projectName = "";
        this.repositoryType = RepositoryType.git;
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

    public HashMap initHashMap(){

        HashMap<String, String> hashMap = new HashMap<>();

        //temporary code
        String fileName = "./tmp/commitsraw.txt";
        String line;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
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


        //git.log();

        return hashMap;
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
