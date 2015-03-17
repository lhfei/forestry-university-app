/**
 * New node file
 */
Ext.define('courseCofig.controller.CourseCofigController', {
    extend: 'Ext.app.Controller',

    views: ['ConfigFormPanel', 'HWGrid'],

    refs: [{
            ref: 'configForm',
            selector: 'configForm'
        }
    ],

    init: function() {
    	this.control({
    		
    	})
    }


});
