package in.create.arena.blogapp.controllers;

public abstract class FileHandling {

    protected abstract void crawlFile();

    protected String resolvePath() {
        return "Resolving file path";
    }

    protected String copyFile() {
        return "Copying file";
    }

    protected String moveFile() {
        return "Moving file";
    }

    protected String deleteFile() {
        return "Deleting file";
    }
}
