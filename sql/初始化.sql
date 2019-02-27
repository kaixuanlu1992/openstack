create database openstack;
use openstack;
//network - server映射关系
CREATE TABLE t_network_server_map(
id CHAR(32) NOT NULL,
network_id VARCHAR(64) NOT NULL ,
server_id VARCHAR(64) NOT NULL
)
)