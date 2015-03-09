Ext.Loader.setConfig({
    enabled: true
});
Ext.Loader.setPath('Ext.ux', '../resources/ext4/ux');

Ext.require([
    //'Ext.form.*',
    //'Ext.layout.container.Column',
    //'Ext.tab.Panel'
    '*',
    'Ext.ux.DataTip'
]);

	var headPanel = new Ext.form.FormPanel({
		//baseCls: 'x-plain',
		id: 'login-head-logo',
		region: 'north',
		contentEl: 'headTitle'
	});
	var logoPanel = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			id: 'login-logo',
			height: 108,
			region: 'center'
	});
	var formPanel = new Ext.form.FormPanel({
		id: 'login-form',
		region: 'south',
		baseCls: 'x-plain',
		baseParams: {
			module: 'login'
		},
		style: {
			padding: '0 0 0 0'
		},
		defaults: {width: 280,allowBlank: false},
		defaultType: 'textfield',
		frame: true,
		plain: true,
		height: 120,
		items: [{
			id: 'userId',
	 		fieldLabel: '用户名',
	 		name: 'user.userId',
	 		value: ''
	 		
			},{
			id: 'passWord',
			fieldLabel: '密码',
	 		inputType: 'password',
	 		name: 'user.passWord',
	 		value: ''
		}],
		keys: {
			key: Ext.EventObject.ENTER,
			fn: function (btn,event){
				Logon();
			},
			scope: this
		},
		labelWidth:120
		
	});
	function Logon(){
		if(formPanel.getForm().isValid()){
			var userId = Ext.getCmp('userId').getValue();
			var passWord = Ext.getCmp('passWord').getValue();
			//Ext.Msg.alert(name,pword);
			Ext.Ajax.request({
				url: 'login.do',
				waitMsg: 'Loading ...',
				method: 'post',
				headers: { 'Content-Type': 'application/json' },
				success: function (response){
					var result = Ext.decode(response.responseText); 
					if(result.success && result.message == '0'){
						document.location.href = 'main.do';
					}else if(!result.success)
						Ext.MessageBox.show({
							title: 'System Message',
							msg: result.message
					});
				},
				failure: function(){
					Ext.MessageBox.show({
						title: 'System Message',
						msg: 'The server engine is busy now, please try a later.'
					});
				},
				params: {},
				jsonData: {
					userId: userId,
					passWord: passWord
				}/*,
				scope: this*/
			});
		}
	}	
	var win = new Ext.Window({
		id: 'login-win',
		title : '',
		labelWidth : 75,
		 	frame : true,
		 	plain: false,
		 	width : 600,
		 	height: 420,
		 	bodyStyle: 'padding:5px 5px 0',
			layout: 'border', //[column]
			//renderTo: 'myForm',
			buttonAlign: 'center',
			waitMsgTarget: true,
		closable: false,
	 	draggable: false,
	 	resizable: false,
	 	
	 	items: [
	 	    headPanel,
			logoPanel,
			formPanel
		],
		keys: {
			key: Ext.EventObject.ENTER,
			fn: function (btn,event){
				Logon();
			},
			scope: this
		},
		buttons : [{	
			text : '登录',
			iconCls: 'logon',
			handler: Logon
	    },{
	       	text : '重置',
	       	iconCls: 'cancel',
	       	handler: function(){
	       		formPanel.getForm().reset();
	       	}
	    }]
	});
	
Ext.onReady(function() {
    Ext.QuickTips.init();
    
    win.show();

});
