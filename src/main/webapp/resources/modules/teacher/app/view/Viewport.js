Ext.define('Ifeng.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'border',
    items: [{
    	id: 'northPanel',
    	itemId: '_nPanel',
        region: 'north',
        xtype: 'headPanel',
        /*minHeight: 70,
        maxHeight: 100,*/
        split: true
    },{
    	id: 'centerPanel',
    	itemId: '_cPanel',
        region: 'center',
        xtype: 'mainPanel',
        split:true
    }, {
    	itemId: '_west',
        region: 'west',
        xtype: 'menuView',
        width: 200,
        minWidth: 100,
        maxWidth: 300,
        split:true
    },{
    	id: 'southPanel',
    	itemId: '_sPanel',
		region: 'south',
		contentEl: 'south',
        split: true,
        height: 25,
        minHeight: 25,
        maxHeight: 25,
        collapsible: false,
        collapsed: false,
        header: false,
        margins: '0 0 0 0'
	}]
});
