/**
 * 
 */
Ext.define('course.model.HWModel', {
	extend: 'Ext.data.Model',	
	requires: ['Ext.data.reader.Json'],
	fields: [
	         'baseId' ,
	         'name' ,
	         'academicYear' ,
	         'semester' ,
			 'studentBaseId' ,
			 'homeworkArahiveId' ,
			 'studentId' ,
			 'studentName' ,
			 'courseName' ,
			 'className' ,
			 'status', 
			 'archiveName' ,
			 'archivePath' ,
			 'teacherName' ,
			 'majorName' ,
			 'pageNum' ,
			 'pageSize' ,
			 'createTime' ,
			 'modifyTime' ,
			 'operationTime' ,
			 'actionType' ,
			 'teacherId' ,
			 'courseCode' ,
			 'majorCode' ,
			 'description' ,
			 'extend' ,
			 'extend1' 
	]
});