package com.szh.task.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileUtils {

    public static List<File> loadFiles(String dir, Predicate<File> filter) {
        File file = new File(dir);
        if (file.isFile()) {
            List<File> fileList = new ArrayList<>();
            fileList.add(file);
            return fileList;
        }

        File[] files = file.listFiles();
        List<File> list = new ArrayList<>(files.length);
        for (File f : files) {
            if (f.isDirectory()) {
                list.addAll(loadFiles(f.getAbsolutePath(), filter));
            } else if (f.isFile() && !filter.test(f)) {
                list.add(f);
            }
        }
        return list;
    }
}
