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
public class Initialization {

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
        System.out.println("Creation of the Administrator  ...");

        //Prerequisites
        groupController.createGroup("Administrator");
        userController.addUser("Administrator", "pwd", "admin@peergren.com");
        groupController.addUser("Administrator", "Administrator");

        System.out.println("Done ...");

    }
}

