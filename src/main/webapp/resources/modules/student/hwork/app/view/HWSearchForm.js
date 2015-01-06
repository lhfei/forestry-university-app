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
    /*fieldLabel: '学年',
    labelWidth:  45,*/
    forceSelection: true
});

var s_semester = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_semester',
    /*fieldLabel: '学期',
    labelWidth:  45,*/
    forceSelection: true
});

var s_courseName = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_courseName',
    labelWidth:  45,
    forceSelection: true
});

var s_className = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_className',
    labelWidth:  45,
    forceSelection: true
});

// =============================================================

Ext.define('hwork.view.HWSearchForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.hwSearchForm',
	
	frame: true,
	//autoWidth: true,
	width: '100%',
	autoHeight: true,
	title: '作业列表',
	
	fieldDefaults: {
        labelAlign: 'right',
        msgTarget: 'side',
        labelWidth: 45,
        anchor: '95%'
    },
    
    defaults: {
        border: false,
        xtype: 'panel',
        flex: 2,
        bodyPadding: 2,
        layout: 'anchor'
    },

    layout: 'hbox',
    
    items: [{
        items: [/*{
            xtype: 'combo',
            fieldLabel: '学年',
            anchor: '-5',
            name: 'academicYear'
        }*/ {
        	anchor: '-5',
        	items: [s_academicYear]
        }]
    },{
        items: [/*{
            xtype: 'combo',
            fieldLabel: '学期',
            anchor: '-5',
            name: 'semester'
        }*/ {
        	anchor: '-5',
        	items: [s_semester]
        }]
    },{
        items: [/*{
            xtype: 'combo',
            fieldLabel: '课程',
            anchor: '-5',
            name: 'courseName'
        }*/{
        	anchor: '-5',
        	items: [s_courseName]
        }]
    },{
        items: [/*{
            xtype: 'combo',
            fieldLabel: '班级',
            anchor: '-5',
            name: 'className'
        }*/ {
        	anchor: '-5',
        	items: [s_className]
        }]
    },{
    	flex: 3,
        items: [{
            xtype: 'textfield',
            labelWidth: 65,
            fieldLabel: '作业名称',
            anchor: '100%',
            name: 'name'
        }]
    },{
    	width: 65,
        items: [{
            xtype:'button',
            action: 'doSearch',
            width: 65,
            iconCls: 'search-icon',
            text: '查询'
        }]
    }]
});