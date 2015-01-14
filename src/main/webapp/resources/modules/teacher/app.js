Ext.application({
    name: 'Ifeng',

    appFolder: '../resources/modules/teacher/app',
    // All the paths for custom classes
    paths: {
        'Ext.ux': '../resources/ext4/ux/'
    },

    // Define all the controllers that should initialize at boot up of your application
    controllers: [
        /*'Articles',
        'Feeds'*/
        'MenuController'
    ],
    
    autoCreateViewport: true
});
