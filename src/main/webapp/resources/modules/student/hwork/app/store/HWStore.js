Ext.define('hwork.store.HWStore', {
    extend: 'Ext.data.Store',
    alias: 'widget.hwStore',
    model: 'hwork.model.HWModel',
    autoLoad: true,
    pageSize: 10,
    proxy: {
        type: 'ajax',
        api: {
        	read: 'read.do',
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