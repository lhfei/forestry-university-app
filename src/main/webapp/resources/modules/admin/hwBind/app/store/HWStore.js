Ext.define('course.store.HWStore', {
    extend: 'Ext.data.Store',
    alias: 'widget.hwStore',
    model: 'course.model.HWModel',
    autoLoad: true,
    pageSize: 10,
    proxy: {
        type: 'ajax',
        api: {
        	read: '../teacher/homeworkRead.do',
            update: 'data/updateUsers.json'
        },
        reader: {
            type: 'json',
            root: 'rows',
            successProperty: 'result'
        },
        listeners: {
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'Remote Exception',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});