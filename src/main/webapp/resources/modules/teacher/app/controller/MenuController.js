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
	views: ['MenuView', 'MainPanel', 'HeadPanel'],
	
	refs: [{
		ref: 'menuView', selector: 'menuView'
	},{
		ref: 'mainPanel', selector: 'mainPanel'
	},{
		ref: 'headPanel', selector: 'headPanel'
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
			}, 
			
			'headPanel button[action=logout]': {
				click: this.logout
			}
		});
	},
	
	onLaunch: function() {
		var userName = '欢迎您: ' +document.getElementById('_userName').value;
		var menu = this.getMenuView().setTitle(userName);		
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
	},
	
	
	logout: function(){
		Ext.MessageBox.show({
			icon: 'warning',
            title: '系统提示',
            msg: '您确认要退出当前登录吗?',
            buttons: Ext.MessageBox.YESNO,
            buttonText:{ 
                yes: "是", 
                no: "否" 
            },
            fn: function(btn){
            	if(btn == 'yes'){
            		document.location.href = "logout.do";
            	}
            }
        });
	}
	
});