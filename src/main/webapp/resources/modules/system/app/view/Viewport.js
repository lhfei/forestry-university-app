Ext.define('Ifeng.view.Viewport', {
    extend: 'Ext.container.Viewport',

    /*requires: [
        'FV.view.Viewer',
        'FV.view.feed.List',
        'Ext.layout.container.Border'
    ],*/

    layout: 'border',
    items: [{
    	id: 'centerPanel',
    	itemId: '_cPanel',
        region: 'center',
        //xtype: 'viewer'
        xtype: 'mainPanel',
        split:true
    }, {
    	itemId: '_west',
        region: 'west',
        xtype: 'menuView',
        width: 225,
        minSize: 100,
        maxSize: 500,
        //xtype: 'feedlist'
        split:true
    }]
});
