package in.create.arena.blogapp.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AllAboutDirectory {

    private String targetPath;

    public AllAboutDirectory(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void crawlDirectory() {
        try {
            File dirFile = new File(targetPath);
            if (dirFile.isDirectory()) {
                File[] childrenFiles = dirFile.listFiles();
                if (childrenFiles != null) {
                    List<File> onlyFiles = Arrays.stream(childrenFiles)
                            .filter(File::isFile).collect(Collectors.toList());
                    System.out.printf("Total files found in the directory => %d\n\r", onlyFiles.size());
                    List<String> fileNames = onlyFiles
                            .stream()
                            .map(File::getName)
                            .collect(Collectors.toList());
                    System.out.printf("Name of all files => %s \n", fileNames.toString());
                } else {
                    System.out.println("No files found in the directory");
                }
            } else {
                System.out.println("Cannot crawl into a file");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
