/**
 * 
 */
Ext.require([
    'Ext.form.Panel',
    'Ext.layout.container.Anchor'
]);

// =====================================================================
var s_academicYear = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_academicYear',
    fieldLabel: '学年',
    labelWidth:  60,
    forceSelection: true
});

var s_semester = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_semester',
    fieldLabel: '学期',
    labelWidth:  60,
    forceSelection: true
});

var s_courseName = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_courseName',
    fieldLabel: '课程名称',
    labelWidth:  60,
    forceSelection: true
});

var s_className = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    fieldLabel: '班级名称',
    transform: 's_className',
    labelWidth:  60,
    forceSelection: true
});

var s_status = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    fieldLabel: '作业状态',
    transform: 's_status',
    labelWidth:  60,
    forceSelection: true
});

// =============================================================

Ext.define('hwork.view.HWSearchForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.hwSearchForm',
	
	frame: true,
	width: '100%',
	autoHeight: true,
	title: '作业列表',
	
	fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 65,
        anchor: '100%'
    },
    
    defaults: {
        border: false,
        xtype: 'panel',
        //flex: 3,
        bodyPadding: 5,
        padding: '3 3 3 3',
        layout: 'anchor'
    },

    layout: 'hbox',
    
    items: [{
    	padding: '3 3 3 3',
        items: [{
        	anchor: '-5',
        	items: [s_academicYear]
        },{
        	anchor: '-5',
        	items: [s_semester]
        }]
    },{
    	padding: '3 3 3 3',
        items: [{
        	anchor: '-5',
        	items: [s_courseName]
        },{
        	anchor: '-5',
        	items: [s_className]
        }]
    },{
    	padding: '3 3 3 3',
    	items: [{
        	anchor: '-5',
        	items: [s_status]
        },{
        	anchor: '-5',
            xtype: 'textfield',
            labelWidth: 65,
            labelAlign: 'left',
            fieldLabel: '作业名称',
            name: 'name'
        }]    	
    },{
    	padding: '3 3 3 3',
    	anchor: '-5',
        items: [{
            xtype: 'textfield',
            labelWidth: 60,
            fieldLabel: '学生编号',
            anchor: '100%',
            name: 'studentId'
        },{
        	xtype: 'textfield',
            labelWidth: 60,
            fieldLabel: '学生姓名',
            anchor: '100%',
            name: 'studentName'
        }]
    },{
        items: [{
        	items: [{
                xtype:'button',
                action: 'doReset',
                iconCls: 'icon-refresh',
                text: '重置'
            }]
        },{
        	padding: '3 3 3 0',
        	items: [{
                xtype:'button',
                action: 'doSearch',
                iconCls: 'search-icon',
                text: '查询'
            }]
        }]
    }]
});