/**
 * New node file
 */

var s_academicYear = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_academicYear',
    fieldLabel: '教学学年',
    forceSelection: true
});

var s_semester = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 's_semester',
    fieldLabel: '所属学期',
    forceSelection: true
});

Ext.define('course.view.CourseForm', {
    extend: 'Ext.window.Window',
    alias : 'widget.courseForm',

    requires: ['Ext.form.Panel','Ext.form.field.Text'],

    title : 'Edit Contact',
    layout: 'fit',
    autoShow: true,
    width: 380,
    height: 250,
    
    iconCls: 'icon-user',

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',
                
                fieldDefaults: {
                    anchor: '100%',
                    labelAlign: 'left',
                    allowBlank: false,
                    combineErrors: true,
                    msgTarget: 'side'
                },

                items: [
					{
					    xtype: 'textfield',
					    name : 'baseId',
					    fieldLabel: 'ID',
					    hidden:true
					},{
                        xtype: 'textfield',
                        name : 'courseName',
                        fieldLabel: '课程名称'
                    },{
                        xtype: 'textfield',
                        name : 'courseCode',
                        fieldLabel: '课程代码'
                    },{
                    	items: [s_academicYear]
                    },{
                    	items: [s_semester]
                    }
                ]
            }
        ];
        
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'bottom',
            id:'buttons',
            ui: 'footer',
            items: ['->', {
                iconCls: 'image-add',
                itemId: 'save',
                text: 'Save',
                action: 'save'
            },{
                iconCls: 'icon-cancel',
                text: 'Cancel',
                scope: this,
                handler: this.close
            }]
        }];

        this.callParent(arguments);
    }
});