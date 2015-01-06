/**
 * 
 */
Ext.define('hwork.view.HWGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.hwGrid',
	
	frame: true,
	autoWidth: true,
	autoHeight: true,
	
	//title: 'FileList',
	store: 'HWStore',
	
	//selType: 'checkboxmodel',

    columns: [
        Ext.create('Ext.grid.RowNumberer'),
        {header: 'ID',  dataIndex: 'id',  flex: 1, align: 'center', hidden: true},
        {header: '学年', dataIndex: 'academicYear', flex: 1, align: 'center'},
        {header: '学期', dataIndex: 'semester', flex: 1, align: 'center'},
        {header: '课程名称', dataIndex: 'courseName', flex: 1, align: 'center'},
        {header: '班级名称', dataIndex: 'className', flex: 1, align: 'center'},
        {header: '作业名称', dataIndex: 'name', flex: 1, align: 'center'},
        {header: '作业状态', dataIndex: 'status', flex: 1, align: 'center'},
        {
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
                tooltip: '上传作业附件',
                action: 'start',
                hidden: true,
                scope: this
            },'-',{
            	xtype: 'button',
            	id: 'startCtrl',
                iconCls: 'icon-download',
                tooltip: '预览作业附件',
                action: 'start',
                scope: this
            }]
        },
        
        /*{
        	header: '作业状态', 
        	dataIndex: 'status', 
        	flex: 1, 
        	align: 'center',
        	renderer :  function(val) {
                if (val > 0) {
                    return '<span style="color:green;">' + val + '</span>';
                } else if (val < 0) {
                    return '<span style="color:red;">' + val + '</span>';
                }
                return val;
            }
        },*/
        
        {header: '专业名称', dataIndex: 'majorName', flex: 1, align: 'center', hidden: true},
        {header: '专业编号', dataIndex: 'majorCode', flex: 1, align: 'center', hidden: true},
        {header: '教师姓名', dataIndex: 'teacherName', flex: 1, align: 'center', hidden: true},
        {header: '教师编号', dataIndex: 'teacherCode', flex: 1, align: 'center', hidden: true},
        {header: '课程编号', dataIndex: 'courseCode', flex: 1, align: 'center', hidden: true},
        {header: '操作时间', dataIndex: 'operationTime', flex: 1, align: 'center', hidden: true},
        {header: '创建时间', dataIndex: 'createTime', flex: 1, align: 'center', hidden: true},
        {header: '最近修改时间', dataIndex: 'modifyTime', flex: 1, align: 'center', hidden: true}
    ],
    
    initComponent: function() {
		this.dockedItems = [{
            xtype: 'pagingtoolbar',
            dock:'bottom',
            store: 'HWStore',
            displayInfo: true,
            displayMsg: 'Displaying contacts {0} - {1} of {2}',
            emptyMsg: "No contacts to display"
        }];
		
		this.callParent(arguments);
	}
    
});