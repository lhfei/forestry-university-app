DROP INDEX "MAJOR-COURSE2_FK"
/

DROP INDEX "MAJOR-COURSE_FK"
/

DROP TABLE MAJOR_COURSE CASCADE CONSTRAINTS
/

DROP INDEX "STUDENT-COURSE2_FK"
/

DROP INDEX "STUDENT-COURSE_FK"
/

DROP TABLE STUDENT_COURSE CASCADE CONSTRAINTS
/

DROP INDEX S_T2_FK
/

DROP INDEX S_T_FK
/

DROP TABLE S_T CASCADE CONSTRAINTS
/

DROP INDEX "TEACHTER-COURSE2_FK"
/

DROP INDEX "TEACHTER-COURSE_FK"
/

DROP TABLE TEACHTER_COURSE CASCADE CONSTRAINTS
/

DROP INDEX TH_HW2_FK
/

DROP INDEX TH_HW_FK
/

DROP TABLE TH_HW CASCADE CONSTRAINTS
/

DROP INDEX T_HW2_FK
/

DROP INDEX T_HW_FK
/

DROP TABLE T_HW CASCADE CONSTRAINTS
/

DROP INDEX T_S2_FK
/

DROP INDEX T_S_FK
/

DROP TABLE T_S CASCADE CONSTRAINTS
/

DROP INDEX T_T2_FK
/

DROP INDEX T_T_FK
/

DROP TABLE T_T CASCADE CONSTRAINTS
/

DROP TABLE "APPROVE_STATUS" CASCADE CONSTRAINTS
/

DROP INDEX "COURSE_CLASS_FK"
/

DROP TABLE "CLASS_BASE" CASCADE CONSTRAINTS
/

DROP TABLE "COMBOBOX" CASCADE CONSTRAINTS
/

DROP TABLE "COURSE_BASE" CASCADE CONSTRAINTS
/

DROP INDEX HW_HW_ARCHIVE_FK
/

DROP INDEX "RELATIONSHIP_7_FK"
/

DROP TABLE "HOMEWORK_ARCHIVE" CASCADE CONSTRAINTS
/

DROP TABLE "HOMEWORK_BASE" CASCADE CONSTRAINTS
/

DROP TABLE "HOMEWORK_HISTORY" CASCADE CONSTRAINTS
/

DROP TABLE "MAJOR_BASE" CASCADE CONSTRAINTS
/

DROP TABLE "STUDENT_BASE" CASCADE CONSTRAINTS
/

DROP TABLE "TEACHER_BASE" CASCADE CONSTRAINTS
/

DROP INDEX TH_TH_ARCHIVE_FK
/

DROP INDEX "RELATIONSHIP_6_FK"
/

DROP TABLE "THESIS_ARCHIVE" CASCADE CONSTRAINTS
/

DROP TABLE "THESIS_BASE" CASCADE CONSTRAINTS
/

DROP TABLE "THESIS_HISTORY" CASCADE CONSTRAINTS
/

DROP TABLE "THESIS_ORIGIN" CASCADE CONSTRAINTS
/

DROP SEQUENCE "SEQ_APPROVE_STATUS"
/

DROP SEQUENCE "SEQ_CLASS_BASE"
/

DROP SEQUENCE "SEQ_COMMBOBOX"
/

DROP SEQUENCE "SEQ_COURSE_BASE"
/

DROP SEQUENCE "SEQ_HOMEWORK_ARCHIVE"
/

DROP SEQUENCE "SEQ_HOMEWORK_BASE"
/

DROP SEQUENCE "SEQ_HW_HISTORY"
/

DROP SEQUENCE "SEQ_MAJOR_BASE"
/

DROP SEQUENCE "SEQ_STUDENT_BASE"
/

DROP SEQUENCE "SEQ_TEACHER_BASE"
/

DROP SEQUENCE "SEQ_THESIS_ARCHIVE"
/

DROP SEQUENCE "SEQ_THESIS_BASE"
/

DROP SEQUENCE "SEQ_THESIS_HISTORY"
/

DROP SEQUENCE "SEQ_THESIS_ORIGIN"
/

CREATE SEQUENCE "SEQ_APPROVE_STATUS"
/

CREATE SEQUENCE "SEQ_CLASS_BASE"
/

CREATE SEQUENCE "SEQ_COMMBOBOX"
/

CREATE SEQUENCE "SEQ_COURSE_BASE"
/

CREATE SEQUENCE "SEQ_HOMEWORK_ARCHIVE"
/

CREATE SEQUENCE "SEQ_HOMEWORK_BASE"
/

CREATE SEQUENCE "SEQ_HW_HISTORY"
/

CREATE SEQUENCE "SEQ_MAJOR_BASE"
/

CREATE SEQUENCE "SEQ_STUDENT_BASE"
/

CREATE SEQUENCE "SEQ_TEACHER_BASE"
/

CREATE SEQUENCE "SEQ_THESIS_ARCHIVE"
/

CREATE SEQUENCE "SEQ_THESIS_BASE"
/

CREATE SEQUENCE "SEQ_THESIS_HISTORY"
/

CREATE SEQUENCE "SEQ_THESIS_ORIGIN"
/

