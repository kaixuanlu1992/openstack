package com.wentt.openstack.service;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.identity.v3.*;
import org.openstack4j.model.network.Network;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.model.common.Identifier;

import java.util.List;

public class TestOpenstackV3 {
    public static void main(String[] args) {

        Identifier domainIdentifier = Identifier.byId("default");

        OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://10.0.0.11:5000/v3")
                .credentials("admin","888", domainIdentifier)
                .authenticate();


        List<? extends Server> servers = os.compute().servers().list();
        List<? extends Network> networks = os.networking().network().list();
        List<? extends Region> tenants = os.identity().regions().list();
        List<? extends User> users = os.identity().users().list();
        List<? extends Domain> domainList = os.identity().domains().list();
        Project project = os.identity().projects().create(Builders.project()
                .name("projectName")
                .description("This is a new project.")
                .enabled(true)
                .build());
    }
}
