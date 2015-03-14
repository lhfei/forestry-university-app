/**
 * New node file
 */
Ext.define('course.view.CourseGrid' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.courseGrid',
    
    requires: ['Ext.toolbar.Paging', 'Ext.selection.CheckboxModel'],
    
    iconCls: 'icon-grid',

    title : '课程列表',
    store: 'CourseStore',

    selType: 'checkboxmodel',
    columnLines: true,
    
    columns: [
        {header: '序号', xtype: 'rownumberer', width: 40, sortable: false, locked: true }, 
        {header: 'ID',  dataIndex: 'baseId',  flex: 1, align: 'center', hidden: true},
        {header: '学年', dataIndex: 'academicYear', flex: 1, align: 'center'},
        {header: '学期', dataIndex: 'semester', flex: 1, align: 'center'},
        {header: '课程名称', dataIndex: 'courseName', flex: 3, align: 'center'},
        {header: '课程编码', dataIndex: 'courseCode', flex: 3, align: 'center'},
        {header: '班级名称', dataIndex: 'className', flex: 1, align: 'center'}       
    ],
	
	initComponent: function() {
		var me = this;
		
		this.dockedItems = [{
            xtype: 'toolbar',
            items: [{
                iconCls: 'icon-add',
                itemId: 'add',
                text: '新建',
                action: 'add'
            },{
            	itemId: 'removeButton',
                iconCls: 'icon-delete',
                tooltip:'选中您要删除的课程',
                text: '删除',
                action: 'delete',
                disabled: false
            }]
        },
        {
            xtype: 'pagingtoolbar',
            dock:'bottom',
            store: 'CourseStore',
            displayInfo: true,
            displayMsg: 'Displaying contacts {0} - {1} of {2}',
            emptyMsg: "No contacts to display"
        }];
		
		this.callParent(arguments);
	}
});
