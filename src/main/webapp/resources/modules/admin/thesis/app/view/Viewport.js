Ext.define('thesis.view.Viewport', {
    extend: 'Ext.container.Viewport',

    layout: 'border',
    items: [{
    	id: 'thesisGrid',
    	itemId: '_thesisGrid',
        region: 'center',
        xtype: 'thesisGrid',
        split:true
    },{
    	id: 'thesisSearchForm',
    	itemId: '_thesisSearchForm',
        region: 'north',
        xtype: 'thesisSearchForm',
        split:true
    }]
});
