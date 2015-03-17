/**
 * New node file
 */
Ext.application({
    name: 'courseCofig',
    appFolder: '../resources/modules/admin/courseCofig/app',
    paths: {
        'Ext.ux': '../resources/ext4/ux/'
    },
    controllers: [
        'CourseCofigController'
    ],
    
    autoCreateViewport: true
});
