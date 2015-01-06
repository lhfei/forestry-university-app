Ext.define('hwork.view.Viewport', {
    extend: 'Ext.container.Viewport',

    layout: 'border',
    items: [{
    	id: 'hwGrid',
    	itemId: '_hwGrid',
        region: 'center',
        xtype: 'hwGrid',
        split:true
    },{
    	id: 'hwSearchForm',
    	itemId: '_hwSearchForm',
        region: 'north',
        xtype: 'hwSearchForm',
        split:true
    }]
});
