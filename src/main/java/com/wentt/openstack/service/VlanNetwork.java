package com.wentt.openstack.service;

import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v2.Tenant;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Subnet;

public class VlanNetwork {
    public static void main( String[] args ){
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        //创建网络
        Tenant tenant = os.identity().tenants().getByName("admin");
        Network localNet1 = os.networking().network()
                .create(Builders.network()
                        .name("vlan_net1")
                        .tenantId(tenant.getId())
                        .networkType(NetworkType.VLAN)
                        .physicalNetwork("physnet1")
                        .segmentId("1001")
                        .adminStateUp(true)
                        .isShared(true)
                        .build());

        Network localNet2 = os.networking().network()
                .create(Builders.network()
                        .name("vlan_net2")
                        .tenantId(tenant.getId())
                        .networkType(NetworkType.VLAN)
                        .physicalNetwork("physnet1")
                        .segmentId("1002")
                        .adminStateUp(true)
                        .isShared(true)
                        .build());


        Subnet subnet = os.networking().subnet().create(Builders.subnet()
                .name("localSubnet")
                .networkId(localNet1.getId())
                .tenantId(tenant.getId())
                .addPool("10.0.7.2", "10.0.7.20")
                .ipVersion(IPVersionType.V4)
                .cidr("10.0.7.0/24")
                .enableDHCP(true)
                .build());

        Subnet subnet1 = os.networking().subnet().create(Builders.subnet()
                .name("localSubnet")
                .networkId(localNet2.getId())
                .tenantId(tenant.getId())
                .addPool("10.0.8.2", "10.0.8.20")
                .ipVersion(IPVersionType.V4)
                .cidr("10.0.8.0/24")
                .enableDHCP(true)
                .build());
    }

}
