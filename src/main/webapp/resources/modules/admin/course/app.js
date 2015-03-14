/**
 * New node file
 */
Ext.application({
    name: 'course',

    appFolder: '../resources/modules/admin/course/app',
    // All the paths for custom classes
    paths: {
        'Ext.ux': '../resources/ext4/ux/'
    },

    // Define all the controllers that should initialize at boot up of your application
    controllers: [
        'CourseController'
    ],
    
    autoCreateViewport: true
});
