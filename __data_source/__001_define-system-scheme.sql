/*
 * COPYRIGHT. CMIR  2026. ALL RIGHTS RESERVED.
 */

-- ----------------------------
-- 1. 部门表
-- ----------------------------
DROP TABLE IF EXISTS "sys_dept" cascade;
CREATE TABLE "sys_dept"
(
  "dept_id"     bigserial primary key,
  "parent_id"   int8    default 0,
  "ancestors"   varchar(50),
  "dept_name"   varchar(30),
  "order_num"   int4,
  "leader"      varchar(20),
  "phone"       varchar(11),
  "email"       varchar(50),
  "status"      char(1),
  "del_flag"    char(1) DEFAULT '0',
  "create_by"   varchar(64),
  "create_time" timestamptz default now(),
  "update_by"   varchar(64),
  "update_time" timestamptz default now()
)
;

-- ----------------------------
-- Primary Key structure for table sys_dept
-- ----------------------------
alter sequence sys_dept_dept_id_seq restart 110;

COMMENT ON COLUMN "sys_dept"."dept_id" IS '部门id';
COMMENT ON COLUMN "sys_dept"."parent_id" IS '父部门id';
COMMENT ON COLUMN "sys_dept"."ancestors" IS '祖级列表';
COMMENT ON COLUMN "sys_dept"."dept_name" IS '部门名称';
COMMENT ON COLUMN "sys_dept"."order_num" IS '显示顺序';
COMMENT ON COLUMN "sys_dept"."leader" IS '负责人';
COMMENT ON COLUMN "sys_dept"."phone" IS '联系电话';
COMMENT ON COLUMN "sys_dept"."email" IS '邮箱';
COMMENT ON COLUMN "sys_dept"."status" IS '部门状态（0正常 1停用';
COMMENT ON COLUMN "sys_dept"."del_flag" IS '删除标志（0代表存在 1代表删除)';
COMMENT ON COLUMN "sys_dept"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dept"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_dept"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dept"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_dept" IS '部门表';

