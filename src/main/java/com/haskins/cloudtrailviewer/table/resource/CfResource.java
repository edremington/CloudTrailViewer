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

package com.haskins.cloudtrailviewer.table.resource;


import com.haskins.cloudtrailviewer.model.event.Event;
import java.util.Map;

/**
 *
 * @author mark
 */
public class CfResource implements Resource {

    /**
     * Return the resource for the passed Event
     * @param event Event from which the resource is require
     * @return either the resource name or an empty string if the EventName is not handled.
     */
    @Override
    public String getResource(Event event) {
        
        String resource = "";
        
        if (event.getEventName().equalsIgnoreCase("DescribeStacks")) {
            resource = describeStacks(event);
        } else if (event.getEventName().equalsIgnoreCase("DescribeStackResource")) {
            resource = describeStackResource(event);
        } else if (event.getEventName().equalsIgnoreCase("DescribeStackResources")) {
            resource = describeStackResources(event);
        } else if (event.getEventName().equalsIgnoreCase("UpdateStack")) {
            resource = updateStack(event);
        }
        
        return resource;
    }
    
    private String describeStacks(Event event) {
        
        return getStackName(event);
    }
    
    private String describeStackResource(Event event) {
        
        return getStackName(event);
    }
    
    private String describeStackResources(Event event) {
        
        return getStackName(event);
    }
    
    private String updateStack(Event event) {
        
        return getStackName(event);
    }
    
    private String getStackName(Event event) {
        
        String stackName = "";
        
        Map<String, String> requestParameters = event.getRequestParameters();
        if (requestParameters != null && requestParameters.containsKey("stackName")) {
            stackName = requestParameters.get("stackName");
        }
        
        return stackName;   
    }
}