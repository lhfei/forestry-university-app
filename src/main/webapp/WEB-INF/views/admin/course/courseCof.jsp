<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/tabs.jsp"%>
<%@ include file="../../commons/easyui.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${globalTitle }</title>
	
	<link rel="stylesheet" type="text/css" href="${basePath }/resources/css/layout-browser.css">
	<script type="text/javascript" src="${extRoot}/locale/ext-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="${basePath}/resources/modules/admin/courseCofig/GridComboBoxList.js"></script>
	<script type="text/javascript" src="${basePath}/resources/modules/admin/courseCofig/GridComboBox.js"></script>
	<script type="text/javascript" src="${basePath}/resources/modules/admin/courseCofig/build.js"></script>
	
	<script type="text/javascript">
	    Ext.require(['*']);
	    var required = '<span style="color:red;font-weight:bold" data-qtip="Required">*</span>';

	    var configForm;

	    var homeworkStore = new Ext.data.ArrayStore({
	    	pageSize: 999999999,
	    	autoLoad: false,
	    	fields:['baseId'           ,
	    			'name'             ,
	    			'academicYear'     ,
	    			'semester'         ,
	    			'studentBaseId'    ,
	    			'homeworkArahiveId',
	    			'studentId'        ,
	    			'studentName'      ,
	    			'courseName'       ,
	    			'className'        ,
	    			'status'           ,
	    			'archiveName'      ,
	    			'archivePath'      ,
	    			'teacherName'      ,
	    			'majorName'        ,
	    			'pageNum'          ,
	    			'pageSize'         ,
	    			'createTime'       ,
	    			'modifyTime'       ,
	    			'operationTime'    ,
	    			'actionType'       ,
	    			'teacherId'        ,
	    			'courseCode'       ,
	    			'majorCode'        ,
	    			'description'      ,
	    			'extend'           ,
	    			'extend1'
	    	],
	    	proxy: {
	    		type: 'ajax',
	    		url: 'getLatestHomework.do?start=0&limit=999999999&page=0',
	    		reader: {
	    			type: 'json',
	    			root: 'rows',
	    			totalProperty: 'total'
	    		}
	    	}	    	
	    });
	    
		// Course data store
	    var courseStore = new Ext.data.ArrayStore({
	    	pageSize: 999999999,
	    	autoLoad: false,
	    	fields: [{
	    		name: 'baseId'
	    	}, {
	    		name: 'courseName'
	    	}, {
	    		name: 'courseCode'
	    	}],
	    	proxy: {
	    		type: 'ajax',
	    		url: 'readCourse.do?start=0&limit=999999999&page=0',
	    		reader: {
	    			type: 'json',
	    			root: 'rows',
	    			totalProperty: 'total'
	    		}
	    	}
	    });
	    
		// Class data store
	    var classStore = new Ext.data.ArrayStore({
	    	pageSize: 999999999,
	    	autoLoad: false,
	    	fields: [{
	    		name: 'id'
	    	}, {
	    		name: 'className'
	    	}],
	    	proxy: {
	    		type: 'ajax',
	    		url: 'readClass.do?start=0&limit=999999999&page=0',
	    		reader: {
	    			type: 'json',
	    			root: 'rows',
	    			totalProperty: 'total'
	    		}
	    	}
	    });	
	    
	    // Teacher data store.
	    var teacherStore = new Ext.data.ArrayStore({
	    	pageSize: 999999999,
	    	autoLoad: false,
	    	fields: [
	    	    {name: 'id'}, 
	    	    {name: 'teacherName'},
	    	    {name: 'teacherId'}
	    	],
	    	proxy: {
	    		type: 'ajax',
	    		url: 'readTeacher.do?start=0&limit=999999999&page=0',
	    		reader: {
	    			type: 'json',
	    			root: 'rows',
	    			totalProperty: 'total'
	    		}
	    	}
	    });

	    // Course combobox component.
	    var courseCombobox = new Ext.form.field.GridComboBox({
	    	fieldLabel: '课程列表',
	    	multiSelect: true,
	    	afterLabelTextTpl: required,
	    	displayField: 'courseName',
	    	valueField: 'baseId',
	    	width: 1005,
	    	labelWidth: 100,
	    	labelAlign: 'left',
	    	store: courseStore,
	    	queryMode: 'remote',
	    	matchFieldWidth: false,
	    	pickerAlign: 'bl',
	    	gridCfg: {
	    		store: courseStore,
	    		selModel: new Ext.selection.CheckboxModel({
	    			checkOnly: true
	    		}),
	    		frame: true,
	    		height: 300,
	    		width: 900,
	    		columns: [
	    		    {header: '序号', xtype: 'rownumberer', width: 40, sortable: false, locked: true }, 
	    		    {text: 'ID', flex: 1, dataIndex: 'baseId', hidden: true }, 
	    			{header: '课程名称', dataIndex: 'courseName', flex: 2, align: 'center'},
	    	        {header: '课程编号', dataIndex: 'courseCode', flex: 1, align: 'center'}
	    		],
	    		
	    		bbar: Ext.create('Ext.PagingToolbar', {
	    			store: courseStore,
	    			displayInfo: true,
	    			displayMsg: '当前显示: {0} - {1} 共 {2}',
	    			emptyMsg: "无记录"
	    		})
	    	}
	    });
	    
	    // Class combobox component.
	    var classCombobox = new Ext.form.field.GridComboBox({
	    	fieldLabel: '班级列表',
	    	multiSelect: true,
	    	afterLabelTextTpl: required,
	    	displayField: 'className',
	    	valueField: 'id',
	    	width: 1005,
	    	labelWidth: 100,
	    	labelAlign: 'left',
	    	store: classStore,
	    	queryMode: 'remote',
	    	matchFieldWidth: false,
	    	pickerAlign: 'bl',
	    	gridCfg: {
	    		store: classStore,
	    		selModel: new Ext.selection.CheckboxModel({
	    			checkOnly: true
	    		}),
	    		frame: true,
	    		height: 300,
	    		width: 900,
	    		columns: [
					{header: '序号', xtype: 'rownumberer', width: 40, sortable: false, locked: true }, 
					{text: 'ID', flex: 1, dataIndex: 'id', hidden: true }, 
					{header: '班级名称', dataIndex: 'className', flex: 2, align: 'center'}       
	    		],
	    		
	    		bbar: Ext.create('Ext.PagingToolbar', {
	    			store: classStore,
	    			displayInfo: true,
	    			displayMsg: '当前显示: {0} - {1} 共 {2}',
	    			emptyMsg: "无记录"
	    		})
	    	}
	    });
	    
	    
	    // Teacher combobox component.
	    var teacherCombobox = new Ext.form.field.GridComboBox({
	    	fieldLabel: '教师列表',
	    	multiSelect: true,
	    	afterLabelTextTpl: required,
	    	displayField: 'teacherName',
	    	valueField: 'teacherId',
	        width: 1005,
	    	labelWidth: 100,
	    	labelAlign: 'left',
	    	store: teacherStore,
	    	queryMode: 'remote',
	    	matchFieldWidth: false,
	    	pickerAlign: 'bl',
	    	gridCfg: {
	    		emptyText: '--',
	    		store: teacherStore,
	    		selModel: new Ext.selection.CheckboxModel({
	    			checkOnly: true
	    		}),
	    		frame: true,
	    		width: 900,
	    		height: 250,
	    		columns: [
					{header: '序号', xtype: 'rownumberer', width: 40, sortable: false, locked: true }, 
					{text: 'ID', flex: 1, dataIndex: 'id', hidden: true }, 
					{header: '教师名称', dataIndex: 'teacherName', flex: 2, align: 'center'},
					{header: '教师编号', dataIndex: 'teacherId', flex: 2, align: 'center'}
	    		],
	    		
	    		bbar: Ext.create('Ext.PagingToolbar', {
	    			store: teacherStore,
	    			displayInfo: true,
	    			displayMsg: '当前显示: {0} - {1} 共 {2}',
	    			emptyMsg: "无记录"
	    		})
	    	}
	    });
	    
	    var hwForm = Ext.widget({
	        xtype: 'form',
	        id: 'fieldSetForm',
	        collapsible: true,
	        url: 'save-form.php',
	        title: '',
	        frame: false,
	        header: false,
	        bodyPadding: '5 5 0',
	        fieldDefaults: {
	            msgTarget: 'side',
	            labelWidth: 100
	        },
	        defaults: {
	            anchor: '100%'
	        },
	        
	        items: [{
	            xtype:'fieldset',
	            title: '作业基本信息',
	            collapsible: true,
	            defaultType: 'textfield',
	            layout: 'anchor',
	            defaults: {
	                anchor: '100%'
	            },
	            items :[{
	            	itemId: 'f_hwname',
	            	id: '_f_hwname',
	                fieldLabel: '作业名称',
	                name: 'name',
	                afterLabelTextTpl: required,
	                emptyText: '请输入作业名称'
	            },{
	                fieldLabel: '作业描述',
	                name: 'desc',
	                emptyText: '请输入作业的相关描述. 非必填项.'
	            }]
	        }]
	    });	 
	    

	    var homeworkGrid = Ext.create('Ext.grid.Panel', {
	        store: homeworkStore,
	        columnLines: true,
	        columns: [
                {header: '序号', xtype: 'rownumberer', width: 75, sortable: false, locked: true }, 
                {header: 'ID',  dataIndex: 'baseId',  flex: 1, align: 'center', hidden: true},
                {header: '学年', dataIndex: 'academicYear', flex: 1, align: 'center'},
                {header: '学期', dataIndex: 'semester', flex: 1, align: 'center'},
                {header: '课程名称', dataIndex: 'courseName', flex: 1, align: 'center'},
                {header: '班级名称', dataIndex: 'className', flex: 1, align: 'center'},
                {header: '教师姓名', dataIndex: 'teacherName', flex: 1, align: 'center', hidden: true},
                {header: '教师编号', dataIndex: 'teacherCode', flex: 1, align: 'center', hidden: true},
                {header: '作业名称', dataIndex: 'name', flex: 2, align: 'center'},
                {header: '作业状态', dataIndex: 'status', flex: 1, align: 'center', hidden: true},
                {header: '课程编号', dataIndex: 'courseCode', flex: 1, align: 'center', hidden: true},
                {header: '操作时间', dataIndex: 'operationTime', flex: 1, align: 'center', hidden: true},
                {header: '创建时间', dataIndex: 'createTime', flex: 1, align: 'center', hidden: false},
				{header: '最近修改时间', dataIndex: 'modifyTime', flex: 1, align: 'center', hidden: true}
			],
	              
			bbar: Ext.create('Ext.PagingToolbar', {
				store: homeworkStore,
				displayInfo: true,
				displayMsg: '当前显示: {0} - {1} 共 {2}',
				emptyMsg: "无记录"
    		})	        
	    });
	    
	    
	    /**
	     *
	     */
	    function doSave() {
	    	var teacherNames = teacherCombobox.rawValue;
	    	var confs = configForm.getForm().getValues(),
	    	    vals = hwForm.getForm().getValues(),
	    	    courseIds = courseCombobox.getSubmitValue().join(),
                classIds = classCombobox.getSubmitValue().join(),
                teacherIds = teacherCombobox.getSubmitValue().join(),
                
                academicYear = confs['academicYear'],
                semester	 = confs['semester'],
                name		 = vals['name'],
                desc  		 = vals['desc'];
	    	
			if(academicYear == '-1'){
				Ext.MessageBox.alert('Status', '学年不能为空, 请选择学年!');
				return false;
			}
			if(semester == '-1'){
				Ext.MessageBox.alert('Status', '学期不能为空, 请选择学期!');
				return false;
			}
			if(courseIds.length < 1 || courseIds == null || typeof(courseIds) == 'undefined'){
				Ext.MessageBox.alert('Status', '请选择课程名称!');
				return false;
			}
			if(classIds.length < 1 || classIds == null || typeof(classIds) == 'undefined'){
				Ext.MessageBox.alert('Status', '请选择班级名称!');
				return false;
			}
			if(teacherIds.length < 1 || teacherIds == null || typeof(teacherIds) == 'undefined'){
				Ext.MessageBox.alert('Status', '请选择授课教师!');
				return false;
			}
			if(name.length < 1 || name == null || typeof(name) == 'undefined'){
				Ext.MessageBox.alert('Status', '请输入作业名称!');
				return false;
			}
			$.ajax({
        		async: false,
        		url: '../admin/createHomework.do?academicYear='+academicYear
        				+ '&semester=' +semester+ '&name=' +name+ '&desc=' +desc+ '&courseId=' +courseIds
        				+ '&classId='+classIds+ '&teacherId=' +teacherIds+ '&teacherName=' +teacherNames,
        				
        		method: 'get',
        		success: function (response){
					if(response.success){
						Ext.MessageBox.show({
							title:'Save Changes?',
    						msg: '作业设置成功. 你可以在"作业设置记录"面板查看上传记录 <br /> 你确认现在查看吗?',
						    buttons: Ext.MessageBox.YESNOCANCEL,
						    fn: function(btn){
								if(btn == 'yes'){
									var viewPanel = Ext.ComponentQuery.query('#viewPanel')[0];
						 		 	viewPanel.setActiveTab(1);
						 		  	homeworkStore.reload();
						 	   	}
						    },
						    animateTarget: 'mb4',
						    icon: Ext.MessageBox.QUESTION
						});
						
					}else {
						Ext.MessageBox.alert('Status', '添加失败!');
					}
				}
        	});
			
	    	
	    }
	    
	    function doCancel() {
	    	hwForm.getForm().reset();
	    	configForm.getForm().reset();
	    }
	
	    Ext.onReady(function() {
	        Ext.QuickTips.init();
	        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	        
		    var s_academicYear = Ext.create('Ext.form.field.ComboBox', {
		        typeAhead: true,
		        transform: 's_academicYear',
		        fieldLabel: '学年',
		        labelWidth:  100,
		        afterLabelTextTpl: required,
		        forceSelection: true
		    });

		    var s_semester = Ext.create('Ext.form.field.ComboBox', {
		        typeAhead: true,
		        transform: 's_semester',
		        fieldLabel: '学期',
		        afterLabelTextTpl: required,
		        labelWidth:  100,
		        forceSelection: true
		    });
		    
		    configForm = Ext.widget({
		    	xtype: 'form',
	            region: 'north',
	            stateId: 'navigation-panel',
	            iconCls: 'icon-commTab',
	            id: 'west-panel', // see Ext.getCmp() below
	            title: '[教师  -- 班级 -- 课程 ] -- 设置',
	            split: true,
	            height: 300,
	            minHeight: 250,
	            maxHeight: 400,
	            collapsible: true,
	            animCollapse: true,
	            //margins: '0 5 0 5',
	            bodyStyle: {
	                //background: '#ffc',
	                padding: '0 5 0 5'
	            },
	            defaults: {
		            anchor: '100%'
		        },
		        
		        items: [{
		        	layout: 'column',
		            items: [{
		            	columnWidth: 1,
		            	xtype:'fieldset',
		            	title: '教学周期',
		                collapsible: true,
		                //checkboxToggle:true,
		                layout: 'column',
		                autoHeight: true,
		                bodyStyle: {
		                    padding: '0 5 0 5'
		                },
		            	items: [{
		            		columnWidth: .5,
		            		padding: '0 5 5 0',
		            		name: 'academicYear',
		            		items: [s_academicYear]
		            	},{
		            		columnWidth: .5,
		            		padding: '0 0 5 5',
		            		name: 'semester',
		            		items: [s_semester]
		            	}]
		            }, {
		            	columnWidth: 1,
		            	xtype:'fieldset',
		            	title: '课程名称',
		            	afterLabelTextTpl: required,
		                collapsible: true,
		                //checkboxToggle:true,
		                layout: 'column',
		                autoHeight: true,
		                bodyStyle: {
		                    padding: '0 5 0 5'
		                },
		                defaults: {
			                anchor: '100%'
			            },
		            	items: [{
		            		columnWidth: 1,
		            		padding: '0 5 5 0',
		            		items: [courseCombobox]
		            	}]
		            }, {
		            	columnWidth: 1,
		            	xtype:'fieldset',
		            	title: '班级名称',
		            	afterLabelTextTpl: required,
		                collapsible: true,
		                layout: 'column',
		                autoHeight: true,
		                bodyStyle: {
		                    padding: '0 5 0 5'
		                },
		                defaults: {
			                anchor: '100%'
			            },
		            	items: [{
		            		columnWidth: 1,
		            		padding: '0 5 5 0',
		            		defaults: {
				                anchor: '100%'
				            },
		            		items: [classCombobox]
		            	}]
		            }, {
		            	columnWidth: 1,
		            	xtype: 'fieldset',
		            	title: '授课教师',
		            	afterLabelTextTpl: required,
		                collapsible: true,
		                layout: 'column',
		                autoHeight: true,
		                bodyStyle: {
		                    padding: '0 5 0 5'
		                },
		                defaults: {
			                anchor: '100%'
			            },
		            	items: [{
		            		columnWidth: 1,
		            		padding: '0 5 5 0',
		            		items: [teacherCombobox]
		            	}]
		            }]
		        }]
	        });	        
	        
	        //var f_hwname = Ext.ComponentQuery.query('#f_hwname')[0];
	
	        var viewport = Ext.create('Ext.Viewport', {
	            id: 'border-example',
	            layout: 'border',
	            title: '',
	            
	            items: [configForm,
	            
	           	Ext.create('Ext.tab.Panel', {
					itemId: 'viewPanel',		        	   
	                region: 'center', 
	                deferredRender: false,
	                activeTab: 0, 
	                autoHeight: true,
	                items: [{
	                    title: '作业信息',
	                    iconCls: 'icon-processform',
	                    closable: false,
	                    autoScroll: true,
	                    frame: true,
	                    items: [hwForm],
	                    buttonAlign: 'center',
	        	        buttons: [{
	        	            text: 'Save',
	        	            iconCls: 'icon-send',
	        	            handler: doSave
	        	        },{
	        	            text: 'Cancel',
	        	            iconCls: 'icon-cancel',
	        	            handler: doCancel
	        	        }]
	                }, {
	                    iconCls: 'icon-refresh',
	                    title: '作业设置记录 -- 按作业设置时间先后降序展示',
	                    autoScroll: true,
	                    frame: true,
	                    autoHeight: true,
	                    items: [homeworkGrid]
	                }]
	            })]
	        });
	       
	    });
    </script>

