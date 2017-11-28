'use strict';

// Creating angular Application with module name "GoogleOAuth"
var app = angular.module('GoogleOAuth2',[]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);