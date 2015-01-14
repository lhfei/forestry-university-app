/**
 * Menu Tree data loader.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 4, 2014
 */
Ext.define('Ifeng.store.MenuStore', {
	extend: 'Ext.data.TreeStore',
	alias: 'widget.menuStore',
	autoLoad: true,
	
	model: 'Ifeng.model.TreeModel',
	
	proxy: {
		type: 'ajax',
        url: '../resources/annex/tree-data-student.json',
        reader: {
        	type: 'json',
        	root: ''
        }
	}
	
	
});