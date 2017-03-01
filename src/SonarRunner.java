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
 *
 */

import org.eclipse.jgit.*;
import org.tmatesoft.svn.*; //https://svnkit.com/javadoc/index.html

public class SonarRunner {

    String repositoryURL; //repository's URL
    String sonarRunnerHome; //path to local folder where Sonar Runner is located
    int nrOfRevisions;

    /*
    FILE.properties: sonarqube configuration
    FILE.svn:
     */

    /**
     * Default constructor
     */
    public SonarRunner(){
        customInitializazion();
    }


    /**
     *
     */
    public void customInitializazion(){
        this.repositoryURL = "https://github.com/apache/lucene-solr";
        this.sonarRunnerHome = "/usr/SQuaSME/sonar-scanner-2.8/bin";
    }

     /**
     * List all revisions, and export to file projectname.svn
     */
    public void updateRevision(){

        //SVN or GIT?
        //param Git   | from dd.mm.yyyy
        //param SVN   | from dd.mm.yyyy to dd.mm.yyyy

        this.nrOfRevisions = countRevisions();
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
    public static void runSonar(){
        SonarRunner sonarRunner = new SonarRunner();
        sonarRunner.updateRevision();

        //readRevision
        //important: handle the case when it aborts the
        int nrOfRevisions = sonarRunner.nrOfRevisions;

        //if we want to skip 50 files at a time, can be i+50
        for(int i=0; i<nrOfRevisions;i++){

            /*
            try
            DO THE ANALYSIS of the REVISION
            catch(errors) and save to file every successful revision check
             */
        }


    }

}
