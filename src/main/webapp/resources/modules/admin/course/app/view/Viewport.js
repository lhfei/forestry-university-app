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
    }/*,{
    	id: 'hwSearchForm',
    	itemId: '_hwSearchForm',
        region: 'north',
        xtype: 'hwSearchForm',
        split:true
    }*/]
});
