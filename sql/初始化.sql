create database openstack;
use openstack;
//network - server映射关系
create table t_network_server_map(
id CHAR(32) NOT NULL,
network_id VARCHAR(32) NOT NULL,
server_id VARCHAR(32) NOT NULL
)