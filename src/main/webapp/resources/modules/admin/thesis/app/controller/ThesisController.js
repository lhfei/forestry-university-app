/**
 * 
 */
Ext.require([
    'Ext.form.field.File',
    'Ext.form.field.Number',
    'Ext.form.Panel',
    'Ext.window.MessageBox'/*,
    'Ext.ux.GridComboBoxList',
    'Ext.ux.GridComboBox'*/
]);


var combo_thesisType = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 'combo_thesisType',
    fieldLabel: '论文类型',
    padding: '5 5 5 5',
    labelWidth:  60,
    forceSelection: true
});

var combo_origin = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 'combo_origin',
    fieldLabel: '论文来源',
    padding: '5 5 5 5',
    labelWidth:  60,
    forceSelection: true
});

var combo_teacher = Ext.create('Ext.form.field.ComboBox', {
    typeAhead: true,
    transform: 'combo_teacher',
    fieldLabel: '指导老师',
    padding: '5 5 5 5',
    labelWidth:  60,
    forceSelection: true
});

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
	    bodyPadding: '5 25 5 25',
	    border: false,
	    defaults: {
	        anchor: '100%',
	        allowBlank: false,
	        msgTarget: 'side',
	        labelWidth: 65
	    },
	
	    items: [{
	        xtype: 'textfield',
	        fieldLabel: '论文标题',
	        emptyText: '默认为附件名称',
	        name: 'thesisTitle',
	        readOnly: false
	    },{
	        xtype: 'textfield',
	        fieldLabel: '英文标题',
	        name: 'thesisEnTitle',
	        readOnly: false
	    },
	    	combo_thesisType,
	    	
	    	combo_origin,
	    	
	    	combo_teacher,
	    {
	        xtype: 'textfield',
	        fieldLabel: '学生编号',
	        name: 'studentId',
	        hidden: true,
	        readOnly: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '学生姓名',
	        name: 'studentName',
	        hidden: true,
	        readOnly: true
	    },{// 附件1
	        xtype: 'filefield',
	        id: 'form-file',
	        emptyText: '请选择作业附件',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{// 附件2
	        xtype: 'filefield',
	        id: 'form-file2',
	        emptyText: '若需上传多个附件，请继续请选择上传',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        allowBlank: true,
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{// 附件3
	        xtype: 'filefield',
	        id: 'form-file3',
	        emptyText: '若需上传多个附件，请继续请选择上传',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        allowBlank: true,
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{// 附件4
	        xtype: 'filefield',
	        id: 'form-file4',
	        emptyText: '若需上传多个附件，请继续请选择上传',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        allowBlank: true,
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{// 附件5
	        xtype: 'filefield',
	        id: 'form-file5',
	        emptyText: '若需上传多个附件，请继续请选择上传',
	        fieldLabel: '作业附件',
	        name: 'files',
	        buttonText: '',
	        allowBlank: true,
	        buttonConfig: {
	            iconCls: 'upload-icon'
	        }
	    },{
	        xtype: 'textfield',
	        fieldLabel: '论文主键',
	        name: 'baseId',
	        hidden: true
	    },{
	        xtype: 'textfield',
	        fieldLabel: '学年',
	        name: 'academicYear',
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
	                    url: '../admin/updateThesis.do',
	                    waitMsg: '正在上传...',
	                    success: function(fp, o) {
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
	
Ext.define('thesis.controller.ThesisController', {
	extend: 'Ext.app.Controller',
	
	stores: ['ThesisStore'],
	models: ['ThesisModel'],
	views: ['ThesisGrid', 
	        'ThesisSearchForm',
	        'UploadWin'],
	
	refs: [
        {ref: 'thesisGrid', selector: 'thesisGrid'},
        {ref: 'thesisSearchForm', selector: 'thesisSearchForm'},
        {ref: 'thesisStore', selector: 'thesisStore'},
        {ref: 'uploadWin', selector: 'uploadWin'}
	],
	
	init: function() {
		var me = this;
		
		// attach 'beforeload' event into HWStore 
		Ext.getStore('ThesisStore').addListener('beforeload', this.doBefore, this);
		
		this.control({
			'viewport > thesisGrid': {
				itemdblclick: this.editTask
			},
			
			'thesisSearchForm button[action=doSearch]': {
				click: this.doSearch
			},
			
			'thesisSearchForm button[action=doReset]': {
				click: this.doReset
			},
			
			'thesisGrid actioncolumn': {
				click: this.completeTask
			},
			
		});
	},
	
	editTask: function(grid,record) {
		//Ext.MessageBox.show(record.name, record.data.name);
	},
	
	doBefore: function(store, operation, eOpts){
		var searchForm,
			thesisGrid,
			store,
			searchModel;	//search form items.
	
		searchForm = this.getThesisSearchForm();
	 	thesisGrid = this.getThesisGrid();
		store = this.getThesisStoreStore();
		
		searchModel = searchForm.getValues();
		
		store.getProxy().setExtraParam('academicYear', searchModel.academicYear);
		store.getProxy().setExtraParam('className', searchModel.className);		
		store.getProxy().setExtraParam('thesisType', searchModel.thesisType);
		store.getProxy().setExtraParam('origin', searchModel.origin);
		store.getProxy().setExtraParam('degree', searchModel.degree);
		store.getProxy().setExtraParam('status', ''+searchModel.status);
		store.getProxy().setExtraParam('studentId', ''+searchModel.studentId);
		store.getProxy().setExtraParam('studentName', ''+searchModel.studentName);
		
	},

	doReset: function() {
		var form = this.getThesisSearchForm().getForm();
		
		form.reset();
	},
	
	doSearch: function() {
		this.getThesisStoreStore().loadPage(1);
	},
	
	completeTask: function(view,cell,row,col,e){
		var tabPanel = parent.Ext.getCmp('centerPanel')//parent.Ifeng.view.MainPanel;
        var m = e.getTarget().className.match(/\bicon-(\w+)\b/);
        var record = this.getThesisGrid().getStore().getAt(row).data;
        var mm =  this.getThesisStoreStore();
        
        var approveButtonIsValid = (record.status != '1');
        
        var archiveTotal = 0,	// 附件总数
        	archives = [],		// 附件路径列表
        	archiveHTML = '';	// 附件下载链接
        
        if(m){
            switch(m[1]){
                case 'upload':
                	if(record.status != '0' && record.status != '3') {
                		Ext.MessageBox.alert('Status', '当前已完成上传, 请不要重复上传!.');
                		return false;
                	}
                	
                	if(!uploadWin){
                		uploadWin = Ext.create('Ext.window.Window', {
                			title: '<b>上传论文附件</b>',
                			header: {
                				titlePosition: 2,
                				titleAlign: 'center'
                			},
                			maximizable: true,
                			modal: true,
                			closeAction: 'hide',
                			width: 650,
                			height: 400,
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
                		studentId: record.studentId,
                		studentName: record.studentName,
                		className: record.className,
                		academicYear: record.academicYear
                	});
                	
                    break;
                	
                case 'download':
                	if(record.status == 0){
                		Ext.MessageBox.alert('Status', '作业尚未上传,\r\n请先上传附件!');
                		return false;
                	};
                	
                	$.ajax({
                		async: false,
                		url: '../admin/preload.do?thesisBaseId=' +record.baseId+ '&studentId=' +record.studentId,
                		method: 'get',
                		success: function (response){
        					if(response.success){
        						archiveTotal = response.total;
        						archives = response.data;
        						
        						archiveHTML += '<h1>论文标题: ['+record.thesisTitle+'] -- 附件总张数: [' +archiveTotal+ '张]</h1>';
        						
        						for(var i=0; i<archiveTotal; i++) {
        							archiveHTML += '<h3>附件['+i+']: '+archives[i].archiveName+' </h3>&nbsp; <a href="../admin/downloadArchive.do?id='+archives[i].id+ '"> 下载 </a>';
        						}
        					}
        				}
                	});                	
                	
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
                		width: 980,
                		height: 420,
                		tools: [{type: 'pin'}],
                		layout: {
                			type: 'border',
                			margin: 30
                		},
                		defaults: {autoScroll: true},
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
                			defaults: {autoScroll: true},
                			items: [{
                				rtl: false,
                				title: record.name,
                				html: archiveHTML
                			}]
                		}],
                		
                		buttons: [{
                			text: '下载',
                			iconCls: 'icon-download',
                			scope: this,
                			handler: function(){
                				for(var i = 0; i < archiveTotal; i++) {
        							//document.location.href = '../teacher/downloadImg.do?id='+archives[i].id;
                					window.open('../admin/downloadArchive.do?id='+archives[i].id, "_blank", "toolbar=no, scrollbars=no, resizable=no");
        						}
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