package com.wentt.openstack.service;

import com.wentt.openstack.util.CommonUitl;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.identity.v2.Tenant;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkType;
import org.openstack4j.model.network.Subnet;

public class VxlanNetwork {
    public static void main( String[] args ){
        OSClient.OSClientV2 os = CommonUitl.getAuthOs();
        //创建网络
        Tenant tenant = os.identity().tenants().getByName("admin");
        Network localNet1 = os.networking().network()
                .create(Builders.network()
                        .name("vxlan_net1")
                        .tenantId(tenant.getId())
                        .networkType(NetworkType.VXLAN)
                        .segmentId("1001")
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


    }

}
