/**
 * 
 */
Ext.require([
    'Ext.form.field.File',
    'Ext.form.field.Number',
    'Ext.form.Panel',
    'Ext.window.MessageBox'
]);

var tpl,
	downloadWin,
	uploadForm,		
	approveForm,
	uploadWin;

	tpl = new Ext.XTemplate(
	    'File processed on the server.<br />',
	    'Name: {fileName}<br />',
	    'Size: {fileSize:fileSize}'
	);
	
	// upload form panel
	uploadForm = Ext.create('Ext.form.Panel', {
	    autoWidth: true,
	    autoHeight: true,
	    frame: true,
	    //title: 'File Upload Form',
	    bodyPadding: '20 10 0',
	    border: false,
	    defaults: {
	        anchor: '100%',
	        allowBlank: false,
	        msgTarget: 'side',
	        labelWidth: 65
	    },
	
	    items: [{
	        xtype: 'textfield',
	        fieldLabel: '作业名称',
	        name: 'name',
	        readOnly: true
	    },{
	        xtype: 'filefield',
	        id: 'form-file',
	        emptyText: '请选择作业附件',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{
	        xtype: 'textfield',
	        fieldLabel: '作业主键',
	        name: 'baseId',
	        hidden: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '学年',
	        name: 'academicYear',
	        hidden: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '学期',
	        name: 'semester',
	        hidden: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '课程名称',
	        name: 'courseName',
	        hidden: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '班级名称',
	        name: 'className',
	        hidden: true
	    }],

	    buttons: [{
	        text: '上传',
	        iconCls: 'icon-upload',
	        handler: function(){
	        	var me = this;
	            var form = me.up('form').getForm();
	            if(form.isValid()){
	                form.submit({
	                    url: 'updateHomework.do',
	                    waitMsg: '正在上传...',
	                    success: function(fp, o) {
	                        //msg('Success', tpl.apply(o.result));
	                    	var response = o.result
	                    	if(response.success){
	                    		Ext.MessageBox.alert('Status', '上传成功!');
	                    		uploadWin.hide();
	                    	}else
	                    		Ext.MessageBox.alert('Status', '上传失败!');
	                    }
	                });
	            }
	        }
	    },{
	        text: '取消',
	        iconCls: 'icon-cancel',
	        handler: function() {
	            this.up('form').getForm().reset();
	            uploadWin.hide();
	        }
	    }]
	}); 

	approveForm = Ext.create('Ext.form.Panel', {
	    autoWidth: true,
	    autoHeight: true,
	    frame: false,
	    items:[{
		    xtype: 'fieldset',
		    flex: 1,
		    title: '作业审批',
		    defaultType: 'radio', // each item will be a radio button
		    layout: 'anchor',
		    defaults: {
		        anchor: '100%',
		        hideEmptyLabel: false
		    },
		    items: [{
		        checked: false,
		        fieldLabel: '审批结果',
		        labelWidth: 65,
		        boxLabel: '通过',
		        name: 'status',
		        inputValue: '1'
		    }, {
		        boxLabel: '未通过',
		        labelWidth: 65,
		        name: 'status',
		        inputValue: '0'
		    },{
		    	xtype: 'textarea',
	            style: 'margin:0',
	            hideLabel: false,
	            labelWidth: 65,
	            fieldLabel: '备注',
	            emptyText: '审批意见描述, 可以不填...',
	            name: 'approveDesc',
		    }]
		}]	

	}); 
	
Ext.define('hwork.controller.HWController', {
	extend: 'Ext.app.Controller',
	
	stores: ['HWStore'],
	models: ['HWModel'],
	views: ['HWGrid', 
	        'HWSearchForm',
	        'UploadWin'],
	
	refs: [
        {ref: 'hwGrid', selector: 'hwGrid'},
        {ref: 'hwSearchForm', selector: 'hwSearchForm'},
        {ref: 'hwStore', selector: 'hwStore'},
        {ref: 'uploadWin', selector: 'uploadWin'}
	],
	
	init: function() {
		var me = this;
		
		// attach 'beforeload' event into HWStore 
		Ext.getStore('HWStore').addListener('beforeload', this.doBefore, this);
		
		this.control({
			'viewport > hwGrid': {
				itemdblclick: this.editTask
			},
			
			'hwSearchForm button[action=doSearch]': {
				click: this.doSearch
			},
			
			'hwGrid actioncolumn': {
				click: this.completeTask
			},
			
		});
	},
	
	editTask: function(grid,record) {
		//Ext.MessageBox.show(record.name, record.data.name);
	},
	
	doBefore: function(store, operation, eOpts){
		var searchForm,
			hwGrid,
			store,
			searchModel;	//search form items.
	
		searchForm = this.getHwSearchForm();
	 	hwGrid = this.getHwGrid();
		store = this.getHWStoreStore();
		
		searchModel = searchForm.getValues();
		
		store.getProxy().setExtraParam('academicYear', searchModel.academicYear);
		store.getProxy().setExtraParam('semester', searchModel.semester);
		store.getProxy().setExtraParam('courseName', searchModel.courseName);
		store.getProxy().setExtraParam('name', ''+searchModel.name);
	},
	
	doSearch: function() {
		this.getHWStoreStore().loadPage(1);
	},
	
	completeTask: function(view,cell,row,col,e){
		var tabPanel = parent.Ext.getCmp('centerPanel')//parent.Ifeng.view.MainPanel;
        var m = e.getTarget().className.match(/\bicon-(\w+)\b/);
        var record = this.getHwGrid().getStore().getAt(row).data;
        var mm =  this.getHWStoreStore();
        
        var approveButtonIsValid = (record.status != '1');
        
        if(m){
            switch(m[1]){
                case 'upload':
                	if(record.status != '0' && record.status != '3') {
                		Ext.MessageBox.alert('Status', '当前已完成上传, 请不要重复上传!.');
                		return false;
                	}
                	
                	if(!uploadWin){
                		uploadWin = Ext.create('Ext.window.Window', {
                			title: '<b>上传作业附件作业</b>',
                			header: {
                				titlePosition: 2,
                				titleAlign: 'center'
                			},
                			maximizable: true,
                			modal: true,
                			closeAction: 'hide',
                			width: 400,
                			height: 250,
                			tools: [{type: 'pin'}],
                			layout: {
                				type: 'fit',
                				padding: 5
                			},
                			items: [uploadForm]
                		});
                	}
                	
                	uploadWin.show();    
                	
                	var form = uploadForm.getForm();
                	form.setValues({ 
                		baseId: record.baseId,
                		name: record.name,
                		academicYear: record.academicYear,
                		semester: record.semester,
                		courseName: record.courseName,
                		className: record.className
                	});
                	
                    break;
                	
                case 'download':
                	if(record.homeworkArahiveId == null){
                		Ext.MessageBox.alert('Status', '作业尚未上传,\r\n请先上传附件!');
                		return false;
                	};  
                	
                	downloadWin = Ext.create('Ext.window.Window', {
                		title: '<em>作业预览</em>',
                		header: {
                			titlePosition: 2,
                			titleAlign: 'center'
                		},
                		maximizable: true,
                		closable: true,
                		modal: true,
                		resizable: true,
                		stateId: 'previewState',
                		stateful: true,
                		autoScroll : true,
                		closeAction: 'hide',
                		width: 800,
                		minWidth: 350,
                		height: 450,
                		tools: [{type: 'pin'}],
                		layout: {
                			type: 'border',
                			margin: 30
                		},
                		items: [{
                			region: 'east',
                			title: 'Navigation',
                			width: 200,
                			split: true,
                			collapsible: true,
                			collapsed: false,
                			floatable: true,
                			bodyStyle: {
                				background: '#ffc',
                				padding: '30px 0px 0px 0px'
                			},
                			items:[approveForm]
                		
                		}, {
                			region: 'center',
                			xtype: 'tabpanel',
                			items: [{
                				rtl: false,
                				title: record.name,
                				html: '<img src="../teacher/downloadImg.do?id='+record.homeworkArahiveId+ '" style="width:100%, height: 100%" />'
                			}]
                		}],
                		
                		buttons: [{
                			text: '下载',
                			iconCls: 'icon-download',
                			scope: this,
                			handler: function(){
                				document.location.href = '../teacher/downloadImg.do?id='+record.homeworkArahiveId;
                			}
                		},{
                			text: '审批',
                			iconCls: 'icon-download',
                			scope: this,
                			hidden: approveButtonIsValid,
                			handler: function(){
                				if(record.status != '1'){
                					Ext.MessageBox.alert('Status', '当前已完成审批, 请不要重复审批!.');
                					return false;
                				}
                				var form = approveForm.getForm();
                				var vals = form.getValues();
                				
                				Ext.Ajax.request({
		            				url: '../teacher/approveHomework.do?id=' +record.homeworkArahiveId+ '&status=' +vals.status+ '&desc=' +vals.approveDesc ,
		            				waitMsg: 'Loading ...',
		            				method: 'get',
		            				success: function (response, opts){
		            					var result = Ext.decode(response.responseText); 
		            					if(result.success){
		            						Ext.MessageBox.alert({
		            							title: 'System Message',
		            							msg: result.message
		            						});
		            						
		            						downloadWin.hide();
		            					}
		            				},
		            				failure: function(response, opts){
		            					var result = Ext.decode(response.responseText); 
		            					Ext.MessageBox.alert({
		            						title: 'System Message',
		            						msg: result.message
		            					});
		            					
		            					downloadWin.hide();
		            				}
		            			});
                			}
                		},{
                			text: '取消',
                			iconCls: 'icon-cancel',
                			scope: this,
                			handler: function() {
                				downloadWin.hide();
                			}
                		}]
                	});
                	
                	var form = approveForm.getForm();
                	form.reset();
                	var status = record.status;
                	if(status == '2' || status == '3'){// approve had done.
                		if(status == '2'){
                    		status = '1';
                    	}else {
                    		status = '0'
                    	}
                    		form.setValues({
                    			status: status,
                    			approveDesc: record.extend
                    		});
                	}
                	
                    downloadWin.show();
                    
                    break;    
            }
        }
    }
	
});