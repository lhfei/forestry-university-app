Ext.define('thesis.store.ThesisStore', {
    extend: 'Ext.data.Store',
    alias: 'widget.thesisStore',
    model: 'thesis.model.ThesisModel',
    autoLoad: true,
    pageSize: 10,
    proxy: {
        type: 'ajax',
        api: {
        	read: 'thesisRead.do',
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