package com.spring.jbpm;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.task.UserGroupCallback;

/**
 * 
 * @author suresh Mahalingam (msuresh_md@yahoo.com)
 * 
 */
public class JBPMUserGroupCallback implements UserGroupCallback {

	@Override  
    public boolean existsUser(String userId) {  
        return true;  
    }

	@Override  
    public boolean existsGroup(String groupId) {  
        return true;  
    }

	@Override  
    public List<String> getGroupsForUser(String userId, List<String> groupIds,  
            List<String> allExistingGroupIds) {  
        if (groupIds != null) {  
  
            List<String> retList = new ArrayList<String>(groupIds);  
  
            // merge all groups  
  
            if (allExistingGroupIds != null) {  
  
                for (String grp : allExistingGroupIds) {  
  
                    if (!retList.contains(grp)) {  
  
                        retList.add(grp);  
  
                    }  
  
                }  
  
            }  
  
            return retList;  
  
        } else {  
            //  
            // return empty list by default  
            // please note: there are different return value for different  
            // version of jPBM  
            // List<String> retList = new ArrayList<String>();  
  
            // retList.add("user");  
            // return retList;  
            // return new ArrayList<String>(); //for jBPM5.3.0.Final  
            return null; // for jBPM5.4.0.CR1  
  
        }  
  
    }  
} 