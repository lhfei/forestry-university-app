/**
 * 
 */
Ext.define('Ifeng.view.HeadPanel', {
	extend: 'Ext.form.Panel',
	xtype: 'headPanel',
	minHeight: 50,
    maxHeight: 50,
    autoScroll: true,
    header: false,
    collapsible: false,
    collapsed: false,
    baseCls: 'head-back',
    bodyStyle: {
    	//background: 'blue'
    },
	
    layout:'column',
    
    items:[{
    	columnWidth: 0.9,
    	style: 'margin-top: 5px',
    	baseCls: 'head-back',
    	contentEl: 'header_title_img'
    },/*{
    	columnWidth: 0.0,
    	baseCls: 'head-back',
    	style: 'margin-top: 15px',
    	contentEl: 'header_user_info'
    },*/{
    	//columnWidth: 0.1,
    	width: 45,
    	style: 'margin-top: 15px',
    	textAlign: 'right',
    	baseCls: 'head-back',
    	bodyStyle: 'padding-left: 40px',
    	items:[{
    		xtype: 'button',
    		text: '安全退出',
    		iconCls: 'icon-logout',
    		action: 'logout'
    			
    	}]
    }]
});