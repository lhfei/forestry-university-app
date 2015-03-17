/**
 * New node file
 */
Ext.define('course.view.Viewport', {
    extend: 'Ext.container.Viewport',

    layout: 'border',
    items: [{
    	id: 'courseGrid',
    	itemId: '_courseGrid',
        region: 'center',
        xtype: 'courseGrid',
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
