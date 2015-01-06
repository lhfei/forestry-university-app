Ext.Loader.setConfig({
    enabled: true
});
Ext.Loader.setPath('Ext.ux', 'resources/ext4/ux');

Ext.require([
    //'Ext.form.*',
    //'Ext.layout.container.Column',
    //'Ext.tab.Panel'
    '*',
    'Ext.ux.DataTip'
]);

	var logoPanel = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			id: 'login-logo',
			region: 'center'
	});
	var formPanel = new Ext.form.FormPanel({
		id: 'login-form',
		baseCls: 'x-plain',
		baseParams: {
			module: 'login'
		},
		style: {
			padding: '0 0 0 20'
		},
		defaults: {width: 280,allowBlank: false},
		defaultType: 'textfield',
		frame: true,
		plain: true,
		height: 60,
		items: [{
			id: 'userId',
	 		fieldLabel: '用户名',
	 		name: 'user.userId',
	 		value: 'student'
	 		
			},{
			id: 'passWord',
				fieldLabel: '密码',
	 		inputType: 'password',
	 		name: 'user.passWord',
	 		value: '123456'
		}],
	keys: {
		key: Ext.EventObject.ENTER,
		fn: function (btn,event){
			Logon();
		},
		scope: this
	},
		labelWidth:120,
		region: 'south'
	});
	function Logon(){
		if(formPanel.getForm().isValid()){
			var userId = Ext.getCmp('userId').getValue();
			var passWord = Ext.getCmp('passWord').getValue();
			//Ext.Msg.alert(name,pword);
			Ext.Ajax.request({
				url: 'system/login.do',
				waitMsg: 'Loading ...',
				method: 'post',
				headers: { 'Content-Type': 'application/json' },
				success: function (response){
					var result = Ext.decode(response.responseText); 
					if(result.success && result.message == '0'){
						document.location.href = 'system/main.do';
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
       	handler: function(){Ext.MessageBox.show("System info.");}
    }]
});
Ext.onReady(function() {
    Ext.QuickTips.init();

    var bd = Ext.getBody();

    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';
    
    var userForm = Ext.widget({
        xtype: 'formpanel',
        id: 'tabForm',
        width: 350,
        border: false,
        bodyBorder: false,
        buttonAlign: 'center',
        region: 'south',
        fieldDefaults: {
            labelWidth: 75,
            msgTarget: 'side'
        },
        items: {
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{
                bodyPadding: 10,
                layout: 'anchor'
            },

            items:[{
                title:'用户登录',
                defaultType: 'textfield',
                defaults: {
                    anchor: '100%'
                },
                items: [{
                    fieldLabel: '帐号',
                    name: 'userId',
                    afterLabelTextTpl: required,
                    allowBlank: false,
                    value: 'Jim'
                },{
                    fieldLabel: '密码',
                    afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'password',
                    vtype: 'password',
                    value: 'jim'
                },{
                    fieldLabel: '组织',
                    name: 'company',
                    value: 'www.ifeng.com'
                }/*, {
                    fieldLabel: 'Email',
                    afterLabelTextTpl: required,
                    allowBlank: false,
                    name: 'email',
                    vtype:'email'
                }*/]
            },{
                title: '注册',
                defaultType: 'textfield',
                defaults: {
                    anchor: '100%'
                },
                items: [{
                    fieldLabel: '邮箱',
                    name: 'home',
                    value: '(888) 555-1212',
                    vtype: 'email'
                },{
                	fieldLabel: '帐号',
                    name: 'name',
                    afterLabelTextTpl: required,
                    allowBlank: false,
                    emptyText: '登录帐号'
                },{
                    fieldLabel: '电话',
                    name: 'mobile'
                },{
                    fieldLabel: 'Fax',
                    name: 'fax'
                }]
            }]
        },

        buttons: [{
            text: '登录',
            handler: function() {
                var form = this.up('form').getForm();
                //form.isValid();
                
                form.submit({
                    url: '/login.do',
                    waitMsg: 'Loading...',
                    method: 'POST',
                    success: function (form, action) {
                       console.log(action.response.responseText);                                   
                    }
                });
            }
        },{
            text: '重置',
            handler: function() {
                this.up('form').getForm().reset();
            }
        }]
    });

    //userForm.render(document.body);
    
    win.show();

});
