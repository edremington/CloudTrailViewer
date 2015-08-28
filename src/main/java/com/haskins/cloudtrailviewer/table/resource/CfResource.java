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
     * @param resources 
     */
    @Override
    public void getResource(Event event, ResourceInfo resources) {
                
        if (event.getEventName().equalsIgnoreCase("DescribeStacks")) {
            describeStacks(event, resources);
            
        } else if (event.getEventName().equalsIgnoreCase("DescribeStackResource")) {
            describeStackResource(event, resources);
            
        } else if (event.getEventName().equalsIgnoreCase("DescribeStackResources")) {
            describeStackResources(event, resources);
            
        } else if (event.getEventName().equalsIgnoreCase("UpdateStack")) {
            updateStack(event, resources);
            
        }
    }
    
    private void describeStacks(Event event, ResourceInfo resources) {
        getStackName(event, resources);
    }
    
    private void describeStackResource(Event event, ResourceInfo resources) {
        getStackName(event, resources);
    }
    
    private void describeStackResources(Event event, ResourceInfo resources) {
        getStackName(event, resources);
    }
    
    private void updateStack(Event event, ResourceInfo resources) {
        getStackName(event, resources);
    }
        
    private void getStackName(Event event, ResourceInfo resources) {
        
        Map<String, String> requestParameters = event.getRequestParameters();
        if (requestParameters != null && requestParameters.containsKey("stackName")) {
            resources.addResource("Stack Name", (String)requestParameters.get("stackName"));
        }  
    }
}