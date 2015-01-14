/**
 * 
 */
var currentItem;

Ext.define('Ifeng.view.MainPanel', {
	extend: 'Ext.tab.Panel',
	alias: 'widget.mainPanel',
	
	requires: ['Ext.tab.*','Ext.ux.TabCloseMenu'],
	
	resizeTabs: true,
    enableTabScroll: true,
    width: 600,
    height: 250,
    defaults: {
        autoScroll: true,
        bodyPadding: 2
    },
    items: [/*{
        title: 'Tab 1',
        iconCls: 'tabs',
        html: 'Tab Body<br/><br/>' ,
        closable: true
    }*/],
    plugins: Ext.create('Ext.ux.TabCloseMenu', {
        extraItemsTail: [
            '-',
            {
                text: 'Closable',
                checked: true,
                hideOnClick: true,
                handler: function (item) {
                    currentItem.tab.setClosable(item.checked);
                }
            },
            '-',
            {
                text: 'Enabled',
                checked: true,
                hideOnClick: true,
                handler: function(item) {
                    currentItem.tab.setDisabled(!item.checked);
                }
            }
        ],
        listeners: {
            beforemenu: function (menu, item) {
                var enabled = menu.child('[text="Enabled"]'); 
                menu.child('[text="Closable"]').setChecked(item.closable);
                if (item.tab.active) {
                    enabled.disable();
                } else {
                    enabled.enable();
                    enabled.setChecked(!item.tab.isDisabled());
                }

                currentItem = item;
            }
        }
    })
});