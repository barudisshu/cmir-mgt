-- ----------------------------
-- 1. 存储每一个已配置的 jobDetail 的详细信息
-- ----------------------------
DROP TABLE IF EXISTS qrtz_job_details cascade;
CREATE TABLE qrtz_job_details
(
  sched_name        varchar(120) NOT NULL,
  job_name          varchar(200) NOT NULL,
  job_group         varchar(200) NOT NULL,
  description       varchar(250),
  job_class_name    varchar(250) NOT NULL,
  is_durable        varchar(50)  NOT NULL,
  is_nonconcurrent  varchar(50)  NOT NULL,
  is_update_data    varchar(50)  NOT NULL,
  requests_recovery varchar(50)  NOT NULL,
  job_data          bytea,
  primary key (sched_name, job_name, job_group)
)
;
-- ----------------------------
-- 2. 存储已配置的 Trigger 的信息
-- ----------------------------
DROP TABLE IF EXISTS qrtz_triggers cascade;
CREATE TABLE qrtz_triggers
(
  sched_name     varchar(120) NOT NULL,
  trigger_name   varchar(200) NOT NULL,
  trigger_group  varchar(200) NOT NULL,
  job_name       varchar(200) NOT NULL,
  job_group      varchar(200) NOT NULL,
  description    varchar(250),
  next_fire_time int8,
  prev_fire_time int8,
  priority       int4,
  trigger_state  varchar(16)  NOT NULL,
  trigger_type   varchar(8)   NOT NULL,
  start_time     int8         NOT NULL,
  end_time       int8,
  calendar_name  varchar(200),
  misfire_instr  int2,
  job_data       bytea,
  primary key (sched_name, trigger_name, trigger_group)
)
;

-- ----------------------------
-- Indexes structure for table qrtz_triggers
-- ----------------------------
CREATE INDEX sched_name ON qrtz_triggers USING btree (
                                                      sched_name
                                                      pg_catalog.text_ops ASC NULLS LAST,
                                                      job_name
                                                      pg_catalog.text_ops ASC NULLS LAST,
                                                      job_group
                                                      pg_catalog.text_ops ASC NULLS LAST
  );


-- ----------------------------
-- 3. 存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
-- ----------------------------
DROP TABLE IF EXISTS qrtz_simple_triggers cascade;
CREATE TABLE qrtz_simple_triggers
(
  sched_name      varchar(120) NOT NULL,
  trigger_name    varchar(200) NOT NULL,
  trigger_group   varchar(200) NOT NULL,
  repeat_count    int8         NOT NULL,
  repeat_interval int8         NOT NULL,
  times_triggered int8         NOT NULL,
  primary key (sched_name, trigger_name, trigger_group),
  foreign key (sched_name, trigger_name, trigger_group)
    references qrtz_triggers (sched_name, trigger_name, trigger_group) on delete set null on update cascade
)
;

-- ----------------------------
-- 4. 存储 Cron Trigger，包括 Cron 表达式和时区信息
-- ----------------------------
DROP TABLE IF EXISTS qrtz_cron_triggers cascade;
CREATE TABLE qrtz_cron_triggers
(
  sched_name      varchar(120) NOT NULL,
  trigger_name    varchar(200) NOT NULL,
  trigger_group   varchar(200) NOT NULL,
  cron_expression varchar(200) NOT NULL,
  time_zone_id    varchar(80),
  primary key (sched_name, trigger_name, trigger_group),
  foreign key (sched_name, trigger_name, trigger_group)
    references qrtz_triggers (sched_name, trigger_name, trigger_group) on delete set null on update cascade
)
;