/*==============================================================*/
/* Table: MAJOR_COURSE                                          */
/*==============================================================*/
CREATE TABLE MAJOR_COURSE 
(
   "MAJOR_BASE_ID"      INTEGER              NOT NULL,
   "COURSE_BASE_ID"     INTEGER              NOT NULL,
   CONSTRAINT PK_MAJOR_COURSE PRIMARY KEY ("MAJOR_BASE_ID", "COURSE_BASE_ID")
)
/

COMMENT ON TABLE MAJOR_COURSE IS
'专业、专业课程关联表'
/

COMMENT ON COLUMN MAJOR_COURSE."MAJOR_BASE_ID" IS
'专业基本信息'
/

COMMENT ON COLUMN MAJOR_COURSE."COURSE_BASE_ID" IS
'课程基本信息主键ID'
/

/*==============================================================*/
/* Index: "MAJOR-COURSE_FK"                                     */
/*==============================================================*/
CREATE INDEX "MAJOR-COURSE_FK" ON MAJOR_COURSE (
   "MAJOR_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: "MAJOR-COURSE2_FK"                                    */
/*==============================================================*/
CREATE INDEX "MAJOR-COURSE2_FK" ON MAJOR_COURSE (
   "COURSE_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: STUDENT_COURSE                                        */
/*==============================================================*/
CREATE TABLE STUDENT_COURSE 
(
   "STUDENT_BASE_ID"    INTEGER              NOT NULL,
   "MAJOR_BASE_ID"      INTEGER              NOT NULL,
   CONSTRAINT PK_STUDENT_COURSE PRIMARY KEY ("STUDENT_BASE_ID", "MAJOR_BASE_ID")
)
/

COMMENT ON TABLE STUDENT_COURSE IS
'学生专业关联信息表'
/

COMMENT ON COLUMN STUDENT_COURSE."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN STUDENT_COURSE."MAJOR_BASE_ID" IS
'专业基本信息'
/

/*==============================================================*/
/* Index: "STUDENT-COURSE_FK"                                   */
/*==============================================================*/
CREATE INDEX "STUDENT-COURSE_FK" ON STUDENT_COURSE (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: "STUDENT-COURSE2_FK"                                  */
/*==============================================================*/
CREATE INDEX "STUDENT-COURSE2_FK" ON STUDENT_COURSE (
   "MAJOR_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: S_T                                                   */
/*==============================================================*/
CREATE TABLE S_T 
(
   "STUDENT_BASE_ID"    INTEGER              NOT NULL,
   "THESIS_BASE_ID"     INTEGER              NOT NULL,
   CONSTRAINT PK_S_T PRIMARY KEY ("STUDENT_BASE_ID", "THESIS_BASE_ID")
)
/

COMMENT ON TABLE S_T IS
'教师-论文关联表'
/

COMMENT ON COLUMN S_T."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN S_T."THESIS_BASE_ID" IS
'论文基本信息主键ID'
/

/*==============================================================*/
/* Index: S_T_FK                                                */
/*==============================================================*/
CREATE INDEX S_T_FK ON S_T (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: S_T2_FK                                               */
/*==============================================================*/
CREATE INDEX S_T2_FK ON S_T (
   "THESIS_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: TEACHTER_COURSE                                       */
/*==============================================================*/
CREATE TABLE TEACHTER_COURSE 
(
   "TEACHER_BASE_ID"    INTEGER              NOT NULL,
   "COURSE_BASE_ID"     INTEGER              NOT NULL,
   CONSTRAINT PK_TEACHTER_COURSE PRIMARY KEY ("TEACHER_BASE_ID", "COURSE_BASE_ID")
)
/

COMMENT ON TABLE TEACHTER_COURSE IS
'教师-课程关联表'
/

COMMENT ON COLUMN TEACHTER_COURSE."TEACHER_BASE_ID" IS
'教师基本信息主键ID'
/

COMMENT ON COLUMN TEACHTER_COURSE."COURSE_BASE_ID" IS
'课程基本信息主键ID'
/

/*==============================================================*/
/* Index: "TEACHTER-COURSE_FK"                                  */
/*==============================================================*/
CREATE INDEX "TEACHTER-COURSE_FK" ON TEACHTER_COURSE (
   "TEACHER_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: "TEACHTER-COURSE2_FK"                                 */
/*==============================================================*/
CREATE INDEX "TEACHTER-COURSE2_FK" ON TEACHTER_COURSE (
   "COURSE_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: TH_HW                                                 */
/*==============================================================*/
CREATE TABLE TH_HW 
(
   "TEACHER_BASE_ID"    INTEGER              NOT NULL,
   "HOMEWORK_BASE_ID"   INTEGER              NOT NULL,
   CONSTRAINT PK_TH_HW PRIMARY KEY ("TEACHER_BASE_ID", "HOMEWORK_BASE_ID")
)
/

COMMENT ON TABLE TH_HW IS
'教师-作业关联表'
/

COMMENT ON COLUMN TH_HW."TEACHER_BASE_ID" IS
'教师基本信息主键ID'
/

COMMENT ON COLUMN TH_HW."HOMEWORK_BASE_ID" IS
'作业基本信息主键ID'
/

/*==============================================================*/
/* Index: TH_HW_FK                                              */
/*==============================================================*/
CREATE INDEX TH_HW_FK ON TH_HW (
   "TEACHER_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: TH_HW2_FK                                             */
/*==============================================================*/
CREATE INDEX TH_HW2_FK ON TH_HW (
   "HOMEWORK_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: T_HW                                                  */
/*==============================================================*/
CREATE TABLE T_HW 
(
   "STUDENT_BASE_ID"    INTEGER              NOT NULL,
   "HOMEWORK_BASE_ID"   INTEGER              NOT NULL,
   CONSTRAINT PK_T_HW PRIMARY KEY ("STUDENT_BASE_ID", "HOMEWORK_BASE_ID")
)
/

COMMENT ON TABLE T_HW IS
'学生-作业关联表'
/

COMMENT ON COLUMN T_HW."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN T_HW."HOMEWORK_BASE_ID" IS
'作业基本信息主键ID'
/

/*==============================================================*/
/* Index: T_HW_FK                                               */
/*==============================================================*/
CREATE INDEX T_HW_FK ON T_HW (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: T_HW2_FK                                              */
/*==============================================================*/
CREATE INDEX T_HW2_FK ON T_HW (
   "HOMEWORK_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: T_S                                                   */
/*==============================================================*/
CREATE TABLE T_S 
(
   "TEACHER_BASE_ID"    INTEGER              NOT NULL,
   "STUDENT_BASE_ID"    INTEGER              NOT NULL,
   CONSTRAINT PK_T_S PRIMARY KEY ("TEACHER_BASE_ID", "STUDENT_BASE_ID")
)
/

COMMENT ON TABLE T_S IS
'教师-学生关联表'
/

COMMENT ON COLUMN T_S."TEACHER_BASE_ID" IS
'教师基本信息主键ID'
/

COMMENT ON COLUMN T_S."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

/*==============================================================*/
/* Index: T_S_FK                                                */
/*==============================================================*/
CREATE INDEX T_S_FK ON T_S (
   "TEACHER_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: T_S2_FK                                               */
/*==============================================================*/
CREATE INDEX T_S2_FK ON T_S (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: T_T                                                   */
/*==============================================================*/
CREATE TABLE T_T 
(
   "TEACHER_BASE_ID"    INTEGER              NOT NULL,
   "THESIS_BASE_ID"     INTEGER              NOT NULL,
   CONSTRAINT PK_T_T PRIMARY KEY ("TEACHER_BASE_ID", "THESIS_BASE_ID")
)
/

COMMENT ON TABLE T_T IS
'教师-论文关联表'
/

COMMENT ON COLUMN T_T."TEACHER_BASE_ID" IS
'教师基本信息主键ID'
/

COMMENT ON COLUMN T_T."THESIS_BASE_ID" IS
'论文基本信息主键ID'
/

/*==============================================================*/
/* Index: T_T_FK                                                */
/*==============================================================*/
CREATE INDEX T_T_FK ON T_T (
   "TEACHER_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: T_T2_FK                                               */
/*==============================================================*/
CREATE INDEX T_T2_FK ON T_T (
   "THESIS_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: "APPROVE_STATUS"                                      */
/*==============================================================*/
CREATE TABLE "APPROVE_STATUS" 
(
   "APPROVE_STATUS_ID"  NUMBER(32)           NOT NULL,
   "DICT_TEXT"          VARCHAR2(32),
   "DICT_CODE"          VARCHAR2(32),
   "DICT_DESC"          VARCHAR2(32),
   CONSTRAINT PK_APPROVE_STATUS PRIMARY KEY ("APPROVE_STATUS_ID")
)
/

COMMENT ON TABLE "APPROVE_STATUS" IS
'作业、论文审核状态表，枚举值包括： [未提交|未审核|已审核|未通过]'
/

COMMENT ON COLUMN "APPROVE_STATUS"."APPROVE_STATUS_ID" IS
'审核状态表主键ID'
/

COMMENT ON COLUMN "APPROVE_STATUS"."DICT_TEXT" IS
'枚举值中文名称'
/

COMMENT ON COLUMN "APPROVE_STATUS"."DICT_CODE" IS
'字典编码'
/

COMMENT ON COLUMN "APPROVE_STATUS"."DICT_DESC" IS
'字典业务意义备注说明'
/

/*==============================================================*/
/* Table: "CLASS_BASE"                                          */
/*==============================================================*/
CREATE TABLE "CLASS_BASE" 
(
   "CLASS_ID"           INTEGER              NOT NULL,
   "COURSE_BASE_ID"     INTEGER,
   "CLASS_NAME"         VARCHAR2(32),
   "COURSE_NAME"        VARCHAR2(32),
   "COURSE_CODE"        VARCHAR2(32),
   CONSTRAINT PK_CLASS_BASE PRIMARY KEY ("CLASS_ID")
)
/

COMMENT ON TABLE "CLASS_BASE" IS
'班级信息表'
/

COMMENT ON COLUMN "CLASS_BASE"."CLASS_ID" IS
'班级主键ID'
/

COMMENT ON COLUMN "CLASS_BASE"."COURSE_BASE_ID" IS
'课程基本信息主键ID'
/

COMMENT ON COLUMN "CLASS_BASE"."CLASS_NAME" IS
'班级名称'
/

COMMENT ON COLUMN "CLASS_BASE"."COURSE_NAME" IS
'课程名称'
/

COMMENT ON COLUMN "CLASS_BASE"."COURSE_CODE" IS
'课程代码'
/

/*==============================================================*/
/* Index: "COURSE_CLASS_FK"                                     */
/*==============================================================*/
CREATE INDEX "COURSE_CLASS_FK" ON "CLASS_BASE" (
   "COURSE_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: "COMBOBOX"                                            */
/*==============================================================*/
CREATE TABLE "COMBOBOX" 
(
   "COMBO_ID"           INTEGER              NOT NULL,
   "COMBO_LABEL"        VARCHAR2(32)         NOT NULL,
   "COMBO_KEY"          VARCHAR2(32)         NOT NULL,
   "COMBO_CODE"         VARCHAR2(10)         NOT NULL,
   "COMBO_DESC"         VARCHAR2(100),
   CONSTRAINT PK_COMBOBOX PRIMARY KEY ("COMBO_ID")
)
/

COMMENT ON TABLE "COMBOBOX" IS
'下拉菜单'
/

COMMENT ON COLUMN "COMBOBOX"."COMBO_ID" IS
'下拉菜单主键ID'
/

COMMENT ON COLUMN "COMBOBOX"."COMBO_LABEL" IS
'下拉菜单文本'
/

COMMENT ON COLUMN "COMBOBOX"."COMBO_KEY" IS
'下拉菜单类别'
/

COMMENT ON COLUMN "COMBOBOX"."COMBO_CODE" IS
'下拉菜单编码'
/

COMMENT ON COLUMN "COMBOBOX"."COMBO_DESC" IS
'下拉菜单描述'
/

/*==============================================================*/
/* Table: "COURSE_BASE"                                         */
/*==============================================================*/
CREATE TABLE "COURSE_BASE" 
(
   "COURSE_BASE_ID"     INTEGER              NOT NULL,
   "CLASS_NAME"         VARCHAR2(32),
   "COURSE_CODE"        VARCHAR2(32),
   "COURSE_NAME"        VARCHAR2(32),
   "ACADEMIC_YEAR"      VARCHAR2(32),
   "SEMESTER"           VARCHAR2(32),
   CONSTRAINT PK_COURSE_BASE PRIMARY KEY ("COURSE_BASE_ID")
)
/

COMMENT ON TABLE "COURSE_BASE" IS
'课程基本信息'
/

COMMENT ON COLUMN "COURSE_BASE"."COURSE_BASE_ID" IS
'课程基本信息主键ID'
/

COMMENT ON COLUMN "COURSE_BASE"."CLASS_NAME" IS
'班级名称'
/

COMMENT ON COLUMN "COURSE_BASE"."COURSE_CODE" IS
'课程代码'
/

COMMENT ON COLUMN "COURSE_BASE"."COURSE_NAME" IS
'课程名称'
/

COMMENT ON COLUMN "COURSE_BASE"."ACADEMIC_YEAR" IS
'学年'
/

COMMENT ON COLUMN "COURSE_BASE"."SEMESTER" IS
'学期(semester)'
/

/*==============================================================*/
/* Table: "HOMEWORK_ARCHIVE"                                    */
/*==============================================================*/
CREATE TABLE "HOMEWORK_ARCHIVE" 
(
   "HOMEWORK_ARCHIVE_ID" INTEGER              NOT NULL,
   "HOMEWORK_BASE_ID"   INTEGER,
   "STUDENT_BASE_ID"    INTEGER,
   "HW_NAME"            VARCHAR2(32),
   "NAME"               VARCHAR2(32),
   "STUDENT_ID"         VARCHAR2(32),
   "ARCHIVE_NAME"       VARCHAR2(255),
   "ARCHIVE_PATH"       VARCHAR2(255),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "STATUS"             VARCHAR2(32),
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_HOMEWORK_ARCHIVE PRIMARY KEY ("HOMEWORK_ARCHIVE_ID")
)
/

COMMENT ON TABLE "HOMEWORK_ARCHIVE" IS
'作业附件表'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."HOMEWORK_ARCHIVE_ID" IS
'作业附件表主键ID'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."HOMEWORK_BASE_ID" IS
'作业基本信息主键ID'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."HW_NAME" IS
'作业名称 HW(Homework)'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."NAME" IS
'学生姓名'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."STUDENT_ID" IS
'学生编号'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."ARCHIVE_NAME" IS
'附件名称'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."ARCHIVE_PATH" IS
'附件存放路径'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."STATUS" IS
'作业状态，枚举值包括：
[
  未提交（0）
  待审核（1）
  已审核（2）
  未通过（3）
]'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "HOMEWORK_ARCHIVE"."EXTEND1" IS
'备用扩展'
/

/*==============================================================*/
/* Index: "RELATIONSHIP_7_FK"                                   */
/*==============================================================*/
CREATE INDEX "RELATIONSHIP_7_FK" ON "HOMEWORK_ARCHIVE" (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: HW_HW_ARCHIVE_FK                                      */
/*==============================================================*/
CREATE INDEX HW_HW_ARCHIVE_FK ON "HOMEWORK_ARCHIVE" (
   "HOMEWORK_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: "HOMEWORK_BASE"                                       */
/*==============================================================*/
CREATE TABLE "HOMEWORK_BASE" 
(
   "HOMEWORK_BASE_ID"   INTEGER              NOT NULL,
   "HW_NAME"            VARCHAR2(32),
   "ACADEMIC_YEAR"      VARCHAR2(32),
   "SEMESTER"           VARCHAR2(32),
   "OPERATION_TIME"     DATE,
   "ACTION_TYPE"        VARCHAR2(32),
   "TEACHER_ID"         VARCHAR2(32),
   "TEACHER_NAME"       VARCHAR2(32),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   "CLASS_NAME"         VARCHAR2(32),
   "COURSE_CODE"        VARCHAR2(32),
   "COURSE_NAME"        VARCHAR2(32),
   "MAJOR_CODE"         VARCHAR2(32),
   "MAJOR_NAME"         VARCHAR2(32),
   "DESC_INFO"          VARCHAR2(256),
   CONSTRAINT PK_HOMEWORK_BASE PRIMARY KEY ("HOMEWORK_BASE_ID")
)
/

COMMENT ON TABLE "HOMEWORK_BASE" IS
'学生作业基本信息表'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."HOMEWORK_BASE_ID" IS
'作业基本信息主键ID'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."HW_NAME" IS
'作业名称 HW(Homework)'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."ACADEMIC_YEAR" IS
'学年'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."SEMESTER" IS
'学期(semester)'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."OPERATION_TIME" IS
'操作时间'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."ACTION_TYPE" IS
'操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."TEACHER_ID" IS
'教师编号teacher ID, 教师主键ID'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."TEACHER_NAME" IS
'教师姓名teacher name'
/

COMMENT ON COLUMN "HOMEWORK_BASE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "HOMEWORK_BASE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."EXTEND1" IS
'备用扩展'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."CLASS_NAME" IS
'班级名称'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."COURSE_CODE" IS
'课程代码'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."COURSE_NAME" IS
'课程名称'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."MAJOR_CODE" IS
'专业代码'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."MAJOR_NAME" IS
'专业名称'
/

COMMENT ON COLUMN "HOMEWORK_BASE"."DESC_INFO" IS
'描述信息'
/

/*==============================================================*/
/* Table: "HOMEWORK_HISTORY"                                    */
/*==============================================================*/
CREATE TABLE "HOMEWORK_HISTORY" 
(
   HOMEWORK_HISTORY_ID  INTEGER              NOT NULL,
   "OPERATOR"           VARCHAR2(32),
   "OPERATOR_ID"        VARCHAR2(32),
   "OPERATION_TIME"     DATE,
   "ACTION_TYPE"        VARCHAR2(32),
   "REF_ID"             VARCHAR2(32),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_HOMEWORK_HISTORY PRIMARY KEY (HOMEWORK_HISTORY_ID)
)
/

COMMENT ON TABLE "HOMEWORK_HISTORY" IS
'作业操作历史表'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY".HOMEWORK_HISTORY_ID IS
'作业操作历史表主键ID'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."OPERATOR" IS
'操作人'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."OPERATOR_ID" IS
'操作人标识'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."OPERATION_TIME" IS
'操作时间'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."ACTION_TYPE" IS
'操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."REF_ID" IS
'操作影响的记录ID，该记录ID即引用【作业基本信息表或论文基本信息表主键ID】'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "HOMEWORK_HISTORY"."EXTEND1" IS
'备用扩展'
/

/*==============================================================*/
/* Table: "MAJOR_BASE"                                          */
/*==============================================================*/
CREATE TABLE "MAJOR_BASE" 
(
   "MAJOR_BASE_ID"      INTEGER              NOT NULL,
   "MAJOR_CODE"         VARCHAR2(32),
   "MAJOR_NAME"         VARCHAR2(32),
   "ACADEMIC_YEAR"      VARCHAR2(32),
   CONSTRAINT PK_MAJOR_BASE PRIMARY KEY ("MAJOR_BASE_ID")
)
/

COMMENT ON TABLE "MAJOR_BASE" IS
'专业基本信息'
/

COMMENT ON COLUMN "MAJOR_BASE"."MAJOR_BASE_ID" IS
'专业基本信息'
/

COMMENT ON COLUMN "MAJOR_BASE"."MAJOR_CODE" IS
'专业代码'
/

COMMENT ON COLUMN "MAJOR_BASE"."MAJOR_NAME" IS
'专业名称'
/

COMMENT ON COLUMN "MAJOR_BASE"."ACADEMIC_YEAR" IS
'学年'
/

/*==============================================================*/
/* Table: "STUDENT_BASE"                                        */
/*==============================================================*/
CREATE TABLE "STUDENT_BASE" 
(
   "STUDENT_BASE_ID"    INTEGER              NOT NULL,
   "BIRTHDAY"           DATE,
   "NAME"               VARCHAR2(32),
   "STUDENT_ID"         VARCHAR2(32),
   "GENDER"             VARCHAR2(32),
   "EMAIL"              VARCHAR2(32),
   "ALIAS_NAME"         VARCHAR2(32),
   "USER_TYPE"          VARCHAR2(32)         DEFAULT '2',
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   "CLASS_NAME"         VARCHAR2(32),
   "MAJOR_CODE"         VARCHAR2(32),
   "MAJOR_NAME"         VARCHAR2(32),
   CONSTRAINT PK_STUDENT_BASE PRIMARY KEY ("STUDENT_BASE_ID")
)
/

COMMENT ON TABLE "STUDENT_BASE" IS
'学生基本信息'
/

COMMENT ON COLUMN "STUDENT_BASE"."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN "STUDENT_BASE"."BIRTHDAY" IS
'出生日期'
/

COMMENT ON COLUMN "STUDENT_BASE"."NAME" IS
'学生姓名'
/

COMMENT ON COLUMN "STUDENT_BASE"."STUDENT_ID" IS
'学生编号'
/

COMMENT ON COLUMN "STUDENT_BASE"."GENDER" IS
'性别， 枚举值包括
[
  男（0）
  女（1）
]'
/

COMMENT ON COLUMN "STUDENT_BASE"."EMAIL" IS
'电子邮箱'
/

COMMENT ON COLUMN "STUDENT_BASE"."ALIAS_NAME" IS
'用户别名'
/

COMMENT ON COLUMN "STUDENT_BASE"."USER_TYPE" IS
'用户类型'
/

COMMENT ON COLUMN "STUDENT_BASE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "STUDENT_BASE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "STUDENT_BASE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "STUDENT_BASE"."EXTEND1" IS
'备用扩展'
/

COMMENT ON COLUMN "STUDENT_BASE"."CLASS_NAME" IS
'班级名称'
/

COMMENT ON COLUMN "STUDENT_BASE"."MAJOR_CODE" IS
'专业代码'
/

COMMENT ON COLUMN "STUDENT_BASE"."MAJOR_NAME" IS
'专业名称'
/

/*==============================================================*/
/* Table: "TEACHER_BASE"                                        */
/*==============================================================*/
CREATE TABLE "TEACHER_BASE" 
(
   "TEACHER_BASE_ID"    INTEGER              NOT NULL,
   "BIRTHDAY"           DATE,
   "GENDER"             VARCHAR2(32),
   "TEACHER_NAME"       VARCHAR2(32),
   "TEACHER_ID"         VARCHAR2(32),
   "USER_TYPE"          VARCHAR2(32)         DEFAULT '1',
   "EMAIL"              VARCHAR2(32),
   "ALIAS_NAME"         VARCHAR2(32),
   CREATE_TIME          DATE,
   MODIFY_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_TEACHER_BASE PRIMARY KEY ("TEACHER_BASE_ID")
)
/

COMMENT ON TABLE "TEACHER_BASE" IS
'教师、导师、老师基本信息'
/

COMMENT ON COLUMN "TEACHER_BASE"."TEACHER_BASE_ID" IS
'教师基本信息主键ID'
/

COMMENT ON COLUMN "TEACHER_BASE"."BIRTHDAY" IS
'出生日期'
/

COMMENT ON COLUMN "TEACHER_BASE"."GENDER" IS
'性别， 枚举值包括
[
  男（0）
  女（1）
]'
/

COMMENT ON COLUMN "TEACHER_BASE"."TEACHER_NAME" IS
'教师姓名teacher name'
/

COMMENT ON COLUMN "TEACHER_BASE"."TEACHER_ID" IS
'教师编号teacher ID, 教师主键ID'
/

COMMENT ON COLUMN "TEACHER_BASE"."USER_TYPE" IS
'用户类型'
/

COMMENT ON COLUMN "TEACHER_BASE"."EMAIL" IS
'电子邮箱'
/

COMMENT ON COLUMN "TEACHER_BASE"."ALIAS_NAME" IS
'用户别名'
/

COMMENT ON COLUMN "TEACHER_BASE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "TEACHER_BASE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "TEACHER_BASE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "TEACHER_BASE"."EXTEND1" IS
'备用扩展'
/

/*==============================================================*/
/* Table: "THESIS_ARCHIVE"                                      */
/*==============================================================*/
CREATE TABLE "THESIS_ARCHIVE" 
(
   "THESIS_ARCHIVE_ID"  INTEGER              NOT NULL,
   "THESIS_BASE_ID"     INTEGER,
   "STUDENT_BASE_ID"    INTEGER,
   "THESIS_TITLE"       VARCHAR2(100),
   "NAME"               VARCHAR2(32),
   "STUDENT_ID"         VARCHAR2(32),
   "ARCHIVE_NAME"       VARCHAR2(255),
   "ARCHIVE_PATH"       VARCHAR2(255),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_THESIS_ARCHIVE PRIMARY KEY ("THESIS_ARCHIVE_ID")
)
/

COMMENT ON TABLE "THESIS_ARCHIVE" IS
'论文附件表'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."THESIS_ARCHIVE_ID" IS
'论文附件表主键ID'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."THESIS_BASE_ID" IS
'论文基本信息主键ID'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."STUDENT_BASE_ID" IS
'学生基本信息主键ID'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."THESIS_TITLE" IS
'论文标题thesis'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."NAME" IS
'学生姓名'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."STUDENT_ID" IS
'学生编号'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."ARCHIVE_NAME" IS
'附件名称'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."ARCHIVE_PATH" IS
'附件存放路径'
/

COMMENT ON COLUMN "THESIS_ARCHIVE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "THESIS_ARCHIVE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "THESIS_ARCHIVE"."EXTEND1" IS
'备用扩展'
/

/*==============================================================*/
/* Index: "RELATIONSHIP_6_FK"                                   */
/*==============================================================*/
CREATE INDEX "RELATIONSHIP_6_FK" ON "THESIS_ARCHIVE" (
   "STUDENT_BASE_ID" ASC
)
/

/*==============================================================*/
/* Index: TH_TH_ARCHIVE_FK                                      */
/*==============================================================*/
CREATE INDEX TH_TH_ARCHIVE_FK ON "THESIS_ARCHIVE" (
   "THESIS_BASE_ID" ASC
)
/

/*==============================================================*/
/* Table: "THESIS_BASE"                                         */
/*==============================================================*/
CREATE TABLE "THESIS_BASE" 
(
   "THESIS_BASE_ID"     INTEGER              NOT NULL,
   "TEACHER_NAME"       VARCHAR2(32),
   "TEACHER_ID"         VARCHAR2(32),
   "ORIGIN"             VARCHAR2(32),
   "THESIS_TITLE"       VARCHAR2(100),
   "OPERATION_TIME"     DATE,
   "ACTION_TYPE"        VARCHAR2(32),
   "THESIS_TYPE"        VARCHAR2(32),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   "DESC_INFO"          VARCHAR2(256),
   CONSTRAINT PK_THESIS_BASE PRIMARY KEY ("THESIS_BASE_ID")
)
/

COMMENT ON TABLE "THESIS_BASE" IS
'论文基本信息'
/

COMMENT ON COLUMN "THESIS_BASE"."THESIS_BASE_ID" IS
'论文基本信息主键ID'
/

COMMENT ON COLUMN "THESIS_BASE"."TEACHER_NAME" IS
'教师姓名teacher name'
/

COMMENT ON COLUMN "THESIS_BASE"."TEACHER_ID" IS
'教师编号teacher ID, 教师主键ID'
/

COMMENT ON COLUMN "THESIS_BASE"."ORIGIN" IS
'论文来源，枚举值包括： [
  科研（1）
  生产（2）
  模拟（3）
  其它（0）
]'
/

COMMENT ON COLUMN "THESIS_BASE"."THESIS_TITLE" IS
'论文标题thesis'
/

COMMENT ON COLUMN "THESIS_BASE"."OPERATION_TIME" IS
'操作时间'
/

COMMENT ON COLUMN "THESIS_BASE"."ACTION_TYPE" IS
'操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
/

COMMENT ON COLUMN "THESIS_BASE"."THESIS_TYPE" IS
'论文类别，枚举值包括：
[
  设计（0）
  论文（1）
]'
/

COMMENT ON COLUMN "THESIS_BASE".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "THESIS_BASE".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "THESIS_BASE"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "THESIS_BASE"."EXTEND1" IS
'备用扩展'
/

COMMENT ON COLUMN "THESIS_BASE"."DESC_INFO" IS
'描述信息'
/

/*==============================================================*/
/* Table: "THESIS_HISTORY"                                      */
/*==============================================================*/
CREATE TABLE "THESIS_HISTORY" 
(
   "THESIS_HISTORY_ID"  NUMBER(32)           NOT NULL,
   "OPERATOR"           VARCHAR2(32),
   "OPERATOR_ID"        VARCHAR2(32),
   "OPERATION_TIME"     DATE,
   "REF_ID"             VARCHAR2(32),
   "ACTION_TYPE"        VARCHAR2(32),
   MODIFY_TIME          DATE,
   CREATE_TIME          DATE,
   "EXTEND"             VARCHAR2(32),
   "EXTEND1"            VARCHAR2(32),
   CONSTRAINT PK_THESIS_HISTORY PRIMARY KEY ("THESIS_HISTORY_ID")
)
/

COMMENT ON TABLE "THESIS_HISTORY" IS
'论文操作历史表'
/

COMMENT ON COLUMN "THESIS_HISTORY"."THESIS_HISTORY_ID" IS
'论文操作历史表主键ID'
/

COMMENT ON COLUMN "THESIS_HISTORY"."OPERATOR" IS
'操作人'
/

COMMENT ON COLUMN "THESIS_HISTORY"."OPERATOR_ID" IS
'操作人标识'
/

COMMENT ON COLUMN "THESIS_HISTORY"."OPERATION_TIME" IS
'操作时间'
/

COMMENT ON COLUMN "THESIS_HISTORY"."REF_ID" IS
'操作影响的记录ID，该记录ID即引用【作业基本信息表或论文基本信息表主键ID】'
/

COMMENT ON COLUMN "THESIS_HISTORY"."ACTION_TYPE" IS
'操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
/

COMMENT ON COLUMN "THESIS_HISTORY".MODIFY_TIME IS
'最近一次修改时间'
/

COMMENT ON COLUMN "THESIS_HISTORY".CREATE_TIME IS
'创建时间'
/

COMMENT ON COLUMN "THESIS_HISTORY"."EXTEND" IS
'备用扩展'
/

COMMENT ON COLUMN "THESIS_HISTORY"."EXTEND1" IS
'备用扩展'
/

/*==============================================================*/
/* Table: "THESIS_ORIGIN"                                       */
/*==============================================================*/
CREATE TABLE "THESIS_ORIGIN" 
(
   "THESIS_ORIGIN_ID"   INTEGER              NOT NULL,
   "DICT_TEXT"          VARCHAR2(32),
   "DICT_CODE"          VARCHAR2(32),
   "DICT_DESC"          VARCHAR2(32),
   CONSTRAINT PK_THESIS_ORIGIN PRIMARY KEY ("THESIS_ORIGIN_ID")
)
/

COMMENT ON TABLE "THESIS_ORIGIN" IS
'论文来源字典表，枚举值包括[科研|生产|模拟|其它]'
/

COMMENT ON COLUMN "THESIS_ORIGIN"."THESIS_ORIGIN_ID" IS
'论文来源字典主键ID'
/

COMMENT ON COLUMN "THESIS_ORIGIN"."DICT_TEXT" IS
'枚举值中文名称'
/

COMMENT ON COLUMN "THESIS_ORIGIN"."DICT_CODE" IS
'字典编码'
/

COMMENT ON COLUMN "THESIS_ORIGIN"."DICT_DESC" IS
'字典业务意义备注说明'
/

ALTER TABLE MAJOR_COURSE
   ADD CONSTRAINT FK_MAJOR_CO_MAJOR_COU_MAJOR_BA FOREIGN KEY ("MAJOR_BASE_ID")
      REFERENCES "MAJOR_BASE" ("MAJOR_BASE_ID")
/

ALTER TABLE MAJOR_COURSE
   ADD CONSTRAINT FK_MAJOR_CO_MAJOR_COU_COURSE_B FOREIGN KEY ("COURSE_BASE_ID")
      REFERENCES "COURSE_BASE" ("COURSE_BASE_ID")
/

ALTER TABLE STUDENT_COURSE
   ADD CONSTRAINT FK_STUDENT__STUDENT_C_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE STUDENT_COURSE
   ADD CONSTRAINT FK_STUDENT__STUDENT_C_MAJOR_BA FOREIGN KEY ("MAJOR_BASE_ID")
      REFERENCES "MAJOR_BASE" ("MAJOR_BASE_ID")
/

ALTER TABLE S_T
   ADD CONSTRAINT FK_S_T_S_T_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE S_T
   ADD CONSTRAINT FK_S_T_S_T2_THESIS_B FOREIGN KEY ("THESIS_BASE_ID")
      REFERENCES "THESIS_BASE" ("THESIS_BASE_ID")
/

ALTER TABLE TEACHTER_COURSE
   ADD CONSTRAINT FK_TEACHTER_TEACHTER__TEACHER_ FOREIGN KEY ("TEACHER_BASE_ID")
      REFERENCES "TEACHER_BASE" ("TEACHER_BASE_ID")
/

ALTER TABLE TEACHTER_COURSE
   ADD CONSTRAINT FK_TEACHTER_TEACHTER__COURSE_B FOREIGN KEY ("COURSE_BASE_ID")
      REFERENCES "COURSE_BASE" ("COURSE_BASE_ID")
/

ALTER TABLE TH_HW
   ADD CONSTRAINT FK_TH_HW_TH_HW_TEACHER_ FOREIGN KEY ("TEACHER_BASE_ID")
      REFERENCES "TEACHER_BASE" ("TEACHER_BASE_ID")
/

ALTER TABLE TH_HW
   ADD CONSTRAINT FK_TH_HW_TH_HW2_HOMEWORK FOREIGN KEY ("HOMEWORK_BASE_ID")
      REFERENCES "HOMEWORK_BASE" ("HOMEWORK_BASE_ID")
/

ALTER TABLE T_HW
   ADD CONSTRAINT FK_T_HW_T_HW_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE T_HW
   ADD CONSTRAINT FK_T_HW_T_HW2_HOMEWORK FOREIGN KEY ("HOMEWORK_BASE_ID")
      REFERENCES "HOMEWORK_BASE" ("HOMEWORK_BASE_ID")
/

ALTER TABLE T_S
   ADD CONSTRAINT FK_T_S_T_S_TEACHER_ FOREIGN KEY ("TEACHER_BASE_ID")
      REFERENCES "TEACHER_BASE" ("TEACHER_BASE_ID")
/

ALTER TABLE T_S
   ADD CONSTRAINT FK_T_S_T_S2_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE T_T
   ADD CONSTRAINT FK_T_T_T_T_TEACHER_ FOREIGN KEY ("TEACHER_BASE_ID")
      REFERENCES "TEACHER_BASE" ("TEACHER_BASE_ID")
/

ALTER TABLE T_T
   ADD CONSTRAINT FK_T_T_T_T2_THESIS_B FOREIGN KEY ("THESIS_BASE_ID")
      REFERENCES "THESIS_BASE" ("THESIS_BASE_ID")
/

ALTER TABLE "CLASS_BASE"
   ADD CONSTRAINT FK_CLASS_BA_COURSE_CL_COURSE_B FOREIGN KEY ("COURSE_BASE_ID")
      REFERENCES "COURSE_BASE" ("COURSE_BASE_ID")
/

ALTER TABLE "HOMEWORK_ARCHIVE"
   ADD CONSTRAINT FK_HOMEWORK_HW_HW_ARC_HOMEWORK FOREIGN KEY ("HOMEWORK_BASE_ID")
      REFERENCES "HOMEWORK_BASE" ("HOMEWORK_BASE_ID")
/

ALTER TABLE "HOMEWORK_ARCHIVE"
   ADD CONSTRAINT FK_HOMEWORK_ST_HW_ARC_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE "THESIS_ARCHIVE"
   ADD CONSTRAINT FK_THESIS_A_ST_THE_AR_STUDENT_ FOREIGN KEY ("STUDENT_BASE_ID")
      REFERENCES "STUDENT_BASE" ("STUDENT_BASE_ID")
/

ALTER TABLE "THESIS_ARCHIVE"
   ADD CONSTRAINT FK_THESIS_A_TH_TH_ARC_THESIS_B FOREIGN KEY ("THESIS_BASE_ID")
      REFERENCES "THESIS_BASE" ("THESIS_BASE_ID")
/


