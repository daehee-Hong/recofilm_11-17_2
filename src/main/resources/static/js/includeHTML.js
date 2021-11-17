var app = angular.module('ngTemplate', []);

app.controller('MainCtrl', function($scope) {
    $scope.templates = [
        { name: 'template1.html', url: 'template1.html'},
        { name: 'template2.html', url: 'template2.html'},
        { name: 'template3.html', url: 'template3.html'},
        { name: 'template4.html', url: 'template4.html'}
    ];
});