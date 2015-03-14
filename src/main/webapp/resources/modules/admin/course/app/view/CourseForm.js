/**
 * New node file
 */
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
					    name : 'id',
					    fieldLabel: 'id',
					    hidden:true
					},    
                    {
                        xtype: 'textfield',
                        name : 'name',
                        fieldLabel: 'Name22'
                    },
                    {
                        xtype: 'textfield',
                        name : 'phone',
                        fieldLabel: 'Phone'
                    },
                    {
                        xtype: 'textfield',
                        name : 'email',
                        fieldLabel: 'Email'
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