/**
 * 
 */
Ext.require([
    'Ext.tab.*',
    'Ext.window.*',
    'Ext.tip.*',
    'Ext.layout.container.Border'
]);

Ext.define('thesis.view.UploadWin', {
	extend: 'Ext.window.Window',
	alias: 'widget.uploadWin',
	
    title: 'Layout Window with title <em>after</em> tools',
    header: {
        titlePosition: 2,
        titleAlign: 'center'
    },
    closable: true,
    closeAction: 'hide',
    width: 600,
    minWidth: 350,
    height: 350,
    tools: [{type: 'pin'}],
    layout: {
        type: 'border',
        padding: 5
    },
    _html: '<img />',
    items: [{
        region: 'west',
        title: 'Navigation',
        width: 200,
        split: true,
        collapsible: true,
        floatable: false
    }, {
        region: 'center',
        xtype: 'tabpanel',
        items: [{
            rtl: false,
            title: 'Bogus Tab',
            loader: {
                url: '../teacher/downloadImg.do?id=35',
                contentType: 'html',
                autoLoad: true/*,
                params: 'foo=123&bar=abc'*/
            }
        }]
    }],
    
    preview: function(id){},
    
    initComponent: function() {
    	this.buttons = [{
            text: '上传',
            scope: this,
            handler: function(){}
        },{
            text: '取消',
            scope: this,
            handler: function() {
                this.hide();
            }
        }];  
    	
    	this.callParent(arguments);
    }
})
