/**
 * Menu data item model.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 4, 2014
 */
Ext.define('Ifeng.model.TreeModel',{
	extend: 'Ext.data.Tree',
	alias: 'widget.treeModel',
	fields: ['id', 'text', 'expanded', 'children', 'iconCls', 'href', 'leaf', 'url']
	
});