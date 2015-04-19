/**
 * 
 */
Ext.define('thesis.model.ThesisModel', {
	extend: 'Ext.data.Model',
	
	requires: ['Ext.data.reader.Json'],
	fields: [
		'baseId', 		    // 论文ID
		'studentBaseId', 	// 学生主键ID
		'studentName', 	    //学生姓名
		'studentId', 		// 学生编号
		'degree',			//学历
		'score',			// 论文成绩
		'academicYear', 	// '学年 academic year'
		'semester', 		// '学期(semester)'
		'className', 		// '班级名称'
		'origin', 		    // '论文来源，枚举值包括： [ 科研（1）生产（2） 模拟（3）其它（0）]
		'thesisTitle', 	    // '论文标题thesis'
		'thesisEnTitle', 	// '论文英文标题thesis'
		'thesisType', 	    // '论文类别，枚举值包括： [ 设计（0） 论文（1） ]',
		'teacherId', 		// 教师编号
		'teacherName', 	    // 教师姓名
		'teacherTitle',	    //指导教师职称
		'archiveId',		// 论文附件主键ID
		'operationTime',      
		'actionType', 	    // '操作类型,枚举值包括【上传|下载|批量上传|批量下载|审核|修改】'
		'status',    		// '论文状态，枚举值包括： 未提交（0） 待审核（1） 已审核（2） 未通过（3） ]'
		'description'       
	]
	
});