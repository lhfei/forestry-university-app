/**
 * 
 */
Ext.define('thesis.view.ThesisGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.thesisGrid',
	
	frame: true,
	autoWidth: true,
	autoHeight: true,
	
	//title: 'FileList',
	store: 'ThesisStore',
	
	//selType: 'checkboxmodel',

    columns: [
        {header: '序号', xtype: 'rownumberer', width: 40, sortable: false, locked: true }, 
        {header: 'ID',  dataIndex: 'baseId',  flex: 1, align: 'center', hidden: true},
        {header: 'ID',  dataIndex: 'studentBaseId',  flex: 1, align: 'center', hidden: true},
        {header: '学生名称', dataIndex: 'studentName', flex: 1, align: 'center'},
        {header: '班级名称', dataIndex: 'className', flex: 1, align: 'center'},
        {header: '论文标题', dataIndex: 'thesisTitle', flex: 2, align: 'center'},
        {header: '论文来源', dataIndex: 'origin', flex: 1, align: 'center'},
        {header: '论文类别', dataIndex: 'thesisType', flex: 1, align: 'center'},
        {header: '教师姓名', dataIndex: 'teacherName', flex: 2, align: 'center'},
        {header: '指导教师职称', dataIndex: 'teacherTitle', flex: 1, align: 'center'},
        {header: '英文标题', dataIndex: 'thesisEnTitle', flex: 2, align: 'center'},
        
        {header: '学生编号', dataIndex: 'studentId', flex: 1, align: 'center', hidden: true},        
        {header: '学科阶段', dataIndex: 'degree', flex: 1, align: 'center', hidden: true},
        {header: '论文成绩', dataIndex: 'score', flex: 1, align: 'center', hidden: true},
        {header: '学年', dataIndex: 'academicYear', flex: 1, align: 'center', hidden: true},
        {header: '学期', dataIndex: 'semester', flex: 1, align: 'center' , hidden: true},
        {header: '教师编号', dataIndex: 'teacherId', flex: 2, align: 'center', hidden: true},
        {header: '操作时间', dataIndex: 'operationTime', flex: 2, align: 'center', hidden: true},
        {header: '操作类型', dataIndex: 'actionType', flex: 2, align: 'center', hidden: true},

        {
        	header: '论文状态', 
        	dataIndex: 'status', 
        	flex: 1, 
        	align: 'center',
        	renderer: function(val) {
        		switch(val) {
        		
        		case 0:
        			return '<span style="color:red;">未提交</span>';
        			break;
        			
        		case 1: 
        			return '<span style="color:green;">待审核 </span>';
        			break;

        		case 2:
        			return '<span style="color:blue;">已审核</span>';
        			break;
        			
        		case 3: 
        			return '<span style="color:red;">未通过</span>';
        			break;
        		}
        	}
        	
        },{
            xtype: 'actioncolumn',
            id: 'ctrlCell',
            flex: 1,
            header: '作业状态',
            align: 'center',
            sortable: false,
            menuDisabled: true,
            items: [{
            	xtype: 'button',
            	id: 'startCtrl',
                iconCls: 'icon-upload',
                tooltip: '上传论文附件',
                action: 'start',
                hidden: true,
                scope: this
            },'-',{
            	xtype: 'button',
            	id: 'startCtrl',
                iconCls: 'icon-download',
                tooltip: '下载论文附件',
                action: 'start',
                scope: this
            }]
        },
        {header: '论文描述', dataIndex: 'description', flex: 1, align: 'center', hidden: true},
        {header: '操作时间', dataIndex: 'operationTime', flex: 1, align: 'center', hidden: true},
        {header: '创建时间', dataIndex: 'createTime', flex: 1, align: 'center', hidden: true},
        {header: '最近修改时间', dataIndex: 'modifyTime', flex: 1, align: 'center', hidden: true}
    ],
    
    initComponent: function() {
		this.dockedItems = [{
            xtype: 'pagingtoolbar',
            dock:'bottom',
            store: 'ThesisStore',
            displayInfo: true,
            displayMsg: 'Displaying contacts {0} - {1} of {2}',
            emptyMsg: "No contacts to display"
        }];
		
		this.callParent(arguments);
	}
    
});