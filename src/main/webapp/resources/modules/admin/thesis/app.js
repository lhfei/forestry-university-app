Ext.application({
    name: 'thesis',

    appFolder: '../resources/modules/admin/thesis/app',
    // All the paths for custom classes
    paths: {
        'Ext.ux': '../resources/ext4/ux/'
    },

    // Define all the controllers that should initialize at boot up of your application
    controllers: [
        'ThesisController'
    ],
    
    autoCreateViewport: true
});
