package in.create.arena.blogapp.controllers;

public class LinuxFileHandling extends FileHandling {

    @Override
    public void crawlFile() {
        System.out.println("Crawling files");
    }

    public void handlePathProcesses(CustomHandler customHandler) {
        customHandler.handle();
    }

    public void start() {
        System.out.println(resolvePath());
        crawlFile();
        handlePathProcesses(new CustomHandler());
    }

    private final class CustomHandler extends DupFileHandling {
        public void handle() {
            System.out.println("Found files");
            System.out.println(copyFile());
            System.out.println(moveFile());
            System.out.println(deleteFile());
        }
    }
}
