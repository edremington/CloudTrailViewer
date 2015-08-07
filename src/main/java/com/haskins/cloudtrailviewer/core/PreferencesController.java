/*    
CloudTrail Viewer, is a Java desktop application for reading AWS CloudTrail logs
files.

Copyright (C) 2015  Mark P. Haskins

This program is free software: you can redistribute it and/or modify it under the
terms of the GNU General Public License as published by the Free Software Foundation,
either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,but WITHOUT ANY 
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.haskins.cloudtrailviewer.core;

import com.haskins.cloudtrailviewer.dialog.preferences.dialogs.AwsAccountDialog;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that stores and retrieves properties from OS provided Preferences implementation.
 * 
 * The class is a Singleton
 * 
 * @author mark
 */
public class PreferencesController {
            
    private static PreferencesController instance = null;
        
    private final Preferences prefs = Preferences.userRoot().node("CloudTrailViewer");
    
    private final Map<String, String> propertiesCache = new HashMap<>();
        
    private PreferencesController() {
        
    }
    
    /**
     * Returns an instance of the class
     * @return 
     */
    public static PreferencesController getInstance() {

        if (instance == null) {
            instance = new PreferencesController();  
        }

        return instance;
    }
    
    /**
     * Saves a Preference
     * @param key Unique Id of the property
     * @param value property to save
     */
    public void setProperty(String key, String value) {
        
        propertiesCache.put(key, value);
        prefs.put(key, value);
    }
    
    /**
     * returns a property
     * @param key Unique Id of the property
     * @return 
     */
    public String getProperty(String key) {
        
        if (propertiesCache.containsKey(key)) {
            return propertiesCache.get(key);
        } else {
            
            String value = prefs.get(key, "");
            propertiesCache.put(key, value);
            return value;
        }
    }
    
    /**
     * Utility method that checks if provided AWS API credentials and bucket name
     * are valid
     * @return 
     */
    public boolean checkS3Credentials() {
        
        boolean validS3Credentials = false;
        
        String keyRegex = "(?<![A-Z0-9])[A-Z0-9]{20}(?![A-Z0-9])";
        String secretRegex = "(?<![A-Za-z0-9/+=])[A-Za-z0-9/+=]{40}(?![A-Za-z0-9/+=])";
     
        Pattern keyPattern = Pattern.compile(keyRegex);
        Pattern secretPattern = Pattern.compile(secretRegex);
        
        boolean keyOK = false;
        if (getProperty(AwsAccountDialog.S3_KEY_PROPERTY) != null) {
            Matcher keyMatcher = keyPattern.matcher(getProperty(AwsAccountDialog.S3_KEY_PROPERTY));
            if (keyMatcher.matches()) {
                keyOK = true;
            }  
        }
        
        boolean secretOK = false;
        if (getProperty(AwsAccountDialog.S3_SECRET_PROPERTY) != null) {
            Matcher secretMatcher = secretPattern.matcher(getProperty(AwsAccountDialog.S3_SECRET_PROPERTY));
            if (secretMatcher.matches()) {
                secretOK = true;
            }
        }

        boolean bucketProvided = false;
        if (getProperty(AwsAccountDialog.S3_BUCKET_PROPERTY) != null && getProperty(AwsAccountDialog.S3_BUCKET_PROPERTY).length() >= 3) {
            bucketProvided = true;
        }
        
        if (keyOK && secretOK && bucketProvided) {
            validS3Credentials = true;
        }
        
        return validS3Credentials;
    }
}
