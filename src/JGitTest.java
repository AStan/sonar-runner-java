import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JGitTest {

    private String localPath, remotePath;
    private Repository localRepo;
    private Git git;

    @Before
    public void init() throws IOException {
        //localPath = "/home/me/repos/mytest";
        localPath = "./tmp/lucene-solr";
        remotePath = "git@github.com:apache/lucene-solr.git";
        localRepo = new FileRepository(localPath + "/.git");
        git = new Git(localRepo);

    }

    @Ignore
    //works
    public void gitOpen() throws IOException {
        Repository repository = FileRepositoryBuilder.create(new File("./tmp/lucene-solr/.git"));
        Map tags = repository.getTags();
        Map refs = repository.getAllRefs();
        String currentBranch = repository.getBranch();
        Ref HEAD = repository.getRef("HEAD");
        repository.open(HEAD.getObjectId()).copyTo(System.out);

    }

    @Test
    public void gitTest() throws IOException, GitAPIException {
        Repository repository = FileRepositoryBuilder.create(new File("./tmp/lucene-solr/.git"));

        Git git = new Git(repository);


        try( RevWalk revWalk = new RevWalk( repository ) ) {
            ObjectId commitId = repository.resolve( "refs/heads/master" );
            revWalk.markStart( revWalk.parseCommit( commitId ) );
            for( RevCommit commit : revWalk ) {
                System.out.println(commit.getFullMessage());
            }
        }


/*        Map tags = repository.getTags();
        Map refs = repository.getAllRefs();
        String currentBranch = repository.getBranch();
        Ref HEAD = repository.getRef("HEAD");
        repository.open(HEAD.getObjectId()).copyTo(System.out);*/

    }

    @Ignore
    public void testCreate() throws IOException {
        Repository newRepo = new FileRepository(localPath + ".git");
        newRepo.create();
    }

    @Ignore
    public void testClone() throws IOException, GitAPIException {
        Git.cloneRepository().setURI(remotePath)
                .setDirectory(new File("./tmp/lucene-solr3")).call();
    }

    @Ignore
    public void testAdd() throws IOException, GitAPIException {
        File myfile = new File(localPath + "/myfile");
        myfile.createNewFile();
        git.add().addFilepattern("myfile").call();
    }

    @Ignore
    public void testCommit() throws IOException, GitAPIException,
            JGitInternalException {
        git.commit().setMessage("Added myfile").call();
    }

    @Ignore
    public void testPush() throws IOException, JGitInternalException,
            GitAPIException {
        git.push().call();
    }

    @Ignore
    public void testTrackMaster() throws IOException, JGitInternalException,
            GitAPIException {
        git.branchCreate().setName("master")
                .setUpstreamMode(SetupUpstreamMode.SET_UPSTREAM)
                .setStartPoint("origin/master").setForce(true).call();
    }

    @Ignore
    public void testPull() throws IOException, GitAPIException {
        git.pull().call();
    }
}
