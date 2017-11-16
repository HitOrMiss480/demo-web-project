'use strict';

// Creating angular Application with module name "Google OAuth2"
var app = angular.module('GoogleOAuth2',[]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);