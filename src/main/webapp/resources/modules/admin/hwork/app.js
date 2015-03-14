Ext.application({
    name: 'hwork',

    appFolder: '../resources/modules/admin/hwork/app',
    // All the paths for custom classes
    paths: {
        'Ext.ux': '../resources/ext4/ux/'
    },

    // Define all the controllers that should initialize at boot up of your application
    controllers: [
        'HWController'
    ],
    
    autoCreateViewport: true
});
