package com.wentt.openstack.util;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.api.types.Facing;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.network.Network;
import org.openstack4j.openstack.OSFactory;

import java.util.ArrayList;
import java.util.List;

public class CommonUitl {
    public static OSClientV2 getAuthOs() {
        return OSFactory.builderV2()
                .endpoint("http://10.0.0.11:5000/v2.0")
                .credentials("admin", "888")
                .perspective(Facing.ADMIN)
                .tenantName("admin")
                .authenticate();
    }

    public static Server createServer(OSClientV2 os, Network network, String serverName) {
        Flavor flavor = os.compute().flavors().get("3e9586d2-b6e1-4dbf-8e0e-a49749ba563b");
        Image image = os.compute().images().list().get(0);
        List<String> net = new ArrayList<>();
        net.add(network.getId());
        ServerCreate sc = Builders.server()
                .name(serverName)
                .flavor(flavor.getId())
                .image(image.getId())
                .addSecurityGroup("default")
                .networks(net)
                .build();
        return os.compute().servers().boot(sc);
    }
}