</head>
<body>
	<div id="div_academicYear" class="code x-hide-display">
		<span>学年:</span> 
		<select id="s_academicYear" name="academicYear" alt="学年">
			<option value="-1">全部学年</option>
			<c:forEach var="item" varStatus="index" items="${XN }">
				<option value='<c:out value="${item.code}"></c:out>'>
					<c:out value="${item.label}"></c:out></option>
			</c:forEach>
		</select>
	</div>
	
	<div id="div_semester"  >
		<span>学期:</span> 
		<select id="s_semester" name="semester">
			<option value="-1">全部学期</option>
			<c:forEach var="item" varStatus="index" items="${XQ }">
				<option value='<c:out value="${item.code}"></c:out>'>
				<c:out value="${item.label}"></c:out></option>
			</c:forEach>
		</select> 
	</div>
	
	<!-- 课程列表 -->
	<div id="div_courseName" class="code x-hide-display">
		<select>
			<option>1</option>
		</select>	
	</div>
	
	<!-- 班级列表 -->
	<div id="div_className" class="code x-hide-display">
		<select>
			<option>1</option>
		</select>		
	</div>

    <div id="center2" class="x-hide-display">
			<!-- <select class="easyui-combogrid" style="width: 100%" data-options="
				multiple: true,
				idField: 'baseId',
				textField: 'courseName',
				url: 'readCourse.do?start=0&limit=9999999&page=0',
				method: 'get',
				columns: [[
					{field:'ck',checkbox:true},
					{field:'baseId',title:'ID',width:80},
					{field:'courseName',title:'课程名称',width:120},
					{field:'courseCode',title:'课程编号',width:80,align:'right'}
				]],
				fitColumns: true
			"> -->
    </div>
    
    <div id="center1" class="x-hide-display">
    
    </div>
</body>
</html>