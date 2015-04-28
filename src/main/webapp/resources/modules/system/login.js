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

	var headPanel = new Ext.FormPanel({
		//baseCls: 'x-plain',
		id: 'login-head-logo',
		region: 'north',
		contentEl: 'headTitle'
	});
	var logoPanel = new Ext.FormPanel({
			baseCls: 'x-plain',
			id: 'login-logo',
			height: 108,
			region: 'center'
	});
	var formPanel = new Ext.FormPanel({
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
    
    var _browser = BrowserUtil()
    
    if(_browser.version < 6){
    	
    	var r = confirm('您当前正在使用的浏览器为: ' +_browser.name
    			+ ', 其内核版本低于6!\r\n将无法访问改系统!请升级您的浏览器或重新设置浏览器内核版本.'
    			+ '\r\n 系统为您准备了一个简单设置浏览器内线的帮助页，请您确认是否前往查看?');
    	
    	
    	if(r == true) {
    		window.open('../help.html', "_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no")
    	}
    	
    	return false;
    }
    
    
    win.show();

});
