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
'ϵͳȨ�޲˵���';

COMMENT ON COLUMN SYS_MENU."MENU_ID" IS
'�˵�����ID';

COMMENT ON COLUMN SYS_MENU."M_NAME" IS
'�˵�����';

COMMENT ON COLUMN SYS_MENU."M_CLS" IS
'�˵���ʽ';

COMMENT ON COLUMN SYS_MENU."M_STATUS" IS
'�˵�״̬��''open'' or ''closed''';

COMMENT ON COLUMN SYS_MENU."M_URL" IS
'�˵�����';

COMMENT ON COLUMN SYS_MENU."PID" IS
'���˵��ڵ�ID���޸��ڵ�ʱ��ֵΪ0';

COMMENT ON COLUMN SYS_MENU."MODIFY_TIME" IS
'�޸�ʱ';

COMMENT ON COLUMN SYS_MENU."CREATE_TIME" IS
'����ʱ��';

COMMENT ON COLUMN SYS_MENU."EXTEND" IS
'������չ����';

COMMENT ON COLUMN SYS_MENU."EXTEND1" IS
'������չ1';

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
'ϵͳ��ɫ��';

COMMENT ON COLUMN SYS_ROLE."R_NAME" IS
'��ɫ����';

COMMENT ON COLUMN SYS_ROLE."R_CODE" IS
'��ɫ���';

COMMENT ON COLUMN SYS_ROLE."R_WEIGHT" IS
'��ɫȨ��';

COMMENT ON COLUMN SYS_ROLE."R_DESC" IS
'��ɫ����';

COMMENT ON COLUMN SYS_ROLE."R_STATUS" IS
'��ɫ״̬';

COMMENT ON COLUMN SYS_ROLE."EXTEND" IS
'������չ����';

COMMENT ON COLUMN SYS_ROLE."EXTEND1" IS
'������չ1';

COMMENT ON COLUMN SYS_ROLE."MODIFY_TIME" IS
'�޸�ʱ';

COMMENT ON COLUMN SYS_ROLE."CREATE_TIME" IS
'����ʱ��';

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
'ϵͳ�û���';

COMMENT ON COLUMN SYS_USER."ID" IS
'�û�����ID';

COMMENT ON COLUMN SYS_USER."USER_ID" IS
'�û���ţ�ѧ������ʦ��ţ�';

COMMENT ON COLUMN SYS_USER."USER_NAME" IS
'�û�����';

COMMENT ON COLUMN SYS_USER."GENDER" IS
'�û��Ա��У�0��  Ů��1����';

COMMENT ON COLUMN SYS_USER."BIRTHDAY" IS
'��������';

COMMENT ON COLUMN SYS_USER."USER_TYPE" IS
'�û�����';

COMMENT ON COLUMN SYS_USER."PASS_WORD" IS
'�û�����';

COMMENT ON COLUMN SYS_USER."EMAIL" IS
'�û�����';

COMMENT ON COLUMN SYS_USER."ALIAS_NAME" IS
'�û�����';

COMMENT ON COLUMN SYS_USER."SIGN_TIME" IS
'��¼ʱ��';

COMMENT ON COLUMN SYS_USER."MODIFY_TIME" IS
'�޸�ʱ';

COMMENT ON COLUMN SYS_USER."CREATE_TIME" IS
'����ʱ��';

COMMENT ON COLUMN SYS_USER."EXTEND" IS
'������չ����';

COMMENT ON COLUMN SYS_USER."EXTEND1" IS
'������չ1';

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
'��ɫ�˵�������';

COMMENT ON COLUMN "SYS_ROLE_MENU"."MENU_ID" IS
'�˵�����ID';

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
