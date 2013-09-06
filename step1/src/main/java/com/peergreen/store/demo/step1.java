package com.peergreen.store.demo;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import com.peergreen.store.controller.IGroupController;
import com.peergreen.store.controller.IPetalController;
import com.peergreen.store.controller.IStoreManagment;
import com.peergreen.store.controller.IUserController;
import com.peergreen.store.db.client.exception.EntityAlreadyExistsException;
import com.peergreen.store.db.client.exception.NoEntityFoundException;

@Component
@Instantiate
@Provides
public class step1 {

    @Requires
    private IStoreManagment storeManagement;
    @Requires
    private IPetalController petalController;
    @Requires
    private IGroupController groupController;
    @Requires
    private IUserController userController;


    @Validate
    public void main() throws EntityAlreadyExistsException, NoEntityFoundException {
        System.out.println("Creation of users ...");

        
        userController.addUser("Alceste", "pwdU1","Alceste@peergreen.com");
        
        userController.addUser("Sidonie", "pwdU2","Sidonie@peergreen.com");
        
        System.out.println("Creation of groups ...");
        
        //Create admin's group and others two groups 
        groupController.createGroup("webAppDev");
        groupController.createGroup("restAppDev");

        System.out.println("Put each user in one group ...");

        //Add user to groups 
        groupController.addUser("webAppDev", "Sidonie");
        groupController.addUser("restAppDev", "Alceste");

        System.out.println("Done ...");

    }
}
