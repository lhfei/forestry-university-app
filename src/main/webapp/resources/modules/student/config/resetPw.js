Ext.require([
    'Ext.form.*',
    'Ext.Img',
    'Ext.tip.QuickTipManager'
]);

Ext.onReady(function() {
    Ext.tip.QuickTipManager.init();

    var formPanel = Ext.widget('form', {
        renderTo: 'centered',
        frame: true,
        width: 350,
        bodyPadding: 10,
        bodyBorder: true,
        title: '重置密码',

        defaults: {
            anchor: '100%'
        },
        fieldDefaults: {
            labelWidth: 110,
            labelAlign: 'left',
            msgTarget: 'none',
            invalidCls: '' //unset the invalidCls so individual fields do not get styled as invalid
        },

        /*
         * Listen for validity change on the entire form and update the combined error icon
         */
        listeners: {
            fieldvaliditychange: function() {
                this.updateErrorState();
            },
            fielderrorchange: function() {
                this.updateErrorState();
            }
        },

        updateErrorState: function() {
            var me = this,
                errorCmp, fields, errors;

            if (me.hasBeenDirty || me.getForm().isDirty()) { //prevents showing global error when form first loads
                errorCmp = me.down('#formErrorState');
                fields = me.getForm().getFields();
                errors = [];
                fields.each(function(field) {
                    Ext.Array.forEach(field.getErrors(), function(error) {
                        errors.push({name: field.getFieldLabel(), error: error});
                    });
                });
                errorCmp.setErrors(errors);
                me.hasBeenDirty = true;
            }
        },

        items: [{
            xtype: 'textfield',
            name: 'id',
            fieldLabel: '用户ID',
            allowBlank: false,
            readOnly: true,
            value: Ext.get('_userId').getValue()
        },/* {
            xtype: 'textfield',
            name: 'email',
            fieldLabel: '邮箱',
            vtype: 'email',
            emptyText: '请输入邮箱',
            allowBlank: false
        },*/ {
            xtype: 'textfield',
            name: 'password1',
            fieldLabel: '新密码',
            inputType: 'password',
            style: 'margin-top:15px',
            emptyText: '请输入新密码',
            allowBlank: false,
            minLength: 6
        }, {
            xtype: 'textfield',
            name: 'password2',
            fieldLabel: '确认密码',
            inputType: 'password',
            emptyText: '请再次输入密码',
            allowBlank: false,
            /**
             * Custom validator implementation - checks that the value matches what was entered into
             * the password1 field.
             */
            validator: function(value) {
                var password1 = this.previousSibling('[name=password1]');
                return (value === password1.getValue()) ? true : '两次输入的密码不匹配.'
            }
        }],

        dockedItems: [{
            cls: Ext.baseCSSPrefix + 'dd-drop-ok',
            xtype: 'container',
            dock: 'bottom',
            layout: {
                type: 'hbox',
                align: 'middle'
            },
            padding: '10 10 5',

            items: [{
                xtype: 'component',
                id: 'formErrorState',
                invalidCls: Ext.baseCSSPrefix + 'form-invalid-icon',
                validCls: Ext.baseCSSPrefix + 'dd-drop-icon',
                baseCls: 'form-error-state',
                flex: 1,
                validText: 'Form is valid',
                invalidText: 'Form has errors',
                tipTpl: Ext.create('Ext.XTemplate', '<ul class="' + Ext.plainListCls + '"><tpl for="."><li><span class="field-name">{name}</span>: <span class="error">{error}</span></li></tpl></ul>'),

                getTip: function() {
                    var tip = this.tip;
                    if (!tip) {
                        tip = this.tip = Ext.widget('tooltip', {
                            target: this.el,
                            title: '错误提示:',
                            minWidth: 200,
                            autoHide: false,
                            anchor: 'top',
                            mouseOffset: [-11, -2],
                            closable: true,
                            constrainPosition: false,
                            cls: 'errors-tip'
                        });
                        tip.show();
                    }
                    return tip;
                },

                setErrors: function(errors) {
                    var me = this,
                        tip = me.getTip();

                    errors = Ext.Array.from(errors);

                    // Update CSS class and tooltip content
                    if (errors.length) {
                        me.addCls(me.invalidCls);
                        me.removeCls(me.validCls);
                        me.update(me.invalidText);
                        tip.setDisabled(false);
                        tip.update(me.tipTpl.apply(errors));
                    } else {
                        me.addCls(me.validCls);
                        me.removeCls(me.invalidCls);
                        me.update(me.validText);
                        tip.setDisabled(true);
                        tip.hide();
                    }
                }
            }, {
                xtype: 'button',
                formBind: true,
                disabled: true,
                text: '提交',
                iconCls: 'icon-process',
                width: 80,
                handler: function() {
                    var form = this.up('form').getForm();

                    if (form.isValid()) {
                        var items = form.getValues();
                        Ext.Ajax.request({
            				url: '../system/restPassword.do?id=' +items['id']+ '&password=' +items['password1'],
            				waitMsg: 'Loading ...',
            				method: 'get',
            				success: function (response, opts){
            					var result = Ext.decode(response.responseText); 
            					if(result.success){
            						Ext.MessageBox.show({
            							title: 'System Message',
            							msg: result.message
            						});
            						
            						form.reset();
            					}
            				},
            				failure: function(response, opts){
            					Ext.MessageBox.show({
            						title: 'System Message',
            						msg: 'The server engine is busy now, please try a later.'
            					});
            				}
            			});                        
                    }
                }
            }]
        }]
    });

});
