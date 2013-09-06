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
import com.peergreen.store.db.client.ejb.entity.Category;
import com.peergreen.store.db.client.ejb.entity.Property;
import com.peergreen.store.db.client.ejb.entity.Requirement;
import com.peergreen.store.db.client.ejb.entity.Vendor;
import com.peergreen.store.db.client.enumeration.Origin;
import com.peergreen.store.db.client.exception.EntityAlreadyExistsException;
import com.peergreen.store.db.client.exception.NoEntityFoundException;

@Component
@Instantiate
@Provides
public class App2 {

    @Requires
    private IStoreManagment storeManagement;
    @Requires
    private IPetalController petalController;
    @Requires
    private IGroupController groupController;
    @Requires
    private IUserController userController;

    private Property property ; 

    @Validate
    public void main() throws EntityAlreadyExistsException, NoEntityFoundException {
        System.out.println("Adding petals to the store");

        Category category = storeManagement.createCategory("Bundle");
        Category category1 = storeManagement.createCategory("Not Bundle");
        Vendor vendor =  petalController.createVendor("Peergreen",
                "Peergreen is a software company started by the core team" +
                        "who created JOnAS, the Open Application Server used for" +
                "critical production processes");    

        File petalBinary = new File("C:\\Users\\user1\\.m2\\repository\\com\\peergreen\\" +
                "store\\controller\\1.0-SNAPSHOT\\controller-1.0-SNAPSHOT.jar");


        Set<Property> properties = new HashSet<>();
        property = new Property("bundle", "true");
        properties.add(property);
        Capability capJX = petalController.createCapability("Jax-RS", "1.0", "Rest", properties);

        Set<Property> properties1 = new HashSet<>();
        property = new Property("bundle", "false");
        properties1.add(property);
        Capability capJPA = petalController.createCapability("JPA", "2.4", "db", properties1);

        Set<Property> properties1Bis = new HashSet<>();
        property = new Property("bundle", "true");
        properties1Bis.add(property);
        petalController.createCapability("JPABundle", "2.4", "db", properties1Bis);

        Set<Property> properties2 = new HashSet<>();
        property = new Property("DB", "H2");
        properties2.add(property);
        property = new Property("bundle", "true");
        properties2.add(property);
        Capability capHB = petalController.createCapability("Hibernate", "4.6", "provider", properties2);

        Set<Property> properties2Bis = new HashSet<>();
        property = new Property("DB", "H2");
        properties2Bis.add(property);
        property = new Property("bundle", "true");
        properties2Bis.add(property);
        petalController.createCapability("Hibernate", "1.6", "provider", properties2Bis);

        Set<Property> properties3 = new HashSet<>();
        property = new Property("DB", "H2");
        properties3.add(property);
        property = new Property("bundle", "true");
        properties3.add(property);
        Capability capELink = petalController.createCapability("EclipseLink", "2.6", "provider", properties3);


        String filter1 = "(&(capabilityName=Jax-RS)(version=1.0)(bundle=true))";
        Requirement requirement1 = petalController.createRequirement("test1", "test1", filter1);

        String filter2 = "(&(capabilityName=JPA)(!(version<=2.0))(bundle=false))";
        Requirement requirement2 = petalController.createRequirement("test2", "test2", filter2);

        String filter3 = "(&(|(capabilityName=Hibernate)(capabilityName=EclipseLink))(!(version<=2.0)))";
        Requirement requirement3 = petalController.createRequirement("test3", "test3", filter3);

        String filter4 = "(&(capabilityName=Hibernate)(!(version<=1.0))(bundle=true))";
        Requirement requirement4 = petalController.createRequirement("test4", "test4", filter4);

        String filter5 = "(&(|(capabilityName=Hibernate)(capabilityName=EclipseLink))(!(version<=2.0)))";
        Requirement requirement5 = petalController.createRequirement("test5", "test5", filter5);

        String filter6 = "(capabilityName=Tomcat)";
        Requirement requirement6 = petalController.createRequirement("WebApp", "WebApp", filter6);

        Set<Capability> setCap0 = new HashSet<>();
        setCap0.add(capJX);  
        Set<Requirement> setReq0 = new HashSet<>();
        setReq0.add(requirement2);
        //setReq0.add(requirement5);

        //Adding petal with capability Jax-RS and 2 requirement: JPA and (Hibernate or EclipseLink)
        petalController.addPetal(vendor.getVendorName(),"RestfulApp", "1.0.0", "Test", category, setReq0, setCap0, Origin.LOCAL, petalBinary);

        Set<Capability> setCap = new HashSet<>();
        setCap.add(capJX);  
        Set<Requirement> setReq = new HashSet<>();
        setReq.add(requirement2);
       // setReq.add(requirement3);

        //Adding petal with capability Jax-RS and 2 requirement: JPA and (Hibernate or EclipseLink with bundle=true)
        petalController.addPetal(vendor.getVendorName(),"RestfulApp", "1.0.1", "Test", category, setReq, setCap, Origin.LOCAL, petalBinary);


        Set<Capability> setCap2 = new HashSet<>();
        setCap2.add(capJPA);
        Set<Requirement> setReq2 = new HashSet<>();
        setReq2.add(requirement4);

        //Adding petal given capability JPA  and had requirement for provider Hibernate 
        petalController.addPetal(vendor.getVendorName(), "Api", "1.0.1", "Test", category1, setReq2, setCap2, Origin.LOCAL, petalBinary);

        Set<Capability> setCap3 = new HashSet<>();
        setCap3.add(capHB);
        Set<Requirement> setReq3 = new HashSet<>();
        //setReq3.add(requirement1);
        setReq3.add(requirement2);

        //Adding petal given capability Hibernate and had 2 requirement : Jax-RS and JPA
        petalController.addPetal(vendor.getVendorName(), "ProviderHB", "1.0.0", "Hibernate", category, setReq3, setCap3, Origin.LOCAL, petalBinary);

        Set<Capability> setCap4 = new HashSet<>();
        setCap4.add(capELink);
        Set<Requirement> setReq4 = new HashSet<>();
        setReq4.add(requirement2);

        //Adding petal given capability EclipseLink and requirement for JPA 
        petalController.addPetal(vendor.getVendorName(), "ProviderEL", "1.0.0", "EclipseLink", category, setReq4, setCap4, Origin.LOCAL, petalBinary);

        Set<Capability> setCap5 = new HashSet<>();
        setCap5.add(capHB);
        setCap5.add(capJX);
        Set<Requirement> setReq5 = new HashSet<>();
     //   setReq5.add(requirement2);
        setReq5.add(requirement6);
        //Adding petal given two capability (Hibernate and Jax-RS) and had a requirement for Tomcat and JPA
        petalController.addPetal(vendor.getVendorName(), "WebApp", "1.4", "Test", category, setReq5, setCap5, Origin.LOCAL, petalBinary);

        System.out.println("Done ...");

    }

}

