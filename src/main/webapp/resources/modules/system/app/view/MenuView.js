/**
 * Menu Tree Panel view.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 4, 2014
 */
Ext.define('Ifeng.view.MenuView',{
	extend: 'Ext.tree.TreePanel',
	alias: 'widget.menuView',
	store: 'MenuStore',
	animate: true,
	rootVisible: false,   //hidden root node.
	autoScroll: true, 
	lines : true,
	collapsible: true,
	draggable: false,
	singleExpand: true,
	split: true
	
})