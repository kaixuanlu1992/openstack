package com.wentt.openstack.service;

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

public class TestOpenstack {
    public static void main(String[] args) {
        // 开始认证
        System.out.println("开始认证：");

        OSClientV2 os = OSFactory.builderV2()
                .endpoint("http://10.0.0.11:5000/v2.0")
                .credentials("admin", "888")
                .perspective(Facing.ADMIN)
                .tenantName("admin")
                .authenticate();
        // 创建实例
        Flavor flavor=os.compute().flavors().get("3e9586d2-b6e1-4dbf-8e0e-a49749ba563b");
        Image image=os.compute().images().list().get(0);
        List<? extends Network> networks = os.networking().network().list();
        List<String> net=new ArrayList<>();
        net.add("05a13b20-7bfb-427d-a7ad-56be05c3f86c");
        ServerCreate sc = Builders.server()
                .name("FLAT2_1")
                .flavor(flavor.getId())
                .image(image.getId())
                .addSecurityGroup("default")
                .networks(net)
                .build();
        Server server = os.compute().servers().boot(sc);

//        List<? extends Flavor> flavors = os.compute().flavors().list();
//        List<? extends Role> roleu=os.getAccess().getUser().getRoles();
//        List<? extends Role> role = os.identity().roles().list();
//        List<? extends User> users = os.identity().users().list();
//        List<? extends Tenant> tenants = os.identity().tenants().list();
//        List<? extends Network> networks = os.networking().network().list();
//        List<? extends Subnet> subnets = os.networking().subnet().list();
//        List<? extends Router> routers = os.networking().router().list();
    }
}
