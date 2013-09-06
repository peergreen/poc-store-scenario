package com.peergreen.store.demo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import com.peergreen.store.controller.IGroupController;
import com.peergreen.store.controller.IPetalController;
import com.peergreen.store.controller.IStoreManagment;
import com.peergreen.store.controller.IUserController;
import com.peergreen.store.db.client.ejb.entity.Capability;
import com.peergreen.store.db.client.ejb.entity.Requirement;
import com.peergreen.store.db.client.exception.EntityAlreadyExistsException;
import com.peergreen.store.db.client.exception.NoEntityFoundException;

@Component
@Instantiate
@Provides
public class App4 {

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
        System.out.println("Submission of petal ...");     
        
        File petalBinary = new File("C:\\Users\\user1\\.m2\\repository\\com\\peergreen\\" +
                "store\\controller\\1.0-SNAPSHOT\\controller-1.0-SNAPSHOT.jar");

        petalController.createVendor("Sonatype", "");  

        Capability capJPA1 = petalController.getCapability("JPABundle", "2.4", "db");
        Capability capHB1 = petalController.getCapability("Hibernate", "1.6", "provider") ;
            
        
        Set<Capability> setCap6 = new HashSet<>(); 
        setCap6.add(capJPA1);
        setCap6.add(capHB1);
        Set<Requirement> setReq6 = new HashSet<>();
        //Adding petal given 2 capability : Hibernate and JPA bundle
        storeManagement.submitPetal("Sonatype", "Jersey", "1.4", "Test", "Bundle", setReq6, setCap6,petalBinary);
        
        System.out.println("Done ...");     
    }
}

