/**
 * Menu Tree Panel controller.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 4, 2014
 */
Ext.define('Ifeng.controller.MenuController', {
	extend: 'Ext.app.Controller',
	alias: 'widget.menuController',
	
	requires: [],	// import dependences
	
	models: ['TreeModel'],
	stores: ['MenuStore'],
	views: ['MenuView', 'MainPanel'],
	
	refs: [{
		ref: 'menuView', selector: 'menuView'
	},{
		ref: 'mainPanel', selector: 'mainPanel'
	}],
	
	init: function(){
		this.control({
			'viewport': {
				afterrender: function(){
					//Ext.Msg.alert('create completed.');
				}
			},
			'viewport > #_west': { // menu item onclick event listener
				itemclick: this.onMenuItemClick
			} 
		});
	},
	
	onLaunch: function() {
		
	},
	
	
	/******************************************************************
	 * 
	 * Event delegate handler 
	 * 
	 * ***************************************************************/
	onMenuItemClick: function(view, record) {
		var me = this,
			tabPanel,
			node,
			tabId;
			
		if(record && !record.data){ return false;}
		
		node = record.data;
		tabId = node.id +'_panel';
		
		tabPanel = this.getMainPanel();
		var tab = tabPanel.getComponent(tabId);
		
		if (node.leaf && !tab) {
			
            tab = tabPanel.add(Ext.create('Ext.panel.Panel', {
                itemId: tabId,
                title: node.text,
                id: node.id,
				//title: node.text,
				iconCls: 'commTab',
				autoScroll: true,
				closable: true,
				//height: 400,
				autoHeight: true,
				layout: 'fit',
				autoLoad: {
    				url: '../resources/annex/tabFrame.jsp?url=' + node.id,
    				method : "post",
    				discardUrl: false,
    				nocache: true,
    				text: 'Loading ...'
				}
            }));
        }
		
		tabPanel.setActiveTab(tab);
	}
	
	
	
});