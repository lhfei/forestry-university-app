ALTER TABLE SYS_USER
   DROP CONSTRAINT FK_SYS_USER_ROLE_USER_SYS_ROLE;

ALTER TABLE "SYS_ROLE_MENU"
   DROP CONSTRAINT FK_SYS_ROLE_ROLE_MENU_SYS_ROLE;

ALTER TABLE "SYS_ROLE_MENU"
   DROP CONSTRAINT FK_SYS_ROLE_ROLE_MENU_SYS_MENU;

DROP TABLE SYS_MENU CASCADE CONSTRAINTS;

DROP TABLE SYS_ROLE CASCADE CONSTRAINTS;

DROP INDEX "ROLE_USER_ID_FK";

DROP TABLE SYS_USER CASCADE CONSTRAINTS;

DROP INDEX "R_M_FK";

DROP TABLE "SYS_ROLE_MENU" CASCADE CONSTRAINTS;

DROP SEQUENCE "SEQ_SYS_MENU";

DROP SEQUENCE "SEQ_SYS_ROLE";

DROP SEQUENCE "SEQ_SYS_USER";

CREATE SEQUENCE "SEQ_SYS_MENU";

CREATE SEQUENCE "SEQ_SYS_ROLE";

CREATE SEQUENCE "SEQ_SYS_USER";

/*==============================================================*/
/* Table: SYS_MENU                                              */
/*==============================================================*/
CREATE TABLE SYS_MENU 
(
   "MENU_ID"            INTEGER              NOT NULL,
   "M_NAME"             VARCHAR2(32)         NOT NULL,
   "M_CLS"              VARCHAR2(32)         NOT NULL,
   "M_STATUS"           VARCHAR2(32)         NOT NULL,
   "M_CODE"             VARCHAR2(32)         NOT NULL,
   "M_URL"              VARCHAR2(256)        NOT NULL,
   "PID"                INTEGER              DEFAULT 0 NOT NULL,
   "MODIFY_TIME"        DATE,
   "CREATE_TIME"        DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_SYS_MENU PRIMARY KEY ("MENU_ID")
);

COMMENT ON TABLE SYS_MENU IS
'系统权限菜单表';

COMMENT ON COLUMN SYS_MENU."MENU_ID" IS
'菜单主键ID';

COMMENT ON COLUMN SYS_MENU."M_NAME" IS
'菜单名称';

COMMENT ON COLUMN SYS_MENU."M_CLS" IS
'菜单样式';

COMMENT ON COLUMN SYS_MENU."M_STATUS" IS
'菜单状态，''open'' or ''closed''';

COMMENT ON COLUMN SYS_MENU."M_URL" IS
'菜单链接';

COMMENT ON COLUMN SYS_MENU."PID" IS
'父菜单节点ID，无父节点时该值为0';

COMMENT ON COLUMN SYS_MENU."MODIFY_TIME" IS
'修改时';

COMMENT ON COLUMN SYS_MENU."CREATE_TIME" IS
'创建时间';

COMMENT ON COLUMN SYS_MENU."EXTEND" IS
'备用扩展属性';

COMMENT ON COLUMN SYS_MENU."EXTEND1" IS
'备用扩展1';

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
CREATE TABLE SYS_ROLE 
(
   "ROLE_ID"            INTEGER              NOT NULL,
   "R_NAME"             VARCHAR2(32),
   "R_CODE"             VARCHAR2(32),
   "R_WEIGHT"           INTEGER,
   "R_DESC"             VARCHAR2(128),
   "R_STATUS"           INTEGER,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   "MODIFY_TIME"        DATE,
   "CREATE_TIME"        DATE,
   CONSTRAINT PK_SYS_ROLE PRIMARY KEY ("ROLE_ID")
);

COMMENT ON TABLE SYS_ROLE IS
'系统角色表';

COMMENT ON COLUMN SYS_ROLE."R_NAME" IS
'角色名称';

COMMENT ON COLUMN SYS_ROLE."R_CODE" IS
'角色编号';

COMMENT ON COLUMN SYS_ROLE."R_WEIGHT" IS
'角色权重';

COMMENT ON COLUMN SYS_ROLE."R_DESC" IS
'角色描述';

COMMENT ON COLUMN SYS_ROLE."R_STATUS" IS
'角色状态';

COMMENT ON COLUMN SYS_ROLE."EXTEND" IS
'备用扩展属性';

