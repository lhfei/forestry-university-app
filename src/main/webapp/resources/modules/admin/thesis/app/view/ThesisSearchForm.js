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

var s_className = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    fieldLabel: '班级名称',
    transform: 's_className',
    labelWidth:  60,
    forceSelection: true
});

var s_thesisType = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_thesisType',
    fieldLabel: '论文类型',
    labelWidth:  60,
    forceSelection: true
});

var s_origin = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_origin',
    fieldLabel: '论文来源',
    labelWidth:  60,
    forceSelection: true
});

var s_degree = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    fieldLabel: '学科阶段',
    transform: 's_degree',
    labelWidth:  60,
    forceSelection: true
});

var s_status = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    fieldLabel: '论文状态',
    transform: 's_status',
    labelWidth:  60,
    forceSelection: true
});

// =============================================================

Ext.define('thesis.view.ThesisSearchForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.thesisSearchForm',
	
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
        	items: [s_className]
        }]
    },{
    	padding: '3 3 3 3',
        items: [{
        	anchor: '-5',
        	items: [s_thesisType]
        },{
        	anchor: '-5',
        	items: [s_origin]
        }]
    },{
    	padding: '3 3 3 3',
    	items: [{
        	anchor: '-5',
        	items: [s_status]
        },{
        	anchor: '-5',
        	items: [s_degree]
        }]    	
    },{
    	padding: '3 3 3 3',
    	anchor: '-5',
        items: [{
            xtype: 'textfield',
            labelWidth: 60,
            fieldLabel: '论文标题',
            anchor: '100%',
            name: 'thesisTitle'
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