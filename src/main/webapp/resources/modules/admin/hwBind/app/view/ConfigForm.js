/**
 * New node file
 */
Ext.define('course.view.ConfigForm', {
    extend: 'Ext.form.Panel',
    alias : 'widget.configForm',
    requires: ['Ext.ux.DataTip'],
    layout: 'form',
    collapsible: true,
    bodyPadding: '5 5 0',
    width: 350,
    fieldDefaults: {
        msgTarget: 'side',
        labelWidth: 75
    },
    defaults: {
        anchor: '100%'
    },

    defaultType: 'textfield',
    
    items: [{
        xtype: 'fieldset',
        title: 'Contact Information',
        defaultType: 'textfield',
        defaults: {
            width: 280
        },
        items: [{
                fieldLabel: 'First Name',
                emptyText: 'First Name',
                name: 'first'
            }, {
                fieldLabel: 'Last Name',
                emptyText: 'Last Name',
                name: 'last'
            }, {
                fieldLabel: 'Company',
                name: 'company'
            }, {
                fieldLabel: 'Email',
                name: 'email',
                vtype:'email'
            }, {
                xtype: 'combobox',
                fieldLabel: 'State',
                name: 'state',
                store: Ext.create('Ext.data.ArrayStore', {
                    fields: ['abbr', 'state'],
                    data : []
                }),
                valueField: 'abbr',
                displayField: 'state',
                typeAhead: true,
                queryMode: 'local',
                emptyText: 'Select a state...'
            }, {
                xtype: 'datefield',
                fieldLabel: 'Date of Birth',
                name: 'dob',
                allowBlank: false,
                maxValue: new Date()
            }
        ]
    }]
    
    
});