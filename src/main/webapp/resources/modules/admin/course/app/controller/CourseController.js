/**
 * New node file
 */
Ext.define('course.controller.CourseController', {
    extend: 'Ext.app.Controller',

    stores: ['CourseStore'],

    models: ['CourseModel'],

    views: ['CourseForm', 'CourseGrid'],

    refs: [{
            ref: 'contactsPanel',
            selector: 'panel'
        },{
            ref: 'courseGrid',
            selector: 'courseGrid'
        }
    ],

    init: function() {
        this.control({
            'courseGrid dataview': {
                itemdblclick: this.editUser
            },
            'courseGrid button[action=add]': {
            	click: this.editUser
            },
            'courseGrid button[action=delete]': {
                click: this.deleteUser
            },
            'courseForm button[action=save]': {
                click: this.updateUser
            }
        });
    },

    editUser: function(grid, record) {
        var edit = Ext.create('course.view.CourseForm').show();
        
        if(record){
        	edit.down('form').loadRecord(record);
        }
    },
    
    updateUser: function(button) {
        var win    = button.up('window'),
            form   = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();
        
		if (values.baseId > 0){
			record.set(values);
		} else{
			record = Ext.create('course.model.CourseModel');
			record.set(values);
			this.getCourseStoreStore().add(record);
		}
        
		win.close();
        this.getCourseStoreStore().sync();
    },
    
    deleteUser: function(button) {
    	
    	var grid = this.getCourseGrid(),
    	record = grid.getSelectionModel().getSelection(), 
        store = this.getCourseStoreStore();

	    store.remove(record);
	    this.getCourseStoreStore().sync();
    }
});
