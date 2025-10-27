package com.szh.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 *
 * @phase process-sources
 */
public class MyMojo extends AbstractMojo {

    private File outputDirectory;

    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    @Override
    public void execute() throws MojoExecutionException {

        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdir();
        }

        File touch = new File(f, "touch.txt");

        FileWriter w = null;
        try {
            w = new FileWriter(touch);

            w.write("touch.txt");
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }

    }
}
