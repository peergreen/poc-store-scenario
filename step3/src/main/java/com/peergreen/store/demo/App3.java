package com.peergreen.store.demo;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import com.peergreen.store.controller.IGroupController;
import com.peergreen.store.db.client.exception.EntityAlreadyExistsException;
import com.peergreen.store.db.client.exception.NoEntityFoundException;

@Component
@Instantiate
@Provides
public class App3 {

    @Requires
    private IGroupController groupController;
    
    @Validate
    public void main() throws EntityAlreadyExistsException, NoEntityFoundException {
        System.out.println("Assignation of petals to group ...");

        groupController.giveAccessToPetal("RestAppDev", "Peergreen", "RestfulApp", "1.0.0");
        groupController.giveAccessToPetal("RestAppDev", "Peergreen", "RestfulApp", "1.0.1");
        groupController.giveAccessToPetal("RestAppDev", "Peergreen", "Api", "1.0.1");
        groupController.giveAccessToPetal("WebAppDev", "Peergreen", "ProviderHB", "1.0.0");
        groupController.giveAccessToPetal("WebAppDev", "Peergreen", "ProviderEL", "1.0.0");
        groupController.giveAccessToPetal("WebAppDev", "Peergreen", "WebApp", "1.4");
        groupController.giveAccessToPetal("WebAppDev", "Peergreen", "WebApp", "1.4");
    
        System.out.println("Done ...");

    }
}