COMMENT ON COLUMN SYS_ROLE."EXTEND1" IS
'备用扩展1';

COMMENT ON COLUMN SYS_ROLE."MODIFY_TIME" IS
'修改时';

COMMENT ON COLUMN SYS_ROLE."CREATE_TIME" IS
'创建时间';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
CREATE TABLE SYS_USER 
(
   "ID"                 INTEGER              NOT NULL,
   "ROLE_ID"            INTEGER,
   "USER_ID"            VARCHAR2(32)         NOT NULL,
   "USER_NAME"          VARCHAR2(32),
   "GENDER"             INTEGER,
   "BIRTHDAY"           DATE,
   "USER_TYPE"          VARCHAR2(32),
   "PASS_WORD"          VARCHAR2(128),
   "EMAIL"              VARCHAR2(128),
   "ALIAS_NAME"         VARCHAR2(32),
   "SIGN_TIME"          DATE,
   "MODIFY_TIME"        DATE,
   "CREATE_TIME"        DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_SYS_USER PRIMARY KEY ("ID")
);

COMMENT ON TABLE SYS_USER IS
'系统用户表';

COMMENT ON COLUMN SYS_USER."ID" IS
'用户主键ID';

COMMENT ON COLUMN SYS_USER."USER_ID" IS
'用户编号（学生、教师编号）';

COMMENT ON COLUMN SYS_USER."USER_NAME" IS
'用户名称';

COMMENT ON COLUMN SYS_USER."GENDER" IS
'用户性别（男（0）  女（1））';

COMMENT ON COLUMN SYS_USER."BIRTHDAY" IS
'出生日期';

COMMENT ON COLUMN SYS_USER."USER_TYPE" IS
'用户类型';

COMMENT ON COLUMN SYS_USER."PASS_WORD" IS
'用户密码';

COMMENT ON COLUMN SYS_USER."EMAIL" IS
'用户邮箱';

COMMENT ON COLUMN SYS_USER."ALIAS_NAME" IS
'用户别名';

COMMENT ON COLUMN SYS_USER."SIGN_TIME" IS
'登录时间';

COMMENT ON COLUMN SYS_USER."MODIFY_TIME" IS
'修改时';

COMMENT ON COLUMN SYS_USER."CREATE_TIME" IS
'创建时间';

COMMENT ON COLUMN SYS_USER."EXTEND" IS
'备用扩展属性';

COMMENT ON COLUMN SYS_USER."EXTEND1" IS
'备用扩展1';

/*==============================================================*/
/* Index: "ROLE_USER_ID_FK"                                     */
/*==============================================================*/
CREATE INDEX "ROLE_USER_ID_FK" ON SYS_USER (
   "ROLE_ID" ASC
);

/*==============================================================*/
/* Table: "SYS_ROLE_MENU"                                       */
/*==============================================================*/
CREATE TABLE "SYS_ROLE_MENU" 
(
   "ROLE_ID"            INTEGER              NOT NULL,
   "MENU_ID"            INTEGER              NOT NULL,
   CONSTRAINT PK_SYS_ROLE_MENU PRIMARY KEY ("ROLE_ID", "MENU_ID")
);

COMMENT ON TABLE "SYS_ROLE_MENU" IS
'角色菜单关联表';

COMMENT ON COLUMN "SYS_ROLE_MENU"."MENU_ID" IS
'菜单主键ID';

/*==============================================================*/
/* Index: "R_M_FK"                                              */
/*==============================================================*/
CREATE INDEX "R_M_FK" ON "SYS_ROLE_MENU" (
   
);

ALTER TABLE SYS_USER
   ADD CONSTRAINT FK_SYS_USER_ROLE_USER_SYS_ROLE FOREIGN KEY ("ROLE_ID")
      REFERENCES SYS_ROLE ("ROLE_ID");

ALTER TABLE "SYS_ROLE_MENU"
   ADD CONSTRAINT FK_SYS_ROLE_ROLE_MENU_SYS_ROLE FOREIGN KEY ()
      REFERENCES SYS_ROLE ("ROLE_ID");

ALTER TABLE "SYS_ROLE_MENU"
   ADD CONSTRAINT FK_SYS_ROLE_ROLE_MENU_SYS_MENU FOREIGN KEY ()
      REFERENCES SYS_MENU ("MENU_ID");
