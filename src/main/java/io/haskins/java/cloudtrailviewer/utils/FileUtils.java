/*
CloudTrail Viewer, is a Java desktop application for reading AWS CloudTrail logs
files.

Copyright (C) 2017  Mark P. Haskins

This program is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package io.haskins.java.cloudtrailviewer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Collection of File / Directory related functionality.
 *
 * Created by markhaskins on 25/01/2017.
 */
public class FileUtils {

    private final static Logger LOGGER = Logger.getLogger("CloudTrail");

    /**
     * Returns the full path to the CloudTrail Viewer directory within the System defined user.home.
     *
     * @return Full path to cloudtrailviewer directory ending in a forward slash '/'
     */
    public static String getApplicationDirectory() {

        return System.getProperty("user.home", ".") + "/.cloudtrailviewer/";
    }

    public static String getFullPathToFile(String pathToFile) {
        return getApplicationDirectory() + pathToFile;
    }

    public static Boolean doesFileExist(String pathToFile) {

        File f = new File(pathToFile);
        return f.exists();
    }

    public static String getFileAsString(String pathToFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(pathToFile)), StandardCharsets.UTF_8);
    }

    public static boolean writeStringToFile(String content, String pathToFile) {

        boolean written = false;

        try (BufferedWriter out = new BufferedWriter(new FileWriter(pathToFile))) {
            out.write(content);
            out.close();
            written = true;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to write String to File", e);
        }

        return written;
    }

    public static String removeExtension(String filename) {
        return filename.replaceFirst("[.][^.]+$", "");
    }

}