-- ----------------------------
-- 5. Trigger 作为 Blob 类型存储(用于Quartz 用户用JDBC 创建他们自己定制的Trigger 类型， JobStore 并不知道如何存储实例的时候)
-- ----------------------------
DROP TABLE IF EXISTS qrtz_blob_triggers cascade;
CREATE TABLE qrtz_blob_triggers
(
  sched_name    varchar(120) NOT NULL,
  trigger_name  varchar(200) NOT NULL,
  trigger_group varchar(200) NOT NULL,
  blob_data     bytea,
  PRIMARY KEY (sched_name, trigger_name, trigger_group),
  foreign key (sched_name, trigger_name, trigger_group)
    references qrtz_triggers (sched_name, trigger_name, trigger_group) on delete set null on update cascade
);


-- ----------------------------
-- 6. 以 Blob 类型存储存放日历信息，quartz 可配置一个日历来指定一个时间范围
-- ----------------------------
DROP TABLE IF EXISTS qrtz_calendars cascade;
CREATE TABLE qrtz_calendars
(
  sched_name    varchar(120) NOT NULL,
  calendar_name varchar(200) NOT NULL,
  calendar      bytea        NOT NULL,
  PRIMARY KEY (sched_name, calendar_name)
)
;

-- ----------------------------
-- 7. 存储已暂停的 Trigger 组的信息
-- ----------------------------
DROP TABLE IF EXISTS qrtz_paused_trigger_grps cascade;
CREATE TABLE qrtz_paused_trigger_grps
(
  sched_name    varchar(120) NOT NULL,
  trigger_group varchar(200) NOT NULL,
  primary key (sched_name, trigger_group)
)
;

-- ----------------------------
-- 8. 存储与已触发的Trigger相关的状态信息，以及相联 Job 的执行信息
-- ----------------------------
DROP TABLE IF EXISTS qrtz_fired_triggers cascade;
CREATE TABLE qrtz_fired_triggers
(
  sched_name        varchar(120) NOT NULL,
  entry_id          varchar(95)  NOT NULL,
  trigger_name      varchar(200) NOT NULL,
  trigger_group     varchar(200) NOT NULL,
  instance_name     varchar(200) NOT NULL,
  fired_time        int8         NOT NULL,
  sched_time        int8         NOT NULL,
  priority          int4         NOT NULL,
  state             varchar(16)  NOT NULL,
  job_name          varchar(200),
  job_group         varchar(200),
  is_nonconcurrent  varchar(20),
  requests_recovery varchar(20),
  primary key (sched_name, entry_id)
)
;


-- ----------------------------
-- 9. 存储少量的有关 Scheduler 的状态信息，加入是用于集群中，可以看到其他的 Scheduler 实例
-- ----------------------------
DROP TABLE IF EXISTS qrtz_scheduler_state cascade;
CREATE TABLE qrtz_scheduler_state
(
  sched_name        varchar(120) NOT NULL,
  instance_name     varchar(200) NOT NULL,
  last_checkin_time int8         NOT NULL,
  checkin_interval  int8         NOT NULL,
  primary key (sched_name, instance_name)
)
;

-- ----------------------------
-- 10. 存储程序的悲观锁的信息(假如使用了悲观锁)
-- ----------------------------
DROP TABLE IF EXISTS qrtz_locks cascade;
CREATE TABLE qrtz_locks
(
  sched_name varchar(120) NOT NULL,
  lock_name  varchar(40)  NOT NULL,
  primary key (sched_name, lock_name)
)
;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS qrtz_simprop_triggers cascade;
CREATE TABLE qrtz_simprop_triggers
(
  sched_name    varchar(120) NOT NULL,
  trigger_name  varchar(200) NOT NULL,
  trigger_group varchar(200) NOT NULL,
  str_prop_1    varchar(512),
  str_prop_2    varchar(512),
  str_prop_3    varchar(512),
  int_prop_1    int4,
  int_prop_2    int4,
  long_prop_1   int8,
  long_prop_2   int8,
  dec_prop_1    numeric(13, 4),
  dec_prop_2    numeric(13, 4),
  bool_prop_1   varchar(2),
  bool_prop_2   varchar(2),
  primary key (sched_name, trigger_name, trigger_group),
  foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers (sched_name, trigger_name, trigger_group) on delete set null on update cascade
)
;