-- ----------------------------
-- 初始化-部门数据
-- ----------------------------
INSERT INTO "sys_dept" VALUES (100, 0,   '0',         '爱宝科技',   0, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), 'admin', now());
INSERT INTO "sys_dept" VALUES (101, 100, '0,100',     '深圳总公司', 1, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), 'admin', now());
INSERT INTO "sys_dept" VALUES (102, 100, '0,100',     '长沙分公司', 2, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (103, 101, '0,100,101', '研发部门', 1, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), 'admin', now());
INSERT INTO "sys_dept" VALUES (104, 101, '0,100,101', '市场部门', 2, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (105, 101, '0,100,101', '测试部门', 3, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (106, 101, '0,100,101', '财务部门', 4, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (107, 101, '0,100,101', '运维部门', 5, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (108, 102, '0,100,102', '市场部门', 1, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());
INSERT INTO "sys_dept" VALUES (109, 102, '0,100,102', '财务部门', 2, '猫头虎', '15888888888', 'ry@qq.com', '0', '0', 'admin', now(), '', now());


-- ----------------------------
-- 2. 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS "sys_user" cascade;
CREATE TABLE "sys_user"
(
  "user_id"     bigserial primary key ,
  "dept_id"     int8,
  "user_name"   varchar(30) NOT NULL,
  "nick_name"   varchar(30) NOT NULL,
  "user_type"   varchar(2) default '00',
  "email"       varchar(50) default '',
  "phonenumber" varchar(11) default '',
  "sex"         char(1) default '0',
  "avatar"      varchar(100) default '',
  "password"    varchar(100) default '',
  "status"      char(1) default '0',
  "del_flag"    char(1) default '0',
  "login_ip"    varchar(128) default '',
  "login_date"  timestamptz default now(),
  "pwd_update_date" timestamptz default now(),
  "create_by"   varchar(64) default '',
  "create_time" timestamptz default now(),
  "update_by"   varchar(64) default '',
  "update_time" timestamptz default now(),
  "remark"      varchar(500) default NULL
)
;

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
alter sequence sys_user_user_id_seq restart 3;

COMMENT ON COLUMN "sys_user"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_user"."dept_id" IS '部门ID';
COMMENT ON COLUMN "sys_user"."user_name" IS '用户账号';
COMMENT ON COLUMN "sys_user"."nick_name" IS '用户昵称';
COMMENT ON COLUMN "sys_user"."user_type" IS '用户类型（00系统用户）';
COMMENT ON COLUMN "sys_user"."email" IS '用户邮箱';
COMMENT ON COLUMN "sys_user"."phonenumber" IS '手机号码';
COMMENT ON COLUMN "sys_user"."sex" IS '用户性别（0男 1女 2未知）';
COMMENT ON COLUMN "sys_user"."avatar" IS '头像地址';
COMMENT ON COLUMN "sys_user"."password" IS '密码';
COMMENT ON COLUMN "sys_user"."status" IS '帐号状态（0正常 1停用）';
COMMENT ON COLUMN "sys_user"."del_flag" IS '删除标志（0代表存在 1代表删除）';
COMMENT ON COLUMN "sys_user"."login_ip" IS '最后登录IP';
COMMENT ON COLUMN "sys_user"."login_date" IS '最后登录时间';
COMMENT ON COLUMN "sys_user"."pwd_update_date" IS '密码最后更新时间';
COMMENT ON COLUMN "sys_user"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_user"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_user"."remark" IS '备注';
COMMENT ON TABLE "sys_user" IS '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
INSERT INTO "sys_user" VALUES (1, 103, 'admin', '猫头虎', '00', 'ry@163.com', '15888888888', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', now(), now(),'admin', now(), '', now(), '管理员');
INSERT INTO "sys_user" VALUES (2, 105, 'ry', '猫头虎', '00', 'ry@qq.com', '15666666666', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', now(), now(), 'admin', now(), 'admin', now(), '测试员');


-- ----------------------------
-- 3. 岗位信息表
-- ----------------------------
DROP TABLE IF EXISTS "sys_post" cascade;
CREATE TABLE "sys_post"
(
  "post_id"     bigserial   primary key ,
  "post_code"   varchar(64) NOT NULL,
  "post_name"   varchar(50) NOT NULL,
  "post_sort"   int4        NOT NULL,
  "status"      char(1)  NOT NULL,
  "create_by"   varchar(64) default '',
  "create_time" timestamptz default now(),
  "update_by"   varchar(64) default '',
  "update_time" timestamptz default now(),
  "remark"      varchar(500) default NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_post
-- ----------------------------
alter sequence sys_post_post_id_seq restart 5;

COMMENT ON COLUMN "sys_post"."post_id" IS '岗位ID';
COMMENT ON COLUMN "sys_post"."post_code" IS '岗位编码';
COMMENT ON COLUMN "sys_post"."post_name" IS '岗位名称';
COMMENT ON COLUMN "sys_post"."post_sort" IS '显示顺序';
COMMENT ON COLUMN "sys_post"."status" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "sys_post"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_post"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_post"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_post"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_post"."remark" IS '备注';
COMMENT ON TABLE "sys_post" IS '岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO "sys_post" VALUES (1, 'ceo',  '董事长',   1, '0', 'admin', now(), 'admin', now(), '');
INSERT INTO "sys_post" VALUES (2, 'se',   '项目经理', 2, '0', 'admin', now());
INSERT INTO "sys_post" VALUES (3, 'hr',   '人力资源', 3, '0', 'admin', now());
INSERT INTO "sys_post" VALUES (4, 'user', '普通员工', 4, '0', 'admin', now());


-- ----------------------------
-- 4. 角色信息表
-- ----------------------------
DROP TABLE IF EXISTS "sys_role" cascade;
CREATE TABLE "sys_role"
(
  "role_id"             bigserial    PRIMARY KEY ,
  "role_name"           varchar(30)  NOT NULL,
  "role_key"            varchar(100) NOT NULL,
  "role_sort"           int4         NOT NULL,
  "data_scope"          char(1),
  "menu_check_strictly" bool,
  "dept_check_strictly" bool,
  "status"              char(1)   NOT NULL,
  "del_flag"            char(1)   DEFAULT '0',
  "create_by"           varchar(64),
  "create_time"         timestamptz default now(),
  "update_by"           varchar(64),
  "update_time"         timestamptz default now(),
  "remark"              varchar(500)
)
;
-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
alter sequence sys_role_role_id_seq restart 3;

COMMENT ON COLUMN "sys_role"."role_id" IS '角色ID';
COMMENT ON COLUMN "sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "sys_role"."role_key" IS '角色权限字符串';
COMMENT ON COLUMN "sys_role"."role_sort" IS '显示顺序';
COMMENT ON COLUMN "sys_role"."data_scope" IS '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）';
COMMENT ON COLUMN "sys_role"."menu_check_strictly" IS '菜单树选择项是否关联显示';
COMMENT ON COLUMN "sys_role"."dept_check_strictly" IS '部门树选择项是否关联显示';
COMMENT ON COLUMN "sys_role"."status" IS '角色状态（0正常 1停用）';
COMMENT ON COLUMN "sys_role"."del_flag" IS '删除标志（0代表存在 1代表删除）';
COMMENT ON COLUMN "sys_role"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_role"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_role"."remark" IS '备注';
COMMENT ON TABLE "sys_role" IS '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO "sys_role" VALUES (1, '超级管理员', 'admin', 1, '1', true, 't', '0', '0', 'admin', now(), '',    now(), '超级管理员');
INSERT INTO "sys_role" VALUES (2, '普通角色', 'common', 2, '2', false, 'f', '0', '0', 'admin', now(), 'admin',now(), '普通角色');

-- ----------------------------
-- 5. 菜单权限表
-- ----------------------------
DROP TABLE IF EXISTS "sys_menu" cascade;
CREATE TABLE "sys_menu"
(
  "menu_id"     bigserial primary key ,
  "menu_name"   varchar(50) NOT NULL,
  "parent_id"   int8        default 0,
  "order_num"   int4,
  "path"        varchar(200) default '',
  "component"   varchar(255),
  "query"       varchar(255),
  "route_name"  varchar(50) default '',
  "is_frame"    char(1) default '1',
  "is_cache"    char(1) default '0',
  "menu_type"   char(1),
  "visible"     char(1),
  "status"      char(1),
  "perms"       varchar(100),
  "icon"        varchar(100) default '#',
  "create_by"   varchar(64) default '',
  "create_time" timestamptz default now(),
  "update_by"   varchar(64) default '',
  "update_time" timestamptz default now(),
  "remark"      varchar(500) default NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
alter sequence sys_menu_menu_id_seq restart 2000;

COMMENT ON COLUMN "sys_menu"."menu_id" IS '菜单ID';
COMMENT ON COLUMN "sys_menu"."menu_name" IS '菜单名称';
COMMENT ON COLUMN "sys_menu"."parent_id" IS '父菜单ID';
COMMENT ON COLUMN "sys_menu"."order_num" IS '显示顺序';
COMMENT ON COLUMN "sys_menu"."path" IS '路由地址';
COMMENT ON COLUMN "sys_menu"."component" IS '组件路径';
COMMENT ON COLUMN "sys_menu"."query" IS '路由参数';
COMMENT ON COLUMN "sys_menu"."route_name" IS '路由名称';
COMMENT ON COLUMN "sys_menu"."is_frame" IS '是否为外链（0是 1否）';
COMMENT ON COLUMN "sys_menu"."is_cache" IS '是否缓存（0缓存 1不缓存）';
COMMENT ON COLUMN "sys_menu"."menu_type" IS '菜单类型（M目录 C菜单 F按钮）';
COMMENT ON COLUMN "sys_menu"."visible" IS '菜单状态（0显示 1隐藏）';
COMMENT ON COLUMN "sys_menu"."status" IS '菜单状态（0正常 1停用）';
COMMENT ON COLUMN "sys_menu"."perms" IS '权限标识';
COMMENT ON COLUMN "sys_menu"."icon" IS '菜单图标';
COMMENT ON COLUMN "sys_menu"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_menu"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_menu"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_menu"."remark" IS '备注';
COMMENT ON TABLE "sys_menu" IS '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values ('1', '系统管理', '0', '1', 'system',          null, '', '', '1', '0', 'M', '0', '0', '', 'system', 'admin', now(), '', null, '系统管理目录');
insert into sys_menu values ('2', '系统监控', '0', '2', 'monitor',         null, '', '', '1', '0', 'M', '0', '0', '', 'monitor', 'admin', now(), '', null, '系统监控目录');
-- 二级菜单
insert into sys_menu values ('100', '用户管理', '1', '1', 'user',     'system/user/index',    '', '', '1', '0', 'C', '0', '0', 'system:user:list', 'user', 'admin', now(), '', null, '用户管理菜单');
insert into sys_menu values ('101', '角色管理', '1', '2', 'role',      'system/role/index',   '', '', '1', '0', 'C', '0', '0', 'system:role:list', 'peoples', 'admin', now(), '', null, '角色管理菜单');
insert into sys_menu values ('102', '菜单管理', '1', '3', 'menu',      'system/menu/index',   '', '', '1', '0', 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', now(), '', null, '菜单管理菜单');
insert into sys_menu values ('103', '部门管理', '1', '4', 'dept',      'system/dept/index',   '', '', '1', '0', 'C', '0', '0', 'system:dept:list', 'tree', 'admin', now(), '', null, '部门管理菜单');
insert into sys_menu values ('104', '岗位管理', '1', '5', 'post',      'system/post/index',   '', '', '1', '0', 'C', '0', '0', 'system:post:list', 'post', 'admin', now(), '', null, '岗位管理菜单');
insert into sys_menu values ('105', '字典管理', '1', '6', 'dict',      'system/dict/index',   '', '', '1', '0', 'C', '0', '0', 'system:dict:list', 'dict', 'admin', now(), '', null, '字典管理菜单');
insert into sys_menu values ('106', '参数设置', '1', '7', 'config',    'system/config/index', '', '', '1', '0', 'C', '0', '0', 'system:config:list', 'edit', 'admin', now(), '', null, '参数设置菜单');
insert into sys_menu values ('107', '通知公告', '1', '8', 'notice',    'system/notice/index', '', '', '1', '0', 'C', '0', '0', 'system:notice:list', 'message', 'admin', now(), '', null, '通知公告菜单');
insert into sys_menu values ('108', '日志管理', '1', '9', 'log',       '',                    '', '', '1',    '0', 0, '0', '0', '', 'log', 'admin', now(), '', null, '日志管理菜单');
insert into sys_menu values ('109', '在线用户', '2', '1', 'online',    'monitor/online/index','', '', '1', '0', 'C', '0', '0', 'monitor:online:list', 'online', 'admin', now(), '', null, '在线用户菜单');
insert into sys_menu values ('110', '定时任务', '2', '2', 'job',       'monitor/job/index',   '', '', '1', '0', 'C', '0', '0', 'monitor:job:list', 'job', 'admin', now(), '', null, '定时任务菜单');
insert into sys_menu values ('112', '服务监控', '2', '4', 'server',    'monitor/server/index','', '', '1', '0', 'C', '0', '0', 'monitor:server:list', 'server', 'admin', now(), '', null, '服务监控菜单');
insert into sys_menu values ('113', '缓存监控', '2', '5', 'cache',     'monitor/cache/index', '', '', '1', '0', 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', now(), '', null, '缓存监控菜单');
insert into sys_menu values ('114', '缓存列表', '2', '6', 'cacheList', 'monitor/cache/list',  '', '', '1', '0', 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', now(), '', null, '缓存列表菜单');
insert into sys_menu values ('115', '表单构建', '3', '1', 'build',     'tool/build/index',    '', '', '1', '0', 'C', '0', '0', 'tool:build:list', 'build', 'admin', now(), '', null, '表单构建菜单');
insert into sys_menu values ('116', '代码生成', '3', '2', 'gen',       'tool/gen/index',      '', '', '1', '0', 'C', '0', '0', 'tool:gen:list', 'code', 'admin', now(), '', null, '代码生成菜单');
insert into sys_menu values ('117', '系统接口', '3', '3', 'swagger',   'tool/swagger/index',  '', '', '1', '0', 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', now(), '', null, '系统接口菜单');
-- 三级菜单
insert into sys_menu values ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '', '','1', '0', 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', now(), '', null, '操作日志菜单');
insert into sys_menu values ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', '', '1', '0', 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', now(), '', null, '登录日志菜单');
-- 用户管理按钮
insert into sys_menu values ('1000', '用户查询', '100', '1', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1001', '用户新增', '100', '2', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1002', '用户修改', '100', '3', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1003', '用户删除', '100', '4', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1004', '用户导出', '100', '5', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:export', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1005', '用户导入', '100', '6', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:import', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1006', '重置密码', '100', '7', '', '', '', '', '1', '0', 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', now(), '', null, '');
-- 角色管理按钮
insert into sys_menu values ('1007', '角色查询', '101', '1', '', '', '', '', '1', '0', 'F', '0', '0', 'system:role:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1008', '角色新增', '101', '2', '', '', '', '', '1', '0', 'F', '0', '0', 'system:role:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1009', '角色修改', '101', '3', '', '', '', '', '1', '0', 'F', '0', '0', 'system:role:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1010', '角色删除', '101', '4', '', '', '', '', '1', '0', 'F', '0', '0', 'system:role:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1011', '角色导出', '101', '5', '', '', '', '', '1', '0', 'F', '0', '0', 'system:role:export', '#', 'admin', now(), '', null, '');
-- 菜单管理按钮
insert into sys_menu values ('1012', '菜单查询', '102', '1', '', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1013', '菜单新增', '102', '2', '', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1014', '菜单修改', '102', '3', '', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1015', '菜单删除', '102', '4', '', '', '', '', '1', '0', 'F', '0', '0', 'system:menu:remove', '#', 'admin', now(), '', null, '');
-- 部门管理按钮
insert into sys_menu values ('1016', '部门查询', '103', '1', '', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1017', '部门新增', '103', '2', '', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1018', '部门修改', '103', '3', '', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1019', '部门删除', '103', '4', '', '', '', '', '1', '0', 'F', '0', '0', 'system:dept:remove', '#', 'admin', now(), '', null, '');
-- 岗位管理按钮
insert into sys_menu values ('1020', '岗位查询', '104', '1', '', '', '', '', '1', '0', 'F', '0', '0', 'system:post:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1021', '岗位新增', '104', '2', '', '', '', '', '1', '0', 'F', '0', '0', 'system:post:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1022', '岗位修改', '104', '3', '', '', '', '', '1', '0', 'F', '0', '0', 'system:post:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1023', '岗位删除', '104', '4', '', '', '', '', '1', '0', 'F', '0', '0', 'system:post:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1024', '岗位导出', '104', '5', '', '', '', '', '1', '0', 'F', '0', '0', 'system:post:export', '#', 'admin', now(), '', null, '');
-- 字典管理按钮
insert into sys_menu values ('1025', '字典查询', '105', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:dict:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1026', '字典新增', '105', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:dict:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1027', '字典修改', '105', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:dict:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1028', '字典删除', '105', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:dict:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1029', '字典导出', '105', '5', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:dict:export', '#', 'admin', now(), '', null, '');
-- 参数设置按钮
insert into sys_menu values ('1030', '参数查询', '106', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:config:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1031', '参数新增', '106', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:config:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1032', '参数修改', '106', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:config:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1033', '参数删除', '106', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:config:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1034', '参数导出', '106', '5', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:config:export', '#', 'admin', now(), '', null, '');
-- 通知公告按钮
insert into sys_menu values ('1035', '公告查询', '107', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:notice:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1036', '公告新增', '107', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:notice:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1037', '公告修改', '107', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:notice:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1038', '公告删除', '107', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'system:notice:remove', '#', 'admin', now(), '', null, '');
-- 操作日志按钮
insert into sys_menu values ('1039', '操作查询', '500', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1040', '操作删除', '500', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1041', '日志导出', '500', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', now(), '', null, '');
-- 登录日志按钮
insert into sys_menu values ('1042', '登录查询', '501', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1043', '登录删除', '501', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1044', '日志导出', '501', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1045', '账户解锁', '501', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', now(), '', null, '');
-- 在线用户按钮
insert into sys_menu values ('1046', '在线查询', '109', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:online:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1047', '批量强退', '109', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1048', '单条强退', '109', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', now(), '', null, '');
-- 定时任务按钮
insert into sys_menu values ('1049', '任务查询', '110', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1050', '任务新增', '110', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:add', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1051', '任务修改', '110', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1052', '任务删除', '110', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1053', '状态修改', '110', '5', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1054', '任务导出', '110', '6', '#', '', '', '', '1', '0', 'F', '0', '0', 'monitor:job:export', '#', 'admin', now(), '', null, '');
-- 代码生成按钮
insert into sys_menu values ('1055', '生成查询', '116', '1', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:query', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1056', '生成修改', '116', '2', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:edit', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1057', '生成删除', '116', '3', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:remove', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1058', '导入代码', '116', '4', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:import', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1059', '预览代码', '116', '5', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:preview', '#', 'admin', now(), '', null, '');
insert into sys_menu values ('1060', '生成代码', '116', '6', '#', '', '', '', '1', '0', 'F', '0', '0', 'tool:gen:code', '#', 'admin', now(), '', null, '');


-- ----------------------------
-- 6. 用户和角色关联表 用户N-1角色
-- ----------------------------
DROP TABLE IF EXISTS "sys_user_role" cascade;
CREATE TABLE "sys_user_role"
(
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "sys_user_role"
  ADD CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("user_id", "role_id");

COMMENT ON COLUMN "sys_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_user_role"."role_id" IS '角色ID';
COMMENT ON TABLE "sys_user_role" IS '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
INSERT INTO "sys_user_role" VALUES (1, 1);
INSERT INTO "sys_user_role" VALUES (2, 2);


-- ----------------------------
-- 7. 角色和菜单关联表 角色1-N菜单
-- ----------------------------
DROP TABLE IF EXISTS "sys_role_menu" cascade;
CREATE TABLE "sys_role_menu"
(
  "role_id" int8 NOT NULL,
  "menu_id" int8 NOT NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "sys_role_menu"
  ADD CONSTRAINT "sys_role_menu_pkey" PRIMARY KEY ("role_id", "menu_id");

COMMENT ON COLUMN "sys_role_menu"."role_id" IS '角色ID';
COMMENT ON COLUMN "sys_role_menu"."menu_id" IS '菜单ID';
COMMENT ON TABLE "sys_role_menu" IS '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
INSERT INTO "sys_role_menu" VALUES (2, 1);
INSERT INTO "sys_role_menu" VALUES (2, 2);
INSERT INTO "sys_role_menu" VALUES (2, 3);
INSERT INTO "sys_role_menu" VALUES (2, 4);

INSERT INTO "sys_role_menu" VALUES (2, 100);
INSERT INTO "sys_role_menu" VALUES (2, 101);
INSERT INTO "sys_role_menu" VALUES (2, 102);
INSERT INTO "sys_role_menu" VALUES (2, 103);
INSERT INTO "sys_role_menu" VALUES (2, 104);
INSERT INTO "sys_role_menu" VALUES (2, 105);
INSERT INTO "sys_role_menu" VALUES (2, 106);
INSERT INTO "sys_role_menu" VALUES (2, 107);
INSERT INTO "sys_role_menu" VALUES (2, 108);
INSERT INTO "sys_role_menu" VALUES (2, 109);
INSERT INTO "sys_role_menu" VALUES (2, 110);
INSERT INTO "sys_role_menu" VALUES (2, 111);
INSERT INTO "sys_role_menu" VALUES (2, 112);
INSERT INTO "sys_role_menu" VALUES (2, 113);
INSERT INTO "sys_role_menu" VALUES (2, 114);
INSERT INTO "sys_role_menu" VALUES (2, 115);
INSERT INTO "sys_role_menu" VALUES (2, 116);

INSERT INTO "sys_role_menu" VALUES (2, 500);
INSERT INTO "sys_role_menu" VALUES (2, 501);

INSERT INTO "sys_role_menu" VALUES (2, 1000);
INSERT INTO "sys_role_menu" VALUES (2, 1001);
INSERT INTO "sys_role_menu" VALUES (2, 1002);
INSERT INTO "sys_role_menu" VALUES (2, 1003);
INSERT INTO "sys_role_menu" VALUES (2, 1004);
INSERT INTO "sys_role_menu" VALUES (2, 1005);
INSERT INTO "sys_role_menu" VALUES (2, 1006);
INSERT INTO "sys_role_menu" VALUES (2, 1007);
INSERT INTO "sys_role_menu" VALUES (2, 1008);
INSERT INTO "sys_role_menu" VALUES (2, 1009);
INSERT INTO "sys_role_menu" VALUES (2, 1010);
INSERT INTO "sys_role_menu" VALUES (2, 1011);
INSERT INTO "sys_role_menu" VALUES (2, 1012);
INSERT INTO "sys_role_menu" VALUES (2, 1013);
INSERT INTO "sys_role_menu" VALUES (2, 1014);
INSERT INTO "sys_role_menu" VALUES (2, 1015);
INSERT INTO "sys_role_menu" VALUES (2, 1016);
INSERT INTO "sys_role_menu" VALUES (2, 1017);
INSERT INTO "sys_role_menu" VALUES (2, 1018);
INSERT INTO "sys_role_menu" VALUES (2, 1019);
INSERT INTO "sys_role_menu" VALUES (2, 1020);
INSERT INTO "sys_role_menu" VALUES (2, 1021);
INSERT INTO "sys_role_menu" VALUES (2, 1022);
INSERT INTO "sys_role_menu" VALUES (2, 1023);
INSERT INTO "sys_role_menu" VALUES (2, 1024);
INSERT INTO "sys_role_menu" VALUES (2, 1025);
INSERT INTO "sys_role_menu" VALUES (2, 1026);
INSERT INTO "sys_role_menu" VALUES (2, 1027);
INSERT INTO "sys_role_menu" VALUES (2, 1028);
INSERT INTO "sys_role_menu" VALUES (2, 1029);
INSERT INTO "sys_role_menu" VALUES (2, 1030);
INSERT INTO "sys_role_menu" VALUES (2, 1031);
INSERT INTO "sys_role_menu" VALUES (2, 1032);
INSERT INTO "sys_role_menu" VALUES (2, 1033);
INSERT INTO "sys_role_menu" VALUES (2, 1034);
INSERT INTO "sys_role_menu" VALUES (2, 1035);
INSERT INTO "sys_role_menu" VALUES (2, 1036);
INSERT INTO "sys_role_menu" VALUES (2, 1037);
INSERT INTO "sys_role_menu" VALUES (2, 1038);
INSERT INTO "sys_role_menu" VALUES (2, 1039);
INSERT INTO "sys_role_menu" VALUES (2, 1040);
INSERT INTO "sys_role_menu" VALUES (2, 1041);
INSERT INTO "sys_role_menu" VALUES (2, 1042);
INSERT INTO "sys_role_menu" VALUES (2, 1043);
INSERT INTO "sys_role_menu" VALUES (2, 1044);
INSERT INTO "sys_role_menu" VALUES (2, 1045);
INSERT INTO "sys_role_menu" VALUES (2, 1046);
INSERT INTO "sys_role_menu" VALUES (2, 1047);
INSERT INTO "sys_role_menu" VALUES (2, 1048);
INSERT INTO "sys_role_menu" VALUES (2, 1049);
INSERT INTO "sys_role_menu" VALUES (2, 1050);
INSERT INTO "sys_role_menu" VALUES (2, 1051);
INSERT INTO "sys_role_menu" VALUES (2, 1052);
INSERT INTO "sys_role_menu" VALUES (2, 1053);
INSERT INTO "sys_role_menu" VALUES (2, 1054);
INSERT INTO "sys_role_menu" VALUES (2, 1055);
INSERT INTO "sys_role_menu" VALUES (2, 1058);
INSERT INTO "sys_role_menu" VALUES (2, 1056);
INSERT INTO "sys_role_menu" VALUES (2, 1057);
INSERT INTO "sys_role_menu" VALUES (2, 1059);
INSERT INTO "sys_role_menu" VALUES (2, 1060);


-- ----------------------------
-- 8. 角色和部门关联表 角色1-N部门
-- ----------------------------
DROP TABLE IF EXISTS "sys_role_dept" cascade;
CREATE TABLE "sys_role_dept"
(
  "role_id" int8 NOT NULL,
  "dept_id" int8 NOT NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_role_dept
-- ----------------------------
ALTER TABLE "sys_role_dept"
  ADD CONSTRAINT "sys_role_dept_pkey" PRIMARY KEY ("role_id", "dept_id");

COMMENT ON COLUMN "sys_role_dept"."role_id" IS '角色ID';
COMMENT ON COLUMN "sys_role_dept"."dept_id" IS '部门ID';
COMMENT ON TABLE "sys_role_dept" IS '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
INSERT INTO "sys_role_dept" VALUES (2, 100);
INSERT INTO "sys_role_dept" VALUES (2, 101);
INSERT INTO "sys_role_dept" VALUES (2, 105);


-- ----------------------------
-- 9. 用户与岗位关联表 用户1-N岗位
-- ----------------------------
DROP TABLE IF EXISTS "sys_user_post" cascade;
CREATE TABLE "sys_user_post"
(
  "user_id" int8 NOT NULL,
  "post_id" int8 NOT NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_user_post
-- ----------------------------
ALTER TABLE "sys_user_post"
  ADD CONSTRAINT "sys_user_post_pkey" PRIMARY KEY ("user_id", "post_id");

COMMENT ON COLUMN "sys_user_post"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_user_post"."post_id" IS '岗位ID';
COMMENT ON TABLE "sys_user_post" IS '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
INSERT INTO "sys_user_post" VALUES (1, 1);
INSERT INTO "sys_user_post" VALUES (2, 2);

-- ----------------------------
-- 10. 操作日志记录
-- ----------------------------
DROP TABLE IF EXISTS "sys_oper_log" cascade;
CREATE TABLE "sys_oper_log"
(
  "oper_id"        bigserial primary key ,
  "title"          varchar(50),
  "business_type"  char(1),
  "method"         varchar(100),
  "request_method" varchar(10),
  "operator_type"  smallint,
  "oper_name"      varchar(50),
  "dept_name"      varchar(50),
  "oper_url"       varchar(255),
  "oper_ip"        varchar(128),
  "oper_location"  varchar(255),
  "oper_param"     varchar(2000),
  "json_result"    varchar(2000),
  "status"         smallint,
  "error_msg"      varchar(2000),
  "oper_time"      timestamptz default now(),
  "cost_time"        int8 default 0
)
;
-- ----------------------------
-- Primary Key structure for table sys_oper_log
-- ----------------------------

COMMENT ON COLUMN "sys_oper_log"."oper_id" IS '日志主键';
COMMENT ON COLUMN "sys_oper_log"."title" IS '模块标题';
COMMENT ON COLUMN "sys_oper_log"."business_type" IS '业务类型（0其它 1新增 2修改 3删除）';
COMMENT ON COLUMN "sys_oper_log"."method" IS '方法名称';
COMMENT ON COLUMN "sys_oper_log"."request_method" IS '请求方式';
COMMENT ON COLUMN "sys_oper_log"."operator_type" IS '操作类别（0其它 1后台用户 2手机端用户）';
COMMENT ON COLUMN "sys_oper_log"."oper_name" IS '操作人员';
COMMENT ON COLUMN "sys_oper_log"."dept_name" IS '部门名称';
COMMENT ON COLUMN "sys_oper_log"."oper_url" IS '请求URL';
COMMENT ON COLUMN "sys_oper_log"."oper_ip" IS '主机地址';
COMMENT ON COLUMN "sys_oper_log"."oper_location" IS '操作地点';
COMMENT ON COLUMN "sys_oper_log"."oper_param" IS '请求参数';
COMMENT ON COLUMN "sys_oper_log"."json_result" IS '返回参数';
COMMENT ON COLUMN "sys_oper_log"."status" IS '操作状态（0正常 1异常）';
COMMENT ON COLUMN "sys_oper_log"."error_msg" IS '错误消息';
COMMENT ON COLUMN "sys_oper_log"."oper_time" IS '操作时间';
COMMENT ON TABLE "sys_oper_log" IS '操作日志记录';


-- ----------------------------
-- 11. 字典类型表
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict_type" cascade;
CREATE TABLE "sys_dict_type"
(
  "dict_id"     bigserial primary key ,
  "dict_name"   varchar(100) default '',
  "dict_type"   varchar(100) default '',
  "status"      char(1) default '0',
  "create_by"   varchar(64) default '',
  "create_time" timestamptz default now(),
  "update_by"   varchar(64) default '',
  "update_time" timestamptz default now(),
  "remark"      varchar(500) default NULL
)
;
-- ----------------------------
-- Indexes structure for table sys_dict_type
-- ----------------------------
CREATE INDEX "dict_type" ON "sys_dict_type" USING btree (
                                                         "dict_type"
                                                         "pg_catalog"."text_ops" ASC NULLS LAST
  );
alter sequence sys_dict_type_dict_id_seq restart 11;

COMMENT ON COLUMN "sys_dict_type"."dict_id" IS '字典主键';
COMMENT ON COLUMN "sys_dict_type"."dict_name" IS '字典名称';
COMMENT ON COLUMN "sys_dict_type"."dict_type" IS '字典类型';
COMMENT ON COLUMN "sys_dict_type"."status" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "sys_dict_type"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dict_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_dict_type"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dict_type"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_dict_type"."remark" IS '备注';
COMMENT ON TABLE "sys_dict_type" IS '字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO "sys_dict_type" VALUES (1,  '用户性别', 'sys_user_sex',       '0', 'admin', now(), '', now(), '用户性别列表');
INSERT INTO "sys_dict_type" VALUES (2,  '菜单状态', 'sys_show_hide',      '0', 'admin', now(), '', now(), '菜单状态列表');
INSERT INTO "sys_dict_type" VALUES (3,  '系统开关', 'sys_normal_disable', '0', 'admin', now(), '', now(), '系统开关列表');
INSERT INTO "sys_dict_type" VALUES (4,  '任务状态', 'sys_job_status',     '0', 'admin', now(), '', now(), '任务状态列表');
INSERT INTO "sys_dict_type" VALUES (5,  '任务分组', 'sys_job_group',      '0', 'admin', now(), '', now(), '任务分组列表');
INSERT INTO "sys_dict_type" VALUES (6,  '系统是否', 'sys_yes_no',         '0', 'admin', now(), '', now(), '系统是否列表');
INSERT INTO "sys_dict_type" VALUES (7,  '通知类型', 'sys_notice_type',    '0', 'admin', now(), '', now(), '通知类型列表');
INSERT INTO "sys_dict_type" VALUES (8,  '通知状态', 'sys_notice_status',  '0', 'admin', now(), '', now(), '通知状态列表');
INSERT INTO "sys_dict_type" VALUES (9,  '操作类型', 'sys_oper_type',      '0', 'admin', now(), '', now(), '操作类型列表');
INSERT INTO "sys_dict_type" VALUES (10, '系统状态', 'sys_common_status',  '0', 'admin', now(), '', now(), '登录状态列表');


-- ----------------------------
-- 12. 字典数据表
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict_data" cascade;
CREATE TABLE "sys_dict_data"
(
  "dict_code"   bigserial primary key ,
  "dict_sort"   int4 default 0,
  "dict_label"  varchar(100) default '',
  "dict_value"  varchar(100) default '',
  "dict_type"   varchar(100) default '',
  "css_class"   varchar(100) default null,
  "list_class"  varchar(100) default null,
  "is_default"  char(1) default 'N',
  "status"      char(1) default '0',
  "create_by"   varchar(64) default '',
  "create_time" timestamptz default now(),
  "update_by"   varchar(64) default '',
  "update_time" timestamptz default now(),
  "remark"      varchar(500) default NULL
)
;
-- ----------------------------
-- Primary Key structure for table __data
-- ----------------------------
alter sequence sys_dict_data_dict_code_seq restart 29;

COMMENT ON COLUMN "sys_dict_data"."dict_code" IS '字典编码';
COMMENT ON COLUMN "sys_dict_data"."dict_sort" IS '字典排序';
COMMENT ON COLUMN "sys_dict_data"."dict_label" IS '字典标签';
COMMENT ON COLUMN "sys_dict_data"."dict_value" IS '字典键值';
COMMENT ON COLUMN "sys_dict_data"."dict_type" IS '字典类型';
COMMENT ON COLUMN "sys_dict_data"."css_class" IS '样式属性（其他样式扩展）';
COMMENT ON COLUMN "sys_dict_data"."list_class" IS '表格回显样式';
COMMENT ON COLUMN "sys_dict_data"."is_default" IS '是否默认（Y是 N否）';
COMMENT ON COLUMN "sys_dict_data"."status" IS '状态（0正常 1停用）';
COMMENT ON COLUMN "sys_dict_data"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dict_data"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_dict_data"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dict_data"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_dict_data"."remark" IS '备注';
COMMENT ON TABLE "sys_dict_data" IS '字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO sys_dict_data VALUES (1,  1, '男' ,     '0',       'sys_user_sex',       '', '',        'Y', '0', 'admin', now(), '', now(), '性别男');
INSERT INTO sys_dict_data VALUES (2,  2, '女',      '1',       'sys_user_sex',       '', '',        'N', '0', 'admin', now(), '', now(), '性别女');
INSERT INTO sys_dict_data VALUES (3,  3, '未知',    '2',       'sys_user_sex',       '', '',        'N', '0', 'admin', now(), '', now(), '性别未知');
INSERT INTO sys_dict_data VALUES (4,  1, '显示',    0,       'sys_show_hide',      '', 'primary', 'Y', '0', 'admin', now(), '', now(), '显示菜单');
INSERT INTO sys_dict_data VALUES (5,  2, '隐藏',    '1',       'sys_show_hide',      '', 'danger',  'N', '0', 'admin', now(), '', now(), '隐藏菜单');
INSERT INTO sys_dict_data VALUES (6,  1, '正常',    0,       'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', now(), '', now(), '正常状态');
INSERT INTO sys_dict_data VALUES (7,  2, '停用',    '1',       'sys_normal_disable', '', 'danger',  'N', '0', 'admin', now(), '', now(), '停用状态');
INSERT INTO sys_dict_data VALUES (8,  1, '正常',    0,       'sys_job_status',     '', 'primary', 'Y', '0', 'admin', now(), '', now(), '正常状态');
INSERT INTO sys_dict_data VALUES (9,  2, '暂停',    '1',       'sys_job_status',     '', 'danger',  'N', '0', 'admin', now(), '', now(), '停用状态');
INSERT INTO sys_dict_data VALUES (10, 1, '默认',    'DEFAULT', 'sys_job_group',      '', '',        'Y', '0', 'admin', now(), '', now(), '默认分组');
INSERT INTO sys_dict_data VALUES (11, 2, '系统',    'SYSTEM',  'sys_job_group',      '', '',        'N', '0', 'admin', now(), '', now(), '系统分组');
INSERT INTO sys_dict_data VALUES (12, 1, '是',      true,       'sys_yes_no',         '', 'primary', 'Y', '0', 'admin', now(), '', now(), '系统默认是');
INSERT INTO sys_dict_data VALUES (13, 2, '否',      false,       'sys_yes_no',         '', 'danger',  'N', '0', 'admin', now(), '', now(), '系统默认否');
INSERT INTO sys_dict_data VALUES (14, 1, '通知',    '1',       'sys_notice_type',    '', 'warning', 'Y', '0', 'admin', now(), '', now(), '通知');
INSERT INTO sys_dict_data VALUES (15, 2, '公告',    '2',       'sys_notice_type',    '', 'success', 'N', '0', 'admin', now(), '', now(), '公告');
INSERT INTO sys_dict_data VALUES (16, 1, '正常',    0,       'sys_notice_status',  '', 'primary', 'Y', '0', 'admin', now(), '', now(), '正常状态');
INSERT INTO sys_dict_data VALUES (17, 2, '关闭',    '1',       'sys_notice_status',  '', 'danger',  'N', '0', 'admin', now(), '', now(), '关闭状态');
INSERT INTO sys_dict_data VALUES (18, 1, '新增',    '1',       'sys_oper_type',      '', 'info',    'N', '0', 'admin', now(), '', now(), '新增操作');
INSERT INTO sys_dict_data VALUES (19, 2, '修改',    '2',       'sys_oper_type',      '', 'info',    'N', '0', 'admin', now(), '', now(), '修改操作');
INSERT INTO sys_dict_data VALUES (20, 3, '删除',    '3',       'sys_oper_type',      '', 'danger',  'N', '0', 'admin', now(), '', now(), '删除操作');
INSERT INTO sys_dict_data VALUES (21, 4, '授权',    '4',       'sys_oper_type',      '', 'primary', 'N', '0', 'admin', now(), '', now(), '授权操作');
INSERT INTO sys_dict_data VALUES (22, 5, '导出',    '5',       'sys_oper_type',      '', 'warning', 'N', '0', 'admin', now(), '', now(), '导出操作');
INSERT INTO sys_dict_data VALUES (23, 6, '导入',    '6',       'sys_oper_type',      '', 'warning', 'N', '0', 'admin', now(), '', now(), '导入操作');
INSERT INTO sys_dict_data VALUES (24, 7, '强退',    '7',       'sys_oper_type',      '', 'danger',  'N', '0', 'admin', now(), '', now(), '强退操作');
INSERT INTO sys_dict_data VALUES (25, 8, '生成代码', '8',       'sys_oper_type',      '', 'warning', 'N', '0', 'admin', now(), '', now(), '生成操作');
INSERT INTO sys_dict_data VALUES (26, 9, '清空数据', '9',       'sys_oper_type',      '', 'danger',  'N', '0', 'admin', now(), '', now(), '清空操作');
INSERT INTO sys_dict_data VALUES (27, 1, '成功',    0,       'sys_common_status',  '', 'primary', 'N', '0', 'admin', now(), '', now(), '正常状态');
INSERT INTO sys_dict_data VALUES (28, 2, '失败',    '1',       'sys_common_status',  '', 'danger',  'N', '0', 'admin', now(), '', now(), '停用状态');


-- ----------------------------
-- 13. 参数配置表
-- ----------------------------
DROP TABLE IF EXISTS "sys_config" cascade;
CREATE TABLE "sys_config"
(
  "config_id"    bigserial    primary key ,
  "config_name"  varchar(100) default '',
  "config_key"   varchar(100) default '',
  "config_value" varchar(500) default '',
  "config_type"  char(1)   default 'N',
  "create_by"    varchar(64)  default '',
  "create_time"  timestamptz default now(),
  "update_by"    varchar(64)  default '',
  "update_time"  timestamptz default now(),
  "remark"       varchar(500) default NULL
)
;
alter sequence sys_config_config_id_seq restart 100;

COMMENT ON COLUMN "sys_config"."config_id" IS '参数主键';
COMMENT ON COLUMN "sys_config"."config_name" IS '参数名称';
COMMENT ON COLUMN "sys_config"."config_key" IS '参数键名';
COMMENT ON COLUMN "sys_config"."config_value" IS '参数键值';
COMMENT ON COLUMN "sys_config"."config_type" IS '系统内置（Y是 N否）';
COMMENT ON COLUMN "sys_config"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_config"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_config"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_config"."remark" IS '备注';
COMMENT ON TABLE "sys_config" IS '参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO "sys_config" VALUES (1, '主框架页-默认皮肤样式名称',    'sys.index.skinName',         'skin-blue',  'Y', 'admin', now(), 'admin',now(), '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO "sys_config" VALUES (2, '用户管理-账号初始密码',       'sys.user.initPassword',      '123456',     'Y', 'admin', now(), 'admin',now(), '初始化密码 123456');
INSERT INTO "sys_config" VALUES (3, '主框架页-侧边栏主题',         'sys.index.sideTheme',        'theme-dark', 'Y', 'admin', now(), 'admin',now(), '深色主题theme-dark，浅色主题theme-light');
INSERT INTO "sys_config" VALUES (4, '账号自助-验证码开关',         'sys.account.captchaEnabled', 'true',       'Y', 'admin', now(), 'admin', now(),'是否开启验证码功能（true开启，false关闭）');
INSERT INTO "sys_config" VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser',   'false',      'Y', 'admin', now(), 'admin', now(), '是否开启注册用户功能（true开启，false关闭）');

-- ----------------------------
-- 14. 系统访问记录表
-- ----------------------------
DROP TABLE IF EXISTS "sys_logininfor" cascade;
CREATE TABLE "sys_logininfor"
(
  "info_id"        bigserial    primary key ,
  "user_name"      varchar(50)  default '',
  "ipaddr"         varchar(128) default '',
  "login_location" varchar(255) default '',
  "browser"        varchar(50)  default '',
  "os"             varchar(50)  default '',
  "status"         char(1)   default '0',
  "msg"            varchar(255) default '',
  "login_time"     timestamptz default now()
)
;

alter sequence sys_logininfor_info_id_seq restart 100;


COMMENT ON COLUMN "sys_logininfor"."info_id" IS '访问ID';
COMMENT ON COLUMN "sys_logininfor"."user_name" IS '用户账号';
COMMENT ON COLUMN "sys_logininfor"."ipaddr" IS '登录IP地址';
COMMENT ON COLUMN "sys_logininfor"."login_location" IS '登录地点';
COMMENT ON COLUMN "sys_logininfor"."browser" IS '浏览器类型';
COMMENT ON COLUMN "sys_logininfor"."os" IS '操作系统';
COMMENT ON COLUMN "sys_logininfor"."status" IS '登录状态（0成功 1失败）';
COMMENT ON COLUMN "sys_logininfor"."msg" IS '提示消息';
COMMENT ON COLUMN "sys_logininfor"."login_time" IS '访问时间';
COMMENT ON TABLE "sys_logininfor" IS '系统访问记录';


-- ----------------------------
-- 15. 定时人物调度表
-- ----------------------------
DROP TABLE IF EXISTS "sys_job" cascade;
CREATE TABLE "sys_job"
(
  "job_id"          bigserial,
  "job_name"        varchar(64)  NOT NULL,
  "job_group"       varchar(64)  NOT NULL,
  "invoke_target"   varchar(500) NOT NULL,
  "cron_expression" varchar(255) default '',
  "misfire_policy"  varchar(20)  default '3',
  "concurrent"      char(1)   default '1',
  "status"          char(1)   default '0',
  "create_by"       varchar(64)  default '',
  "create_time"     timestamptz default now(),
  "update_by"       varchar(64)  default '',
  "update_time"     timestamptz default now(),
  "remark"          varchar(500) default NULL
)
;
-- ----------------------------
-- Primary Key structure for table sys_job
-- ----------------------------
ALTER TABLE "sys_job"
  ADD CONSTRAINT "sys_job_pkey" PRIMARY KEY ("job_id", "job_name", "job_group");
alter sequence sys_job_job_id_seq restart 4;

COMMENT ON COLUMN "sys_job"."job_id" IS '任务ID';
COMMENT ON COLUMN "sys_job"."job_name" IS '任务名称';
COMMENT ON COLUMN "sys_job"."job_group" IS '任务组名';
COMMENT ON COLUMN "sys_job"."invoke_target" IS '调用目标字符串';
COMMENT ON COLUMN "sys_job"."cron_expression" IS 'cron执行表达式';
COMMENT ON COLUMN "sys_job"."misfire_policy" IS '计划执行错误策略（1立即执行 2执行一次 3放弃执行）';
COMMENT ON COLUMN "sys_job"."concurrent" IS '是否并发执行（0允许 1禁止）';
COMMENT ON COLUMN "sys_job"."status" IS '状态（0正常 1暂停）';
COMMENT ON COLUMN "sys_job"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_job"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_job"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_job"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_job"."remark" IS '备注信息';
COMMENT ON TABLE "sys_job" IS '定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO "sys_job" VALUES (1, '系统默认（无参）', 'DEFAULT', 'acTask.acNoParams',                                          '0/10 * * * * ?', '3', '1', '1', 'admin',        now(), '', now(), '');
INSERT INTO "sys_job" VALUES (2, '系统默认（有参）', 'DEFAULT', 'acTask.acParams(''ac'')',                                    '0/15 * * * * ?', '3', '1', '1', 'admin',        now(), '', now(), '');
INSERT INTO "sys_job" VALUES (3, '系统默认（多参）', 'DEFAULT', 'acTask.acMultipleParams(''ac'', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', now(), '', now(), '');


-- ----------------------------
-- 16. 定时人物调度日志表
-- ----------------------------
DROP TABLE IF EXISTS "sys_job_log" cascade;
CREATE TABLE "sys_job_log"
(
  "job_log_id"     bigserial primary key ,
  "job_name"       varchar(64)   NOT NULL,
  "job_group"      varchar(64)   NOT NULL,
  "invoke_target"  varchar(500)  NOT NULL,
  "job_message"    varchar(500),
  "status"         char(1)    default '0',
  "exception_info" varchar(2000) default '',
  "create_time"    timestamptz default now()
)
;

COMMENT ON COLUMN "sys_job_log"."job_log_id" IS '任务日志ID';
COMMENT ON COLUMN "sys_job_log"."job_name" IS '任务名称';
COMMENT ON COLUMN "sys_job_log"."job_group" IS '任务组名';
COMMENT ON COLUMN "sys_job_log"."invoke_target" IS '调用目标字符串';
COMMENT ON COLUMN "sys_job_log"."job_message" IS '日志信息';
COMMENT ON COLUMN "sys_job_log"."status" IS '执行状态（0正常 1失败）';
COMMENT ON COLUMN "sys_job_log"."exception_info" IS '异常信息';
COMMENT ON COLUMN "sys_job_log"."create_time" IS '创建时间';
COMMENT ON TABLE "sys_job_log" IS '定时任务调度日志表';


-- ----------------------------
-- 17. 通知公告表
-- ----------------------------
DROP TABLE IF EXISTS "sys_notice" cascade;
CREATE TABLE "sys_notice"
(
  "notice_id"      bigserial primary key ,
  "notice_title"   varchar(50)   NOT NULL,
  "notice_type"    char(1)    NOT NULL,
  "notice_content" text          default NULL,
  "status"         char(1)    default '0',
  "create_by"      varchar(64)   default '',
  "create_time"    timestamptz default now(),
  "update_by"      varchar(64)   default '',
  "update_time"    timestamptz default now(),
  "remark"         varchar(255)  default NULL
)
;
alter sequence sys_notice_notice_id_seq restart 3;

COMMENT ON COLUMN "sys_notice"."notice_id" IS '公告ID';
COMMENT ON COLUMN "sys_notice"."notice_title" IS '公告标题';
COMMENT ON COLUMN "sys_notice"."notice_type" IS '公告类型（1通知 2公告）';
COMMENT ON COLUMN "sys_notice"."notice_content" IS '公告内容';
COMMENT ON COLUMN "sys_notice"."status" IS '公告状态（0正常 1关闭）';
COMMENT ON COLUMN "sys_notice"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_notice"."create_time" IS '创建时间';
COMMENT ON COLUMN "sys_notice"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_notice"."update_time" IS '更新时间';
COMMENT ON COLUMN "sys_notice"."remark" IS '备注';
COMMENT ON TABLE "sys_notice" IS '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
INSERT INTO "sys_notice" VALUES (2, '维护通知：2026-07-01 爱宝系统凌晨维护', '1', '新版本内容', '0', 'admin', now(), '',     now(), '管理员');
INSERT INTO "sys_notice" VALUES (1, '温馨提醒：2026-07-01 爱宝新版本发布啦', '2', '维护内容',   '0', 'admin', now(), 'admin',now(), '管理员');
