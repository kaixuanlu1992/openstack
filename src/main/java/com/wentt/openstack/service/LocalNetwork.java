package com.wentt.openstack.service;

import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v2.Tenant;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Subnet;

public class LocalNetwork {
    public static void main(String[] args) {
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();

        //创建网络
        Tenant tenant = os.identity().tenants().getByName("admin");
        Network localNet1 = os.networking().network()
                .create(Builders.network()
                        .name("local_net1")
                        .tenantId(tenant.getId())
                        .networkType(NetworkType.LOCAL)
                        .adminStateUp(true)
                        .isShared(true)
                        .build());

        Network localNet2 = os.networking().network()
                .create(Builders.network()
                        .name("local_net2")
                        .tenantId(tenant.getId())
                        .networkType(NetworkType.LOCAL)
                        .adminStateUp(true)
                        .isShared(true)
                        .build());


        Subnet subnet = os.networking().subnet().create(Builders.subnet()
                .name("localSubnet")
                .networkId(localNet1.getId())
                .tenantId(tenant.getId())
                .addPool("10.0.2.2", "10.0.2.20")
                .ipVersion(IPVersionType.V4)
                .cidr("10.0.2.0/24")
                .enableDHCP(true)
                .build());

        Subnet subnet1 = os.networking().subnet().create(Builders.subnet()
                .name("localSubnet")
                .networkId(localNet2.getId())
                .tenantId(tenant.getId())
                .addPool("10.0.3.2", "10.0.3.20")
                .ipVersion(IPVersionType.V4)
                .cidr("10.0.3.0/24")
                .enableDHCP(true)
                .build());

    }
}
