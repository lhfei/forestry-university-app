/**
 * New node file
 */
Ext.define('course.store.CourseStore', {
    extend: 'Ext.data.Store',
    model: 'course.model.CourseModel',
    autoLoad: true,
    pageSize: 10,
    autoLoad: {start: 0, limit: 10},
    
    proxy: {
        type: 'ajax',
        api: {
        	read : '../admin/courseRead.do',
            create : '../admin/courseCreate.do',
            update: '../admin/courseUpdate.do',
            destroy: '../admin/courseDelete.do'
        },
        reader: {
            type: 'json',
            root: 'rows',
            successProperty: 'result'
        },
        writer: {
            type: 'json',
            writeAllFields: true,
            encode: false,
            root: ''
        },
        listeners: {
        	success: function(proxy, response, operation) {
        		Ext.MessageBox.alert({
                    title: 'REMOTE EXCEPTION',
                    msg: response,
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
        	}/*,
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'REMOTE EXCEPTION',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }*/
        }
    }
});