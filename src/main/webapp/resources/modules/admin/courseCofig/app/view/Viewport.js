/**
 * New node file
 */
Ext.define('courseCofig.view.Viewport', {
    extend: 'Ext.container.Viewport',

    layout: 'border',
    items: [{
    	id: 'hwGrid',
    	itemId: '_hwGrid',
        region: 'center',
        xtype: 'hwGrid',
        split:true
    },{
    	id: 'configForm',
    	itemId: '_configForm',
        region: 'north',
        xtype: 'configForm',
        split:true
    }/*,{
    	id: 'hwGrid',
    	itemId: '_hwGrid',
    	region: 'east',
    	xtype: 'hwGrid',
    	split: true
    }*/]
});
